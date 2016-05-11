package joogl.gl;

import org.lwjgl.opengl.GL;

public class GLContext
{
	public static void createContext()
	{
		GL.createCapabilities();
	}
	
	public static void destroyContext()
	{
		GL.destroy();
	}
}
