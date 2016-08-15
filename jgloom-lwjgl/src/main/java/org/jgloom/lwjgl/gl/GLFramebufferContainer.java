package org.jgloom.lwjgl.gl;

import java.nio.IntBuffer;

import org.jgloom.gl.GLFramebuffer;
import org.jgloom.gl.GLRenderbuffer;
import org.jgloom.gl.GLTexture;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL32;
import org.lwjgl.opengl.GL43;

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

    /**
     * Deletes the fbo
     */
    public void delete() {
        onStateChanged();
        GL30.glDeleteFramebuffers(getFrameBuffer());
    }

    @Override
    public void attachTexture(int target, int attachment, int level, GLTexture texture) {
        onStateChanged();
        GL32.glFramebufferTexture(target, attachment, texture.getTexture(), level);
    }

    @Override
    public void attachRenderBuffer(int target, int attachment, GLRenderbuffer renderBuffer) {
        onStateChanged();
        GL30.glFramebufferRenderbuffer(target, attachment, GL30.GL_RENDERBUFFER, renderBuffer.getRenderBuffer());
    }

    @Override
    public void setDrawBuffers(IntBuffer attachments) {
        onStateChanged();
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
        onStateChanged();
        return GL30.glCheckFramebufferStatus(target);
    }

    @Override
    public void setParameter(int target, int pname, int param) {
        onStateChanged();
        GL43.glFramebufferParameteri(target, pname, param);
    }

    @Override
    public int getParameter(int target, int pname) {
        onStateChanged();
        return GL43.glGetFramebufferParameteri(target, pname);
    }

    @Override
    public int[] getParameterv(int target, int pname) {
        int[] out = null;
        onStateChanged();
        GL43.glGetFramebufferParameteriv(target, pname, out);
        return out;
    }

    @Override
    public void onStateChanged() {}
}
