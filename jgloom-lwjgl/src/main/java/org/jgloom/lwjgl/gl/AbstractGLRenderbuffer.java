package org.jgloom.lwjgl.gl;

import org.jgloom.JgloomContainer;
import org.jgloom.gl.GLRenderbuffer;
import org.jgloom.gl.functions.renderbuffer.GLFRenderbufferGetParameter;
import org.jgloom.gl.functions.renderbuffer.GLFRenderbufferStorage;
import org.jgloom.gl.functions.renderbuffer.GLFRenderbufferStorageMultisample;

/**
 * Renderbuffer Objects are OpenGL Objects that contain images. They are created and used specifically with Framebuffer
 * Objects. They are optimized for enable as render targets, while Textures may not be, and are the logical choice when you
 * do not need to sample (i.e. in a post-pass shader) from the produced image. If you need to resample (such as when
 * reading depth back in a second shader pass), enable Textures instead. Renderbuffer objects also natively accommodate
 * Multisampling (MSAA).
 *
 * AbstractRenderbuffer methods do not take targets because their target is always GL_RENDERBUFFER
 */
public abstract class AbstractGLRenderbuffer extends JgloomContainer<GLRenderbuffer> implements GLFRenderbufferGetParameter, GLFRenderbufferStorage,
        GLFRenderbufferStorageMultisample {
    /**
     * Contains a single renderbuffer
     * @param renderbufferInstance The renderbuffer to contain
     */
    public AbstractGLRenderbuffer(GLRenderbuffer renderbufferInstance){
        super(renderbufferInstance);
    }

    @Override
    public int getRenderBuffer() {
        return getInstance().getRenderBuffer();
    }
}
