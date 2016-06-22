package jgloom.lwjgl.gl;

import jgloom.gl.GLTexture;
import jgloom.gl.functions.texture.GLFTextureImage1D;
import jgloom.gl.functions.texture.GLFTextureImage2D;
import jgloom.gl.functions.texture.GLFTextureSetParameter;

/**
 * A texture is an OpenGL Object that contains one or more images that all have the same image format. A texture can be
 * used in two ways. It can be the source of a texture access from a Shader, or it can be used as a render target.
 */
public abstract class AbstractGLTexture implements GLFTextureImage1D, GLFTextureImage2D, GLFTextureSetParameter {
    private GLTexture textureInstance;

    /**
     * Contains a single texture object to be manipulated by OpenGL
     * @param textureInstance The texture to contain
     */
    public AbstractGLTexture(GLTexture textureInstance){
        this.textureInstance = textureInstance;
    }

    @Override
    public int getTexture() {
        return textureInstance.getTexture();
    }

    /**@return The texture instance*/
    public GLTexture getTextureInstance() {
        return textureInstance;
    }
}
