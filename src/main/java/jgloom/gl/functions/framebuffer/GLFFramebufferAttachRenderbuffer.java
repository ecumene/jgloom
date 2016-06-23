package jgloom.gl.functions.framebuffer;

import jgloom.gl.GLRenderbuffer;

/**
 * Framebuffer Objects are OpenGL Objects, which allow for the creation of user-defined Framebuffers.
 * With them, one can render to non-Default Framebuffer locations, and thus render without disturbing the main screen.
 * @see <a href="https://www.opengl.org/wiki/Framebuffer_Object"></a>
 */
public interface GLFFramebufferAttachRenderbuffer extends GLFFramebuffer {
    /**
     * @param target One of 3 values: GL_FRAMEBUFFER, GL_READ_FRAMEBUFFER, or GL_DRAW_FRAMEBUFFER (the one the fbo is
     *               bound to)
     * @param attachment Must be one of the following symbolic constants: GL_COLOR_ATTACHMENT0, GL_DEPTH_ATTACHMENT, or
     *                   GL_STENCIL_ATTACHMENT.
     * @param renderBuffer Specifies the renderbuffer object that is to be attached. If renderbuffer is not 0, the value
     *                     of GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE for the specified attachment point is set to
     *                     GL_RENDERBUFFER and the value of GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME is set to renderbuffer
     */
    void attachRenderBuffer(int target, int attachment, GLRenderbuffer renderBuffer);
}
