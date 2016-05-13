package joogl;

import static org.lwjgl.opengl.GL11.*;

import joogl.glfw.GLFWWindow;
import joogl.glfw.GLFWWindowContainer;
import joogl.glfw.GLFWWindows;

import org.junit.Test;
import org.lwjgl.opengl.GLContext;

public class TestGLMultiWindow
{
	public GLFWWindow window1;
	public GLFWWindow window2;
	
	@Test
	public void testGLMultiWindow()
	{
		SharedLibraryLoader.load();
		GLFWWindows.init();
		window1 = new GLFWWindowContainer(GLFWWindows.createWindow(800, 450, "Window 1", 0L, 0L));
		GLFWWindows.makeContextCurrent(window1);
		GLContext.createFromCurrent();
		window2 = new GLFWWindowContainer(GLFWWindows.createWindow(800, 450, "Window 2", 0L, 0L));
		GLFWWindows.makeContextCurrent(window2);
		GLContext.createFromCurrent();

		GLFWWindows.makeContextCurrent(window1);
			glClearColor(1, 0, 0, 1);
		glClear(GL_COLOR_BUFFER_BIT);
		((GLFWWindowContainer) window1).swapBuffers();

		GLFWWindows.makeContextCurrent(window2);
			glClearColor(0, 1, 0, 1);
		glClear(GL_COLOR_BUFFER_BIT);
		((GLFWWindowContainer) window2).swapBuffers();

		((GLFWWindowContainer) window1).destroy();
		((GLFWWindowContainer) window2).destroy();
		GLFWWindows.terminate();
	}
}
