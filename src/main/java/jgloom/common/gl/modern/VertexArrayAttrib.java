package jgloom.common.gl.modern;

import jgloom.common.gl.VertexArrayPointer;
import org.lwjgl.opengl.GL20;

/**
 * Uses {@link GL20#glVertexAttribPointer(int, int, int, boolean, int, long)}  (modern opengl) to define an array of
 * vertex data
 */
public class VertexArrayAttrib implements VertexArrayPointer {
    public int     index, size, type, stride;
    public long    pointer;
    public boolean normalized;

    /**
     * @param index      Specifies the index of the generic vertex attribute to be modified.
     * @param size       Specifies the number of components per generic vertex attribute. Must be 1, 2, 3, 4.
     *                   Additionally, the symbolic constant GL_BGRA is accepted by glVertexAttribPointer. The initial
     *                   value is 4.
     * @param type       Specifies the data type of each component in the array. The symbolic constants GL_BYTE,
     *                   GL_UNSIGNED_BYTE, GL_SHORT, GL_UNSIGNED_SHORT, GL_INT, and GL_UNSIGNED_INT are accepted by
     *                   glVertexAttribPointer and glVertexAttribIPointer. Additionally GL_HALF_FLOAT, GL_FLOAT,
     *                   GL_DOUBLE, GL_FIXED, GL_INT_2_10_10_10_REV, GL_UNSIGNED_INT_2_10_10_10_REV and
     *                   GL_UNSIGNED_INT_10F_11F_11F_REV are accepted by glVertexAttribPointer. GL_DOUBLE is also
     *                   accepted by glVertexAttribLPointer and is the only token accepted by the type parameter for
     *                   that function. The initial value is GL_FLOAT.
     * @param normalized For glVertexAttribPointer, specifies whether fixed-point data values should be normalized
     *                   (GL_TRUE) or converted directly as fixed-point values (GL_FALSE) when they are accessed.
     * @param stride     Specifies the byte offset between consecutive generic vertex attributes. If stride is 0, the
     *                   generic vertex attributes are understood to be tightly packed in the array. The initial value
     *                   is 0.
     * @param pointer    Specifies a offset of the first component of the first generic vertex attribute in the array in
     *                   the data store of the buffer currently bound to the GL_ARRAY_BUFFER target. The initial value
     *                   is 0.
     */
    public VertexArrayAttrib(int index, int size, int type, boolean normalized, int stride, long pointer){
        this.index      = index;
        this.size       = size;
        this.type       = type;
        this.normalized = normalized;
        this.stride     = stride;
        this.pointer    = pointer;
    }

    @Override
    public void enable() {
        GL20.glEnableVertexAttribArray(index);
        GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointer);
    }

    @Override
    public void disable() {
        GL20.glDisableVertexAttribArray(index);
    }

    /**
     *
     * @return
     */
    public int getIndex() {
        return index;
    }
}
