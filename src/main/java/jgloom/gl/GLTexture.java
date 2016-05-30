package jgloom.gl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

/**
 * A texture is an OpenGL Object that contains one or more images that all have the same image format. A texture can be
 * used in two ways. It can be the source of a texture access from a Shader, or it can be used as a render target.
 */
public interface GLTexture {
    /** @return The texture's identifier */
    int getTexture();

    /** The OpenGL texture object identifier */
    int IDENTIFIER = GL11.GL_TEXTURE;

    /**
     * @return A constructed texture with glGenTextures
     */
    static GLTexture createTexture() {
        int texture = GL11.glGenTextures();
        return () -> texture;
    }

    /**
     * @param texture
     * @return if an integer corresponds to an OpenGL texture buffer object
     */
    static boolean isTexture(int texture){
        return GL11.glIsTexture(texture);
    }

}
