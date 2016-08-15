package org.jgloom.lwjgl.gl;

import org.jgloom.JgloomContainer;
import org.jgloom.gl.GLBuffer;
import org.jgloom.gl.functions.buffer.*;

/**
 * Buffer Objects are OpenGL Objects that store an array of unformatted memory allocated by the OpenGL context (aka: the
 * GPU). These can be used to store vertex data, pixel data retrieved from images or the framebuffer, and a variety of
 * other things.
 *
 * Use {@link GLBuffer} to create {@link org.jgloom.gl.GLBuffer} objects
 */
public abstract class AbstractGLBuffer extends JgloomContainer<GLBuffer> implements GLFBufferClear, GLFBufferData, GLFBufferGetSubData,
        GLFBufferInvalidate, GLFBufferMap, GLFBufferParameter, GLFBufferStorage, GLFBufferSubClear, GLFBufferSubData {
    /** @param buffer The buffer to track */
    public AbstractGLBuffer(GLBuffer buffer){
        super(buffer);
    }

    @Override
    public int getBuffer() {
        return getInstance().getBuffer();
    }
}
