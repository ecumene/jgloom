package jgloom.gl.functions.buffer;

import java.nio.*;

/**
 * Includes functions for OpenGL buffer objects
 * @see <a href="https://www.opengl.org/wiki/Buffer_Object"></a>
 */
public interface GLFBufferStorage extends GLFBuffer {
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
    void storage(int target, long size, int flags);
    /**@see #storage(int, long, int) */
    void storage(int target, ByteBuffer data, int flags);
    /**@see #storage(int, ByteBuffer, int) */
    void storage(int target, ShortBuffer data, int flags);
    /**@see #storage(int, ByteBuffer, int) */
    void storage(int target, IntBuffer data, int flags);
    /**@see #storage(int, ByteBuffer, int) */
    void storage(int target, FloatBuffer data, int flags);
    /**@see #storage(int, ByteBuffer, int) */
    void storage(int target, DoubleBuffer data, int flags);
}
