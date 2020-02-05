package org.dontStarveLite;

public class Campfire extends CraftItem {
	public Campfire() {
		super(CraftType.CAMPFIRE);
		this.setId(Item.getAndIncrementAllIds());
	}

	public static void craft(Player player, Inventory inventory) {
		Campfire campfire = new Campfire();
		StackItem[] ingredient = campfire.getType().getIngredients();
		for (StackItem stackItem : ingredient) {
			if (!inventory.hasEnough(stackItem))
				return;
		}
		for (StackItem stackItem : ingredient) {
			inventory.useStackItem(stackItem.getType(), stackItem.getQuantity());
		}
		inventory.pickUpItem(campfire);
		player.useActionPoints(1);
	}

}
