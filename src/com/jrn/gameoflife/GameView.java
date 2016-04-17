package com.jrn.gameoflife;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

/**
 * Represents the view on which the game is displayed.
 */
@SuppressWarnings("serial")
public class GameView extends javax.swing.JFrame implements IAbstractView {
	
	private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem startMenuItem;
    private javax.swing.JMenuItem stopMenuItem;
    private javax.swing.JMenuItem randMenuItem;
    private javax.swing.JMenuItem resetMenuItem;
    
    private GamePanel gamePanel;
	private int nCols, nRows;
	private Controller controller;

	/**
	 * Constructor
	 * @param controller The controller to which the view will register.
	 * @param nCols The number of columns in the game field panel.
	 * @param nRows The number of rows in the game field panel.
	 */
    public GameView(Controller controller, int nCols, int nRows) {
    	this.nCols = nCols;
    	this.nRows = nRows;
    	initComponents();
    	this.controller = controller;
        this.controller.addView(this);
    }
                      
    /**
     * Initializes the GUI components.
     */
    private void initComponents() {
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        startMenuItem = new javax.swing.JMenuItem();
        stopMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        randMenuItem = new javax.swing.JMenuItem();
        resetMenuItem = new javax.swing.JMenuItem();
        
        gamePanel = new GamePanel(nCols, nRows);
        gamePanel.addMouseListener(new MouseListener() {

    			@Override
    			public void mousePressed(MouseEvent e) {
    				if (!startMenuItem.isEnabled())
    					return;
    				
    				Index2D toggledCell = 
    						GamePanel.getCellIndex(e.getX(), e.getY());
    				
    				ArrayList<Index2D> toToggle = new ArrayList<Index2D>();
    				toToggle.add(toggledCell);
    				gamePanel.toggleCells(toToggle);
    				
    				controller.actionToggleCellAlive(
    						toggledCell.getX(), toggledCell.getY());
    			}

    			@Override
    			public void mouseClicked(MouseEvent e) {
    				
    			}

    			@Override
    			public void mouseEntered(MouseEvent e) {
    			}

    			@Override
    			public void mouseExited(MouseEvent e) {
    			}

    			@Override
    			public void mouseReleased(MouseEvent e) {
    			}
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game of Life");
        setResizable(false);

        fileMenu.setText("File");

        startMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        startMenuItem.setText("Start");
        startMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startMenuItem.setEnabled(false);
				stopMenuItem.setEnabled(true);
				randMenuItem.setEnabled(false);
				resetMenuItem.setEnabled(false);
				controller.actionStartTimer();
			}
        	
        });
        fileMenu.add(startMenuItem);

        stopMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        stopMenuItem.setText("Stop");
        stopMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startMenuItem.setEnabled(true);
				stopMenuItem.setEnabled(false);
				randMenuItem.setEnabled(true);
				resetMenuItem.setEnabled(true);
				controller.actionStopTimer();
			}
        	
        });
        stopMenuItem.setEnabled(false);
        fileMenu.add(stopMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");

        randMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, 0));
        randMenuItem.setText("Randomize");
        randMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Index2D> toggledCells = gamePanel.resetCells();
				
				for (Index2D toggledCell : toggledCells) {
					controller.actionToggleCellAlive(
							toggledCell.getX(), toggledCell.getY());
				}
				
				toggledCells = gamePanel.toggleRandomCells();
				
				for (Index2D toggledCell : toggledCells) {
					controller.actionToggleCellAlive(
							toggledCell.getX(), toggledCell.getY());
				}
			}
        	
        });
        editMenu.add(randMenuItem);
        
        resetMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, 0));
        resetMenuItem.setText("Reset");
        resetMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Index2D> toggledCells = gamePanel.resetCells();
				
				for (Index2D toggledCell : toggledCells) {
					controller.actionToggleCellAlive(
							toggledCell.getX(), toggledCell.getY());
				}
			}
        	
        });
        editMenu.add(resetMenuItem);

        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        getContentPane().add(gamePanel);

        pack();
        setLocationRelativeTo(null);
    } 
    
    /**
     * This method is called whenever a notification of a property change
     * has reached the controller. Toggles the appropriate cells in the
     * game field panel.
     */
    @SuppressWarnings("unchecked")
	@Override
	public void modelPropertyChange(PropertyChangeEvent e) {
		if (e.getPropertyName().equals(Controller.PROPERTY_CELL_ALIVE)) {
			ArrayList<Index2D> toToggle = (ArrayList<Index2D>) e.getNewValue();
			gamePanel.toggleCells(toToggle);
		}
	}
                
}
