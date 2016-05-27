package jgloom.gl;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL43;

/**
 * Buffer Objects are OpenGL Objects that store an array of unformatted memory allocated by the OpenGL context (aka:
 * the GPU). These can be used to store vertex data, pixel data retrieved from images or the framebuffer, and a variety
 * of other things.
 * @see <a href=https://www.opengl.org/wiki/Buffer_Object>opengl.org - Buffer Objects</a>
 */
public interface GLBuffer {
    /** @return The identifier for the buffer object */
    int getBuffer();

    /** The OpenGL framebuffer object identifier */
    int IDENTIFIER = GL43.GL_BUFFER;

    /**
     * Constructs a {@link GLBuffer} object with {@link GL15#glGenBuffers()}.
     */
    static GLBuffer createBuffer() {
        int buffer = GL15.glGenBuffers();
        return () -> buffer;
    }
}
