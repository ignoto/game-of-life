package com.jrn.gameoflife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 * Represents a game field panel.
 */
@SuppressWarnings("serial")
class GamePanel extends javax.swing.JPanel {
	private int nCols, nRows;
	private Vector<Rectangle> rectsToDraw;
	private static final int CELL_SIDE_LENGTH = 10;
	
	/**
	 * Constructor.
	 * @param nCols The number of columns in the game field.
	 * @param nRows The number of rows in the game field.
	 */
	public GamePanel(int nCols, int nRows) {
		this.nCols = nCols;
		this.nRows = nRows;
		
		rectsToDraw = new Vector<Rectangle>();
		
		setSize(CELL_SIDE_LENGTH * nCols, CELL_SIDE_LENGTH * nRows);
		setBackground(new Color(255, 255, 255));
	}
	
	/**
	 * Calculates and return the cell index given panel coordinates.
	 * @param panelX The x panel coordinate.
	 * @param panelY The y panel coordinate.
	 * @return The calculated cell index.
	 */
	public static Index2D getCellIndex(int panelX, int panelY) {
		Index2D cellIndex = new Index2D(
				panelX / CELL_SIDE_LENGTH,
				panelY / CELL_SIDE_LENGTH);
		return cellIndex;
	}
	
	/**
	 * Toggles cells in the game field by adding to the list
	 * of rectangles to draw.
	 * @param toToggle A list of cell indices for the cells to toggle.
	 */
	public void toggleCells(ArrayList<Index2D> toToggle) {
		for (Index2D cellIndex : toToggle) {
			
			Rectangle identicalRect = null;
			
			synchronized(rectsToDraw) {
				for (Rectangle rect : rectsToDraw) {
					if (rect.x == cellIndex.getX() * CELL_SIDE_LENGTH &&
						rect.y == cellIndex.getY() * CELL_SIDE_LENGTH)
						identicalRect = rect;
				}
			}
			
			if (identicalRect != null) {
				rectsToDraw.remove(identicalRect);
			}
			else {
				rectsToDraw.add(new Rectangle(
						cellIndex.getX() * CELL_SIDE_LENGTH, 
						cellIndex.getY() * CELL_SIDE_LENGTH, 
						CELL_SIDE_LENGTH, 
						CELL_SIDE_LENGTH));
			}
		}
		
		this.repaint();
	}

	/**
	 * Toggles random cells in the game field.
	 * @return A list of indices for the toggled cells.
	 */
	public ArrayList<Index2D> toggleRandomCells() {
		
		ArrayList<Index2D> toToggle = new ArrayList<Index2D>();
		
		for (int col = 0; col < this.nCols; col++) {
			for (int row = 0; row < this.nRows; row++) {
				Random rand = new Random();
				if (rand.nextInt(5) == 0) {
					toToggle.add(new Index2D(col, row));
				}
			}
		}
		
		toggleCells(toToggle);
		
		return toToggle;
	}
	
	/**
	 * Toggles all live cells in the game field.
	 * @return A list of indices for the toggled cells.
	 */
	public ArrayList<Index2D> resetCells() {
		ArrayList<Index2D> toToggle = new ArrayList<Index2D>();
		
		synchronized(rectsToDraw) {
			for (Rectangle rect : rectsToDraw) {
				toToggle.add(new Index2D(
						rect.x / CELL_SIDE_LENGTH,
						rect.y / CELL_SIDE_LENGTH));
			}
		}
		toggleCells(toToggle);
		
		return toToggle;
	}
	
	@Override
	public Dimension getPreferredSize() {
        return new Dimension(CELL_SIDE_LENGTH * (nCols), 
        		CELL_SIDE_LENGTH * (nRows));
    }
	
	/**
	 * Paints the game field.
	 * @param g The graphics context.
	 */
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);     
 
        synchronized(rectsToDraw) {
        	for (Rectangle rect : rectsToDraw) {
        		Index2D cellIndex = getCellIndex(rect.x, rect.y);
        		float red = cellIndex.getX() / (float)nCols;
        		float green = 1.0f - 
        				(cellIndex.getX() + cellIndex.getY())/(float)(nCols + nRows);
        		float blue = cellIndex.getY() / (float)nRows;
        		
        		g.setColor(new Color(red, green, blue));
            	g.fillRect(rect.x, rect.y,
            			rect.width, rect.height);
            }
        }
       
        g.setColor(new Color(200, 200, 200));
        
        for (int i = 0; i <= nCols; i++) {
        	g.drawLine(CELL_SIDE_LENGTH * i, 0, 
        			CELL_SIDE_LENGTH * i, CELL_SIDE_LENGTH * nRows);
        }
        
        for (int i = 0; i <= nRows; i++) {
        	g.drawLine(0, CELL_SIDE_LENGTH * i, 
        			CELL_SIDE_LENGTH * nCols, CELL_SIDE_LENGTH * i);
        }
        
    }
}
