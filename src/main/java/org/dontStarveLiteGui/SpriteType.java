package org.dontStarveLiteGui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

public enum SpriteType {
	STARTERGRASS(
			'S',
			(byte)0,
			SpriteType.TILE_SHEET
			),
	GRASS1(
			'g',
			(byte)0,
			SpriteType.TILE_SHEET
			),
	GRASS2(
			'g',
			(byte)1,
			SpriteType.TILE_SHEET
	),
	GRASS3(
			'g',
			(byte)2,
			SpriteType.TILE_SHEET
	),
	MARSHLAND(
			'm',
			(byte)3,
			SpriteType.TILE_SHEET
			),
	DRYGRASS(
			'D',
			(byte)6,
			SpriteType.TILE_SHEET
			),
	ROCKY_PLAINS(
			'r',
			(byte)8,
			SpriteType.TILE_SHEET
			),
	MOUNTAIN(
			'M',
			(byte)4,
			SpriteType.TILE_SHEET
			),
	COAST(
			'c',
			(byte)7,
			SpriteType.TILE_SHEET
			),
	WATER(
			'w',
			(byte)5,
			SpriteType.TILE_SHEET
			),
	DARK(
			'd',
			(byte)9,
			SpriteType.TILE_SHEET
			),
	BRIGHT_DARK(
			'b',
			(byte)10,
			SpriteType.TILE_SHEET
			),
	PLAYER(
			'P',
			(byte)0,
			SpriteType.PLAYER_SHEET
			),
	TREE(
			'T',
			(byte)0,
			SpriteType.STRUCTURES_SHEET
			),
	CHOPPED_TREE(
			't',
			(byte)8,
			SpriteType.STRUCTURES_SHEET
			),
	PINE(
			'P',
			(byte)1,
			SpriteType.STRUCTURES_SHEET
			),
	CHOPPED_PINE(
			'p',
			(byte)9,
			SpriteType.STRUCTURES_SHEET
			),
	GRASS_SPOT(
			'G',
			(byte)2,
			SpriteType.STRUCTURES_SHEET
			),
	USED_GRASS_SPOT(
			'g',
			(byte)10,
			SpriteType.STRUCTURES_SHEET
			),
	ROCK(
			'R',
			(byte)3,
			SpriteType.STRUCTURES_SHEET
			),
	MINED_ROCK(
			'r',
			(byte)11,
			SpriteType.STRUCTURES_SHEET
			),
	FLOWER_SPOT(
			'F',
			(byte)4,
			SpriteType.STRUCTURES_SHEET
			),
	PICKED_FLOWER_SPOT(
			'f',
			(byte)12,
			SpriteType.STRUCTURES_SHEET
			),
	BUSH(
			'B',
			(byte)5,
			SpriteType.STRUCTURES_SHEET
			),
	PICKED_BUSH(
			'b',
			(byte)13,
			SpriteType.STRUCTURES_SHEET
			),
	CARROT_SPOT(
			'C',
			(byte)6,
			SpriteType.STRUCTURES_SHEET
			),
	PICKED_CARROT_SPOT(
			'c',
			(byte)14,
			SpriteType.STRUCTURES_SHEET
			),
	TWIGS_SPOT(
			'w',
			(byte)7,
			SpriteType.STRUCTURES_SHEET
			),
	PICKED_TWIGS_SPOT(
			'W',
			(byte)15,
			SpriteType.STRUCTURES_SHEET
			),
	CAMPFIRE_SPOT(
			'L',
			(byte)16,
			SpriteType.STRUCTURES_SHEET
			),
	USED_CAMPFIRE_SPOT(
			'l',
			(byte)17,
			SpriteType.STRUCTURES_SHEET
			),
	FLINT_SPOT(
			'I',
			(byte)18,
			SpriteType.STRUCTURES_SHEET
			),
	USED_FLINT_SPOT(
			'i',
			(byte)19,
			SpriteType.STRUCTURES_SHEET
	),
	SANITY_100(
			's',
			16,
			(byte)0,
			SpriteType.INDICATOR_SHEET
			),
	SANITY_80(
			's',
			16,
			(byte)1,
			SpriteType.INDICATOR_SHEET
			),
	SANITY_60(
			's',
			16,
			(byte)2,
			SpriteType.INDICATOR_SHEET
			),
	SANITY_40(
			's',
			16,
			(byte)3,
			SpriteType.INDICATOR_SHEET
			),
	SANITY_20(
			's',
			16,
			(byte)4,
			SpriteType.INDICATOR_SHEET
			),
	HEALTH_100(
			'h',
			16,
			(byte)5,
			SpriteType.INDICATOR_SHEET
			),
	HEALTH_80(
			'h',
			16,
			(byte)6,
			SpriteType.INDICATOR_SHEET
			),
	HEALTH_60(
			'h',
			16,
			(byte)7,
			SpriteType.INDICATOR_SHEET
			),
	HEALTH_40(
			'h',
			16,
			(byte)8,
			SpriteType.INDICATOR_SHEET
			),
	HEALTH_20(
			'h',
			16,
			(byte)9,
			SpriteType.INDICATOR_SHEET
			),
	HUNGER_100(
			'H',
			16,
			(byte)10,
			SpriteType.INDICATOR_SHEET
			),
	HUNGER_80(
			'H',
			16,
			(byte)11,
			SpriteType.INDICATOR_SHEET
			),
	HUNGER_60(
			'H',
			16,
			(byte)12,
			SpriteType.INDICATOR_SHEET
			),
	HUNGER_40(
			'H',
			16,
			(byte)13,
			SpriteType.INDICATOR_SHEET
			),
	HUNGER_20(
			'H',
			16,
			(byte)14,
			SpriteType.INDICATOR_SHEET
			)
	;

	
	public static final String TILE_SHEET = 
							"/img/tileTextures.png";
	public static final String STRUCTURES_SHEET =
							"/img/Structures.png";
	public static final String PLAYER_SHEET =
							"/img/playerIdle.png";
	public static final String INDICATOR_SHEET = 
							"/img/Indicators.png";
	public static final String ITEM_SHEET =
							"";
	public static final int SCALE_FACTOR = 1;

