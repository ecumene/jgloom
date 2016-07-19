package org.jgloom.lwjgl.gl;

/**
 * An interface between legacy (1/2), modern (3) and future (4) OpenGL vertex arrays.
 */
public interface VertexArrayPointer {
    /**
     * Called when the vertex array should be used, in higher-level model loaders this is used as a intermediary between
     * legacy, modern, and future vertex arrays.
     */
    public void enable();
    /**
     * Called when the vertex array should stop be used, in higher-level model loaders this is used as a intermediary
     * between legacy, modern, and future vertex arrays.
     */
    public void disable();
}
