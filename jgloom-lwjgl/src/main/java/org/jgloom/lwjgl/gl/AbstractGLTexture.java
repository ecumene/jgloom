
package org.jgloom.lwjgl.gl;

import org.jgloom.JgloomContainer;
import org.jgloom.gl.GLTexture;
import org.jgloom.gl.functions.texture.*;

/**
 * A texture is an OpenGL Object that contains one or more images that all have the same image format. A texture can be
 * used in two ways. It can be the source of a texture access from a Shader, or it can be used as a render target.
 */
public abstract class AbstractGLTexture extends JgloomContainer<GLTexture> implements GLFTextureImage1D, GLFTextureImage2D, GLFTextureImage3D,
        GLFTextureSetParameter, GLFTextureSubImage1D, GLFTextureSubImage2D, GLFTextureSubImage3D {
    /**
     * Contains a single texture object to be manipulated by OpenGL
     * @param textureInstance The texture to contain
     */
    public AbstractGLTexture(GLTexture textureInstance){
        super(textureInstance);
    }

    @Override
    public int getTexture() {
        return getInstance().getTexture();
    }
}
