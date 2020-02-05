package org.dontStarveLiteGui;

import org.dontStarveLite.Axe;
import org.dontStarveLite.Campfire;
import org.dontStarveLite.CraftType;
import org.dontStarveLite.Garland;
import org.dontStarveLite.Item;
import org.dontStarveLite.Pickaxe;
import org.dontStarveLite.Player;
import org.dontStarveLite.StackItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class HUD_Pane extends BorderPane {
	private VBox infoBox;
	private PlayerStatBox playerStatBox;
	private GridPane guiInventory;

	public HUD_Pane(Player player) {
		this.infoBox = new VBox();
		this.playerStatBox = new PlayerStatBox(player);
		this.guiInventory = new GridPane();
		this.infoBox.getChildren().add(playerStatBox);
		this.infoBox.getChildren().add(guiInventory);
		this.playerStatBox.setStats(player);
		setInventory(player);
		this.setRight(infoBox);
	}
	public PlayerStatBox getPlayerStatBox(){
		return this.playerStatBox;
	}

	public void setInventory(Player player) {
		this.guiInventory.getChildren().clear();
		for (byte row = 0; row < player.getInventory().getBag().length; row++) {
			for (byte col = 0; col < player.getInventory().getBag()[0].length; col++) {
				if (player.getInventory().getBag()[row][col] == null) {
					InventoryButton invButton = new InventoryButton.InventoryButtonBuilder("Empty", true).build();
					this.guiInventory.add(invButton, col, row);
				} else {
					//TODO
					Item item = player.getInventory().getBag()[row][col];
					InventoryButton invButton = new InventoryButton.InventoryButtonBuilder(item.toGuiString(), false).build();
					ContextMenu contextMenu = new ContextMenu();
					MenuItem destroy = new MenuItem("Destroy");
					byte a = row;
					byte b = col;
					destroy.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							player.getInventory().destroyItem(a, b);
							setInventory(player);
							getPlayerStatBox().setStats(player);
						}
					});
					contextMenu.getItems().add(destroy);
					if (item instanceof StackItem) {
						StackItem stackItem = (StackItem) item;
						if (stackItem.getType().isCookable()) {
							MenuItem cook = new MenuItem("Cook");
							cook.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent e) {
									player.cook(stackItem.getType());
									setInventory(player);
									getPlayerStatBox().setStats(player);
								}
							});
							if (!player.isAtCampFire()) {
								cook.setDisable(true);
							}
							contextMenu.getItems().add(cook);
							}
						invButton.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent e){
								player.eat(stackItem.getType());
								setInventory(player);
								getPlayerStatBox().setStats(player);
							}
						});
						for (CraftType c : CraftType.values()) {
							for(StackItem i : c.getIngredients()) {
								if (stackItem.getType() == i.getType()) {
									MenuItem toCraft = new MenuItem(c.getName() + " (" + c.getRecipe() + ")");
									for (StackItem j : c.getIngredients()) {
										if (!player.getInventory().hasEnough(j))
											toCraft.setDisable(true);
									}
									toCraft.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent e) {
											switch (c) {
											case AXE:
												Axe.craft(player, player.getInventory());
												setInventory(player);
												getPlayerStatBox().setStats(player);
												break;
											case PICKAXE:
												Pickaxe.craft(player, player.getInventory());
												setInventory(player);
												getPlayerStatBox().setStats(player);
											case GARLAND:
												Garland.craft(player, player.getInventory());
												setInventory(player);
												getPlayerStatBox().setStats(player);
												break;
											case CAMPFIRE:
												Campfire.craft(player, player.getInventory());
												setInventory(player);
												getPlayerStatBox().setStats(player);
												break;
											default:
												break;
											}
											player.cook(stackItem.getType());
											setInventory(player);
											getPlayerStatBox().setStats(player);
										}
									});
									contextMenu.getItems().add(toCraft);
									break;
								}
							}
						}
					}
					invButton.setContextMenu(contextMenu);
					this.guiInventory.add(invButton, col, row);
				}
			}
		}
	}
	public GridPane getGuiInventory() {
		return this.guiInventory;
	}
}
