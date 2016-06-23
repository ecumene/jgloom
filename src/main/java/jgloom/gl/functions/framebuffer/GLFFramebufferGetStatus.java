package jgloom.gl.functions.framebuffer;

import jgloom.gl.GLFramebuffer;

/**
 * Framebuffer Objects are OpenGL Objects, which allow for the creation of user-defined Framebuffers. With them, one
 * can render to non-Default Framebuffer locations, and thus render without disturbing the main screen.
 *
 * Use {@link GLFramebuffer} to create {@link GLFramebuffer}s
 * @see <a href="https://www.opengl.org/wiki/Framebuffer_Object">opengl.org - framebuffer object</a>
 */
public interface GLFFramebufferGetStatus extends GLFFramebuffer {
    /**
     * Runs through potential errors of the frame buffer, then diagnoses them
     * @param target One of 3 values: GL_FRAMEBUFFER, GL_READ_FRAMEBUFFER, or GL_DRAW_FRAMEBUFFER (the one the fbo is
     *               bound to)
     * @return glCheckFramebufferStatus - The framebuffer's status
     */
    int getStatus(int target);
}
