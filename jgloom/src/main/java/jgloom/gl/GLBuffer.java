package jgloom.gl;

/**
 * Buffer Objects are OpenGL Objects that store an array of unformatted memory allocated by the OpenGL context (aka:
 * the GPU). These can be used to store vertex data, pixel data retrieved from images or the framebuffer, and a variety
 * of other things.
 * @see <a href=https://www.opengl.org/wiki/Buffer_Object>opengl.org - Buffer Objects</a>
 */
@FunctionalInterface
public interface GLBuffer {
    /** @return The identifier for the buffer object */
    int getbuffer();
}
