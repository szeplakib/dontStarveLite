package org.dontStarveLite;

public class Settings {
	public static class Graphics{
		public static double SCREEN_WIDTH;
		public static double SCREEN_HEIGHT;
		public static int WORLD_VIEW_WIDTH;
		public static int WORLD_VIEW_HEIGHT;
	}
	public static class GamePlay{
		public static int DIFFICULTY;
	}
	
	public static void setDefault() {
		Settings.Graphics.SCREEN_WIDTH = 1366;
		Settings.Graphics.SCREEN_HEIGHT = 756;
		Settings.Graphics.WORLD_VIEW_WIDTH = 23;
		Settings.Graphics.WORLD_VIEW_HEIGHT = 13;
		Settings.GamePlay.DIFFICULTY = 100;
	}
	
}
