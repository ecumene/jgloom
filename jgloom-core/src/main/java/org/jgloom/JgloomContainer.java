package org.jgloom;

/**
 * A simple container for any object defining a method that should be called if the object's state has changed.
 * @param <T> The type to contain / receive
 */

// TODO: State listeners?

public abstract class JgloomContainer<T> {
    private T instance;

    public JgloomContainer(T instance){
        this.instance = instance;
    }

    public abstract void onStateChanged();

    public T getInstance(){
        return instance;
    }
}
