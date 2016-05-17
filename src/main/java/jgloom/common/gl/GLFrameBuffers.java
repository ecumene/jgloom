package jgloom.common.gl;

import org.lwjgl.opengl.GL30;

import jgloom.gl.GLFrameBuffer;

public class GLFrameBuffers {
	public static synchronized GLFrameBuffer createFrameBuffer() {
		int frameBuffer = GL30.glGenFramebuffers();
		return new GLFrameBuffer() {
			@Override
			public int getFrameBuffer() { return frameBuffer; }
		};
	}
	
	public static synchronized void bindFrameBuffer(int target, GLFrameBuffer frameBuffer) {
		if (frameBuffer == null) GL30.glBindFramebuffer(target, 0);
		else 					 GL30.glBindFramebuffer(target, frameBuffer.getFrameBuffer());
	}
}
