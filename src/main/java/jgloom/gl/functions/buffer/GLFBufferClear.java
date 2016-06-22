package jgloom.gl.functions.buffer;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

/**
 * Includes functions for OpenGL buffer objects
 * @see <a href="https://www.opengl.org/wiki/Buffer_Object"></a>
 */
public interface GLFBufferClear extends GLFBuffer {
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
    void clear(int target, int internalformat, int format, int type, ByteBuffer data);
    /**@see #clear(int, int, int, int, ByteBuffer) */
    void clear(int target, int internalformat, int format, int type, ShortBuffer data);
    /**@see #clear(int, int, int, int, ByteBuffer) */
    void clear(int target, int internalformat, int format, int type, IntBuffer data);
    /**@see #clear(int, int, int, int, ByteBuffer) */
    void clear(int target, int internalformat, int format, int type, FloatBuffer data);
}
