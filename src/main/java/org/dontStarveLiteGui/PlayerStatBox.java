package org.dontStarveLiteGui;

import java.text.DecimalFormat;

import org.dontStarveLite.Player;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PlayerStatBox extends VBox {
	private static final int H_SIZE = 300;
	private static final int V_SIZE = 100;
	private static final double H_INSET = 5;
	private static final double V_INSET = 7.5;
	private static final String STYLE = "-fx-background-color: transparent; -fx-background-image: url('/img/playerPanel.png');";
	
	private Text sanity;
	private Text health;
	private Text hunger;
	private Text actionPoints;
	private Text daysSurvived;
	private ImageView sanityIcon;
	private ImageView healthIcon;
	private ImageView hungerIcon;
	private HBox sanityBox;
	private HBox healthBox;
	private HBox hungerBox;
	private HBox actionPointsBox;
	private HBox daysSurvivedBox;
	
	public PlayerStatBox(Player player){
		this.sanityBox = new HBox();
		this.healthBox = new HBox();
		this.hungerBox = new HBox();
		this.sanityBox.setPrefWidth(H_SIZE);
		this.healthBox.setPrefWidth(H_SIZE);
		this.hungerBox.setPrefWidth(H_SIZE);
		this.setPadding(new Insets(V_INSET,H_INSET,V_INSET,H_INSET));
		this.setPrefSize(H_SIZE, V_SIZE);
		this.sanityIcon = new ImageView(SpriteType.createIndicatorPicture('s', (int) player.getSanity()));
		this.healthIcon = new ImageView(SpriteType.createIndicatorPicture('h', (int) player.getHealth()));
		this.hungerIcon = new ImageView(SpriteType.createIndicatorPicture('H', (int) player.getHunger()));
		this.sanityBox.getChildren().add(sanityIcon);
		this.healthBox.getChildren().add(healthIcon);
		this.hungerBox.getChildren().add(hungerIcon);
		this.sanity = new Text("Sanity: " + new DecimalFormat("#.##").format(player.getSanity()) + "%");
		this.health = new Text("Health: " + new DecimalFormat("#.##").format(player.getHealth()) + "%");
		this.hunger = new Text("Hunger: " + new DecimalFormat("#.##").format(player.getHunger()) + "%");
		this.actionPoints = new Text("Action points: " + (int) player.getActionPoints());
		this.daysSurvived = new Text("Day: " + player.getDaysSurvived());
		this.sanityBox.getChildren().add(this.sanity );
		this.healthBox.getChildren().add(this.health);
		this.hungerBox.getChildren().add(this.hunger);
		this.sanity.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		this.health.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		this.hunger.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		this.actionPoints.setFont(Font.font("Arial", FontWeight.BOLD, 11));
		this.daysSurvived.setFont(Font.font("Arial", FontWeight.BOLD, 11));
		this.actionPointsBox = new HBox(50,this.actionPoints);
		this.daysSurvivedBox = new HBox(50,this.daysSurvived);
		this.getChildren().add(this.sanityBox);
		this.getChildren().add(this.healthBox);
		this.getChildren().add(this.hungerBox);
		this.getChildren().add(this.actionPointsBox);
		this.getChildren().add(this.daysSurvivedBox);
		
		this.setStyle(STYLE);
	}
	
	public void setStats(Player player) {
		sanity.setText("Sanity: " + new DecimalFormat("#.##").format(player.getSanity()) + "%");
		health.setText("Health: " + new DecimalFormat("#.##").format(player.getHealth()) + "%");
		hunger.setText("Hunger: " + new DecimalFormat("#.##").format(player.getHunger()) + "%");
		actionPoints.setText("Action points: " + (int) player.getActionPoints());
		daysSurvived.setText("Day: " + player.getDaysSurvived());
		this.sanityIcon.setImage(SpriteType.createIndicatorPicture('s', (int) player.getSanity()));
		this.healthIcon.setImage(SpriteType.createIndicatorPicture('h', (int) player.getHealth()));
		this.hungerIcon.setImage(SpriteType.createIndicatorPicture('H', (int) player.getHunger()));
	}
	
}
