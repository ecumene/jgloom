package jgloom.common.gl;

import jgloom.gl.GLBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;

import java.nio.*;

/**
 * Buffer Objects are OpenGL Objects that store an array of unformatted memory allocated by the OpenGL context (aka: the
 * GPU). These can be used to store vertex data, pixel data retrieved from images or the framebuffer, and a variety of
 * other things.
 *
 * Use {@link GLBuffer} to create {@link jgloom.gl.GLBuffer} objects
 */

public class GLBufferContainer implements GLBuffer {
    private GLBuffer bufferInstance;

    /**
     * Creates a GLBuffer container for storing convenient buffer-specific functions
     * @param buffer The buffer to track
     */
    public GLBufferContainer(GLBuffer buffer){
        this.bufferInstance = buffer;
    }

    /**
     * Bind a named buffer object
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     */
    public void bind(int target){
        GL15.glBindBuffer(target, bufferInstance.getBuffer());
    }

    /**
     * After a buffer object is deleted, it has no contents, and its name is free for reuse (for example by
     * glGenBuffers). If a buffer object that is currently bound is deleted, the binding reverts to 0 (the absence of
     * any buffer object).
     */
    public void delete(){
        GL15.glDeleteBuffers(bufferInstance.getBuffer());
    }

    /**
     * Map a buffer object's data store
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     * @param access
     */
    public void map(int target, int access){
        GL15.glMapBuffer(target, access);
    }

    /**
     * @see #map(int, int)
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     */
    public void unmap(int target){
        GL15.glUnmapBuffer(target);
    }

    /**
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     * @param pname The parameter name
     * @return The parameter currently set to that name
     */
    public int getParameter(int target, int pname){
        return GL15.glGetBufferParameteri(target, pname);
    }

    /**
     * glBufferData and glNamedBufferData create a new data store for a buffer object. In case of glBufferData, the
     * buffer object currently bound to target is used. For glNamedBufferData, a buffer object associated with ID
     * specified by the caller in buffer will be used instead.
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     * @param size Specifies the size in bytes of the buffer object's new data.
     * @param usage Specifies the expected usage pattern of the data store. The symbolic constant must be
     *              GL_STREAM_DRAW, GL_STREAM_READ, GL_STREAM_COPY, GL_STATIC_DRAW, GL_STATIC_READ, GL_STATIC_COPY,
     *              GL_DYNAMIC_DRAW, GL_DYNAMIC_READ, or GL_DYNAMIC_COPY.
     */
    public void data(int target, long size, int usage){
        GL15.glBufferData(target, size, usage);
    }

    /**
     * @see GLBufferContainer#data(int, long, int)
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     * @param buffer The buffer to upload
     * @param usage Specifies the expected usage pattern of the data store. The symbolic constant must be
     *              GL_STREAM_DRAW, GL_STREAM_READ, GL_STREAM_COPY, GL_STATIC_DRAW, GL_STATIC_READ, GL_STATIC_COPY,
     *              GL_DYNAMIC_DRAW, GL_DYNAMIC_READ, or GL_DYNAMIC_COPY.
     */
    public void data(int target, ByteBuffer buffer, int usage){
        GL15.glBufferData(target, buffer, usage);
    }

