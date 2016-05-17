package jgloom.common.gl;

import org.lwjgl.opengl.GL30;

import jgloom.gl.GLFrameBuffer;

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
