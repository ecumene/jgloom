package joogl.gl;

import org.lwjgl.opengl.GL43;

/**
 * Buffer Objects are OpenGL Objects that store an array of unformatted memory allocated by the OpenGL context (aka:
 * the GPU). These can be used to store vertex data, pixel data retrieved from images or the framebuffer, and a variety
 * of other things.
 * @see <a href=https://www.opengl.org/wiki/Buffer_Object>opengl.org - Buffer Objects</a>
 */
public interface GLBuffer {
    /** @return The identifier for the buffer object */
    public int getBuffer();

    /** The OpenGL framebuffer object identifier */
    public static final int IDENTIFIER = GL43.GL_BUFFER;
}
