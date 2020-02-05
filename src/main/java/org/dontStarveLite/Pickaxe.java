package org.dontStarveLite;

public class Pickaxe extends CraftItem {
	public Pickaxe() {
		super(CraftType.PICKAXE);
		this.setId(Item.getAndIncrementAllIds());
	}

	public static void craft(Player player, Inventory inventory) {
		Pickaxe pickaxe = new Pickaxe();
		StackItem[] ingredients = pickaxe.getType().getIngredients();
		for (StackItem ingredient : ingredients) {
			if (!inventory.hasEnough(ingredient))
				return;
		}
		for (StackItem ingredient : ingredients) {
			inventory.useStackItem(ingredient.getType(), ingredient.getQuantity());
		}
		inventory.pickUpItem(pickaxe);
		player.useActionPoints(1);
	}
}
