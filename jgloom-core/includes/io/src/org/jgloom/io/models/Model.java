package org.jgloom.io.models;

/**
 * Interface representing an object, which is renderable, consisting of several faces
 */
public interface Model {
    /**
     * @return All instances of {@link ModelFace} which make up the model's figure
     */
    ModelFace[] getFaces();
    
    /**
     * WIP: May be replaced with a mesh creation method
     */
    void render();
}
