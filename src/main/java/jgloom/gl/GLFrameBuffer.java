package jgloom.gl;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

/**
 * Framebuffer Objects are OpenGL Objects, which allow for the creation of user-defined Framebuffers. With them, one
 * can render to non-Default Framebuffer locations, and thus render without disturbing the main screen.
 * @see <a href="https://www.opengl.org/wiki/Framebuffer_Object">opengl.org - framebuffer object</a>
 */
@FunctionalInterface
public interface GLFrameBuffer {
    /**The identifier used for binding framebuffer objects using EXT framebuffers and core framebuffers both enable
     * integer identifiers.
     * @return The procedural component of the framebuffer object */
    int getFrameBuffer();

    /** The OpenGL shader object identifier */
    int IDENTIFIER = GL30.GL_FRAMEBUFFER;

    /**
     * @return A constructed framebuffer with glGenFrameBuffers
     */
    static GLFrameBuffer createFrameBuffer() {
        int frameBuffer = GL30.glGenFramebuffers();
        return () -> frameBuffer;
    }

    /**
     * @param fb
     * @return if an integer corresponds to an OpenGL buffer object
     */
    static boolean isFrameBuffer(int fb){
        return GL30.glIsFramebuffer(fb);
    }
}
