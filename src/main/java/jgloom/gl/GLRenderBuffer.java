package jgloom.gl;

import org.lwjgl.opengl.GL30;

/**
 * Renderbuffer Objects are OpenGL Objects that contain images. They are created and used specifically with Framebuffer
 * Objects. They are optimized for use as render targets, while Textures may not be, and are the logical choice when you
 * do not need to sample (i.e. in a post-pass shader) from the produced image. If you need to resample (such as when
 * reading depth back in a second shader pass), use Textures instead. Renderbuffer objects also natively accommodate
 * Multisampling (MSAA).
 * @see <a href="https://www.opengl.org/wiki/Renderbuffer_Object">opengl.org - framebuffer object</a>
 */
public interface GLRenderBuffer {
    /** @return The identifier for the renderbuffer */
    public int getRenderBuffer();

    /** The OpenGL renderbuffer object identifier */
    public static final int IDENTIFIER = GL30.GL_RENDERBUFFER;

    /** @return Renderbuffer object names */
    public static GLRenderBuffer createRenderBuffer(){
        return new GLRenderBuffer() {
            @Override
            public int getRenderBuffer() {
                return GL30.glGenRenderbuffers();
            }
        };
    }
}
