package jgloom.lwjgl.gl;

import jgloom.gl.GLBuffer;
import jgloom.gl.functions.buffer.*;

import java.nio.*;

/**
 * Buffer Objects are OpenGL Objects that store an array of unformatted memory allocated by the OpenGL context (aka: the
 * GPU). These can be used to store vertex data, pixel data retrieved from images or the framebuffer, and a variety of
 * other things.
 *
 * Use {@link GLBuffer} to create {@link jgloom.gl.GLBuffer} objects
 */
// TODO: Add classes to the enums in documentation
public abstract class AbstractGLBuffer implements GLFBufferClear, GLFBufferData, GLFBufferGetSubData,
        GLFBufferInvalidate, GLFBufferMap, GLFBufferParameter, GLFBufferStorage, GLFBufferSubClear, GLFBufferSubData {
    private GLBuffer bufferInstance;

    /** @param buffer The buffer to track */
    public AbstractGLBuffer(GLBuffer buffer){
        bufferInstance = buffer;
    }

    /**
     * Return parameters of a buffer object
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER or
     *               GL_ELEMENT_ARRAY_BUFFER.
     * @param pname  Specifies the symbolic name of a buffer object parameter. Accepted values are GL_BUFFER_SIZE or
     *               GL_BUFFER_USAGE.
     * @return A buffer containing the buffer's parameters
     */
    public abstract IntBuffer getParameters(int target, int pname);

    /**
     * Return parameter of a buffer object
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER or
     *               GL_ELEMENT_ARRAY_BUFFER.
     * @param pname  Specifies the symbolic name of a buffer object parameter. Accepted values are GL_BUFFER_SIZE or
     *               GL_BUFFER_USAGE.
     * @return The parameter's value
     */
    public abstract int getParameter(int target, int pname);

    @Override
    public int getBuffer() {
        return bufferInstance.getBuffer();
    }

    public GLBuffer getBufferInstance() {
        return bufferInstance;
    }
}
