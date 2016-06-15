package jgloom.common.gl;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL32;

import jgloom.gl.GLFramebuffer;
import jgloom.gl.GLRenderbuffer;
import jgloom.gl.GLTexture;

/**
 * A shell class containing functions for manipulating a given {@link GLFramebuffer}
 */
public class GLFramebufferContainer extends AbstractGLFramebuffer {

    /**
     * @param frameBufferInstance The framebuffer to track
     */
    public GLFramebufferContainer(GLFramebuffer frameBufferInstance)
    {
        super(frameBufferInstance);
    }

    @Override
    public void bind(int target){
        GL30.glBindFramebuffer(target, getFrameBuffer());
    }

    @Override
    public void attachTexture(int target, int attachment, int level, GLTexture texture) {
        GL32.glFramebufferTexture(target, attachment, texture.getTexture(), level);
    }

    @Override
    public void attachRenderBuffer(int target, int attachment, GLRenderbuffer renderBuffer) {
        // The 3rd is a symbolic constant (aka they added it to be cheeky)
        GL30.glFramebufferRenderbuffer(target, attachment, GL30.GL_RENDERBUFFER, renderBuffer.getRenderBuffer());
    }

    @Override
    public void setDrawBuffers(IntBuffer attachments) {
        GL20.glDrawBuffers(attachments);
    }

    /**
     * @see #setDrawBuffers(IntBuffer)
     */
    public void setDrawBuffers(int ... attachments) {
        IntBuffer drawBuffers = BufferUtils.createIntBuffer(attachments.length);
        drawBuffers.put(attachments);
        drawBuffers.flip();
        setDrawBuffers(drawBuffers);
    }

    @Override
    public int getStatus(int target) {
        return GL30.glCheckFramebufferStatus(target);
    }

    /**
     * Deletes the fbo
     */
    public void delete() {
        GL30.glDeleteFramebuffers(getFrameBuffer());
    }

}
