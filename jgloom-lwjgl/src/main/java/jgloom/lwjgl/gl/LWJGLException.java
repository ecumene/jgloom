package jgloom.lwjgl.gl;

import jgloom.GLNativeException;
import org.lwjgl.opengl.GL11;

/**
 * Represents an exception thrown in the LWJGL platform
 */
public class LWJGLException extends GLNativeException {
    public LWJGLException(String error){
        super(error);
    }

    /**
     * Checks for and throws OpenGL errors
     * @throws GLNativeException If an OpenGL error exists
     */
    public static void checkOGL() throws GLNativeException {
        if(GL11.glGetError() != GL11.GL_NO_ERROR)
            throw new GLNativeException(GL11.glGetError());
    }

}
