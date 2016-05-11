package joogl.gl;

import static org.lwjgl.opengl.GL11.*;

/**
 * Handles textures and texture binding
 */
public class Texture
{
	/**
	 * Pointer to the GL texture object
	 */
	private int texture;
	
	/**
	 * Creates an empty container
	 */
	public Texture() { }
	
	/**
	 * Creates a container for a texture
	 * @param texture Texture to "contain"
	 */
	public Texture(int texture)
	{
		this.texture = texture;
	}
	
	/**
	 * @return Pointer to the GL texture object
	 */
	public int getTexture()
	{
		return texture;
	}
	
	/**
	 * Binds the given texture
	 * @param texture Texture wrapper to bind
	 */
	public static void bindTexture(Texture texture)
	{
		glBindTexture(GL_TEXTURE_2D, texture.texture);
	}
	
	/**
	 * Releases all textures (binds 0)
	 */
	public static void releaseTextures()
	{
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	/**
	 * Deletes the given texture wrapper
	 * @param texture Texture wrapper to delete
	 */
	public static void destroyTexture(Texture texture)
	{
		glDeleteTextures(texture.texture);
	}
}
