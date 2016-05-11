package joogl.glfw;

import static org.lwjgl.glfw.GLFW.*;

import joogl.GLError;

public class GLFWContext
{
	public static final int CONTEXT_VERSION_MAJOR = 3;
	public static final int CONTEXT_VERSION_MINOR = 2;
	
	public static void createContext()
	{
		if (glfwInit() != GLFW_TRUE)
			throw new GLError("GLFW failed to init.");
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, CONTEXT_VERSION_MAJOR);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, CONTEXT_VERSION_MINOR);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
	}
	
	public static void destroyContext()
	{
		glfwTerminate();
	}
}
