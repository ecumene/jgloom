package joogl;

import static org.lwjgl.opengl.GL11.*;

import org.junit.Test;

import joogl.gl.GLContext;
import joogl.glfw.GLFWContext;
import joogl.glfw.Window;

public class TestGLMultiWindow
{
	// Test windows for testing
	public Window window1;
	public Window window2;
	
	@Test
	public void testGLMultiWindow()
	{
		// Creates GLFW context
		GLFWContext.createContext();
		// Creates a test window
		window1 = new Window();
		Window.genWindow(window1, 800, 450, "Window 1", true);
		window2 = new Window();
		Window.genWindow(window2, 800, 450, "Window 2", true);
		// Creates GL context
		GLContext.createContext();
		// Tracks switched colors
		boolean flop = false;
		
		// Maintains test window
		while (!Window.shouldWindowClose(window1) && 
				!Window.shouldWindowClose(window2))
		{
			Window.makeWindowCurrent(window1);
			if (flop)
				glClearColor(1, 0, 0, 1);
			else
				glClearColor(0, 1, 0, 1);
			glClear(GL_COLOR_BUFFER_BIT);
			Window.updateWindow(window1);
			
			Window.makeWindowCurrent(window2);
			if (flop)
				glClearColor(0, 1, 0, 1);
			else
				glClearColor(1, 0, 0, 1);
			glClear(GL_COLOR_BUFFER_BIT);
			Window.updateWindow(window2);
			
			try
			{
				flop = !flop;
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		// Destroys test window
		Window.destroyWindow(window1);
		Window.destroyWindow(window2);
		// Destroys GLFW and GL contexts
		GLFWContext.destroyContext();
		GLContext.destroyContext();
	}
}
