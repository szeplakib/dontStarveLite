package org.dontStarveLite;

public class PlacedCampfire {
	private int[] campCoord;
	private byte durability;

	public PlacedCampfire(int[] campCoord) {
		this.campCoord = campCoord;
		this.durability = 100;
	}

	public void decreaseDurability() {
		this.durability -= 1;
	}

	public byte getDurability() {
		return this.durability;
	}

	public void destroyCampfire(World world) {
		Structure campFire = world.getWorldMap()[this.campCoord[0]][this.campCoord[1]].getStructure();
		campFire.setCurrentType('l');
		campFire.setIsUsable(false);
		for (int row = this.campCoord[0] - 3; row < this.campCoord[0] + 4; row++) {
			for (int col = this.campCoord[1] - 3; col < this.campCoord[1] + 4; col++) {
				world.getWorldMap()[row][col].setLit(false);
			}
		}
	}
	
	public int[] getCampCoord() {
		return this.campCoord;
	}

}
