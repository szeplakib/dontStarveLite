/**
 * Fan work of the game "Don't Starve".
 * This is just a fan-made minigame, it is not connected to Klei Entertainment, or Don't Starve.
 *
 * @author  Bence Széplaki
 * @version 0.6
 * @since   2018-04-10
 */
package org.dontStarveLiteGui;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.dontStarveLite.Player;
import org.dontStarveLite.Settings;
import org.dontStarveLite.World;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainGui extends Application {

	@Override
	public void init() throws Exception {
		super.init();
		Settings.setDefault();
	}

	@Override
	public void stop() throws Exception {
		super.stop();
	}

	@Override
	public void start(Stage gameStage){
		gameStage.setTitle("Don't Starve Lite");
		StackPane mainPane = new StackPane();
		mainPane.setAlignment(Pos.CENTER);
		World world = new World();
		Player player = new Player(world, Settings.GamePlay.DIFFICULTY);
		WorldPane worldPane = new WorldPane(player, world);

		mainPane.getChildren().add(worldPane);

		HUD_Pane hudPane = new HUD_Pane(player);
		mainPane.getChildren().add(hudPane);

		Scene scene = new Scene(mainPane, Settings.Graphics.SCREEN_WIDTH, Settings.Graphics.SCREEN_HEIGHT);

		gameStage.setScene(scene);
		gameStage.show();

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case W:
					player.moveUp();
					moveGui(player, hudPane, worldPane);
					break;
				case S:
					player.moveDown();
					moveGui(player, hudPane, worldPane);
					break;
				case A:
					player.moveLeft();
					moveGui(player, hudPane, worldPane);
					break;
				case D:
					player.moveRight();
					moveGui(player, hudPane, worldPane);
					break;
				case E:
					useCurrentStructure(player, hudPane, worldPane);
					break;
				case SHIFT:
					player.placeCampfire();
					hudPane.getPlayerStatBox().setStats(player);
					hudPane.setInventory(player);
					worldPane.getChildren().clear();
					worldPane.fillWorldPane();
					break;
				default:
					break;
				}
				if (player.isGameOver())
					gameOver(gameStage, player);
			}
		});

	}

	public void useCurrentStructure(Player player, HUD_Pane hudPane, WorldPane worldPane) {
		player.getInventory().pickUpItem(player.useStructure(
				worldPane.getWorld().getWorldMap()[player.getPosition()[0]][player.getPosition()[1]].getStructure()));
		hudPane.getPlayerStatBox().setStats(player);
		player.getInventory().printInventory();
		hudPane.setInventory(player);
		worldPane.getChildren().clear();
		worldPane.fillWorldPane();
	}

	private void moveGui(Player player, HUD_Pane hudPane, WorldPane worldPane) {
		hudPane.getPlayerStatBox().setStats(player);
		hudPane.setInventory(player);
		worldPane.getChildren().clear();
		worldPane.fillWorldPane();
	}

	public void gameOver(Stage gameStage, Player player) {
		StackPane stackPane = new StackPane();
		Scene scene = new Scene(stackPane, Settings.Graphics.SCREEN_WIDTH, Settings.Graphics.SCREEN_HEIGHT);
		Text gameOverText = new Text("You died!");
		gameOverText.setFont(Font.font("Arial", FontWeight.BOLD, 90));
		String day = player.getDaysSurvived() == 1 ? " day!" : " days!";
		Text survivedText = new Text("You survived: " + player.getDaysSurvived() + day);
		survivedText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		survivedText.setFill(Color.RED);
		VBox box = new VBox();
		box.getChildren().add(gameOverText);
		box.getChildren().add(survivedText);
		box.setAlignment(Pos.CENTER);
		stackPane.getChildren().add(box);
		gameStage.setTitle("Game over!");
		gameStage.setScene(scene);
	}

	public static void main(String[] args) {
		Application.launch();
	}
}
