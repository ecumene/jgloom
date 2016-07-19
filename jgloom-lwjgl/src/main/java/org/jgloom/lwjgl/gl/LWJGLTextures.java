package org.jgloom.lwjgl.gl;

import org.jgloom.gl.GLTexture;
import org.lwjgl.opengl.GL11;

/**
 * A texture is an OpenGL Object that contains one or more images that all have the same image format. A texture can be
 * used in two ways. It can be the source of a texture access from a Shader, or it can be used as a render target.
 * @see <a href="https://www.opengl.org/wiki/Texture_Object">opengl.org - texture object</a>
 */
public class LWJGLTextures {
    /** The OpenGL texture object identifier */
    public static int IDENTIFIER = GL11.GL_TEXTURE;

    /**
     * @return A constructed texture with glGenTextures
     */
    public static GLTexture createTexture() {
        int texture = GL11.glGenTextures();
        return () -> texture;
    }

    /**
     * @param texture
     * @return if an integer corresponds to an OpenGL texture object
     */
    public static boolean isTexture(int texture){
        return GL11.glIsTexture(texture);
    }
}
