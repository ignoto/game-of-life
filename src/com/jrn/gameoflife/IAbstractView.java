package com.jrn.gameoflife;
import java.beans.PropertyChangeEvent;

/**
 * Represents a view that listens to property changes.
 */
public interface IAbstractView {

	public void modelPropertyChange(PropertyChangeEvent e);

}
