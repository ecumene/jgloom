package jgloom;

import jgloom.gl.GLPlatform;
import jgloom.common.gl.GLVersion;
import org.junit.Test;
import org.lwjgl.opengl.GL11;

public class TestGLVersion {
    @Test
    public void testGLVersion() throws GLOOMException {
        TestGLUtil.openContext();
        @SuppressWarnings("unused")
        GLVersion version = new GLVersion(GLPlatform.OpenGL, GL11.glGetString(GL11.GL_VERSION),
                GL11.glGetString(GL11.GL_VENDOR), GL11.glGetString(GL11.GL_RENDERER));
        TestGLUtil.closeContext();
    }
}
