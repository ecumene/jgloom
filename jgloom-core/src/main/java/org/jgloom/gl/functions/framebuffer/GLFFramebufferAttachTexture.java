package org.jgloom.gl.functions.framebuffer;

import org.jgloom.gl.GLTexture;

/**
 * Framebuffer Objects are OpenGL Objects, which allow for the creation of user-defined Framebuffers.
 * With them, one can render to non-Default Framebuffer locations, and thus render without disturbing the main screen.
 * @see <a href="https://www.opengl.org/wiki/Framebuffer_Object"></a>
 */
public interface GLFFramebufferAttachTexture extends GLFFramebuffer {
    /**
     * Attach a level of a texture object as a logical buffer of a framebuffer object
     * @param target One of 3 values: GL_FRAMEBUFFER, GL_READ_FRAMEBUFFER, or GL_DRAW_FRAMEBUFFER (the one the fbo is
     *               bound to)
     * @param attachment Specifies the attachment point of the framebuffer.
     * @param level Specifies the mipmap level of the texture object to attach.
     * @param texture Specifies the name of an existing texture object to attach.
     */
    void attachTexture(int target, int attachment, int level, GLTexture texture);
}
