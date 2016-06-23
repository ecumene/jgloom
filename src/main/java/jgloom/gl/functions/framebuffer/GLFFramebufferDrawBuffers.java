package jgloom.gl.functions.framebuffer;

import jgloom.gl.GLFramebuffer;

import java.nio.IntBuffer;

/**
 * Framebuffer Objects are OpenGL Objects, which allow for the creation of user-defined Framebuffers. With them, one
 * can render to non-Default Framebuffer locations, and thus render without disturbing the main screen.
 *
 * Use {@link GLFramebuffer} to create {@link GLFramebuffer}s
 * @see <a href="https://www.opengl.org/wiki/Framebuffer_Object">opengl.org - framebuffer object</a>
 */
public interface GLFFramebufferDrawBuffers extends GLFFramebuffer {
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
     void setDrawBuffers(IntBuffer attachments);
}
