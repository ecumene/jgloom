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
    
    /**
     * @param array Contents of the buffer to create
     * @return Created {@link ByteBuffer} with the given data
     */
    public static ByteBuffer fromArray(byte[] array) {
        return (ByteBuffer) createByteBuffer(array.length).put(array).flip();
    }
    
    /**
     * @param array Contents of the buffer to create
     * @return Created {@link ShortBuffer} with the given data
     */
    public static ShortBuffer fromArray(short[] array) {
        return (ShortBuffer) createShortBuffer(array.length).put(array).flip();
    }
    
    /**
     * @param array Contents of the buffer to create
     * @return Created {@link IntBuffer} with the given data
     */
    public static IntBuffer fromArray(int[] array) {
        return (IntBuffer) createIntBuffer(array.length).put(array).flip();
    }
    
    /**
     * @param array Contents of the buffer to create
     * @return Created {@link LongBuffer} with the given data
     */
    public static LongBuffer fromArray(long[] array) {
        return (LongBuffer) createLongBuffer(array.length).put(array).flip();
    }
    
    /**
     * @param array Contents of the buffer to create
     * @return Created {@link FloatBuffer} with the given data
     */
    public static FloatBuffer fromArray(float[] array) {
        return (FloatBuffer) createFloatBuffer(array.length).put(array).flip();
    }
    
    /**
     * @param array Contents of the buffer to create
     * @return Created {@link DoubleBuffer} with the given data
     */
    public static DoubleBuffer fromArray(double[] array) {
        return (DoubleBuffer) createDoubleBuffer(array.length).put(array).flip();
    }
    
    /**
     * @param array Contents of the buffer to create
     * @return Created {@link CharBuffer} with the given data
     */
    public static CharBuffer fromArray(char[] array) {
        return (CharBuffer) createCharBuffer(array.length).put(array).flip();
    }
    
    /**
     * @param buffer {@link ByteBuffer} to decode data from
     * @return Array of data decoded from the buffer
     */
    public static byte[] fromBuffer(ByteBuffer buffer) {
        return buffer.array();
    }
    
    /**
     * @param buffer {@link ShortBuffer} to decode data from
     * @return Array of data decoded from the buffer
     */
    public static short[] fromBuffer(ShortBuffer buffer) {
        return buffer.array();
    }
    
    /**
     * @param buffer {@link IntBuffer} to decode data from
     * @return Array of data decoded from the buffer
     */
    public static int[] fromBuffer(IntBuffer buffer) {
        return buffer.array();
    }
    
    /**
     * @param buffer {@link LongBuffer} to decode data from
     * @return Array of data decoded from the buffer
     */
    public static long[] fromBuffer(LongBuffer buffer) {
        return buffer.array();
    }
    
    /**
     * @param buffer {@link FloatBuffer} to decode data from
     * @return Array of data decoded from the buffer
     */
    public static float[] fromBuffer(FloatBuffer buffer) {
        return buffer.array();
    }
    
    /**
     * @param buffer {@link DoubleBuffer} to decode data from
     * @return Array of data decoded from the buffer
     */
    public static double[] fromBuffer(DoubleBuffer buffer) {
        return buffer.array();
    }
    
    /**
     * @param buffer {@link CharBuffer} to decode data from
     * @return Array of data decoded from the buffer
     */
    public static char[] fromBuffer(CharBuffer buffer) {
        return buffer.array();
    }
}
