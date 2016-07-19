package org.jgloom.gl.functions.buffer;

/**
 * Includes functions for OpenGL buffer objects
 * @see <a href="https://www.opengl.org/wiki/Buffer_Object"></a>
 */
public interface GLFBufferInvalidate extends GLFBuffer {
    /**
     * Makes the buffer's contents undefined
     */
    void invalidate();

    /**
     * Makes the buffer's contents undefined
     * @param offset The beginning value
     * @param size   The size of the subrange
     */
    void invalidateRange(int offset, int size);
}
