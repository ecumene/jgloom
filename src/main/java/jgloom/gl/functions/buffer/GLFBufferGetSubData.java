package jgloom.gl.functions.buffer;

import java.nio.*;

/**
 * Includes functions for OpenGL buffer objects
 * @see <a href="https://www.opengl.org/wiki/Buffer_Object"></a>
 */
public interface GLFBufferGetSubData extends GLFBuffer {
    /**
     * Returns a subset of a buffer object's data store
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DISPATCH_INDIRECT_BUFFER,
     *               GL_DRAW_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER, GL_PIXEL_UNPACK_BUFFER,
     *               GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER, GL_TRANSFORM_FEEDBACK_BUFFER,
     *               or GL_UNIFORM_BUFFER.
     * @param offset Specifies the offset into the buffer object's data store from which data will be returned, measured
     *               in bytes.
     * @param data   Specifies a pointer to the location where buffer object data is returned.
     */
    void getSubData(int target, long offset, ByteBuffer data);
    /**@see #getSubData(int, long, ByteBuffer) */
    void getSubData(int target, long offset, ShortBuffer data);
    /**@see #getSubData(int, long, ByteBuffer) */
    void getSubData(int target, long offset, IntBuffer data);
    /**@see #getSubData(int, long, ByteBuffer) */
    void getSubData(int target, long offset, FloatBuffer data);
    /**@see #getSubData(int, long, ByteBuffer) */
    void getSubData(int target, long offset, DoubleBuffer data);
}
