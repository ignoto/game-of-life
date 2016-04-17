package com.jrn.gameoflife;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents a model object that notifies listeners of property changes.
 */
public abstract class AbstractModel {
	protected PropertyChangeSupport propertyChangeSupport;
	
	/**
	 * Default constructor.
	 */
	public AbstractModel() {
		propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	/**
	 * Adds a listener to the list of registered listeners.
	 * @param listener The listener to add.
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	
	/**
	 * Removes a listener from the list of registered listeners.
	 * @param listener The listener to remove.
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
	/**
	 * Notifies all registered listeners of a property change.
	 * @param propertyName The name of the changed property.
	 * @param oldValue The property's old value.
	 * @param newValue The property's new value.
	 */
	protected void firePropertyChange(String propertyName, 
			Object oldValue, Object newValue) {
		propertyChangeSupport.firePropertyChange(propertyName, 
				oldValue, newValue);
	}
}
