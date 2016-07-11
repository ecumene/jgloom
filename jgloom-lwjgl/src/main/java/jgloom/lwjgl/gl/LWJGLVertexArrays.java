package jgloom.lwjgl.gl;

import jgloom.gl.GLVertexArray;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

/**
 * A Vertex Array Object (VAO) is an OpenGL Object that stores all of the state needed to supply vertex data (with one
 * minor exception noted below). It stores the format of the vertex data as well as the Buffer Objects (see below)
 * providing the vertex data arrays. Note that VAOs do not copy, freeze or store the contents of the referenced buffers
 * - if you change any of the data in the buffers referenced by an existing VAO, those changes will be seen by users of
 * the VAO.
 * @see <a href="https://www.opengl.org/wiki/Vertex_Specification#Vertex_Array_Object">opengl.org - texture object</a>
 */
public class LWJGLVertexArrays {
    /** The OpenGL vertex array object identifier */
    public static int IDENTIFIER = GL11.GL_VERTEX_ARRAY;

    /**
     * @return A constructed vertex array with glGenVertexArrays
     */
    public static GLVertexArray createVertexArray() {
        int vao = GL30.glGenVertexArrays();
        return () -> vao;
    }
}
