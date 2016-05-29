package jgloom;

import jgloom.common.gl.GLFrameBufferContainer;
import jgloom.common.gl.GLRenderBufferContainer;
import jgloom.common.gl.GLTextureContainer;
import jgloom.gl.GLFrameBuffer;
import jgloom.gl.GLRenderBuffer;
import jgloom.gl.GLTexture;
import org.junit.Test;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class TestOffscreenFramebuffer {
    @Test
    public void testGLSLProgram() {
        TestGLUtil.openContext();
        GLTextureContainer      colorBufferContainer = new GLTextureContainer(GLTexture.createTexture());
        GLFrameBufferContainer  frameBufferContainer = new GLFrameBufferContainer(GLFrameBuffer.createFrameBuffer());
        GLRenderBufferContainer depthBufferContainer = new GLRenderBufferContainer(GLRenderBuffer.createRenderBuffer());

        colorBufferContainer.bind(GL11.GL_TEXTURE_2D);
        colorBufferContainer.image2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, 100, 100, 0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, 0);
        colorBufferContainer.parameter(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        colorBufferContainer.parameter(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
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

        TestGLUtil.closeContext();
    }
}
