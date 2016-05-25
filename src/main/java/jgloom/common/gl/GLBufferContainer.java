package jgloom.common.gl;

import jgloom.gl.GLBuffer;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL44;

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

    //TODO: Methods for taking in java primitive arrays and such

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

    /**
     * @see GLBufferContainer#data(int, long, int)
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     * @param buffer The buffer to upload
     * @param size Specifies the size in bytes of the buffer object's new data.
     * @param usage Specifies the expected usage pattern of the data store. The symbolic constant must be
     *              GL_STREAM_DRAW, GL_STREAM_READ, GL_STREAM_COPY, GL_STATIC_DRAW, GL_STATIC_READ, GL_STATIC_COPY,
     *              GL_DYNAMIC_DRAW, GL_DYNAMIC_READ, or GL_DYNAMIC_COPY.
     */
    public void data(int target, long size, ByteBuffer buffer, int usage){
        GL15.glBufferData(target, size, buffer, usage);
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

    /**
     * Creates and initializes a buffer object's immutable data store
     * @param flags Specifies the intended usage of the buffer's data store. Must be a bitwise combination of the
     *              following flags. GL_DYNAMIC_STORAGE_BIT, GL_MAP_READ_BIT, GL_MAP_WRITE_BIT, GL_MAP_PERSISTENT_BIT,
     *              GL_MAP_COHERENT_BIT, and GL_CLIENT_STORAGE_BIT.
     * @param buffer The buffer to put in the storage's space
     * @param size Specifies the size in bytes of the buffer object's new data.
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     */
    public void storage(int target, int size, ByteBuffer buffer, int flags){
        GL44.glBufferStorage(target, size, buffer, flags);
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
