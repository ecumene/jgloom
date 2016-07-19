package org.jgloom.gl.functions.buffer;

import java.nio.*;

/**
 * Includes functions for OpenGL buffer objects
 * @see <a href="https://www.opengl.org/wiki/Buffer_Object"></a>
 */
public interface GLFBufferData extends GLFBuffer {
    /**
     *
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     * @param size   the size in bytes of the buffer object's new data store
     * @param usage  Specifies the expected usage pattern of the data store. The symbolic constant must be
     *               GL_STREAM_DRAW, GL_STREAM_READ, GL_STREAM_COPY, GL_STATIC_DRAW, GL_STATIC_READ, GL_STATIC_COPY,
     *               GL_DYNAMIC_DRAW, GL_DYNAMIC_READ, or GL_DYNAMIC_COPY.
     */
    void data(int target, long size, int usage);
    /**@see #data(int, long, int) */
    void data(int target, ByteBuffer data, int usage);
    /**@see #data(int, ByteBuffer, int)  */
    void data(int target, ShortBuffer data, int usage);
    /**@see #data(int, ByteBuffer, int)  */
    void data(int target, IntBuffer data, int usage);
    /**@see #data(int, ByteBuffer, int)  */
    void data(int target, FloatBuffer data, int usage);
    /**@see #data(int, ByteBuffer, int)  */
    void data(int target, DoubleBuffer data, int usage);
}
