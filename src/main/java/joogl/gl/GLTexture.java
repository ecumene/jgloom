package joogl.gl;

/**
 * A texture is an OpenGL Object that contains one or more images that all have the same image format. A texture can be
 * used in two ways. It can be the source of a texture access from a Shader, or it can be used as a render target.
 */
public interface GLTexture {
    /** @return The texture's identifier */
    public int getTexture();
}
