package joogl.glfw;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window
{
	private long glfwWindow = 0L;
	
	public static void genWindow(Window window, int width, int height, String title)
	{
		window.glfwWindow = glfwCreateWindow(width, height, title, NULL, NULL);
	}
}
