package jgloom.io.images;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.lwjgl.opengl.GL11;

import jgloom.TestGLUtil;
import jgloom.common.gl.GLTextureContainer;
import jgloom.gl.GLTexture;
import jgloom.glfw.GLFWWindow;

/**
 * Tests loading the 2x2 PNG texture and rendering a quad with it
 */
public class TestTexturing {
    @Test
    public void testTexturedQuad() throws IOException {
        TestGLUtil.openContext();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        InputStream in = TestImageDecoder.class.getClassLoader().getResourceAsStream("textures/test/tiny/2x2.png");
        GLTexture loaded = TextureLoader.loadTexture(in);
        GLTextureContainer cont = new GLTextureContainer(loaded);
        cont.setParameter(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        cont.setParameter(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
        cont.setParameter(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        cont.setParameter(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        for (int i = 0; i < 1000; i++) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            cont.bind(GL11.GL_TEXTURE_2D);
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2f(0, 1);
            GL11.glVertex2f(-1, -1);
            GL11.glTexCoord2f(1, 1);
            GL11.glVertex2f(1, -1);
            GL11.glTexCoord2f(1, 0);
            GL11.glVertex2f(1, 1);
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex2f(-1, 1);
            GL11.glEnd();

            TestGLUtil.getWindow().swapBuffers();
            GLFWWindow.pollEvents();
        }

        TestGLUtil.closeContext();
    }
}
