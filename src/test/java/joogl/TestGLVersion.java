package joogl;

import org.junit.Test;
import org.lwjgl.opengl.GL11;

import joogl.gl.GLContext;
import joogl.glfw.GLFWContext;
import joogl.glfw.Window;

public class TestGLVersion
{
	@Test
	public void testGLVersion() throws JOOGLException
	{
		GLFWContext.createContext();
		Window temp = new Window();
		Window.genWindow(temp, 20, 20, "Temp", false);
		GLContext.createContext();
		GLVersion version = new GLVersion(GLPlatform.OpenGL, GL11.glGetString(GL11.GL_VERSION),
				GL11.glGetString(GL11.GL_VENDOR), GL11.glGetString(GL11.GL_RENDERER));
		System.out.println(version.getMajorVersion() + " " + version.getMinorVersion());
		Window.destroyWindow(temp);
		GLFWContext.destroyContext();
		GLContext.destroyContext();
	}
}
