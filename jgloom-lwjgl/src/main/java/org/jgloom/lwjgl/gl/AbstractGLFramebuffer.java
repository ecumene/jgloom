package org.jgloom.lwjgl.gl;

import org.jgloom.gl.GLFramebuffer;
import org.jgloom.gl.functions.framebuffer.*;

/**
 * Framebuffer Objects are OpenGL Objects, which allow for the creation of user-defined Framebuffers. With them, one
 * can render to non-Default Framebuffer locations, and thus render without disturbing the main screen.
 *
 * Use {@link GLFramebuffer} to create {@link GLFramebuffer}s
 * @see <a href="https://www.opengl.org/wiki/Framebuffer_Object">opengl.org - framebuffer object</a>
 */
public abstract class AbstractGLFramebuffer implements GLFramebuffer, GLFFramebufferAttachRenderbuffer,
        GLFFramebufferAttachTexture, GLFFramebufferDrawBuffers, GLFFramebufferGetParameter, GLFFramebufferGetStatus,
        GLFFramebufferSetParameter{
    private GLFramebuffer framebufferInstance;

    /**
     * @param framebufferInstance The framebuffer to track
     */
    public AbstractGLFramebuffer(GLFramebuffer framebufferInstance){
        this.framebufferInstance = framebufferInstance;
    }

    @Override
    public int getFrameBuffer() {
        return framebufferInstance.getFrameBuffer();
    }

    /**
     * @return The framebuffer instance
     */
    public GLFramebuffer getFrameBufferInstance()
    {
        return framebufferInstance;
    }
}