	private char type;
	public  int spriteSize = 64;
	private byte spriteNum;
	private Image sheet;
	private Image sprite;
	private double sheetWidth;
	private double sheetHeight;
	
	SpriteType(char type, byte spriteNum, String sheetPath){
		this.type = type;
		this.spriteNum = spriteNum;
		try {
			this.sheet =  new Image(this.getClass().getResource(sheetPath).openStream());
			setSheetSize();
			setSprite();
        } catch (Exception e) {
        	this.sheet = null;
       }
		
	}
	
	SpriteType(char type, int size, byte spriteNum, String sheetPath){
		this.type = type;
		this.spriteSize = size;
		this.spriteNum = spriteNum;
		try {
			this.sheet =  new Image(this.getClass().getResource(sheetPath).openStream());
			setSheetSize();
			setSprite();
        } catch (Exception e) {
        	this.sheet = null;
       }
		
	}
	
	public void setSheetSize() {
		this.sheetWidth = this.sheet.getWidth();
		this.sheetHeight = this.sheet.getHeight();
	}
	
	public void setSprite() {
		byte counter = 0;
		for (int row = 0; row < this.sheetHeight; row += spriteSize) {
			for (int col = 0; col < this.sheetWidth; col += spriteSize) {
				if (counter == this.spriteNum) {
					this.sprite = new WritableImage(sheet.getPixelReader(), col, row, spriteSize, spriteSize);
					return;
				}
				counter ++;
			}
		}
	}
	public static Image createIndicatorPicture(char indicator, int amount) {
		switch (indicator) {
		case 's':
			if (80 <= amount)
				return SpriteType.SANITY_100.getSprite();
			else if (60 <= amount)
				return SpriteType.SANITY_80.getSprite();
			else if (40 <= amount)
				return SpriteType.SANITY_60.getSprite();
			else if (20 <= amount)
				return SpriteType.SANITY_40.getSprite();
			else
				return SpriteType.SANITY_20.getSprite();
		case 'h':
			if (80 <= amount)
				return SpriteType.HEALTH_100.getSprite();
			else if (60 <= amount)
				return SpriteType.HEALTH_80.getSprite();
			else if (40 <= amount)
				return SpriteType.HEALTH_60.getSprite();
			else if (20 <= amount)
				return SpriteType.HEALTH_40.getSprite();
			else
				return SpriteType.HEALTH_20.getSprite();
		case 'H':
			if (80 <= amount)
				return SpriteType.HUNGER_100.getSprite();
			else if (60 <= amount)
				return SpriteType.HUNGER_80.getSprite();
			else if (40 <= amount)
				return SpriteType.HUNGER_60.getSprite();
			else if (20 <= amount)
				return SpriteType.HUNGER_40.getSprite();
			else
				return SpriteType.HUNGER_20.getSprite();
		default:
			return new Image("NOT FOUND");
		}
	}
	public static ImageView createTilePicture(char tile, int pseudoRand) {
		switch (tile) {
		case 'S':
			return new ImageView(SpriteType.GRASS1.getSprite());
		case 'g':
			if (pseudoRand == 0)
				return new ImageView(SpriteType.GRASS1.getSprite());
			if (pseudoRand == 1)
				return new ImageView(SpriteType.GRASS2.getSprite());
			if (pseudoRand == 2)
				return new ImageView(SpriteType.GRASS3.getSprite());
		case 'm':
			return new ImageView(SpriteType.MARSHLAND.getSprite());
		case 'D':
			return new ImageView(SpriteType.DRYGRASS.getSprite());
		case 'r':
			return new ImageView(SpriteType.ROCKY_PLAINS.getSprite());
		case 'M':
			return new ImageView(SpriteType.MOUNTAIN.getSprite());
		case 'c':
			return new ImageView(SpriteType.COAST.getSprite());
		case 'w':
			return new ImageView(SpriteType.WATER.getSprite());
		default:
			return new ImageView(SpriteType.GRASS1.getSprite());
		}
	}
	
