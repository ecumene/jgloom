package joogl;

import org.junit.Test;

import joogl.gl.GLContext;
import joogl.glfw.GLFWContext;
import joogl.glfw.Window;

public class TestGL
{
	// Context manager for window
	public Window window;
	
	@Test
	public void testGLUtils()
	{
		// Creates GLFW context
		GLFWContext.createContext();
		// Creates a test window
		window = new Window();
		Window.genWindow(window, 800, 450, "Test Window", true);
		// Creates GL context
		GLContext.createContext();
		
		// Maintains test window
		while (!Window.shouldWindowClose(window))
		{
			Window.updateWindow(window);
		}
		
		// Destroys test window
		Window.destroyWindow(window);

		// Destroys GLFW and GL contexts
		GLFWContext.destroyContext();
		GLContext.destroyContext();
	}
}
