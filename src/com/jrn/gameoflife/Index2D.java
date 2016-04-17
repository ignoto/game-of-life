package com.jrn.gameoflife;

/**
 * Represents a 2-dimensional index.
 */
public class Index2D {
	private int x, y;
	
	/**
	 * Constructor.
	 * @param x The x index.
	 * @param y The y index.
	 */
	public Index2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets the x index.
	 * @return The x index.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x index.
	 * @param x The new x index.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the y index.
	 * @return The y index.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y index.
	 * @param y The new y index.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
}
