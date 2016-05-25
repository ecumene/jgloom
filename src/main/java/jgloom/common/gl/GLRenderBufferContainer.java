package jgloom.common.gl;

import jgloom.gl.GLRenderBuffer;
import org.lwjgl.opengl.GL30;

/**
 * Renderbuffer Objects are OpenGL Objects that contain images. They are created and used specifically with Framebuffer
 * Objects. They are optimized for use as render targets, while Textures may not be, and are the logical choice when you
 * do not need to sample (i.e. in a post-pass shader) from the produced image. If you need to resample (such as when
 * reading depth back in a second shader pass), use Textures instead. Renderbuffer objects also natively accommodate
 * Multisampling (MSAA).
 */
public class GLRenderBufferContainer implements GLRenderBuffer {
    private GLRenderBuffer renderBufferInstance;

    /**
     * Contains a single renderbuffer
     * @param renderBufferInstance The renderbuffer to contain
     */
    public GLRenderBufferContainer(GLRenderBuffer renderBufferInstance){
        this.renderBufferInstance = renderBufferInstance;
    }

    /**
     * Bind a renderbuffer to a renderbuffer target
     */
    public void bind(){
        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, renderBufferInstance.getRenderBuffer());
    }

    /**
     * Establish data storage, format and dimensions of a renderbuffer object's image
     * @param internalformat Specifies the internal format to use for the renderbuffer object's image.
     * @param width          Specifies the width of the renderbuffer, in pixels.
     * @param height         Specifies the height of the renderbuffer, in pixels.
     */
    public void storage(int internalformat, int width, int height){
        GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, internalformat, width, height);
    }

    /**
     * Establish data storage, format, dimensions and sample count of a renderbuffer object's image
     */
    public void storageMultisample(int samples, int internalformat, int width, int height){
        GL30.glRenderbufferStorageMultisample(GL30.GL_RENDERBUFFER, samples, internalformat, width, height);
    }

    @Override
    public int getRenderBuffer() {
        return renderBufferInstance.getRenderBuffer();
    }

    /** @return The renderbuffer instance contained  */
    public GLRenderBuffer getRenderBufferInstance() {
        return renderBufferInstance;
    }
}
