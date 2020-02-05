package org.dontStarveLite;

import java.util.Random;

public class World {

	public static final byte PAD = 1;
	private final int ROW = 150;// Only even values. 
	private final int COL = 150;
	private final int MAX_RIDGE = 4;
	private final int WATER_PADDING = 15;
	private Random rand = new Random();

	private Tile[][] worldMap;

	public World() {
		this.worldMap = new Tile[ROW][COL];
		generateWorld();
		placeStructures();
	}

	public Tile[][] getWorldMap() {
		return this.worldMap;
	}

	public void generateWorld() {
		generateEmptyWorld();
		for (byte i = 0; i < 4; i++) {
			generatePatchOnWorld(createPatch(15, 25, (byte) 5), TileType.MARSHLAND);
			generatePatchOnWorld(createPatch(10, 30, (byte) 4), TileType.DRYGRASS);
			generatePatchOnWorld(createPatch(4, 10, (byte) 2), TileType.MOUNTAIN);
			generatePatchOnWorld(createPatch(5, 10, (byte) 2), TileType.ROCKY_PLAINS);
		}
	}

	public void switchDayTime() {
		for (int row = 0; row < this.worldMap.length; row++) {
			for (int col = 0; col < this.worldMap.length; col++) {
				this.worldMap[row][col].setDark(!this.worldMap[row][col].isDark());
			}
		}
	}

	public void generateEmptyWorld() {
		int nextRowMax = ROW;
		for (int row = 0; row < this.worldMap.length; row++) {
			nextRowMax -= 1;
			for (int col = 0; col < nextRowMax; col++) {
				if (row <= (WATER_PADDING - 1) || row >= (ROW - WATER_PADDING) || col <= (WATER_PADDING - 1)
						|| col >= (COL - WATER_PADDING)) {
					this.worldMap[row][col] = new Tile(TileType.WATER);
				} else {
					this.worldMap[row][col] = new Tile();
					createRidgesFirstHalf(row, col, TileType.WATER);
				}
			}
		}
		int nextRowMin = -1;
		for (int row = ROW - 1; row >= 0; row--) {
			nextRowMin += 1;
			for (int col = COL - 1; col >= nextRowMin; col--) {
				if (row <= (WATER_PADDING - 1) || row >= (ROW - WATER_PADDING) || col <= (WATER_PADDING - 1)
						|| col >= (COL - WATER_PADDING)) {
					this.worldMap[row][col] = new Tile(TileType.WATER);
				} else {
					this.worldMap[row][col] = new Tile();
					createRidgesOtherHalf(row, col, TileType.WATER);
				}
			}
		}
		generateCoast();
	}

	public void generateCoast() {
		for (int row = WATER_PADDING; row < this.worldMap.length - WATER_PADDING; row++) {
			for (int col = WATER_PADDING; col < this.worldMap.length - WATER_PADDING; col++) {
				if (this.worldMap[row][col].getType() != TileType.WATER
						&& isNextToNeighbour(row, col, TileType.WATER)) {
					this.worldMap[row][col] = new Tile(TileType.COAST);
				}
			}
		}
	}

	public boolean isNextToNeighbour(int row, int col, TileType type) {
		return this.worldMap[row - 2][col].getType().getType() == type.getType()
				|| this.worldMap[row][col - 2].getType().getType() == type.getType()
				|| this.worldMap[row + 2][col].getType().getType() == type.getType()
				|| this.worldMap[row][col + 2].getType().getType() == type.getType() || isNeighbour(row, col, type);
	}

	public boolean isNeighbour(int row, int col, TileType type) {
		return this.worldMap[row - 1][col].getType().getType() == type.getType()
				|| this.worldMap[row][col - 1].getType().getType() == type.getType()
				|| this.worldMap[row + 1][col].getType().getType() == type.getType()
				|| this.worldMap[row][col + 1].getType().getType() == type.getType();
	}

	public int[] setStartingPosition() {
		int row = rand.nextInt(ROW - 2 * (WATER_PADDING + MAX_RIDGE)) + (WATER_PADDING + MAX_RIDGE);
		int col = rand.nextInt(COL - 2 * (WATER_PADDING + MAX_RIDGE)) + (WATER_PADDING + MAX_RIDGE);
		if (!worldMap[row][col].getType().isPassable()) {
			return setStartingPosition();
		}
		int[] startPos = { row, col };
		worldMap[row][col] = new Tile(TileType.STARTERGRASS);
		return startPos;
	}

