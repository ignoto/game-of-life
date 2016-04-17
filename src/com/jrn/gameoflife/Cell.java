package com.jrn.gameoflife;
import java.util.HashSet;

/**
 * Represents a cell in the game.
 */
class Cell {
	private boolean aliveNow;
	private boolean aliveNext;
	private HashSet<Cell> neighbors;
	
	/**
	 * Default constructor.
	 */
	public Cell() {
		neighbors = new HashSet<Cell>();
		this.aliveNow = false;
		this.aliveNext = false;
	}
	
	/**
	 * Gets the boolean value indicating whether the cell is alive or not.
	 * @return A value indicating whether the cell is alive or not.
	 */
	public boolean isAlive() {
		return aliveNow;
	}
	
	/**
	 * Sets the field indicating whether the cell is alive ot not.
	 * @param aliveNow The new value.
	 */
	public void setAlive(boolean aliveNow) {
		this.aliveNow = aliveNow;
	}
	
	/**
	 * Adds a neighbor cell to the list of neighbor cells.
	 * @param neighbor The neighbor cell to add.
	 */
	public void addNeighbor(Cell neighbor) {
		neighbors.add(neighbor);
	}
	
	/**
	 * Sets the next state (alive or dead) for the cell.
	 */
	public void prepareNextState() {
		int nLiveNeighbors = 0;
		
		for (Cell cell : neighbors) {
			if (cell.isAlive()) {
				++nLiveNeighbors;
			}
		}
		
		if (nLiveNeighbors < 2 || nLiveNeighbors > 3)
	        aliveNext = false;
	    else if (nLiveNeighbors == 3)
	        aliveNext = true;
	    else
	    	aliveNext = aliveNow;
	}
	
	/**
	 * Moves the cell to the next state by assigning the "next" state
	 * to the "current" state.
	 * @return A value indicating whether the state changed or not.
	 */
	public boolean goToNextState() {
		boolean stateChanged = aliveNow != aliveNext;
		
		aliveNow = aliveNext;
		
		return stateChanged;
	}
}
