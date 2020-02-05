package org.dontStarveLiteGui;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class InventoryButton extends Button {
	private static final double BUTTON_WIDTH_MIN = 150d;
	private static final double BUTTON_WIDTH_MAX = 150d;
	private static final double BUTTON_HEIGHT_MIN = 30d;
	private static final double BUTTON_HEIGHT_MAX = 30d;
	private static final String RELEASED_BUTTON_STYLE = " -fx-background-color: transparent; -fx-background-image: url('/img/invButton.png');";
	private static final String PRESSED_BUTTON_STYLE = " -fx-background-color: transparent; -fx-background-image: url('/img/invButtonPressed.png');";
	
	private final String releasedStyle;
	private final String pressedStyle;
	
	private InventoryButton(InventoryButtonBuilder builder) {
		this.setText(builder.text);
		this.setMinWidth(builder.widthMin);
		this.setMaxWidth(builder.widthMax);
		this.setMinHeight(builder.heightMin);
		this.setMaxHeight(builder.heightMax);
		this.releasedStyle = builder.releasedStyle;
		this.pressedStyle = builder.pressedStyle;
		this.setStyle(builder.releasedStyle);
		this.setDisable(builder.disabled);
		this.initButtonListeners();
		getStylesheets().add(getClass().getResource("/style/InventoryButtonStyle.css").toExternalForm());
		getStyleClass().add("inventory-button");
	}
	
	public String getReleasedStyle() {
		return this.releasedStyle;
	}
	
	public String getPressedStyle() {
		return this.pressedStyle;
	}
	
	private void initButtonListeners() {
		this.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setStyle(pressedStyle);
				}
			}
		});
		this.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setStyle(releasedStyle);
				}
			}
		});
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow());
			}
		});
		this.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
			}
		});
	}
	
	public static class InventoryButtonBuilder {
		private String text;
		private boolean disabled;
		private double widthMin;
		private double widthMax;
		private double heightMin;
		private double heightMax;
		private String releasedStyle;
		private String pressedStyle;
		
		public InventoryButtonBuilder(String text, boolean disabled) {
			this.text = text;
			this.disabled = disabled;
			this.widthMin = BUTTON_WIDTH_MIN;
			this.widthMax = BUTTON_WIDTH_MAX;
			this.heightMin = BUTTON_HEIGHT_MIN;
			this.heightMax = BUTTON_HEIGHT_MAX;
			this.releasedStyle = RELEASED_BUTTON_STYLE;
			this.pressedStyle = PRESSED_BUTTON_STYLE;
		}
		
		public void setWidthMin(double newMin){
			this.widthMin = newMin;
		}
		
		public void setWidthMax(double newMax){
			this.widthMax = newMax;
		}
		
		public void setHeightMin(double newMin){
			this.widthMin = newMin;
		}
		
		public void setHeightMax(double newMax){
			this.widthMax = newMax;
		}
		
		public void setStyle(String style) {
			this.releasedStyle = style;
		}
		
		public InventoryButton build() {
			return new InventoryButton(this);
		}
	}
}