	public boolean[][] createPatch(int minSize, int maxSize, byte ridgeSize) {
		int randRow = rand.nextInt((maxSize + ridgeSize) - minSize) + minSize + ridgeSize;
		int randCol = rand.nextInt((maxSize + ridgeSize) - minSize) + minSize + ridgeSize;
		boolean[][] patch = new boolean[randRow][randCol];
		for (int row = 0; row < patch.length; row++) {
			for (int col = 0; col < patch[row].length; col++) {
				if (row > ridgeSize && col > ridgeSize && row <= patch.length - 1 - ridgeSize
						&& col <= patch[row].length - 1 - ridgeSize) {
					patch[row][col] = true;
				}
			}
		}
		for (int row = 1; row < patch.length; row++) {
			for (int col = 1; col < patch[row].length; col++) {
				createPatchRidges(row, col, patch, ridgeSize);
			}
		}
		return patch;
	}

	public void createPatchRidges(int row, int col, boolean[][] patch, int maxRidge) {
		if ((col + 1 < patch[0].length) && (col >= maxRidge) && (patch[row][col - 1])) {
			int ridge = rand.nextInt(10);
			if (ridge > 4) {
				patch[row][col] = true;
			}
		}
		if ((row + 1 < patch.length) && (row >= maxRidge) && (patch[row - 1][col])) {
			int ridge = rand.nextInt(10);
			if (ridge > 4) {
				patch[row][col] = true;
			}
		}
		if ((col + 1 < patch[0].length) && (col <= maxRidge) && (patch[row][col + 1])) {
			int ridge = rand.nextInt(10);
			if (ridge > 4) {
				patch[row][col] = true;
			}
		}
		if ((row + 1 < patch.length) && (row <= maxRidge) && (patch[row + 1][col])) {
			int ridge = rand.nextInt(10);
			if (ridge > 4) {
				patch[row][col] = true;
			}
		}
	}

	public void generatePatchOnWorld(boolean[][] patch, TileType type) {
		int worldMapRow = rand.nextInt(((ROW - patch.length - WATER_PADDING) - WATER_PADDING) + 1) + WATER_PADDING;
		int initWorldMapCol = rand.nextInt(((COL - patch[0].length - WATER_PADDING) - WATER_PADDING) + 1)
				+ WATER_PADDING;
		int worldMapCol;
		for (boolean[] tiles : patch) {
			worldMapCol = initWorldMapCol;
			for (boolean tile : tiles) {
				if (tile
						&& worldMap[worldMapRow][worldMapCol].getType().getType() != TileType.WATER.getType()
						&& worldMap[worldMapRow][worldMapCol].getType().getType() != TileType.COAST.getType())
					worldMap[worldMapRow][worldMapCol] = new Tile(type);
				worldMapCol++;
			}
			worldMapRow++;
		}
	}

	public void createRidgesFirstHalf(int row, int col, TileType type) {
		if ((col <= (WATER_PADDING - 1) + MAX_RIDGE)
				&& (this.worldMap[row][col - 1].getType().getType() == type.getType())) {
			int ridge = rand.nextInt(10);
			if (ridge > 3) {
				this.worldMap[row][col] = new Tile(TileType.WATER);
			}
		}
		if ((row <= (WATER_PADDING - 1) + MAX_RIDGE)
				&& (this.worldMap[row - 1][col].getType().getType() == type.getType())) {
			int ridge = rand.nextInt(10);
			if (ridge > 3) {
				this.worldMap[row][col] = new Tile(TileType.WATER);
			}
		}
	}

	public void createRidgesOtherHalf(int row, int col, TileType type) {
		if ((col >= (COL - WATER_PADDING) - MAX_RIDGE)
				&& (this.worldMap[row][col + 1].getType().getType() == type.getType())) {
			int ridge = rand.nextInt(10);
			if (ridge > 3) {
				this.worldMap[row][col] = new Tile(TileType.WATER);
			}
		}
		if ((row >= (ROW - WATER_PADDING) - MAX_RIDGE)
				&& (this.worldMap[row + 1][col].getType().getType() == type.getType())) {
			int ridge = rand.nextInt(10);
			if (ridge > 3) {
				this.worldMap[row][col] = new Tile(TileType.WATER);
			}
		}
	}