	public static ImageView createStructurePicture(char structure) {
		switch (structure) {
		case 'P':
			return new ImageView(SpriteType.PINE.getSprite());
		case 'p':
			return new ImageView(SpriteType.CHOPPED_PINE.getSprite());
		case 'T':
			return new ImageView(SpriteType.TREE.getSprite());
		case 't':
			return new ImageView(SpriteType.CHOPPED_TREE.getSprite());
		case 'R':
			return new ImageView(SpriteType.ROCK.getSprite());
		case 'r':
			return new ImageView(SpriteType.MINED_ROCK.getSprite());
		case 'B':
			return new ImageView(SpriteType.BUSH.getSprite());
		case 'b':
			return new ImageView(SpriteType.PICKED_BUSH.getSprite());
		case 'F':
			return new ImageView(SpriteType.FLOWER_SPOT.getSprite());
		case 'f':
			return new ImageView(SpriteType.PICKED_FLOWER_SPOT.getSprite());
		case 'G':
			return new ImageView(SpriteType.GRASS_SPOT.getSprite());
		case 'g':
			return new ImageView(SpriteType.USED_GRASS_SPOT.getSprite());
		case 'C':
			return new ImageView(SpriteType.CARROT_SPOT.getSprite());
		case 'c':
			return new ImageView(SpriteType.PICKED_CARROT_SPOT.getSprite());
		case 'W':
			return new ImageView(SpriteType.TWIGS_SPOT.getSprite());
		case 'w':
			return new ImageView(SpriteType.PICKED_TWIGS_SPOT.getSprite());
		case 'L':
			return new ImageView(SpriteType.CAMPFIRE_SPOT.getSprite());
		case 'l':
			return new ImageView(SpriteType.USED_CAMPFIRE_SPOT.getSprite());
		case 'I':
			return new ImageView(SpriteType.FLINT_SPOT.getSprite());
		case 'i':
			return new ImageView(SpriteType.USED_FLINT_SPOT.getSprite());
		}
		return new ImageView();
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	public char getType(){
		return type;
	}
	
	public byte getSpriteNum(){
		return spriteNum;
	}
}