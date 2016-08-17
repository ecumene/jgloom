package org.jgloom.lwjgl.gl;

import org.jgloom.gl.GLVertexArray;

/**
 * Identical to {@link GLVertexArrayContainer}, except binds when the state is changed
 */
public class StatefulGLVertexArrayContainer extends GLVertexArrayContainer {
    /** @param vertexArray The vertex array to track */
    public StatefulGLVertexArrayContainer(GLVertexArray vertexArray){
        super(vertexArray);
    }

    @Override
    public void bind() {
        super.bind();
    }

    @Override
    public void onStateChanged() {
        super.onStateChanged();
        bind();
    }
}
