package jgloom.common.gl;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL32;

import jgloom.GLNativeException;
import jgloom.gl.GLFrameBuffer;
import jgloom.gl.GLRenderBuffer;
import jgloom.gl.GLTexture;

/**
 * A shell class containing functions for manipulating a given {@link GLFrameBuffer}
 */
public class GLFrameBufferContainer implements GLFrameBuffer {
    private GLFrameBuffer frameBufferInstance;

    /**
     * @param frameBufferInstance The framebuffer to track
     */
    public GLFrameBufferContainer(GLFrameBuffer frameBufferInstance)
    {
        this.frameBufferInstance = frameBufferInstance;
    }

    /**
     * @param target One of 3 values: GL_FRAMEBUFFER, GL_READ_FRAMEBUFFER, or GL_DRAW_FRAMEBUFFER (the one the fbo is
     *               bound to)
     */
    public void bind(int target){
        GL30.glBindFramebuffer(target, frameBufferInstance.getFrameBuffer());
    }

    /**
     * Attach a level of a texture object as a logical buffer of a framebuffer object
     * @param target One of 3 values: GL_FRAMEBUFFER, GL_READ_FRAMEBUFFER, or GL_DRAW_FRAMEBUFFER (the one the fbo is
     *               bound to)
     * @param attachment Specifies the attachment point of the framebuffer.
     * @param level Specifies the mipmap level of the texture object to attach.
     * @param texture Specifies the name of an existing texture object to attach.
     */
    public void attachTexture(int target, int attachment, int level, GLTexture texture) {
        GL32.glFramebufferTexture(target, attachment, texture.getTexture(), level);
    }

    /**
     * @param target One of 3 values: GL_FRAMEBUFFER, GL_READ_FRAMEBUFFER, or GL_DRAW_FRAMEBUFFER (the one the fbo is
     *               bound to)
     * @param attachment Must be one of the following symbolic constants: GL_COLOR_ATTACHMENT0, GL_DEPTH_ATTACHMENT, or
     *                   GL_STENCIL_ATTACHMENT.
     * @param renderBuffer Specifies the renderbuffer object that is to be attached. If renderbuffer is not 0, the value
     *                     of GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE for the specified attachment point is set to
     *                     GL_RENDERBUFFER and the value of GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME is set to renderbuffer
     */
    public void attachRenderBuffer(int target, int attachment, GLRenderBuffer renderBuffer) {
        // The 3rd is a symbolic constant (aka they added it to be cheeky)
        GL30.glFramebufferRenderbuffer(target, attachment, GL30.GL_RENDERBUFFER, renderBuffer.getRenderBuffer());
    }

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
    public void setDrawBuffers(IntBuffer attachments) {
        GL20.glDrawBuffers(attachments); // Yes this is static, no I don't care.
    }

    /** @param attachments GL_COLOR_ATTACHMENTi: These are an implementation-dependent number of attachment points.
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
    public void setDrawBuffers(int ... attachments) {
        IntBuffer drawBuffers = BufferUtils.createIntBuffer(attachments.length);
        drawBuffers.put(attachments);
        drawBuffers.flip();
        setDrawBuffers(drawBuffers);
    }

    /**
     * Runs through potential errors of the frame buffer, then diagnoses them
     * @param target One of 3 values: GL_FRAMEBUFFER, GL_READ_FRAMEBUFFER, or GL_DRAW_FRAMEBUFFER (the one the fbo is
     *               bound to)
     * @throws GLNativeException When the fbo is invalid
     */
    public void checkCompleteness(int target) throws GLNativeException {
        int status = GL30.glCheckFramebufferStatus(target);
        if (status != GL30.GL_FRAMEBUFFER_COMPLETE) {
            String humanReadable = "is not complete";
            switch (status) {
            case GL30.GL_FRAMEBUFFER_UNDEFINED:
                humanReadable = "is undefined (does not exist)";
                break;
            case GL30.GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT:
                humanReadable = "has incomplete attachments";
                break;
            case GL30.GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT :
                humanReadable = "does not have at least one image attached";
                break;
            case GL30.GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER:
                humanReadable = "has a draw buffer with an invalid color attachment point";
                break;
            case GL30.GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER:
                humanReadable = "has read buffer with no attachment point with an image attached";
                break;
            case GL30.GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE:
                humanReadable = "has attached images with different numbers of multisamples";
                break;
            case GL32.GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS:
                humanReadable = "requires all images to be layered if one is";
                break;
            case GL30.GL_FRAMEBUFFER_UNSUPPORTED:
                humanReadable = "is not supported";
                break;
            }
            
            throw new GLNativeException("FBO " + humanReadable);
        }
    }

    /**
     * Deletes the fbo
     */
    public void delete() {
        GL30.glDeleteFramebuffers(frameBufferInstance.getFrameBuffer());
    }
    
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
