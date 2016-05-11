package joogl.utils;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL;

import joogl.GLError;
import joogl.SharedLibraryLoader;

/**
 * Handles a GLFW window and GL capabilities
 */
public class ContextManager
{
	private String title = "JOOGL Window";
	private int width = 800;
	private int height = 450;
	private boolean vSync = true;

	private IntBuffer widthBuffer, heightBuffer;
	private int lastWidth = 0;
	private int lastHeight = 0;
	private boolean created = false;
	private long glfwWindow = 0L;
	
	/**
	 * Creates a manager with default window settings
	 */
	public ContextManager()
	{
		ByteBuffer wB = ByteBuffer.allocateDirect(4);
		widthBuffer = wB.asIntBuffer();
		ByteBuffer hB = ByteBuffer.allocateDirect(4);
		heightBuffer = hB.asIntBuffer();
	}
	
	/**
	 * Creates a manager with given window settings
	 * @param title Title for the window
	 * @param width Starting width of the window
	 * @param height Starting height of the window
	 * @param vSync Whether to enable vertical sync
	 */
	public ContextManager(String title, int width, int height, boolean vSync)
	{
		this();
		this.title = title;
		this.width = width;
		this.height = height;
		this.vSync = vSync;
	}
	
	/**
	 * Creates a GLFW window and GL capabilities
	 * @throws GLError If GLFW context creation fails
	 */
	public void createContext() throws GLError
	{
		SharedLibraryLoader.load();
        if (glfwInit() != GLFW_TRUE)
            throw new GLError("Error creating GLFW context...");
        glfwWindow = glfwCreateWindow(width, height, title, NULL, NULL);
        if (glfwWindow == NULL)
            throw new GLError("Couldn't create GLFW window");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_SAMPLES, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwMakeContextCurrent(glfwWindow);

        GL.createCapabilities();
        glfwSwapInterval(vSync ? 1 : 0);
        created = true;
	}
	
	/**
	 * Updates window and local data
	 */
	public void update()
	{
		lastWidth = width;
		lastHeight = height;
		glfwGetWindowSize(glfwWindow, widthBuffer, heightBuffer);
		width = widthBuffer.get(0);
		height = heightBuffer.get(0);
		widthBuffer.clear();
		heightBuffer.clear();
		glfwSwapBuffers(glfwWindow);
		glfwPollEvents();
	}
	
	/**
	 * Tells GLFW that the window should be closed
	 */
	public void requestWindowClose()
	{
		glfwSetWindowShouldClose(glfwWindow, GLFW_TRUE);
	}
	
	/**
	 * Whether the display has been resized since the last frame
	 * @return If the last size does not equal the current size
	 */
	public boolean wasResized()
	{
		boolean result = (lastWidth != width) || (lastHeight != height);
		return result;
	}
	
	/**
	 * Destroys the GLFW window and GL capabilities
	 */
	public void destroyContext()
	{
		glfwDestroyWindow(glfwWindow);
		glfwTerminate();
		GL.destroy();
	}
	
	/**
	 * Whether the window has been requested to close
	 * @return If the window should close
	 */
	public boolean shouldWindowClose()
	{
		return glfwWindowShouldClose(glfwWindow) == GLFW_TRUE;
	}

	/**
	 * Current title of the window
	 * @return Private window title value
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Modifies the title of the window
	 * @param title New title for the window
	 */
	public void setTitle(String title)
	{
		this.title = title;
		if (created)
			glfwSetWindowTitle(glfwWindow, title);
	}

	/**
	 * Current width of the window
	 * @return Private window width value
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * Modifies the width of the window
	 * @param width New width for the window
	 */
	public void setWidth(int width)
	{
		this.width = width;
		if (created)
			glfwSetWindowSize(glfwWindow, width, height);
	}

	/**
	 * Current height of the window
	 * @return Private window height value
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * Modifies the height of the window
	 * @param height New height for the window
	 */
	public void setHeight(int height)
	{
		this.height = height;
		if (created)
			glfwSetWindowSize(glfwWindow, width, height);
	}

	/**
	 * Whether VSync is enabled for the window
	 * @return Private window VSync value
	 */
	public boolean isVSync()
	{
		return vSync;
	}

	/**
	 * Enables/Disables VSync depending on request
	 * @param vSync Whether VSync should be enabled
	 */
	public void setVSync(boolean vSync)
	{
		this.vSync = vSync;
		if (created)
			glfwSwapInterval(vSync ? 1 : 0);
	}

	/**
	 * GLFW window pointer value
	 * @return Private window pointer value
	 */
	public long getGLFWWindow()
	{
		return glfwWindow;
	}
}
