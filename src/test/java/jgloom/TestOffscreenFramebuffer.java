package jgloom;

import jgloom.lwjgl.gl.GLFramebufferContainer;
import jgloom.lwjgl.gl.GLRenderbufferContainer;
import jgloom.lwjgl.gl.GLTextureContainer;
import jgloom.concurrent.NonConcurrentTest;
import jgloom.concurrent.RunInThread;
import jgloom.gl.GLFramebuffer;
import jgloom.gl.GLRenderbuffer;
import jgloom.gl.GLTexture;
import org.junit.Test;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class TestOffscreenFramebuffer extends NonConcurrentTest {
    @Test
    @RunInThread

    public void testOffscreenFramebuffer() {
        TestGLUtil.openContext();
        GLTextureContainer      colorBufferContainer = new GLTextureContainer(GLTexture.createTexture());
        GLFramebufferContainer  frameBufferContainer = new GLFramebufferContainer(GLFramebuffer.createFrameBuffer());
        GLRenderbufferContainer depthBufferContainer = new GLRenderbufferContainer(GLRenderbuffer.createRenderBuffer());

        colorBufferContainer.bind(GL11.GL_TEXTURE_2D);
        colorBufferContainer.image2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, 100, 100, 0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, 0);
        colorBufferContainer.setParameter(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        colorBufferContainer.setParameter(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        // Bind the depth render buffer and add a depth component
        depthBufferContainer.bind();
        depthBufferContainer.storage(GL11.GL_DEPTH_COMPONENT, 100, 100);
        // Render a triangle to the standard framebuffer
        frameBufferContainer.bind(GL30.GL_FRAMEBUFFER);
        frameBufferContainer.attachRenderBuffer(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, depthBufferContainer);

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        GL11.glBegin(GL11.GL_TRIANGLES);
        GL11.glVertex2f(-1, -1);
        GL11.glVertex2f(1, -1);
        GL11.glVertex2f(0, 1);
        GL11.glEnd();

        // Switch back and clear
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        GL11.glBegin(GL11.GL_TRIANGLES);
        GL11.glVertex2f(-1, -1); GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f( 1, -1); GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f( 1,  1); GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(-1,  1); GL11.glTexCoord2f(0, 1);
        GL11.glEnd();

        colorBufferContainer.delete();
        frameBufferContainer.delete();
        depthBufferContainer.delete();

        TestGLUtil.closeContext();
    }
}
