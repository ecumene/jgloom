package jgloom.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

/**
 * Utility methods for creating empty buffer, creating buffers from arrays, and decoding back to arrays
 */
public class BufferUtils {
    /**
     * Count of bytes taken up by one short
     */
    public static final int SHORT_BYTES = 2;
    
    /**
     * Count of bytes taken up by one integer
     */
    public static final int INT_BYTES = 4;
    
    /**
     * Count of bytes taken up by one long integer
     */
    public static final int LONG_BYTES = 8;
    
    /**
     * Count of bytes taken up by one floating point value
     */
    public static final int FLOAT_BYTES = 4;
    
    /**
     * Count of bytes taken up by one double precision floating point value
     */
    public static final int DOUBLE_BYTES = 8;
    
    /**
     * Count of bytes taken up by one character
     */
    public static final int CHAR_BYTES = 2;
    
    /**
     * @param capacity Capacity of the buffer to create
     * @return Empty {@link ByteBuffer} with the given capacity
     */
    public static ByteBuffer createByteBuffer(int capacity) {
        return ByteBuffer.allocateDirect(capacity);
    }
    
    /**
     * @param capacity Capacity of the buffer to create
     * @return Empty {@link ShortBuffer} with the given capacity
     */
    public static ShortBuffer createShortBuffer(int capacity) {
        return createByteBuffer(capacity * SHORT_BYTES).asShortBuffer();
    }
    
    /**
     * @param capacity Capacity of the buffer to create
     * @return Empty {@link IntBuffer} with the given capacity
     */
    public static IntBuffer createIntBuffer(int capacity) {
        return createByteBuffer(capacity * INT_BYTES).asIntBuffer();
    }
    
    /**
     * @param capacity Capacity of the buffer to create
     * @return Empty {@link LongBuffer} with the given capacity
     */
    public static LongBuffer createLongBuffer(int capacity) {
        return createByteBuffer(capacity * LONG_BYTES).asLongBuffer();
    }
    
    /**
     * @param capacity Capacity of the buffer to create
     * @return Empty {@link FloatBuffer} with the given capacity
     */
    public static FloatBuffer createFloatBuffer(int capacity) {
        return createByteBuffer(capacity * FLOAT_BYTES).asFloatBuffer();
    }
    
    /**
     * @param capacity Capacity of the buffer to create
     * @return Empty {@link DoubleBuffer} with the given capacity
     */
    public static DoubleBuffer createDoubleBuffer(int capacity) {
        return createByteBuffer(capacity * DOUBLE_BYTES).asDoubleBuffer();
    }
    
    /**
     * @param capacity Capacity of the buffer to create
     * @return Empty {@link CharBuffer} with the given capacity
     */
    public static CharBuffer createCharBuffer(int capacity) {
        return createByteBuffer(capacity * CHAR_BYTES).asCharBuffer();
    }
}
