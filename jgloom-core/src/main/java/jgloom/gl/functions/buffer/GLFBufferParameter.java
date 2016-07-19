package jgloom.gl.functions.buffer;

import java.nio.IntBuffer;

/**
 * Created by mh on 6/22/16.
 */
public interface GLFBufferParameter extends GLFBuffer {
    /**
     * Return parameters of a buffer object
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER or
     *               GL_ELEMENT_ARRAY_BUFFER.
     * @param pname  Specifies the symbolic name of a buffer object parameter. Accepted values are GL_BUFFER_SIZE or
     *               GL_BUFFER_USAGE.
     * @return A buffer containing the buffer's parameters
     */
    IntBuffer getParameters(int target, int pname);

    /**
     * Return parameter of a buffer object
     * @param target Specifies the target buffer object. The symbolic constant must be GL_ARRAY_BUFFER or
     *               GL_ELEMENT_ARRAY_BUFFER.
     * @param pname  Specifies the symbolic name of a buffer object parameter. Accepted values are GL_BUFFER_SIZE or
     *               GL_BUFFER_USAGE.
     * @return The parameter's value
     */
    int getParameter(int target, int pname);
}
