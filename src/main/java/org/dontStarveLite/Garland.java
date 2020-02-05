package org.dontStarveLite;

public class Garland extends CraftItem {
	public Garland() {
		super(CraftType.GARLAND);
		this.setId(Item.getAndIncrementAllIds());
		this.setDurability(1500);
	}

	public static void craft(Player player, Inventory inventory) {
		Garland garland = new Garland();
		StackItem[] ingredient = garland.getType().getIngredients();
		for (StackItem stackItem : ingredient) {
			if (!inventory.hasEnough(stackItem))
				return;
		}
		for (StackItem stackItem : ingredient) {
			inventory.useStackItem(stackItem.getType(), stackItem.getQuantity());
		}
		inventory.pickUpItem(garland);
		player.useActionPoints(1);
	}
}
