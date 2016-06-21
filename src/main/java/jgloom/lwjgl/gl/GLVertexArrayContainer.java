package jgloom.lwjgl.gl;

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
public class GLVertexArrayContainer extends AbstractGLVertexArray {

    /**
     * Constructs the array container given a pre-computed vertex array object
     * @param vertexArrayInstance The object to track
     */
    public GLVertexArrayContainer(GLVertexArray vertexArrayInstance){
        super(vertexArrayInstance);
    }

    @Override
    public void bind(){
        GL30.glBindVertexArray(getVertexArray());
    }

    @Override
    public void delete(){
        GL30.glDeleteVertexArrays(getVertexArray());
    }

}
