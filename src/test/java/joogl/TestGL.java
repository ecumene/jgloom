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
		// Create GLFW context
		GLFWContext.createContext();
		// Create a test window
		window = new Window();
		Window.genWindow(window, 800, 450, "Test Window", true);
		// Create GL context
		GLContext.createContext();
		// Value to increment during run
		long l = 0L;
		
		// Maintain test window
		while (!Window.shouldWindowClose(window))
		{
			// Set title to number and increment
			Window.setWindowTitle(window, "" + l);
			Window.updateWindow(window);
			l ++;
		}
		
		// Destroy test window
		Window.destroyWindow(window);
		// Destroy GLFW and GL contexts
		GLFWContext.destroyContext();
		GLContext.destroyContext();
	}
}
