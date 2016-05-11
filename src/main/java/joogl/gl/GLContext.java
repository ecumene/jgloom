package joogl.gl;

import org.lwjgl.opengl.GL;

/**
 * Creates and destroys the GL context
 */
public class GLContext
{
	/**
	 * Creates the GL context
	 */
	public static void createContext()
	{
		GL.createCapabilities();
	}
	
	/**
	 * Destroys the GL context
	 */
	public static void destroyContext()
	{
		GL.destroy();
	}
}
