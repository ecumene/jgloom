package jgloom.lwjgl.gl;

import jgloom.gl.GLRenderbuffer;
import jgloom.gl.functions.renderbuffer.GLFRenderbufferGetParameter;
import jgloom.gl.functions.renderbuffer.GLFRenderbufferStorage;
import jgloom.gl.functions.renderbuffer.GLFRenderbufferStorageMultisample;

/**
 * Renderbuffer Objects are OpenGL Objects that contain images. They are created and used specifically with Framebuffer
 * Objects. They are optimized for enable as render targets, while Textures may not be, and are the logical choice when you
 * do not need to sample (i.e. in a post-pass shader) from the produced image. If you need to resample (such as when
 * reading depth back in a second shader pass), enable Textures instead. Renderbuffer objects also natively accommodate
 * Multisampling (MSAA).
 *
 * AbstractRenderbuffer methods do not take targets because their target is always GL_RENDERBUFFER
 */
public abstract class AbstractGLRenderbuffer implements GLFRenderbufferGetParameter, GLFRenderbufferStorage,
        GLFRenderbufferStorageMultisample {
    private GLRenderbuffer renderbufferInstance;

    /**
     * Contains a single renderbuffer
     * @param renderbufferInstance The renderbuffer to contain
     */
    public AbstractGLRenderbuffer(GLRenderbuffer renderbufferInstance){
        this.renderbufferInstance = renderbufferInstance;
    }

    @Override
    public int getRenderBuffer() {
        return renderbufferInstance.getRenderBuffer();
    }

    /** @return The renderbuffer instance contained  */
    public GLRenderbuffer getRenderBufferInstance() {
        return renderbufferInstance;
    }
}
