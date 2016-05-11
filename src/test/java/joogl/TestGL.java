package joogl;

import org.junit.Test;

import joogl.gl.GLContext;
import joogl.glfw.GLFWContext;
import joogl.glfw.Window;

public class TestGL
{
	// Test window for testing
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
		// Value to increment during run
		long l = 0L;
		
		// Maintains test window
		while (!Window.shouldWindowClose(window))
		{
			Window.setWindowTitle(window, "" + l);
			Window.updateWindow(window);
			l ++;
		}
		
		// Destroys test window
		Window.destroyWindow(window);

		// Destroys GLFW and GL contexts
		GLFWContext.destroyContext();
		GLContext.destroyContext();
	}
}
