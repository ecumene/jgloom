package jgloom.common.gl;

import org.lwjgl.opengl.GL30;

import jgloom.gl.GLFrameBuffer;

/**
 * Contains non-framebuffer-specific methods
 * @see <a href="https://www.opengl.org/wiki/Framebuffer_Object">The OpenGL definition of FBOs</a>
 */
public class GLFrameBuffers {
    /**
     * @return A constructed framebuffer with glGenFrameBuffers
     */
    public static synchronized GLFrameBuffer createFrameBuffer() {
        return new GLFrameBuffer() {
            @Override
            public int getFrameBuffer() { return GL30.glGenFramebuffers(); }
        };
    }
}
