package org.jgloom.gl.functions.framebuffer;

/**
 * Framebuffer Objects are OpenGL Objects, which allow for the creation of user-defined Framebuffers.
 * With them, one can render to non-Default Framebuffer locations, and thus render without disturbing the main screen.
 * @see <a href="https://www.opengl.org/wiki/Framebuffer_Object"></a>
 */
public interface GLFFramebufferSetParameter extends GLFFramebuffer {
    /**
     * Set a named parameter of a framebuffer
     * @param target The target of the operation, which must be GL_READ_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER or GL_FRAMEBUFFER.
     * @param pname  A token indicating the parameter to be modified.
     * @param param  The new parameter
     */
    void setParameter(int target, int pname, int param);
}
