package jgloom.gl.functions.framebuffer;

import jgloom.gl.GLFramebuffer;

/**
 * Framebuffer Objects are OpenGL Objects, which allow for the creation of user-defined Framebuffers. With them, one
 * can render to non-Default Framebuffer locations, and thus render without disturbing the main screen.
 *
 * Use {@link GLFramebuffer} to create {@link GLFramebuffer}s
 * @see <a href="https://www.opengl.org/wiki/Framebuffer_Object">opengl.org - framebuffer object</a>
 */
public interface GLFFramebufferGetParameter extends GLFFramebuffer {
    /**
     * @param target The target of the operation, which must be GL_READ_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER or GL_FRAMEBUFFER.
     * @param pname  The parameter's name
     * @return pname's value
     */
    int getParameter(int target, int pname);

    /**
     * @param target The target of the operation, which must be GL_READ_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER or GL_FRAMEBUFFER.
     * @param pname  The parameter's name
     * @return pname's value
     */
    int[] getParameterv(int target, int pname);
}
