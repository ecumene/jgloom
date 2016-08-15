package org.jgloom.lwjgl.gl;

import org.jgloom.JgloomContainer;
import org.jgloom.gl.GLVertexArray;

/**
 * Created by mh on 6/15/16.
 */
public abstract class AbstractGLVertexArray extends JgloomContainer<GLVertexArray> implements GLVertexArray {
    /**
     * Constructs the array container given a pre-computed vertex array object
     * @param vertexArrayInstance The object to track
     */
    public AbstractGLVertexArray(GLVertexArray vertexArrayInstance){
        super(vertexArrayInstance);
    }

    /**
     * Bind a vertex array object
     */
    public abstract void bind();

    /**
     * Deletes the vertexarray, after it's deleted it's name can be used again. If a vertex array is bound and deleted,
     * the default vertex array (0) gets bound.
     */
    public abstract void delete();

    @Override
    public int getVertexArray() {
        return getInstance().getVertexArray();
    }
}