	public boolean isPassable(int row, int col) {
		return worldMap[row][col].getType().isPassable();
	}

	public void placeStructures() {
		for (int row = 0; row < this.worldMap.length; row++) {
			for (int col = 0; col < this.worldMap[row].length; col++) {
				int structureChance = rand.nextInt((100 - 1) + 1) + 1;
				switch (this.getWorldMap()[row][col].getType().getType()) {
				case 'g':
					if (structureChance < 8) {
						this.worldMap[row][col].placeStructure(new Structure(StructureType.PINE));
					} else if (structureChance < 10) {
						this.worldMap[row][col].placeStructure(new Structure(StructureType.BUSH));
					} else if (structureChance < 13) {
						this.worldMap[row][col].placeStructure(new Structure(StructureType.FLOWER_SPOT));
					} else if (structureChance < 20) {
						this.worldMap[row][col].placeStructure(new Structure(StructureType.GRASS_SPOT));
					} else if (structureChance < 23) {
						this.worldMap[row][col].placeStructure(new Structure(StructureType.ROCK));
					} else if (structureChance < 30) {
						this.worldMap[row][col].placeStructure(new Structure(StructureType.TWIGS_SPOT));
					} else if (structureChance < 32) {
						this.worldMap[row][col].placeStructure(new Structure(StructureType.CARROT_SPOT));
					} else if (structureChance < 34) {
						this.worldMap[row][col].placeStructure(new Structure(StructureType.FLINT_SPOT));
					}
					break;
				case 'm':
					if (structureChance < 2) {
						this.worldMap[row][col].placeStructure(new Structure(StructureType.TREE));
					} else if (structureChance < 18) {
						this.worldMap[row][col].placeStructure(new Structure(StructureType.TWIGS_SPOT));
					} else if (structureChance == 19) {
						this.worldMap[row][col].placeStructure(new Structure(StructureType.FLINT_SPOT));
					}
					break;
				case 'D':
					if (structureChance < 8) {
						this.worldMap[row][col].placeStructure(new Structure(StructureType.GRASS_SPOT));
					} else if (structureChance < 10) {
						this.worldMap[row][col].placeStructure(new Structure(StructureType.CARROT_SPOT));
					}
					break;
				case 'r':
					if (structureChance < 50) {
						this.worldMap[row][col].placeStructure(new Structure(StructureType.ROCK));
					}
					if (50 <= structureChance &&  structureChance < 55) {
						this.worldMap[row][col].placeStructure(new Structure(StructureType.FLINT_SPOT));
					}
					break;
				}
			}
		}
	}

	public void printWorldMap() {
		for (Tile[] tiles : this.worldMap) {
			for (Tile tile : tiles) {
				if (tile == null) {
					System.out.print(String.format("%1$-" + PAD + "s", "NULL") + " ");
				} else {
					System.out.print(
							String.format("%1$-" + PAD + "s", tile.getType().getType()) + " ");
				}

			}
			System.out.println();
		}
	}

	public void printWorldMap(Player player) {
		for (int row = 0; row < this.worldMap.length; row++) {
			for (int col = 0; col < this.worldMap[row].length; col++) {
				if (this.worldMap[row][col] == null) {
					System.out.print(String.format("%1$-" + PAD + "s", "NULL") + " ");
				} else if (player.getPosition()[0] == row && player.getPosition()[1] == col) {
					System.out.print(String.format("%1$-" + PAD + "s", "P") + " ");
				} else if (player.getPosition()[0] + 4 > row && player.getPosition()[0] - 4 < row
						&& player.getPosition()[1] + 8 > col && player.getPosition()[1] - 8 < col) {
					System.out.print(
							String.format("%1$-" + PAD + "s", this.worldMap[row][col].getType().getType()) + " ");
				}

			}
			if (player.getPosition()[0] + 4 > row && player.getPosition()[0] - 4 < row)
				System.out.println();
		}
	}
}
