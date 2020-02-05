package org.dontStarveLiteGui;

import org.dontStarveLite.Player;
import org.dontStarveLite.Settings;
import org.dontStarveLite.World;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class WorldPane extends GridPane {

	private World world;
	private Player player;

	public WorldPane(Player player, World world) {
		this.world = world;
		this.player = player;
		this.buildWorldPane();
	}

	private void buildWorldPane() {
		this.setAlignment(Pos.CENTER);
		fillWorldPane();
	}

	public World getWorld() {
		return this.world;
	}

	public void fillWorldPane() {
		int widthFromCenter = (Settings.Graphics.WORLD_VIEW_WIDTH / 2) + 1;
		int heightFromCenter = (Settings.Graphics.WORLD_VIEW_HEIGHT / 2) + 1;
		for (int row = 0; row < this.world.getWorldMap().length; row++) {
			for (int col = 0; col < this.world.getWorldMap()[row].length; col++) {
				if (this.world.getWorldMap()[row][col] == null) {
				} else if (player.getPosition()[0] + heightFromCenter > row
						&& player.getPosition()[0] - heightFromCenter < row
						&& player.getPosition()[1] + widthFromCenter > col
						&& player.getPosition()[1] - widthFromCenter < col) {
					char tile = this.world.getWorldMap()[row][col].getType().getType();
					int pseudorand = ((col%5)+row) % 3;
					this.add(SpriteType.createTilePicture(tile, pseudorand), col, row);
					char structure = this.world.getWorldMap()[row][col].getStructure().getCurrentType();
					this.add(SpriteType.createStructurePicture(structure), col, row);
					if (player.getPosition()[0] == row && player.getPosition()[1] == col) {
						this.add(new ImageView(SpriteType.PLAYER.getSprite()), col, row);
					}
					if (this.world.getWorldMap()[row][col].isDark() && !this.world.getWorldMap()[row][col].isLit()) {
						this.add(new ImageView(SpriteType.DARK.getSprite()), col, row);
					} else if (this.world.getWorldMap()[row][col].isDark()
							&& this.world.getWorldMap()[row][col].isLit()) {
						this.add(new ImageView(SpriteType.BRIGHT_DARK.getSprite()), col, row);
					}
				}
			}
		}
	}
}
