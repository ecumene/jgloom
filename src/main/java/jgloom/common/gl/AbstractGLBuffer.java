package jgloom.common.gl;

import jgloom.gl.GLBuffer;
import org.lwjgl.opengl.GL43;
import org.lwjgl.opengl.GL44;

import java.nio.*;

/**
 * Buffer Objects are OpenGL Objects that store an array of unformatted memory allocated by the OpenGL context (aka: the
 * GPU). These can be used to store vertex data, pixel data retrieved from images or the framebuffer, and a variety of
 * other things.
 *
 * Use {@link GLBuffer} to create {@link jgloom.gl.GLBuffer} objects
 */
// TODO: Add classes to the enums in documentation
public abstract class AbstractGLBuffer implements GLBuffer {
    private GLBuffer bufferInstance;

    /** @param buffer The buffer to track */
    public AbstractGLBuffer(GLBuffer buffer){
        bufferInstance = buffer;
    }

    /**
     * Bind a named buffer object
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ATOMIC_COUNTER_BUFFER, GL_COPY_READ_BUFFER, GL_COPY_WRITE_BUFFER, GL_DRAW_INDIRECT_BUFFER,
     *               GL_DISPATCH_INDIRECT_BUFFER, GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER,
     *               GL_PIXEL_UNPACK_BUFFER, GL_QUERY_BUFFER, GL_SHADER_STORAGE_BUFFER, GL_TEXTURE_BUFFER,
     *               GL_TRANSFORM_FEEDBACK_BUFFER, or GL_UNIFORM_BUFFER.
     */
    public abstract void bind(int target);
    /** Delete the buffer and free it's data store */
    public abstract void delete();

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
    public abstract void data(int target, long size, int usage);
    /**@see #data(int, long, int) */
    public abstract void data(int target, ByteBuffer data, int usage);
    /**@see #data(int, ByteBuffer, int)  */
    public abstract void data(int target, ShortBuffer data, int usage);
    /**@see #data(int, ByteBuffer, int)  */
    public abstract void data(int target, IntBuffer data, int usage);
    /**@see #data(int, ByteBuffer, int)  */
    public abstract void data(int target, FloatBuffer data, int usage);
    /**@see #data(int, ByteBuffer, int)  */
    public abstract void data(int target, DoubleBuffer data, int usage);

    /**
     * Much like immutable storage textures, the storage for buffer objects can be allocated immutably. When this is
     * done, you will be unable to reallocate that storage. You may still invalidate it with an explicit invalidation
     * command or through mapping the buffer. But you cannot do the glBufferData(..., NULL)​ trick to invalidate it if
     * the storage is immutable.
     *
     * @param target parameter is just like the one for glBindBuffer​; it says which bound buffer to modify. size​
     *               represents how many bytes you want to allocate in this buffer object
     * @param size   the size of the data store in basic machine units
     * @param flags  The flags​ field sets up a contract between you and OpenGL, describing how you may and may not
     *               access the contents of the buffer.
     */
    public abstract void storage(int target, long size, int flags);
    /**@see AbstractGLBuffer#storage(int, long, int) */
    public abstract void storage(int target, ByteBuffer data, int flags);
    /**@see AbstractGLBuffer#storage(int, ByteBuffer, int) */
    public abstract void storage(int target, ShortBuffer data, int flags);
    /**@see AbstractGLBuffer#storage(int, ByteBuffer, int) */
    public abstract void storage(int target, IntBuffer data, int flags);
    /**@see AbstractGLBuffer#storage(int, ByteBuffer, int) */
    public abstract void storage(int target, FloatBuffer data, int flags);
    /**@see AbstractGLBuffer#storage(int, ByteBuffer, int) */
    public abstract void storage(int target, DoubleBuffer data, int flags);

    /**
     * A buffer object's storage can be cleared, in part or in full, to a specific value. These functions work in a
     * similar fashion to Pixel Transfer operations
     * @param target         is just like the one for glBindBuffer​; it says which bound buffer to be cleared.
     * @param internalformat must be a sized Image Format, but only of the kind that can be used for buffer textures.
     *                       This defines how OpenGL will store the data in the buffer object. format​ and type​ operate
     *                       as normal for Pixel Transfer operations
     * @param format         the format of the data in memory addressed by {@code data}.
     * @param type           the type of the data in memory addressed by {@code data}.
     * @param data           the buffer containing the data to be used as the source of the constant fill value. The elements of data are converted by the GL into the format
     *                       specified by internalformat, and then used to fill the specified range of the destination buffer. If data is {@code NULL}, then it is ignored and the
     *                       sub-range of the buffer is filled with zeros.
     */
    public abstract void clear(int target, int internalformat, int format, int type, ByteBuffer data);
    /**@see #clear(int, int, int, int, ByteBuffer) */
    public abstract void clear(int target, int internalformat, int format, int type, ShortBuffer data);
    /**@see #clear(int, int, int, int, ByteBuffer) */
    public abstract void clear(int target, int internalformat, int format, int type, IntBuffer data);
    /**@see #clear(int, int, int, int, ByteBuffer) */
    public abstract void clear(int target, int internalformat, int format, int type, FloatBuffer data);

