package joogl.gl;

/**
 * Framebuffer Objects are OpenGL Objects, which allow for the creation of user-defined Framebuffers. With them, one
 * can render to non-Default Framebuffer locations, and thus render without disturbing the main screen.
 * @see <a href=https://www.opengl.org/wiki/Framebuffer_Object>opengl.org - framebuffer object</a>
 */
public interface GLFrameBuffer {
    /**The identifier used for binding framebuffer objects using EXT framebuffers and core framebuffers both use
     * integer identifiers.
     * @return The procedural component of the framebuffer object */
    public int getFrameBuffer();
}
