package com.jrn.gameoflife;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

/**
 * Represents the view on which the configurations are displayed.
 */
@SuppressWarnings("serial")
public class ConfigView extends javax.swing.JFrame implements IAbstractView {
	
    private javax.swing.JLabel heightLabel;
    private javax.swing.JButton buttonOpen;
    private javax.swing.JLabel tickTimeLabel;
    private javax.swing.JSlider heightSlider;
    private javax.swing.JSlider tickTimeSlider;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel widthLabel;
    private javax.swing.JSlider widthSlider;
	
	private Controller controller;

	/**
	 * Constructor.
	 * @param controller The controller to which the view will register.
	 */
    public ConfigView(Controller controller) {
        initComponents();
        this.controller = controller;
        this.controller.addView(this);
    }
                       
    /**
     * Initializes the GUI components.
     */
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        widthLabel = new javax.swing.JLabel();
        heightLabel = new javax.swing.JLabel();
        buttonOpen = new javax.swing.JButton();
        widthSlider = new javax.swing.JSlider();
        heightSlider = new javax.swing.JSlider();
        tickTimeSlider = new javax.swing.JSlider();
        tickTimeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Configuration");
        setResizable(false);

        mainPanel.setLayout(new java.awt.GridBagLayout());

        widthLabel.setText("Width:");
        mainPanel.add(widthLabel, new java.awt.GridBagConstraints());
        widthLabel.getAccessibleContext().setAccessibleName("widthLabel");

        heightLabel.setText("Height:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        mainPanel.add(heightLabel, gridBagConstraints);

        buttonOpen.setText("Open Game Window");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        buttonOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.changePropertyTickTimeMs(tickTimeSlider.getValue());
				controller.changePropertySize(widthSlider.getValue(),
						heightSlider.getValue());
				
				new GameView(controller, widthSlider.getValue(), 
						heightSlider.getValue()).setVisible(true);
				
				ConfigView.this.dispose();
			}
        	
        });
        mainPanel.add(buttonOpen, gridBagConstraints);

        widthSlider.setMinimum(30);
        widthSlider.setMaximum(150);
        widthSlider.setMajorTickSpacing(120);
        widthSlider.setMinorTickSpacing(10);
        widthSlider.setPaintLabels(true);
        widthSlider.setPaintTicks(true);
        widthSlider.setSnapToTicks(true);
        mainPanel.add(widthSlider, new java.awt.GridBagConstraints());

        heightSlider.setMinimum(30);
        heightSlider.setMaximum(90);
        heightSlider.setMajorTickSpacing(60);
        heightSlider.setMinorTickSpacing(5);
        heightSlider.setPaintLabels(true);
        heightSlider.setPaintTicks(true);
        heightSlider.setSnapToTicks(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        mainPanel.add(heightSlider, gridBagConstraints);

        tickTimeSlider.setMajorTickSpacing(1900);
        tickTimeSlider.setMaximum(2000);
        tickTimeSlider.setMinimum(100);
        tickTimeSlider.setMinorTickSpacing(100);
        tickTimeSlider.setPaintLabels(true);
        tickTimeSlider.setPaintTicks(true);
        tickTimeSlider.setSnapToTicks(true);
        tickTimeSlider.setToolTipText("");
        tickTimeSlider.setValue(1000);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        mainPanel.add(tickTimeSlider, gridBagConstraints);

        tickTimeLabel.setText("Tick time (ms):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        mainPanel.add(tickTimeLabel, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        
        pack();
        setLocationRelativeTo(null);
    }
    
    @Override
	public void modelPropertyChange(PropertyChangeEvent e) {
	}
    
}
