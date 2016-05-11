package joogl.glfw;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

// import java.nio.ByteBuffer;
// import java.nio.IntBuffer;

public class Window
{
	private long glfwWindow = 0L;
	
//	private static IntBuffer widthBuffer, heightBuffer;
	
	static
	{
//		widthBuffer = ByteBuffer.allocateDirect(4).asIntBuffer();
//		heightBuffer = ByteBuffer.allocateDirect(4).asIntBuffer();
	}
	
	public static void genWindow(Window window, int width, int height, String title, boolean vSync)
	{
		window.glfwWindow = glfwCreateWindow(width, height, title, NULL, NULL);
		glfwMakeContextCurrent(window.glfwWindow);
		glfwSwapInterval(vSync ? 1 : 0);
	}
	
	public static void makeWindowCurrent(Window window)
	{
		glfwMakeContextCurrent(window.glfwWindow);
	}
	
	public static void updateWindow(Window window)
	{
		makeWindowCurrent(window);
		glfwSwapBuffers(window.glfwWindow);
		glfwPollEvents();
	}
	
	public static void destroyWindow(Window window)
	{
		glfwDestroyWindow(window.glfwWindow);
	}
	
	public static boolean shouldWindowClose(Window window)
	{
		return glfwWindowShouldClose(window.glfwWindow) == GLFW_TRUE;
	}
}
