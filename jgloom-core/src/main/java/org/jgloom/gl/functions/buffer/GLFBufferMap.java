package org.jgloom.gl.functions.buffer;

/**
 * Includes functions for OpenGL buffer objects
 * @see <a href="https://www.opengl.org/wiki/Buffer_Object"></a>
 */
public interface GLFBufferMap extends GLFBuffer {
    /**
     * Map a buffer object's data store
     * @param target Specifies the target buffer object being mapped. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER, or GL_PIXEL_UNPACK_BUFFER.
     * @param access Specifies the access policy, indicating whether it will be possible to read from, write to, or both
     *               read from and write to the buffer object's mapped data store. The symbolic constant must be
     *               GL_READ_ONLY, GL_WRITE_ONLY, or GL_READ_WRITE.
     */
    void map(int target, int access);

    /**
     * Relinquishes the mapping of a buffer object and invalidates the pointer to its data store
     * @param target Specifies the target buffer object being mapped. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER, or GL_PIXEL_UNPACK_BUFFER.
     */
    boolean unmap(int target);
}
