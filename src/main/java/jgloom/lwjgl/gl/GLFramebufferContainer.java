package jgloom.lwjgl.gl;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL32;

import jgloom.gl.GLFramebuffer;
import jgloom.gl.GLRenderbuffer;
import jgloom.gl.GLTexture;
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

    @Override
    public void attachTexture(int target, int attachment, int level, GLTexture texture) {
        GL32.glFramebufferTexture(target, attachment, texture.getTexture(), level);
    }

    @Override
    public void attachRenderBuffer(int target, int attachment, GLRenderbuffer renderBuffer) {
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

    @Override
    public void setParameter(int target, int pname, int param) {
        GL43.glFramebufferParameteri(target, pname, param);
    }

    @Override
    public int getParameter(int target, int pname) {
        return GL43.glGetFramebufferParameteri(target, pname);
    }

    @Override
    public int[] getParameterv(int target, int pname) {
        int[] out = null;
        GL43.glGetFramebufferParameteriv(target, pname, out);
        return out;
    }

    /**
     * Deletes the fbo
     */
    public void delete() {
        GL30.glDeleteFramebuffers(getFrameBuffer());
    }

}