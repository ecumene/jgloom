package joogl.glfw;

import static org.lwjgl.glfw.GLFW.*;

import joogl.errors.GLError;

/**
 * Creates and destroys the GLFW context
 */
public class GLFWContext
{
	/**
	 * Major version of GL context
	 */
	public static final int CONTEXT_VERSION_MAJOR = 3;
	
	/**
	 * Minor version of GL context
	 */
	public static final int CONTEXT_VERSION_MINOR = 2;
	
	/**
	 * Creates the GLFW context
	 */
	public static void createContext()
	{
		if (glfwInit() != GLFW_TRUE)
			throw new GLError("GLFW failed to init.");
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, CONTEXT_VERSION_MAJOR);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, CONTEXT_VERSION_MINOR);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
	}
	
	/**
	 * Destroys the GLFW context
	 */
	public static void destroyContext()
	{
		glfwTerminate();
	}
}
