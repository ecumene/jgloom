package org.jgloom.gl.functions.renderbuffer;

/**
 * Renderbuffer Objects are OpenGL Objects that contain images. They are created and used specifically with Framebuffer
 * Objects. They are optimized for enable as render targets, while Textures may not be, and are the logical choice when you
 * do not need to sample (i.e. in a post-pass shader) from the produced image. If you need to resample (such as when
 * reading depth back in a second shader pass), enable Textures instead. Renderbuffer objects also natively accommodate
 * Multisampling (MSAA).
 */
public interface GLFRenderbufferStorageMultisample extends GLFRenderbuffer {
    /**
     * Establish data storage, format, dimensions and sample count of a renderbuffer object's image
     * @param samples Specifies the number of samples to be used for the renderbuffer object's storage.
     * @param internalformat Specifies the internal format to use for the renderbuffer object's image.
     * @param width Specifies the width of the renderbuffer, in pixels
     * @param height Specifies the height of the renderbuffer, in pixels
     */
    void storageMultisample(int samples, int internalformat, int width, int height);
}
