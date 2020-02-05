package org.dontStarveLite;

public class Axe extends CraftItem {
	public Axe() {
		super(CraftType.AXE);
		this.setId(Item.getAndIncrementAllIds());
	}

	public static void craft(Player player, Inventory inventory) {
		Axe axe = new Axe();
		StackItem[] ingredient = axe.getType().getIngredients();
		for (StackItem stackItem : ingredient) {
			if (!inventory.hasEnough(stackItem))
				return;
		}
		for (StackItem stackItem : ingredient) {
			inventory.useStackItem(stackItem.getType(), stackItem.getQuantity());
		}
		inventory.pickUpItem(axe);
		player.useActionPoints(1);
	}
}
