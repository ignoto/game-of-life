package com.jrn.gameoflife;

/**
 * Represents a controller object that relays commands between the
 * registered models and views.
 */
public class Controller extends AbstractController {
	
	/* Property names */
	public static String PROPERTY_CELL_ALIVE = "CellAlive";
	
	/**
	 * Changes the size of the grid in the model.
	 * @param nCols The number of columns in the grid.
	 * @param nRows The number of rows in the grid.
	 */
	public void changePropertySize(Integer nCols, Integer nRows) {
		invokeModelMethod("setSize", new Object[] {nCols, nRows});
	}

	/**
	 * Changes the update period in the model.
	 * @param tickTimeMs The new update period. 
	 */
	public void changePropertyTickTimeMs(Integer tickTimeMs) {
		invokeModelMethod("setTickTimeMs", new Object[] {tickTimeMs});
	}
	
	/**
	 * Starts the game loop in the model.
	 */
	public void actionStartTimer() {
		invokeModelMethod("startTimer", new Object[0]);
	}
	
	/**
	 * Stops the game loop in the model.
	 */
	public void actionStopTimer() {
		invokeModelMethod("stopTimer", new Object[0]);
	}
	
	/**
	 * Toggles a cell in the model.
	 * @param col The column in which the cell resides.
	 * @param row The row in which the cell resides.
	 */
	public void actionToggleCellAlive(Integer col, Integer row) {
		invokeModelMethod("toggleCellAlive", new Object[] {col, row});
	}
	
}
