package org.dontStarveLite;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
	private final int NEED_VALUE;
	private boolean gameOver = false;
	private double health;
	private double sanity;
	private double hunger;
	private double actionPoints;
	private boolean isDayTime;
	private int[] position;
	private int daysSurvived = 0;
	private World world;
	private Inventory inventory;
	private ArrayList<PlacedCampfire> placedCampfires;

	public Player(World worldmap, int difficulty) {
		NEED_VALUE = difficulty;
		setHealth(NEED_VALUE);
		setSanity(NEED_VALUE);
		setHunger(NEED_VALUE);
		setActionPoints(NEED_VALUE);
		this.inventory = new Inventory();
		this.world = worldmap;
		this.position = worldmap.setStartingPosition();
		this.isDayTime = true;
		this.placedCampfires = new ArrayList<PlacedCampfire>();
	}

	public void moveUp() {
		if (world.isPassable(position[0] - 1, position[1])) {
			position[0] = position[0] - 1;
			useActionPoints(1);
		}
	}

	public void moveDown() {
		if (world.isPassable(position[0] + 1, position[1])) {
			position[0] = position[0] + 1;
			useActionPoints(1);
		}
	}

	public void moveLeft() {
		if (world.isPassable(position[0], position[1] - 1)) {
			position[1] = position[1] - 1;
			useActionPoints(1);
		}
	}

	public void moveRight() {
		if (world.isPassable(position[0], position[1] + 1)) {
			position[1] = position[1] + 1;
			useActionPoints(1);
		}
	}

	public void useActionPoints(double usedAc) {
		this.actionPoints -= usedAc;
		this.hunger -= usedAc * 0.4;
		boolean hasGarland = this.inventory.hasCraftItem(CraftType.GARLAND);
		if (hasGarland) {
			for (byte row = 0; row < inventory.getBag().length; row++) {
				for (byte col = 0; col < inventory.getBag()[row].length; col++) {
					if (inventory.getBag()[row][col] instanceof Garland) {
						Garland garland = ((Garland) inventory.getBag()[row][col]);
						garland.deteriorate();
						if (garland.getDurability() <= 0)
							inventory.breakCraftItem(garland);
					}
				}
			}
		}

		if (this.isDayTime) {
			if (hasGarland) {
				this.sanity += 0.05;
			}
			if (this.actionPoints <= 0) {
				this.actionPoints = 25;
				this.isDayTime = false;
				this.world.switchDayTime();
			}
		} else {
			if (hasGarland) {
				this.sanity -= 0.3;
			} else {
				this.sanity -= 0.4;
			}
			if (this.actionPoints <= 0) {
				this.actionPoints = NEED_VALUE;
				isDayTime = true;
				daysSurvived ++;
				this.world.switchDayTime();
			}
		}
		if (!this.placedCampfires.isEmpty()) {
			for (int i = 0; i < this.placedCampfires.size(); i++) {
				PlacedCampfire campFire = this.placedCampfires.get(i);
				campFire.decreaseDurability();
				if (campFire.getDurability() <= 0) {
					this.placedCampfires.remove(i);
					campFire.destroyCampfire(this.world);
				}
			}
		}
		if (world.getWorldMap()[position[0]][position[1]].isDark()
				&& !world.getWorldMap()[position[0]][position[1]].isLit()) {
			this.health -= 5;
			this.sanity -= 7;
		}
		if (this.hunger > NEED_VALUE) {
			this.hunger = NEED_VALUE;
		}
		if (this.health > NEED_VALUE) {
			this.health = NEED_VALUE;
		}
		if (this.sanity > NEED_VALUE) {
			this.sanity = NEED_VALUE;
		}
		if (this.hunger <= 0 || this.health <= 0 || this.sanity <= 0) {
			this.gameOver = true;
		}
	}

	public void placeCampfire() {
		Campfire campfire = new Campfire();
		if (!this.world.getWorldMap()[position[0]][position[1]].getStructure().isUseAble()
				&& this.inventory.hasCraftItem(CraftType.CAMPFIRE)) {
			this.world.getWorldMap()[position[0]][position[1]]
					.placeStructure(new Structure(StructureType.CAMPFIRE_SPOT));
			this.inventory.destroyCraftItem(campfire);
			int[] campCoords = { position[0], position[1] };
			this.placedCampfires.add(new PlacedCampfire(campCoords));
			for (int row = position[0] - 3; row < position[0] + 4; row++) {
				for (int col = position[1] - 3; col < position[1] + 4; col++) {
					this.world.getWorldMap()[row][col].setLit(true);
				}
			}
			this.useActionPoints(2);
		}
	}

	public void cook(StackType type) {
		if (inventory.isEnough(type, 1) && this.isAtCampFire()) {
			inventory.useStackItem(type, (byte) 1);
			switch (type) {
			case BERRY:
				inventory.pickUpItem(new StackItem(StackType.COOKED_BERRY));
				this.useActionPoints(1);
				break;
			case CARROT:
				inventory.pickUpItem(new StackItem(StackType.COOKED_CARROT));
				this.useActionPoints(1);
				break;
			}
		}
	}
	
	public boolean isAtCampFire() {
		return this.world.getWorldMap()[this.position[0]][this.position[1]].getStructure()
		.getCurrentType() == StructureType.CAMPFIRE_SPOT.getType();
	}
	
	public void eat(StackType type) {
		if (!type.isEdible())
			return;
		if (this.inventory.isEnough(type, 1)) {
			inventory.useStackItem(type, (byte) 1);
			this.health += type.getHealth();
			this.sanity += type.getSanity();
			this.hunger += type.getHunger();
			this.useActionPoints(1);
		}
	}
	//TODO
	public StackItem useStructure(Structure structure) {
		if (structure.isUseAble() && (structure.getCurrentType() == 'T' || structure.getCurrentType() == 'P')
				&& this.inventory.hasCraftItem(CraftType.AXE)) {
			CraftItem craftItem = inventory.getCraftItem(CraftType.AXE);
			craftItem.deteriorate();
			if (craftItem.getDurability() <= 0)
				inventory.breakCraftItem(craftItem);
			structure.setCurrentType(structure.getType().getAfterUsageType());
			structure.setIsUsable(false);
			this.useActionPoints(3);
			return (StackItem) structure.getType().getLoot().clone();
		} else if (structure.isUseAble() && structure.getCurrentType() == 'R'
				&& this.inventory.hasCraftItem(CraftType.PICKAXE)) {
			CraftItem craftItem = inventory.getCraftItem(CraftType.PICKAXE);
			craftItem.deteriorate();
			if (craftItem.getDurability() <= 0)
				inventory.breakCraftItem(craftItem);
			structure.setCurrentType(structure.getType().getAfterUsageType());
			structure.setIsUsable(false);
			this.useActionPoints(4);
			return (StackItem) structure.getType().getLoot().clone();
		} else if (structure.isUseAble() && structure.getCurrentType() != 'R' && structure.getCurrentType() != 'T'
				&& structure.getCurrentType() != 'P') {
			structure.setCurrentType(structure.getType().getAfterUsageType());
			structure.setIsUsable(false);
			this.useActionPoints(1);
			StackItem loot = (StackItem) structure.getType().getLoot().clone();
			if (structure.getType() == StructureType.CAMPFIRE_SPOT) {
				int[] campCoord = {this.position[0], this.position[1]};
				for (int i = 0; i < this.placedCampfires.size(); i++) {
					PlacedCampfire campFire = this.placedCampfires.get(i);
					if (Arrays.equals(campCoord, campFire.getCampCoord())) {
						this.placedCampfires.remove(i);
						campFire.destroyCampfire(world);
					}
				}
			}
			if (loot.getType() == StackType.FLOWER)
				this.sanity += 2;
				if (this.sanity >= 100)
					this.sanity = 100;
			return loot;
		} else {
			this.useActionPoints(1);
		}
		return new StackItem();
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public void setSanity(double sanity) {
		this.sanity = sanity;
	}

	public void setHunger(double hunger) {
		this.hunger = hunger;
	}

	public void setActionPoints(double actionPoints) {
		this.actionPoints = actionPoints;
	}

	public double getHealth() {
		return this.health;
	}

	public double getSanity() {
		return this.sanity;
	}

	public double getHunger() {
		return this.hunger;
	}

	public double getActionPoints() {
		return this.actionPoints;
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public int[] getPosition() {
		return position;
	}

	public int getDaysSurvived() {
		return daysSurvived;
	}

	public boolean isGameOver() {
		//TODO
		return this.gameOver;
	}
}
