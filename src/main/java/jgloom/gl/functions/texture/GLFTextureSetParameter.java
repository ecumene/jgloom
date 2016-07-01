
package jgloom.gl.functions.texture;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * A texture is an OpenGL Object that contains one or more images that all have the same image format. A texture can be
 * used in two ways. It can be the source of a texture access from a Shader, or it can be used as a render target.
 */
public interface GLFTextureSetParameter extends GLFTexture {
    /**
     * @param target    the texture target
     * @param param     the parameter to set
     * @param parameter the parameter value
     */
    void setParameter(int target, int param, float parameter);
    /** Int version of {@link #setParameter(int, int, float)} */
    void setParameter(int target, int param, int parameter);
    /** Float buffer version of {@link #setParameter(int, int, float)} */
    void setParameter(int target, int param, FloatBuffer parameter);
    /** Int buffer version of {@link #setParameter(int, int, float)} */
    void setParameter(int target, int param, IntBuffer parameter);
}
