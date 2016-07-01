package jgloom.gl.functions.texture;

import jgloom.gl.GLTexture;
import org.lwjgl.opengl.*;

/**
 * A texture is an OpenGL Object that contains one or more images that all have the same image format. A texture can be
 * used in two ways. It can be the source of a texture access from a Shader, or it can be used as a render target.
 */
public interface GLFTexture extends GLTexture {
    /**
     * <p><a href="http://www.opengl.org/sdk/docs/man/html/glBindTexture.xhtml">OpenGL SDK Reference</a></p>
     *
     * Binds the a texture to a texture target.
     *
     * <p>While a texture object is bound, GL operations on the target to which it is bound affect the bound object, and
     * queries of the target to which it is bound return state from the bound object. If texture mapping of the
     * dimensionality of the target to which a texture object is bound is enabled, the state of the bound texture object
     * directs the texturing operation.</p>
     *
     * @param target  the texture target. One of:<br>{@link GL11#GL_TEXTURE_1D TEXTURE_1D}, {@link GL11#GL_TEXTURE_2D
     *                TEXTURE_2D}, {@link GL30#GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY}, {@link GL31#GL_TEXTURE_RECTANGLE
     *                TEXTURE_RECTANGLE}, {@link GL13#GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP}, {@link GL12#GL_TEXTURE_3D
     *                TEXTURE_3D}, {@link GL30#GL_TEXTURE_2D_ARRAY TEXTURE_2D_ARRAY}, {@link
     *                GL40#GL_TEXTURE_CUBE_MAP_ARRAY TEXTURE_CUBE_MAP_ARRAY}, {@link GL31#GL_TEXTURE_BUFFER
     *                TEXTURE_BUFFER}, {@link GL32#GL_TEXTURE_2D_MULTISAMPLE TEXTURE_2D_MULTISAMPLE}, {@link
     *                GL32#GL_TEXTURE_2D_MULTISAMPLE_ARRAY TEXTURE_2D_MULTISAMPLE_ARRAY}
     */
    void bind(int target);
    /**
     * Deletes the texture. After a texture is deleted, it has no contents or dimensionality, and its name is free for
     * reuse (for example by glGenTextures). If a texture that is currently bound is deleted, the binding reverts to 0
     * (the default texture).
     */
    void delete();
}