    /** @see GLBufferContainer#data(int, ByteBuffer, int)  */
    public void data(int target, byte[] bytes, int usage){
        ByteBuffer buffer = BufferUtils.createByteBuffer(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        data(target, buffer, usage);
    }

    /**
     * @see GLBufferContainer#data(int, long, int)
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     * @param buffer The buffer to upload
     * @param usage Specifies the expected usage pattern of the data store. The symbolic constant must be
     *              GL_STREAM_DRAW, GL_STREAM_READ, GL_STREAM_COPY, GL_STATIC_DRAW, GL_STATIC_READ, GL_STATIC_COPY,
     *              GL_DYNAMIC_DRAW, GL_DYNAMIC_READ, or GL_DYNAMIC_COPY.
     */
    public void data(int target, FloatBuffer buffer, int usage){
        GL15.glBufferData(target, buffer, usage);
    }

    /** @see GLBufferContainer#data(int, FloatBuffer, int)  */
    public void data(int target, float[] floats, int usage){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(floats.length);
        buffer.put(floats);
        buffer.flip();
        data(target, buffer, usage);
    }

    /**
     * @see GLBufferContainer#data(int, long, int)
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     * @param buffer The buffer to upload
     * @param usage Specifies the expected usage pattern of the data store. The symbolic constant must be
     *              GL_STREAM_DRAW, GL_STREAM_READ, GL_STREAM_COPY, GL_STATIC_DRAW, GL_STATIC_READ, GL_STATIC_COPY,
     *              GL_DYNAMIC_DRAW, GL_DYNAMIC_READ, or GL_DYNAMIC_COPY.
     */
    public void data(int target, ShortBuffer buffer, int usage){
        GL15.glBufferData(target, buffer, usage);
    }

    /** @see GLBufferContainer#data(int, ShortBuffer, int)  */
    public void data(int target, short[] shorts, int usage){
        ShortBuffer buffer = BufferUtils.createShortBuffer(shorts.length);
        buffer.put(shorts);
        buffer.flip();
        data(target, buffer, usage);
    }

    /**
     * @see GLBufferContainer#data(int, long, int)
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     * @param buffer The buffer to upload
     * @param usage Specifies the expected usage pattern of the data store. The symbolic constant must be
     *              GL_STREAM_DRAW, GL_STREAM_READ, GL_STREAM_COPY, GL_STATIC_DRAW, GL_STATIC_READ, GL_STATIC_COPY,
     *              GL_DYNAMIC_DRAW, GL_DYNAMIC_READ, or GL_DYNAMIC_COPY.
     */
    public void data(int target, DoubleBuffer buffer, int usage){
        GL15.glBufferData(target, buffer, usage);
    }

    /** @see GLBufferContainer#data(int, DoubleBuffer, int)  */
    public void data(int target, double[] doubles, int usage){
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(doubles.length);
        buffer.put(buffer);
        buffer.flip();
        data(target, buffer, usage);
    }

    /**
     * @see GLBufferContainer#data(int, long, int)
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     * @param buffer The buffer to upload
     * @param usage Specifies the expected usage pattern of the data store. The symbolic constant must be
     *              GL_STREAM_DRAW, GL_STREAM_READ, GL_STREAM_COPY, GL_STATIC_DRAW, GL_STATIC_READ, GL_STATIC_COPY,
     *              GL_DYNAMIC_DRAW, GL_DYNAMIC_READ, or GL_DYNAMIC_COPY.
     */
    public void data(int target, IntBuffer buffer, int usage){
        GL15.glBufferData(target, buffer, usage);
    }

    /** @see GLBufferContainer#data(int, IntBuffer, int)  */
    public void data(int target, int[] ints, int usage){
        IntBuffer buffer = BufferUtils.createIntBuffer(ints.length);
        buffer.put(ints);
        buffer.flip();
        data(target, buffer, usage);
    }

    /**
     * Creates and initializes a buffer object's immutable data store
     * @param flags Specifies the intended usage of the buffer's data store. Must be a bitwise combination of the
     *              following flags. GL_DYNAMIC_STORAGE_BIT, GL_MAP_READ_BIT, GL_MAP_WRITE_BIT, GL_MAP_PERSISTENT_BIT,
     *              GL_MAP_COHERENT_BIT, and GL_CLIENT_STORAGE_BIT.
     * @param size Specifies the size in bytes of the buffer object's new data store.
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     */
    public void storage(int target, long size, int flags){
        GL44.glBufferStorage(target, size, flags);
    }

    /**
     * Creates and initializes a buffer object's immutable data store
     * @param flags Specifies the intended usage of the buffer's data store. Must be a bitwise combination of the
     *              following flags. GL_DYNAMIC_STORAGE_BIT, GL_MAP_READ_BIT, GL_MAP_WRITE_BIT, GL_MAP_PERSISTENT_BIT,
     *              GL_MAP_COHERENT_BIT, and GL_CLIENT_STORAGE_BIT.
     * @param buffer The buffer to put in the storage's space
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     */
    public void storage(int target, ByteBuffer buffer, int flags){
        GL44.glBufferStorage(target, buffer, flags);
    }

    /** @see GLBufferContainer#storage(int, ByteBuffer, int) */
    public void storage(int target, byte[] bytes, int flags){
        ByteBuffer buffer = BufferUtils.createByteBuffer(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        storage(target, buffer, flags);
    }

    /**
     * Creates and initializes a buffer object's immutable data store
     * @param flags Specifies the intended usage of the buffer's data store. Must be a bitwise combination of the
     *              following flags. GL_DYNAMIC_STORAGE_BIT, GL_MAP_READ_BIT, GL_MAP_WRITE_BIT, GL_MAP_PERSISTENT_BIT,
     *              GL_MAP_COHERENT_BIT, and GL_CLIENT_STORAGE_BIT.
     * @param buffer The buffer to put in the storage's space
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     */
    public void storage(int target, ShortBuffer buffer, int flags){
        GL44.glBufferStorage(target, buffer, flags);
    }

    /** @see GLBufferContainer#storage(int, ShortBuffer, int) */
    public void storage(int target, short[] shorts, int flags){
        ShortBuffer buffer = BufferUtils.createShortBuffer(shorts.length);
        buffer.put(shorts);
        buffer.flip();
        storage(target, buffer, flags);
    }

    /**
     * Creates and initializes a buffer object's immutable data store
     * @param flags Specifies the intended usage of the buffer's data store. Must be a bitwise combination of the
     *              following flags. GL_DYNAMIC_STORAGE_BIT, GL_MAP_READ_BIT, GL_MAP_WRITE_BIT, GL_MAP_PERSISTENT_BIT,
     *              GL_MAP_COHERENT_BIT, and GL_CLIENT_STORAGE_BIT.
     * @param buffer The buffer to put in the storage's space
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     */
    public void storage(int target, FloatBuffer buffer, int flags){
        GL44.glBufferStorage(target, buffer, flags);
    }

    /** @see GLBufferContainer#storage(int, FloatBuffer, int) */
    public void storage(int target, float[] floats, int flags){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(floats.length);
        buffer.put(floats);
        buffer.flip();
        storage(target, buffer, flags);
    }

    /**
     * Creates and initializes a buffer object's immutable data store
     * @param flags Specifies the intended usage of the buffer's data store. Must be a bitwise combination of the
     *              following flags. GL_DYNAMIC_STORAGE_BIT, GL_MAP_READ_BIT, GL_MAP_WRITE_BIT, GL_MAP_PERSISTENT_BIT,
     *              GL_MAP_COHERENT_BIT, and GL_CLIENT_STORAGE_BIT.
     * @param buffer The buffer to put in the storage's space
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     */
    public void storage(int target, IntBuffer buffer, int flags){
        GL44.glBufferStorage(target, buffer, flags);
    }

    /** @see GLBufferContainer#storage(int, IntBuffer, int) */
    public void storage(int target, int[] ints, int flags){
        IntBuffer buffer = BufferUtils.createIntBuffer(ints.length);
        buffer.put(ints);
        buffer.flip();
        storage(target, buffer, flags);
    }

    /**
     * Creates and initializes a buffer object's immutable data store
     * @param flags Specifies the intended usage of the buffer's data store. Must be a bitwise combination of the
     *              following flags. GL_DYNAMIC_STORAGE_BIT, GL_MAP_READ_BIT, GL_MAP_WRITE_BIT, GL_MAP_PERSISTENT_BIT,
     *              GL_MAP_COHERENT_BIT, and GL_CLIENT_STORAGE_BIT.
     * @param buffer The buffer to put in the storage's space
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     */
    public void storage(int target, DoubleBuffer buffer, int flags){
        GL44.glBufferStorage(target, buffer, flags);
    }

    /** @see GLBufferContainer#storage(int, DoubleBuffer, int) */
    public void storage(int target, double[] doubles, int flags){
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(doubles.length);
        buffer.put(doubles);
        buffer.flip();
        storage(target, buffer, flags);
    }

    /**
     * Returns a subset of a buffer object's data store.
     *
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     * @param offset the offset into the buffer object's data store from which data will be returned, measured in bytes
     * @return The buffer
     */
    public ByteBuffer getSubByte(int target, long offset){
        ByteBuffer buffer = null;
        GL15.glGetBufferSubData(target, offset, buffer);
        return buffer;
    }

    /**
     * Returns a subset of a buffer object's data store.
     *
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     * @param offset the offset into the buffer object's data store from which data will be returned, measured in bytes
     * @return The buffer
     */
    public FloatBuffer getSubFloat(int target, long offset){
        FloatBuffer buffer = null;
        GL15.glGetBufferSubData(target, offset, buffer);
        return buffer;
    }

    /**
     * Returns a subset of a buffer object's data store.
     *
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     * @param offset the offset into the buffer object's data store from which data will be returned, measured in bytes
     * @return The buffer
     */
    public ShortBuffer getSubShort(int target, long offset){
        ShortBuffer buffer = null;
        GL15.glGetBufferSubData(target, offset, buffer);
        return buffer;
    }

    /**
     * Returns a subset of a buffer object's data store.
     *
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     * @param offset the offset into the buffer object's data store from which data will be returned, measured in bytes
     * @return The buffer
     */
    public DoubleBuffer getSubDouble(int target, long offset){
        DoubleBuffer buffer = null;
        GL15.glGetBufferSubData(target, offset, buffer);
        return buffer;
    }

    /**
     * Returns a subset of a buffer object's data store.
     *
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     * @param offset the offset into the buffer object's data store from which data will be returned, measured in bytes
     * @return The buffer
     */
    public IntBuffer getSubInt(int target, long offset){
        IntBuffer buffer = null;
        GL15.glGetBufferSubData(target, offset, buffer);
        return buffer;
    }

    /**
     * glBufferSubData redefines some or all of the data store for the buffer object currently bound to target. Data
     * starting at byte offset offset and extending for size bytes is copied to the data store from the memory pointed
     * to by data. An error is thrown if offset and size together define a range beyond the bounds of the buffer
     * objects's data store.
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER, or GL_PIXEL_UNPACK_BUFFER
     * @param offset Specifies the offset into the buffer object's data store where data replacement will begin,
     *               measured in bytes
     * @param data Specifies a pointer to the new data that will be copied into the data store
     */
    public void sub(int target, int offset, ByteBuffer data){
        GL15.glBufferSubData(target, offset, data);
    }

    /** Convenience version of GLBufferContainer{@link #sub(int, int, ByteBuffer)}*/
    public void sub(int target, int offset, byte[] data){
        ByteBuffer buffer = BufferUtils.createByteBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        GL15.glBufferSubData(target, offset, buffer);
    }

    /** ShortBuffer version of GLBufferContainer{@link #sub(int, int, ByteBuffer)}*/
    public void sub(int target, int offset, ShortBuffer data){
        GL15.glBufferSubData(target, offset, data);
    }

    /** Convenience version of GLBufferContainer{@link #sub(int, int, ShortBuffer)} */
    public void sub(int target, int offset, short[] data){
        ShortBuffer buffer = BufferUtils.createShortBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        GL15.glBufferSubData(target, offset, buffer);
    }

    /** FloatBuffer version of GLBufferContainer{@link #sub(int, int, ByteBuffer)}*/
    public void sub(int target, int offset, FloatBuffer data){
        GL15.glBufferSubData(target, offset, data);
    }

    /** Convenience version of GLBufferContainer{@link #sub(int, int, FloatBuffer)} */
    public void sub(int target, int offset, float[] data){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        GL15.glBufferSubData(target, offset, buffer);
    }

    /** IntBuffer version of GLBufferContainer{@link #sub(int, int, ByteBuffer)}*/
    public void sub(int target, int offset, IntBuffer data){
        GL15.glBufferSubData(target, offset, data);
    }

    /** Convenience version of GLBufferContainer{@link #sub(int, int, IntBuffer)} */
    public void sub(int target, int offset, int[] data){
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        GL15.glBufferSubData(target, offset, buffer);
    }

    /** DoubleBuffer version of GLBufferContainer{@link #sub(int, int, ByteBuffer)}*/
    public void sub(int target, int offset, DoubleBuffer data){
        GL15.glBufferSubData(target, offset, data);
    }

    /** Convenience version of GLBufferContainer{@link #sub(int, int, DoubleBuffer)} */
    public void sub(int target, int offset, double[] data){
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        GL15.glBufferSubData(target, offset, buffer);
    }

    @Override
    public int getBuffer(){
        return bufferInstance.getBuffer();
    }

    /**
     * @return The container's buffer JGLOOm object instance
     */
    public GLBuffer getBufferInstance() {
        return bufferInstance;
    }
}
