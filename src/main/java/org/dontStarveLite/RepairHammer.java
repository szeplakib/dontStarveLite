package org.dontStarveLite;

public class RepairHammer extends CraftItem {
	//TODO
	public RepairHammer() {
		super(CraftType.REPAIR_HAMMER);
		this.setId(Item.getAndIncrementAllIds());
		this.setDurability((byte) 100);
	}

	public boolean isDamaged(CraftItem tool) {
		return 100 > tool.getDurability();
	}

	public void repairDamaged(CraftItem tool) {
		if (isDamaged(tool)) {
			tool.setDurability((byte) (tool.getDurability() + tool.getType().getRepairRate()));
		}
	}
}
