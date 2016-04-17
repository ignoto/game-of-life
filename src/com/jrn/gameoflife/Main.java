package com.jrn.gameoflife;

/**
 * Launches the application.
 */
public class Main {
	public static void main(String[] args) {
		Controller controller = new Controller();
		GameModel gameModel = new GameModel();
		
		controller.addModel(gameModel);

		ConfigView configView = new ConfigView(controller);
		configView.setVisible(true);
	}
}
