package joogl.glfw;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Handles operations related to GLFW windows
 */
public class Window
{
	/**
	 * Pointer to the GLFW window object
	 */
	private long glfwWindow = 0L;

	/**
	 * @return Pointer to the GLFW window object
	 */
	public long getGLFWWindow()
	{
		return glfwWindow;
	}
	
	/**
	 * Buffer used to read window widths
	 */
	private static IntBuffer widthBuffer;
	
	/**
	 * Buffer used to read window heights
	 */
	private static IntBuffer heightBuffer;
	
	/**
	 * Creates buffers for reading size
	 */
	static
	{
		widthBuffer = ByteBuffer.allocateDirect(4).asIntBuffer();
		heightBuffer = ByteBuffer.allocateDirect(4).asIntBuffer();
	}
	
	/**
	 * Creates and opens a GLFW window object
	 * @param window Window wrapper to initialize
	 * @param width Width for the created window
	 * @param height Height for the created window
	 * @param title Title for the created window
	 * @param vSync Whether to enable VSync
	 */
	public static void genWindow(Window window, int width, int height, String title, boolean vSync)
	{
		window.glfwWindow = glfwCreateWindow(width, height, title, NULL, NULL);
		glfwMakeContextCurrent(window.glfwWindow);
		glfwSwapInterval(vSync ? 1 : 0);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
	}
	
	/**
	 * Makes the given window the current window
	 * @param window Window wrapper to make current
	 */
	public static void makeWindowCurrent(Window window)
	{
		glfwMakeContextCurrent(window.glfwWindow);
	}
	
	/**
	 * Updates the GLFW window and polls for input
	 * @param window Window wrapper to update
	 */
	public static void updateWindow(Window window)
	{
		makeWindowCurrent(window);
		glfwSwapBuffers(window.glfwWindow);
		glfwPollEvents();
	}
	
	/**
	 * Tells the given window to close
	 * @param window Window to request close
	 */
	public static void requestWindowClose(Window window)
	{
		glfwSetWindowShouldClose(window.glfwWindow, GLFW_TRUE);
	}
	
	/**
	 * Destroys the GLFW window object
	 * @param window Window wrapper to destroy
	 */
	public static void destroyWindow(Window window)
	{
		glfwDestroyWindow(window.glfwWindow);
	}
	
	/**
	 * Finds the size of the given window
	 * @param window Window to check size of
	 * @param size Two element array to return values
	 */
	public static void getWindowSize(Window window, int[] size)
	{
		glfwGetWindowSize(window.glfwWindow, widthBuffer, heightBuffer);
		size[0] = widthBuffer.get(0);
		size[1] = heightBuffer.get(0);
		widthBuffer.clear();
		heightBuffer.clear();
	}
	
	/**
	 * Finds the width of the given window
	 * @param window Window to check width of
	 * @return Width of the given window
	 */
	public static int getWindowWidth(Window window)
	{
		int[] size = new int[2];
		getWindowSize(window, size);
		return size[0];
	}
	
	/**
	 * Finds the height of the given window
	 * @param window Window to check height of
	 * @return Height of the given window
	 */
	public static int getWindowHeight(Window window)
	{
		int[] size = new int[2];
		getWindowSize(window, size);
		return size[1];
	}
	
	/**
	 * Checks whether the given window should close
	 * @param window Window wrapper to check status of
	 * @return If the window is requested to close
	 */
	public static boolean shouldWindowClose(Window window)
	{
		return glfwWindowShouldClose(window.glfwWindow) == GLFW_TRUE;
	}
	
	/**
	 * Modifies the title of the given window
	 * @param window Window to modify title of
	 * @param title New title for the window
	 */
	public static void setWindowTitle(Window window, String title)
	{
		glfwSetWindowTitle(window.glfwWindow, title);
	}
	
	/**
	 * Modifies the dimensions of the given window
	 * @param window Window to modify dimensions of
	 * @param width New width for the window
	 * @param height New height for the window
	 */
	public static void setWindowSize(Window window, int width, int height)
	{
		glfwSetWindowSize(window.glfwWindow, width, height);
	}
	
	/**
	 * Enables or disables VSync
	 * @param vSync Whether VSync should be enabled
	 */
	public static void setVSync(boolean vSync)
	{
		glfwSwapInterval(vSync ? 1 : 0);
	}
}
