package joogl.gl;

/**
 * Buffer Objects are OpenGL Objects that store an array of unformatted memory allocated by the OpenGL context (aka:
 * the GPU). These can be used to store vertex data, pixel data retrieved from images or the framebuffer, and a variety
 * of other things.
 */
public interface GLBuffer {
    /** @return The identifier for the buffer object */
    public int getBuffer();
}
