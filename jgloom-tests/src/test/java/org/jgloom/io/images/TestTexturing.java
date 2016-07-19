package org.jgloom.io.images;

import java.io.IOException;

import org.jgloom.io.TestGLUtil;
import org.jgloom.io.resources.ClasspathResource;
import org.jgloom.io.resources.Resource;
import org.jgloom.lwjgl.LWJGLEnumStore;
import org.jgloom.lwjgl.gl.GLTextureContainer;
import org.jgloom.lwjgl.gl.LWJGLException;
import org.jgloom.lwjgl.gl.LWJGLTextures;
import org.jgloom.lwjgl.glfw.GLFWWindow;
import org.junit.Test;
import org.lwjgl.opengl.GL11;

/**
 * Tests loading the 2x2 PNG texture and rendering a quad with it
 */
public class TestTexturing {
    @Test
    public void testTexturedQuad() throws IOException, ReflectiveOperationException {
        TestGLUtil.openContext();

        LWJGLEnumStore.getInstance().store(TextureLoader.getInstance());

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        Resource testTexture = ClasspathResource.createClasspathResource("textures/tiny/2x2.png");
        GLTextureContainer textureContainer = new GLTextureContainer(LWJGLTextures.createTexture());
        textureContainer.bind(GL11.GL_TEXTURE_2D);
        TextureLoader.getInstance().loadTexture2D(textureContainer, testTexture);
        LWJGLException.checkOGL(); // < - Throws exception, enums are not set in TextureLoader
        testTexture.close();
        textureContainer.setParameter(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        textureContainer.setParameter(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
        textureContainer.setParameter(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        textureContainer.setParameter(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        for (int i = 0; i < 10; i++) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            textureContainer.bind(GL11.GL_TEXTURE_2D);
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
