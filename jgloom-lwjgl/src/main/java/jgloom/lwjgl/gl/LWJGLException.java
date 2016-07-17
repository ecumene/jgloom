package jgloom.lwjgl.gl;

import jgloom.GLNativeException;
import org.lwjgl.opengl.GL11;

/**
 * Represents an exception thrown in the LWJGL platform
 */
public class LWJGLException extends GLNativeException {
	private static final long serialVersionUID = 1L;

	public LWJGLException(String error){
        super(error);
    }

    /**
     * Checks for and throws OpenGL errors
     * @throws GLNativeException If an OpenGL error exists
     */
    public static void checkOGL() throws GLNativeException {
    	int error = GL11.glGetError();
        if(error != GL11.GL_NO_ERROR)
            throw new GLNativeException(error);
    }

}
