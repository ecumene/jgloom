package jgloom.gl.functions.buffer;

import java.nio.*;

/**
 * Includes functions for OpenGL buffer objects
 * @see <a href="https://www.opengl.org/wiki/Buffer_Object"></a>
 */
public interface GLFBufferSubData extends GLFBuffer{
    /**
     * Updates a subset of a buffer object's data store
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER or
     *               GL_ELEMENT_ARRAY_BUFFER.
     * @param offset Specifies the offset into the buffer object's data store where data replacement will begin,
     *               measured in bytes.
     * @param data   Specifies a pointer to the new data that will be copied into the data store.
     */
    void subData(int target, long offset, ByteBuffer data);
    /**@see #subData(int, long, ByteBuffer) */
    void subData(int target, long offset, ShortBuffer data);
    /**@see #subData(int, long, ByteBuffer) */
    void subData(int target, long offset, IntBuffer data);
    /**@see #subData(int, long, ByteBuffer) */
    void subData(int target, long offset, FloatBuffer data);
    /**@see #subData(int, long, ByteBuffer) */
    void subData(int target, long offset, DoubleBuffer data);
}
