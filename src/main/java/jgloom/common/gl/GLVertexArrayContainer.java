package jgloom.common.gl;

import jgloom.gl.GLVertexArray;
import org.lwjgl.opengl.GL30;

/**
 * A Vertex Array Object (VAO) is an OpenGL Object that stores all of the state needed to supply vertex data. It stores
 * the format of the vertex data as well as the Buffer Objects providing the vertex data arrays. Note that VAOs do not
 * copy, freeze or store the contents of the referenced buffers - if you change any of the data in the buffers
 * referenced by an existing VAO, those changes will be seen by users of the VAO.
 *
 * Note: VAOs cannot be shared between OpenGL contexts.
 */
public class GLVertexArrayContainer implements GLVertexArray {
    private GLVertexArray vertexArrayInstance;

    /**
     * Constructs the array container given a pre-computed vertex array object
     * @param array The object to track
     */
    public GLVertexArrayContainer(GLVertexArray array){
        this.vertexArrayInstance = array;
    }

    /**
     * Bind a vertex array object
     */
    public void bind(){
        GL30.glBindVertexArray(getVertexArray());
    }

    /**
     * Deletes the vertexarray using glDeleteVertexArrays
     */
    public void delete(){
        GL30.glDeleteVertexArrays(getVertexArray());
    }

    /** @return The container's instance of the vertex array*/
    public GLVertexArray getVertexArrayInstance() {
        return vertexArrayInstance;
    }

    @Override
    public int getVertexArray() {
        return vertexArrayInstance.getVertexArray();
    }
}
