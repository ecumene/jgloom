package jgloom.lwjgl.gl;

import jgloom.gl.GLBuffer;
import jgloom.gl.functions.buffer.*;

/**
 * Buffer Objects are OpenGL Objects that store an array of unformatted memory allocated by the OpenGL context (aka: the
 * GPU). These can be used to store vertex data, pixel data retrieved from images or the framebuffer, and a variety of
 * other things.
 *
 * Use {@link GLBuffer} to create {@link jgloom.gl.GLBuffer} objects
 */
public abstract class AbstractGLBuffer implements GLFBufferClear, GLFBufferData, GLFBufferGetSubData,
        GLFBufferInvalidate, GLFBufferMap, GLFBufferParameter, GLFBufferStorage, GLFBufferSubClear, GLFBufferSubData {
    private GLBuffer bufferInstance;

    /** @param buffer The buffer to track */
    public AbstractGLBuffer(GLBuffer buffer){
        bufferInstance = buffer;
    }

    @Override
    public int getBuffer() {
        return bufferInstance.getBuffer();
    }

    public GLBuffer getBufferInstance() {
        return bufferInstance;
    }
}
