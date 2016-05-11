package joogl.gl;

import static org.lwjgl.opengl.GL11.*;

/**
 * Container for managing a texture
 */
public class Texture
{
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
	 * Binds the texture
	 */
	public void bind()
	{
		glBindTexture(GL_TEXTURE_2D, texture);
	}
	
	/**
	 * Releases the texture (binds 0)
	 */
	public void release()
	{
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	/**
	 * Deletes the contained texture
	 */
	public void destroy()
	{
		glDeleteTextures(texture);
	}
	
	/**
	 * Texture being managed by the container
	 * @return Texture pointer of texture
	 */
	public int getTexture()
	{
		return texture;
	}
}
