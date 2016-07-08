package jgloom.gl.functions.buffer;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

/**
 * Includes functions for OpenGL buffer objects
 * @see <a href="https://www.opengl.org/wiki/Buffer_Object"></a>
 */
public interface GLFBufferSubClear extends GLFBuffer {
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
    void subClear(int target, int internalformat, long offset, long size, int format, int type, ByteBuffer data);
    /**@see #subClear(int, int, long, long, int, int, ByteBuffer)  */
    void subClear(int target, int internalformat, long offset, long size, int format, int type, ShortBuffer data);
    /**@see #subClear(int, int, long, long, int, int, ByteBuffer)  */
    void subClear(int target, int internalformat, long offset, long size, int format, int type, IntBuffer data);
    /**@see #subClear(int, int, long, long, int, int, ByteBuffer)  */
    void subClear(int target, int internalformat, long offset, long size, int format, int type, FloatBuffer data);

}
