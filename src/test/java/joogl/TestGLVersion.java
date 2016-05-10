package joogl;

import org.junit.Test;
import org.lwjgl.opengl.GL11;

public class TestGLVersion {
    @Test
    public void testGLVersion() throws JOOGLException{
        TestGLUtil.openContext();
        GLVersion version = new GLVersion(GLPlatform.OpenGL, GL11.glGetString(GL11.GL_VERSION),
                GL11.glGetString(GL11.GL_VENDOR),
                GL11.glGetString(GL11.GL_RENDERER));
        System.out.println(version.getMajorVersion() + " " + version.getMinorVersion());
        TestGLUtil.closeContext();
    }
}