    /**
     * Fills all or part of buffer object's data store with a fixed value.
     * @param target         is just like the one for glBindBuffer​; it says which bound buffer to be cleared.
     * @param internalformat must be a sized Image Format, but only of the kind that can be used for buffer textures.
     *                       This defines how OpenGL will store the data in the buffer object. format​ and type​ operate
     *                       as normal for Pixel Transfer operations
     * @param offset         the offset, in basic machine units into the buffer object's data store at which to start filling
     * @param size           the size, in basic machine units of the range of the data store to fill
     * @param format         the format of the data in memory addressed by {@code data}.
     * @param type           the type of the data in memory addressed by {@code data}.
     * @param data           the buffer containing the data to be used as the source of the constant fill value. The elements of data are converted by the GL into the format
     *                       specified by internalformat, and then used to fill the specified range of the destination buffer. If data is {@code NULL}, then it is ignored and the
     *                       sub-range of the buffer is filled with zeros.
     */
    public abstract void subClear(int target, int internalformat, long offset, long size, int format, int type, ByteBuffer data);
    /**@see #subClear(int, int, long, long, int, int, ByteBuffer)  */
    public abstract void subClear(int target, int internalformat, long offset, long size, int format, int type, ShortBuffer data);
    /**@see #subClear(int, int, long, long, int, int, ByteBuffer)  */
    public abstract void subClear(int target, int internalformat, long offset, long size, int format, int type, IntBuffer data);
    /**@see #subClear(int, int, long, long, int, int, ByteBuffer)  */
    public abstract void subClear(int target, int internalformat, long offset, long size, int format, int type, FloatBuffer data);

    /**
     * Updates a subset of a buffer object's data store
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER or
     *               GL_ELEMENT_ARRAY_BUFFER.
     * @param offset Specifies the offset into the buffer object's data store where data replacement will begin,
     *               measured in bytes.
     * @param data   Specifies a pointer to the new data that will be copied into the data store.
     */
    public abstract void subData(int target, long offset, ByteBuffer data);
    /**@see #subData(int, long, ByteBuffer) */
    public abstract void subData(int target, long offset, ShortBuffer data);
    /**@see #subData(int, long, ByteBuffer) */
    public abstract void subData(int target, long offset, IntBuffer data);
    /**@see #subData(int, long, ByteBuffer) */
    public abstract void subData(int target, long offset, FloatBuffer data);
    /**@see #subData(int, long, ByteBuffer) */
    public abstract void subData(int target, long offset, DoubleBuffer data);

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
    public abstract void getSubData(int target, long offset, ByteBuffer data);
    /**@see #getSubData(int, long, ByteBuffer) */
    public abstract void getSubData(int target, long offset, ShortBuffer data);
    /**@see #getSubData(int, long, ByteBuffer) */
    public abstract void getSubData(int target, long offset, IntBuffer data);
    /**@see #getSubData(int, long, ByteBuffer) */
    public abstract void getSubData(int target, long offset, FloatBuffer data);
    /**@see #getSubData(int, long, ByteBuffer) */
    public abstract void getSubData(int target, long offset, DoubleBuffer data);

    /**
     * Map a buffer object's data store
     * @param target Specifies the target buffer object being mapped. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER, or GL_PIXEL_UNPACK_BUFFER.
     * @param access Specifies the access policy, indicating whether it will be possible to read from, write to, or both
     *               read from and write to the buffer object's mapped data store. The symbolic constant must be
     *               GL_READ_ONLY, GL_WRITE_ONLY, or GL_READ_WRITE.
     */
    public abstract void map(int target, int access);
    /**@see #map(int, int) */
    public abstract void map(int target, int access, ByteBuffer old_buffer);
    /**@see #map(int, int) */
    public abstract void map(int target, int access, long length, ByteBuffer old_buffer);

    /**
     * Relinquishes the mapping of a buffer object and invalidates the pointer to its data store
     * @param target Specifies the target buffer object being mapped. The symbolic constant must be GL_ARRAY_BUFFER,
     *               GL_ELEMENT_ARRAY_BUFFER, GL_PIXEL_PACK_BUFFER, or GL_PIXEL_UNPACK_BUFFER.
     */
    public abstract boolean unmap(int target);

    /**
     * Makes the buffer's contents undefined
     */
    public abstract void invalidate();
    /**
     * Makes the buffer's contents undefined
     * @param offset The beginning value
     * @param size   The size of the subrange
     */
    public abstract void invalidateRange(int offset, int size);

    /**
     * Return parameters of a buffer object
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER or
     *               GL_ELEMENT_ARRAY_BUFFER.
     * @param pname  Specifies the symbolic name of a buffer object parameter. Accepted values are GL_BUFFER_SIZE or
     *               GL_BUFFER_USAGE.
     * @return A buffer containing the buffer's parameters
     */
    public abstract IntBuffer getParameters(int target, int pname);

    /**
     * Return parameter of a buffer object
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER or
     *               GL_ELEMENT_ARRAY_BUFFER.
     * @param pname  Specifies the symbolic name of a buffer object parameter. Accepted values are GL_BUFFER_SIZE or
     *               GL_BUFFER_USAGE.
     * @return The parameter's value
     */
    public abstract int getParameter(int target, int pname);

    @Override
    public int getBuffer() {
        return bufferInstance.getBuffer();
    }

    public GLBuffer getBufferInstance() {
        return bufferInstance;
    }
}
