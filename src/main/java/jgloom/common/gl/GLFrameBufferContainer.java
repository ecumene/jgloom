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
 * A shell class containing functions for manipulating a given
 * {@link GLFrameBuffer}
 */
public class GLFrameBufferContainer implements GLFrameBuffer {
	private GLFrameBuffer frameBufferInstance;
	
	public GLFrameBufferContainer(GLFrameBuffer frameBufferInstance)
	{
		this.frameBufferInstance = frameBufferInstance;
	}
	
	public void attachTexture(int target, int attachment, int level, GLTexture texture) {
		// Is this needed? GLTextures.bindTexture(texture); (once added)
		GLFrameBuffers.bindFrameBuffer(target, this);
		GL32.glFramebufferTexture(target, attachment, texture.getTexture(), level);
	}
	
	public void attachRenderBuffer(int target, int attachment, int rbTarget, GLRenderBuffer renderBuffer) {
		GLFrameBuffers.bindFrameBuffer(target, this);
		GL30.glFramebufferRenderbuffer(target, attachment, rbTarget, renderBuffer.getRenderBuffer());
	}
	
	public void setDrawBuffers(int target, IntBuffer attachments) {
		GLFrameBuffers.bindFrameBuffer(target, this);
		GL20.glDrawBuffers(attachments);
	}
	
	public void setDrawBuffers(int target, int ... attachments) {
		IntBuffer drawBuffers = BufferUtils.createIntBuffer(attachments.length);
		drawBuffers.put(attachments);
		drawBuffers.flip();
		setDrawBuffers(target, drawBuffers);
	}
	
	public void checkCompleteness(int target) {
		GLFrameBuffers.bindFrameBuffer(target, this);
		int status = GL30.glCheckFramebufferStatus(target);
		if (status != GL30.GL_FRAMEBUFFER_COMPLETE) {
			String start = "Frame buffer ";
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
			
			throw new GLNativeException(start + humanReadable);
		}
	}
	
	public void destroy() {
		GL30.glDeleteFramebuffers(frameBufferInstance.getFrameBuffer());
	}
	
	@Override
	public int getFrameBuffer() {
		return frameBufferInstance.getFrameBuffer();
	}
	
	public GLFrameBuffer getFrameBufferInstance()
	{
		return frameBufferInstance;
	}
}
