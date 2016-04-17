package com.jrn.gameoflife;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents a model of the game.
 */
public class GameModel extends AbstractModel {
	
	private Cell[][] grid;
	private Timer timer;
	private int tickTimeMs;
	
	/**
	 * Constructor.
	 * @param nCols The number of columns in the game grid.
	 * @param nRows The number of rows in the game grid.
	 * @param tickTimeMs The update period in milliseconds.
	 */
	public GameModel(int nCols, int nRows, int tickTimeMs) {
		this.tickTimeMs = tickTimeMs;
		initGrid(nCols, nRows);
	}
	
	/**
	 * Default constructor.
	 */
	public GameModel() {
		timer = new Timer();
		tickTimeMs = 1000;
		initGrid(10, 10);
	}
	
	/**
	 * Initializes the game grid by setting its size and assigning neighbors
	 * to the cells within it.
	 * @param nCols The number of columns in the game grid.
	 * @param nRows The number of rows in the game grid.
	 */
	private void initGrid(int nCols, int nRows) {
		grid = new Cell[nCols][nRows];
		
		for (int col = 0; col < grid.length; col++) {
			for (int row = 0; row < grid[col].length; row++) {
				
				grid[col][row] = new Cell();
				
			}
		}
		
		for (int col = 0; col < grid.length; col++) {
			for (int row = 0; row < grid[col].length; row++) {
				
				Cell currentCell = grid[col][row];
				
				if (row > 0)
					currentCell.addNeighbor(grid[col][row-1]);
				if (col < nCols - 1)
					currentCell.addNeighbor(grid[col+1][row]);
				if (row < nRows - 1)
					currentCell.addNeighbor(grid[col][row+1]);
				if (col > 0)
					currentCell.addNeighbor(grid[col-1][row]);
				if (row > 0 && col > 0)
					currentCell.addNeighbor(grid[col-1][row-1]);
				if (row > 0 && col < nCols - 1)
					currentCell.addNeighbor(grid[col+1][row-1]);
				if (row < nRows - 1 && col < nCols - 1)
					currentCell.addNeighbor(grid[col+1][row+1]);
				if (row < nRows - 1 && col > 0)
					currentCell.addNeighbor(grid[col-1][row+1]);
			}
		}
	}
	
	/**
	 * Sets the size of the game grid.
	 * @param nCols The number of columns in the game grid.
	 * @param nRows The number of rows in the game grid.
	 */
	public void setSize(Integer nCols, Integer nRows) {
		initGrid(nCols, nRows);
	}
	
	/**
	 * Sets the update period in milliseconds.
	 * @param tickTimeMs The update period in milliseconds.
	 */
	public void setTickTimeMs(Integer tickTimeMs) {
		this.tickTimeMs = tickTimeMs;
	}
	
	/**
	 * Starts the game loop.
	 */
	public void startTimer() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						tick();
					}
				}, 0, tickTimeMs);
	}
	
	/**
	 * Stops the game loop.
	 */
	public void stopTimer() {
		timer.cancel();
		timer.purge();
	}
	
	/**
	 * Toggles a cell in the grid.
	 * @param col The column in which the cell resides.
	 * @param row The row in which the cell resides.
	 */
	public void toggleCellAlive(Integer col, Integer row) {
		Cell cell = grid[col][row];
		cell.setAlive(!cell.isAlive());
	}
	
	/**
	 * Updates the states (alive or dead) for each cell in the grid.
	 */
	public void tick() {
		for (int col = 0; col < grid.length; col++) {
			for (int row = 0; row < grid[col].length; row++) {
				grid[col][row].prepareNextState();
			}
		}
		
		ArrayList<Index2D> toggledCells = new ArrayList<Index2D>();
		
		for (int col = 0; col < grid.length; col++) {
			for (int row = 0; row < grid[col].length; row++) {
				if (grid[col][row].goToNextState()) {
					toggledCells.add(new Index2D(col, row));
				}
			}
		}
		
		firePropertyChange(Controller.PROPERTY_CELL_ALIVE, 
				new Object(), toggledCells);
	}
	
}