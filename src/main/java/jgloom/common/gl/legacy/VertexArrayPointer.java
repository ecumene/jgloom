package jgloom.common.gl.legacy;

import org.lwjgl.opengl.GL11;

/**
 * Uses {@link GL11#glVertexPointer(int, int, int, long)} (legacy opengl) to define an array of vertex data
 */
public class VertexArrayPointer implements jgloom.common.gl.VertexArrayPointer {

    // Mutable because it doesn't really matter... :/
    public int size, type, stride;
    public long pointer;

    /**
     * Define an array of vertex data
     * @param size    Specifies the number of coordinates per vertex. Must be 2, 3, or 4. The initial value is 4
     * @param type    Specifies the data type of each coordinate in the array. Symbolic constants GL_SHORT, GL_INT,
     *                GL_FLOAT, or GL_DOUBLE are accepted. The initial value is GL_FLOAT.
     * @param stride  Specifies the byte offset between consecutive vertices. If stride is 0, the vertices are
     *                understood to be tightly packed in the array. The initial value is 0.
     * @param pointer Specifies a pointer to the first coordinate of the first vertex in the array. The initial value is
     *                0
     */
    public VertexArrayPointer(int size, int type, int stride, long pointer){
        this.size    = size;
        this.type    = type;
        this.stride  = stride;
        this.pointer = pointer;
    }

    @Override
    public void enable() {
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glVertexPointer(size, type, stride, pointer);
    }

    @Override
    public void disable(){
        GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
    }

    /**
     * Specifies the number of coordinates per vertex. Must be 2, 3, or 4. The initial value is 4.
     * @return The pointer's size
     */
    public int getSize() {
        return size;
    }

    /**
     * Specifies the data type of each coordinate in the array. Symbolic constants GL_SHORT, GL_INT, GL_FLOAT, or
     * GL_DOUBLE are accepted. The initial value is GL_FLOAT.
     * @return The type of the vertex pointer
     */
    public int getType() {
        return type;
    }

    /**
     * Specifies the byte offset between consecutive vertices. If stride is 0, the vertices are understood to be tightly
     * packed in the array. The initial value is 0.
     * @return The vertex array's stride
     */
    public int getStride() {
        return stride;
    }

    /**
     * Specifies a pointer to the first coordinate of the first vertex in the array. The initial value is 0.
     * @return The vertex array's first value
     */
    public long getPointer() {
        return pointer;
    }
}
