package jgloom.gl.functions.texture;

import java.nio.*;

/**
 * A texture is an OpenGL Object that contains one or more images that all have the same image format. A texture can be
 * used in two ways. It can be the source of a texture access from a Shader, or it can be used as a render target.
 * This defines basic sub-image 2D functions for textures
 */
public interface GLFTextureSubImage2D extends GLFTexture {
    /**
     * <p><a href="http://www.opengl.org/sdk/docs/man/html/glTexImage2D.xhtml">OpenGL SDK Reference</a></p>
     *
     * @param target  the texture target. One of: GL_TEXTURE_2D TEXTURE_2D, GL_TEXTURE_1D_ARRAY TEXTURE_1D_ARRAY, GL_TEXTURE_RECTANGLE TEXTURE_RECTANGLE, GL_TEXTURE_CUBE_MAP TEXTURE_CUBE_MAP, GL_PROXY_TEXTURE_2D PROXY_TEXTURE_2D, GL_PROXY_TEXTURE_1D_ARRAY PROXY_TEXTURE_1D_ARRAY, GL_PROXY_TEXTURE_RECTANGLE PROXY_TEXTURE_RECTANGLE, GL_PROXY_TEXTURE_CUBE_MAP PROXY_TEXTURE_CUBE_MAP
     * @param level   the level-of-detail number
     * @param xoffset the sub-texture xoffset
     * @param yoffset the sub-texture yoffset
     * @param width   the sub-texture width
     * @param height  the sub-texture height
     * @param format  the texel data format
     * @param type    the texel data type
     * @param pixels  the texel data
     */
    void subImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, ByteBuffer pixels);
    /** Buffer object offset version of: {@link #subImage2D(int, int, int, int, int, int, int, int, ByteBuffer)} */
    void subImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int border, int format, int type, long pixelsOffset);
    /** ShortBuffer version of: {@link #subImage2D(int, int, int, int, int, int, int, int, ByteBuffer)} */
    void subImage2D(int target, int level, int xoffset, int yoffset, int width, int height,int border, int format, int type, ShortBuffer pixels);
    /** IntBuffer version of: {@link #subImage2D(int, int, int, int, int, int, int, int, ByteBuffer)} */
    void subImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int border, int format, int type, IntBuffer pixels);
    /** FloatBuffer version of: {@link #subImage2D(int, int, int, int, int, int, int, int, ByteBuffer)} */
    void subImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int border, int format, int type, FloatBuffer pixels);
    /** DoubleBuffer version of: {@link #subImage2D(int, int, int, int, int, int, int, int, ByteBuffer)} */
    void subImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int border, int format, int type, DoubleBuffer pixels);
}
