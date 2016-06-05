package jgloom.common.gl.legacy;

import jgloom.common.gl.VertexArrayPointer;
import org.lwjgl.opengl.GL11;

/**
 * Uses {@link GL11#glTexCoordPointer(int, int, int, long)} (legacy opengl) to define an array of vertex data
 */
public class TexCoordArrayPointer implements VertexArrayPointer {

    // Mutable because it doesn't really matter... :/
    public int size, type, stride;
    public long pointer;

    /**
     * Define an array of vertex data
     * @param size    Specifies the number of coordinates per array element. Must be 1, 2, 3, or 4. The initial value is
     *                4.
     * @param type    Specifies the data type of each texture coordinate. Symbolic constants GL_SHORT, GL_INT, GL_FLOAT,
     *                or GL_DOUBLE are accepted. The initial value is GL_FLOAT.
     * @param stride  Specifies the byte offset between consecutive texture coordinate sets. If stride is 0, the array
     *                elements are understood to be tightly packed. The initial value is 0.
     * @param pointer Specifies a pointer to the first coordinate of the first texture coordinate set in the array. The
     *                initial value is 0.
     */
    public TexCoordArrayPointer(int size, int type, int stride, long pointer){
        this.size    = size;
        this.type    = type;
        this.stride  = stride;
        this.pointer = pointer;
    }

    @Override
    public void enable() {
        GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        GL11.glTexCoordPointer(size, type, stride, pointer);
    }

    @Override
    public void disable(){
        GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
    }

    /**
     * Specifies the number of coordinates per array element. Must be 1, 2, 3, or 4. The initial value is 4.
     */
    public int getSize() {
        return size;
    }

    /**
     * Specifies the data type of each texture coordinate. Symbolic constants GL_SHORT, GL_INT, GL_FLOAT, or GL_DOUBLE
     * are accepted. The initial value is GL_FLOAT.
     */
    public int getType() {
        return type;
    }

    /**
     * Specifies the byte offset between consecutive texture coordinate sets. If stride is 0, the array elements are
     * understood to be tightly packed. The initial value is 0.
     */
    public int getStride() {
        return stride;
    }

    /**
     * Specifies a pointer to the first coordinate of the first texture coordinate set in the array. The initial value
     * is 0.
     */
    public long getPointer() {
        return pointer;
    }
}
