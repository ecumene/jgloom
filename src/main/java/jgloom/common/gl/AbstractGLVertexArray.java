package jgloom.common.gl;

import jgloom.gl.GLVertexArray;

/**
 * Created by mh on 6/15/16.
 */
public abstract class AbstractGLVertexArray implements GLVertexArray {
    private GLVertexArray vertexArrayInstance;

    /**
     * Constructs the array container given a pre-computed vertex array object
     * @param vertexArrayInstance The object to track
     */
    public AbstractGLVertexArray(GLVertexArray vertexArrayInstance){
        this.vertexArrayInstance = vertexArrayInstance;
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


    /** @return The container's instance of the vertex array*/
    public GLVertexArray getVertexArrayInstance() {
        return vertexArrayInstance;
    }

    @Override
    public int getVertexArray() {
        return vertexArrayInstance.getVertexArray();
    }
}
