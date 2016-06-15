package jgloom.common.gl;

import jgloom.gl.GLRenderBuffer;
import org.lwjgl.opengl.GL30;

/**
 * Renderbuffer Objects are OpenGL Objects that contain images. They are created and used specifically with Framebuffer
 * Objects. They are optimized for enable as render targets, while Textures may not be, and are the logical choice when you
 * do not need to sample (i.e. in a post-pass shader) from the produced image. If you need to resample (such as when
 * reading depth back in a second shader pass), enable Textures instead. Renderbuffer objects also natively accommodate
 * Multisampling (MSAA).
 *
 * AbstractRenderBuffer methods do not take targets because their target is always GL_RENDERBUFFER
 */
public abstract class AbstractGLRenderBuffer implements GLRenderBuffer {
    private GLRenderBuffer renderBufferInstance;

    /**
     * Contains a single renderbuffer
     * @param renderBufferInstance The renderbuffer to contain
     */
    public AbstractGLRenderBuffer(GLRenderBuffer renderBufferInstance){
        this.renderBufferInstance = renderBufferInstance;
    }

    /**
     * Bind a renderbuffer to a renderbuffer target
     */
    public abstract void bind();

    /**
     * Establish data storage, format and dimensions of a renderbuffer object's image
     * @param internalformat Specifies the internal format to enable for the renderbuffer object's image.
     * @param width          Specifies the width of the renderbuffer, in pixels.
     * @param height         Specifies the height of the renderbuffer, in pixels.
     */
    public abstract void storage(int internalformat, int width, int height);

    /**
     * Establish data storage, format, dimensions and sample count of a renderbuffer object's image
     */
    public abstract void storageMultisample(int samples, int internalformat, int width, int height);

    /**
     * @param pname The pname
     * @return the renderbuffer's parameter value
     */
    public abstract int getParameter(int pname);

    /**
     * Deletes the renderbuffer and frees up memory
     */
    public abstract void delete();

    @Override
    public int getRenderBuffer() {
        return renderBufferInstance.getRenderBuffer();
    }

    /** @return The renderbuffer instance contained  */
    public GLRenderBuffer getRenderBufferInstance() {
        return renderBufferInstance;
    }
}
