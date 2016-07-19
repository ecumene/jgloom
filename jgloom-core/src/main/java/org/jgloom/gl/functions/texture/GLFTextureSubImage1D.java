package org.jgloom.gl.functions.texture;

import java.nio.*;

/**
 * A texture is an OpenGL Object that contains one or more images that all have the same image format. A texture can be
 * used in two ways. It can be the source of a texture access from a Shader, or it can be used as a render target.
 * This defines basic sub-image 1D functions for textures
 */
public interface GLFTextureSubImage1D extends GLFTexture {
    /**
     * <p><a href="http://www.opengl.org/sdk/docs/man/html/glTexImage1D.xhtml">OpenGL SDK Reference</a></p>
     *
     * @param target  the texture target. One of: GL_TEXTURE_1D TEXTURE_1D, GL_PROXY_TEXTURE_1D PROXY_TEXTURE_1D
     * @param level   the level-of-detail number
     * @param xoffset the sub-texture x-offset
     * @param width   the sub-texture width
     * @param format  the texel data format
     * @param type    the texel data type
     * @param pixels  the texel data
     */
    void subImage1D(int target, int level, int xoffset, int width, int format, int type, ByteBuffer pixels);
    /** Buffer object offset version of: {@link #subImage1D(int, int, int, int, int, int, ByteBuffer)} */
    void subImage1D(int target, int level, int xoffset, int width, int format, int type, long pixelsOffset);
    /** ShortBuffer version of: {@link #subImage1D(int, int, int, int, int, int, ByteBuffer)} */
    void subImage1D(int target, int level, int xoffset, int width, int format, int type, ShortBuffer pixels);
    /** IntBuffer version of: {@link #subImage1D(int, int, int, int, int, int, ByteBuffer)} */
    void subImage1D(int target, int level, int xoffset, int width, int format, int type, IntBuffer pixels);
    /** FloatBuffer version of: {@link #subImage1D(int, int, int, int, int, int, ByteBuffer)} */
    void subImage1D(int target, int level, int xoffset, int width, int format, int type, FloatBuffer pixels);
    /** DoubleBuffer version of: {@link #subImage1D(int, int, int, int, int, int, ByteBuffer)} */
    void subImage1D(int target, int level, int xoffset, int width, int format, int type, DoubleBuffer pixels);
}
