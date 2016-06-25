package jgloom.lwjgl.gl;

import jgloom.gl.GLRenderbuffer;
import org.lwjgl.opengl.GL30;

/**
 * Renderbuffer Objects are OpenGL Objects that contain images. They are created and used specifically with Framebuffer
 * Objects. They are optimized for enable as render targets, while Textures may not be, and are the logical choice when you
 * do not need to sample (i.e. in a post-pass shader) from the produced image. If you need to resample (such as when
 * reading depth back in a second shader pass), enable Textures instead. Renderbuffer objects also natively accommodate
 * Multisampling (MSAA).
 */
public class GLRenderbufferContainer extends AbstractGLRenderbuffer {
    /**
     * Contains a single renderbuffer
     * @param renderbufferInstance The renderbuffer to contain
     */
    public GLRenderbufferContainer(GLRenderbuffer renderbufferInstance){
        super(renderbufferInstance);
    }

    @Override
    public void bind(){
        GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, getRenderBuffer());
    }

    @Override
    public void storage(int internalformat, int width, int height){
        GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, internalformat, width, height);
    }

    @Override
    public void storageMultisample(int samples, int internalformat, int width, int height){
        GL30.glRenderbufferStorageMultisample(GL30.GL_RENDERBUFFER, samples, internalformat, width, height);
    }

    @Override
    public int getParameter(int pname){
        return GL30.glGetRenderbufferParameteri(GL30.GL_RENDERBUFFER, pname);
    }

    @Override
    public void delete(){
        GL30.glDeleteRenderbuffers(getRenderBuffer());
    }
}
