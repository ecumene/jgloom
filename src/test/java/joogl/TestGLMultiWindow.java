package joogl;

import static org.lwjgl.opengl.GL11.*;

import joogl.glfw.GLFWWindow;
import joogl.glfw.GLFWWindowContainer;
import joogl.glfw.GLFWWindows;
import org.junit.Test;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLContext;

public class TestGLMultiWindow
{
	// Test windows for testing
	public GLFWWindow window1;
	public GLFWWindow window2;
	
	@Test
	public void testGLMultiWindow()
	{
		SharedLibraryLoader.load();
		GLFWWindows.init();
		// Create a test window
		window1 = new GLFWWindowContainer(GLFWWindows.createWindow(800, 450, "Window 1", 0L, 0L));
		GLFWWindows.makeContextCurrent(window1);
		GLContext.createFromCurrent();
		window2 = new GLFWWindowContainer(GLFWWindows.createWindow(800, 450, "Window 2", 0L, 0L));
		GLFWWindows.makeContextCurrent(window2);
		GLContext.createFromCurrent();
		// Track switched colors
		boolean flop = false;

		// Running while loops in JUnit tests are... Not fun. (it will spam you with GLFW windows)
		// Rendering to the screen a single frame and closing is good enough!

		// Draw red or green to window 1 depending on flop
		GLFWWindows.makeContextCurrent(window1);
		if (flop)
			glClearColor(1, 0, 0, 1);
		else
			glClearColor(0, 1, 0, 1);
		glClear(GL_COLOR_BUFFER_BIT);
		((GLFWWindowContainer) window1).swapBuffers();

		// Draw green or red to window 2 depending on flop
		GLFWWindows.makeContextCurrent(window2);
		if (flop)
			glClearColor(0, 1, 0, 1);
		else
			glClearColor(1, 0, 0, 1);
		glClear(GL_COLOR_BUFFER_BIT);
		((GLFWWindowContainer) window2).swapBuffers();

		// Destroy test window
		((GLFWWindowContainer) window1).destroy();
		((GLFWWindowContainer) window2).destroy();
		// Destroy GLFW and GL contexts
		GLFWWindows.terminate();
	}
}
