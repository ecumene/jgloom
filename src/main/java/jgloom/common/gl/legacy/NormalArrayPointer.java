package jgloom.common.gl.legacy;

import jgloom.common.gl.VertexArrayPointer;
import org.lwjgl.opengl.GL11;

/**
 * Uses {@link GL11#glNormalPointer(int, int, long)} (legacy opengl) to define an array of vertex data
 */
public class NormalArrayPointer implements VertexArrayPointer {

    // Mutable because it doesn't really matter... :/
    public int type, stride;
    public long pointer;

    /**
     * @param type    Specifies the data type of each coordinate in the array. Symbolic constants GL_SHORT, GL_INT,
     *                GL_FLOAT, or GL_DOUBLE are accepted. The initial value is GL_FLOAT.
     * @param stride  Specifies the byte offset between consecutive vertices. If stride is 0, the vertices are
     *                understood to be tightly packed in the array. The initial value is 0.
     * @param pointer Specifies a pointer to the first coordinate of the first vertex in the array. The initial value is
     *                0
     */
    public NormalArrayPointer(int type, int stride, long pointer){
        this.type    = type;
        this.stride  = stride;
        this.pointer = pointer;
    }

    @Override
    public void enable() {
        GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
        GL11.glNormalPointer(type, stride, pointer);
    }

    @Override
    public void disable(){
        GL11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
    }

    /**
     * @return The array's type (Symbolic constants GL_SHORT, GL_INT, GL_FLOAT, or GL_DOUBLE are accepted. The initial
     *                           value is GL_FLOAT)
     */
    public int getType() {
        return type;
    }

    /**
     * @return Specifies the byte offset between consecutive vertices. If stride is 0, the vertices are
     *         understood to be tightly packed in the array. The initial value is 0.
     */
    public int getStride() {
        return stride;
    }

    /**
     * @return Specifies a pointer to the first coordinate of the first vertex in the array. The initial value is 0
     */
    public long getPointer() {
        return pointer;
    }
}
