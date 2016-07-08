package jgloom.gl.functions.buffer;

import jgloom.gl.GLBuffer;

/**
 * Includes the basic functions in OpenGL buffer objects
 * @see <a href="https://www.opengl.org/wiki/Buffer_Object"></a>
 */
public interface GLFBuffer extends GLBuffer {
    /**
     * Bind a named buffer object
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     */
    void bind(int target);
    /** Delete the buffer and free it's data store */
    void delete();
}
