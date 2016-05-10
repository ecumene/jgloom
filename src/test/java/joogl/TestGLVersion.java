package joogl;

import org.junit.Test;
import org.lwjgl.opengl.GL11;

import joogl.utils.ContextManager;

public class TestGLVersion {
    @Test
    public void testGLVersion() throws JOOGLException{
        ContextManager contextManager = new ContextManager();
        contextManager.createContext();
        GLVersion version = new GLVersion(GLPlatform.OpenGL, GL11.glGetString(GL11.GL_VERSION),
                GL11.glGetString(GL11.GL_VENDOR),
                GL11.glGetString(GL11.GL_RENDERER));
        System.out.println(version.getMajorVersion() + " " + version.getMinorVersion());
        contextManager.destroyContext();
    }
}
