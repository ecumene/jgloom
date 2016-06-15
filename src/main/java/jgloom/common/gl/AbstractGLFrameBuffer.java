package jgloom.common.gl;

import jgloom.GLNativeException;
import jgloom.gl.GLFrameBuffer;
import jgloom.gl.GLRenderBuffer;
import jgloom.gl.GLTexture;

import java.nio.IntBuffer;

/**
 * Framebuffer Objects are OpenGL Objects, which allow for the creation of user-defined Framebuffers. With them, one
 * can render to non-Default Framebuffer locations, and thus render without disturbing the main screen.
 *
 * Use {@link jgloom.gl.GLFrameBuffer} to create {@link jgloom.gl.GLFrameBuffer}s
 * @see <a href="https://www.opengl.org/wiki/Framebuffer_Object">opengl.org - framebuffer object</a>
 */
public abstract class AbstractGLFrameBuffer implements GLFrameBuffer {
    private GLFrameBuffer frameBufferInstance;

    /**
     *
     * @param frameBufferInstance The framebuffer to track
     */
    public AbstractGLFrameBuffer(GLFrameBuffer frameBufferInstance){
        this.frameBufferInstance = frameBufferInstance;
    }

    /**
     * @param target One of 3 values: GL_FRAMEBUFFER, GL_READ_FRAMEBUFFER, or GL_DRAW_FRAMEBUFFER (the one the fbo is
     *               bound to)
     */
    public abstract void bind(int target);
    /**
     * Attach a level of a texture object as a logical buffer of a framebuffer object
     * @param target One of 3 values: GL_FRAMEBUFFER, GL_READ_FRAMEBUFFER, or GL_DRAW_FRAMEBUFFER (the one the fbo is
     *               bound to)
     * @param attachment Specifies the attachment point of the framebuffer.
     * @param level Specifies the mipmap level of the texture object to attach.
     * @param texture Specifies the name of an existing texture object to attach.
     */
    public abstract void attachTexture(int target, int attachment, int level, GLTexture texture);
    /**
     * @param target One of 3 values: GL_FRAMEBUFFER, GL_READ_FRAMEBUFFER, or GL_DRAW_FRAMEBUFFER (the one the fbo is
     *               bound to)
     * @param attachment Must be one of the following symbolic constants: GL_COLOR_ATTACHMENT0, GL_DEPTH_ATTACHMENT, or
     *                   GL_STENCIL_ATTACHMENT.
     * @param renderBuffer Specifies the renderbuffer object that is to be attached. If renderbuffer is not 0, the value
     *                     of GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE for the specified attachment point is set to
     *                     GL_RENDERBUFFER and the value of GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME is set to renderbuffer
     */
    public abstract void attachRenderBuffer(int target, int attachment, GLRenderBuffer renderBuffer);
    /**
     * @param attachments GL_COLOR_ATTACHMENTi: These are an implementation-dependent number of attachment points.
     *                    You can query GL_MAX_COLOR_ATTACHMENTS to determine the number of color attachments that an
     *                    implementation will allow. The minimum value for this is 8, so you are guaranteed to be able
     *                    to have at least color attachments 0-7. These attachment points can only have images bound to
     *                    them with color-renderable formats. All compressed image formats are not color-renderable, and
     *                    thus cannot be attached to an FBO.
     *
     *                    GL_DEPTH_ATTACHMENT: This attachment point can only have images with depth formats bound to it
     *                    . The image attached becomes the Depth Buffer for the FBO. **NOTE** Even if you don't plan on
     *                    reading from this depth_attachment, an off screen buffer that will be rendered to should have
     *                    a depth attachment.
     *
     *                    GL_STENCIL_ATTACHMENT: This attachment point can only have images with stencil formats bound
     *                    to it. The image attached becomes the stencil buffer for the FBO.
     *                    GL_DEPTH_STENCIL_ATTACHMENT: This is shorthand for "both depth and stencil". The image
     *                    attached becomes both the depth and stencil buffers.
     *
     *                    Note: If you enable GL_DEPTH_STENCIL_ATTACHMENT, you should enable a packed depth-stencil internal
     *                    format for the texture or renderbuffer you are attaching.
     */
    public abstract void setDrawBuffers(IntBuffer attachments);
    /**
     * Runs through potential errors of the frame buffer, then diagnoses them
     * @param target One of 3 values: GL_FRAMEBUFFER, GL_READ_FRAMEBUFFER, or GL_DRAW_FRAMEBUFFER (the one the fbo is
     *               bound to)
     * @return glCheckFramebufferStatus - The framebuffer's status
     */
    public abstract int getStatus(int target);

    /**
     * Deletes the FBO and frees the memory
     */
    public abstract void delete();

    @Override
    public int getFrameBuffer() {
        return frameBufferInstance.getFrameBuffer();
    }

    /**
     * @return The framebuffer instance
     */
    public GLFrameBuffer getFrameBufferInstance()
    {
        return frameBufferInstance;
    }
}
