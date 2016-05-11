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
		// Create GLFW context
		GLFWContext.createContext();
		// Create a test window
		window1 = new Window();
		Window.genWindow(window1, 800, 450, "Window 1", true);
		window2 = new Window();
		Window.genWindow(window2, 800, 450, "Window 2", true);
		// Create GL context
		GLContext.createContext();
		// Track switched colors
		boolean flop = false;
		
		// Maintain test window
		while (!Window.shouldWindowClose(window1) && 
				!Window.shouldWindowClose(window2))
		{
			// Draw red or green to window 1 depending on flop
			Window.makeWindowCurrent(window1);
			if (flop)
				glClearColor(1, 0, 0, 1);
			else
				glClearColor(0, 1, 0, 1);
			glClear(GL_COLOR_BUFFER_BIT);
			Window.updateWindow(window1);
			
			// Draw green or red to window 2 depending on flop
			Window.makeWindowCurrent(window2);
			if (flop)
				glClearColor(0, 1, 0, 1);
			else
				glClearColor(1, 0, 0, 1);
			glClear(GL_COLOR_BUFFER_BIT);
			Window.updateWindow(window2);
			
			// Switch flop and sleep (not recommended)
			try
			{
				flop = !flop;
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		// Destroy test window
		Window.destroyWindow(window1);
		Window.destroyWindow(window2);
		// Destroy GLFW and GL contexts
		GLFWContext.destroyContext();
		GLContext.destroyContext();
	}
}
