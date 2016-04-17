package com.jrn.gameoflife;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Represents a controller object that relays commands between the
 * registered models and views.
 */
public abstract class AbstractController implements PropertyChangeListener {
	private ArrayList<AbstractModel> registeredModels;
	private ArrayList<IAbstractView> registeredViews;
	
	/**
	 * Default constructor.
	 */
	public AbstractController() {
		 registeredModels = new ArrayList<AbstractModel>();
		 registeredViews = new ArrayList<IAbstractView>();
    }
	
	/**
	 * Adds a model to the list of registered models.
	 * @param model The model to add.
	 */
    public void addModel(AbstractModel model) {
        registeredModels.add(model);
        model.addPropertyChangeListener(this);
    }

    /**
     * Removes a model from the list of registered models.
     * @param model The model to remove.
     */
    public void removeModel(AbstractModel model) {
        registeredModels.remove(model);
        model.removePropertyChangeListener(this);
    }

    /**
     * Adds a view to the list of registered views.
     * @param view The view to add.
     */
    public void addView(IAbstractView view) {
        registeredViews.add(view);
    }

    /**
     * Removes a view from the list of registered views.
     * @param view The view to remove.
     */
    public void removeView(IAbstractView view) {
        registeredViews.remove(view);
    }

    /**
     * Notifies all registered views of a property change in the model.
     */
    public void propertyChange(PropertyChangeEvent e) {
        for (IAbstractView view: registeredViews) {
            view.modelPropertyChange(e);
        }
    }
    
    /**
     * Invokes the method with the given name in all registered models
     * if that method exists.
     * @param name The name of the method to invoke.
     * @param args The arguments to pass to the method.
     */
    protected void invokeModelMethod(String name, Object[] args) {
        for (AbstractModel model: registeredModels) {
            try {
            	Class<?>[] argTypes = new Class<?>[args.length]; 
            	
            	for (int i = 0; i < args.length; i++) {
            		argTypes[i] = args[i].getClass();
            	}
            	
                Method method = model.getClass().
                		getMethod(name, argTypes);
                method.invoke(model, args);

            } catch (Exception e) {
            }
        }
    }
}