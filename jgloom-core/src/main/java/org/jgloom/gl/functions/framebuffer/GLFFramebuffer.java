package org.jgloom.gl.functions.framebuffer;

import org.jgloom.gl.GLFramebuffer;

/**
 * Framebuffer Objects are OpenGL Objects, which allow for the creation of user-defined Framebuffers.
 * With them, one can render to non-Default Framebuffer locations, and thus render without disturbing the main screen.
 * @see <a href="https://www.opengl.org/wiki/Framebuffer_Object"></a>
 */
public interface GLFFramebuffer extends GLFramebuffer {
    /**
     * @param target One of 3 values: GL_FRAMEBUFFER, GL_READ_FRAMEBUFFER, or GL_DRAW_FRAMEBUFFER (the one the fbo is
     *               bound to)
     */
    void bind(int target);
    /**
     * Deletes the FBO and frees the memory
     */
    void delete();
}
