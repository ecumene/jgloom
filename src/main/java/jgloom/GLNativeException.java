package jgloom;

import org.lwjgl.opengl.GL11;

/**
 * Signals that an OpenGL method has been invoked / used at an incorrect
 * or inappropriate time.
 */
public class GLNativeException extends IllegalStateException {
    private static final long serialVersionUID = -4526854477334203279L;

    /**
     * Throw an OpenGL error with the description given
     * @param desc The description
     */
    public GLNativeException(String desc){
        super(desc);
    }

    /**
     * Throw an OpenGL error with the OpenGL error flag (doesn't
     * interprets errors just prints them)
     * @param error The error to save
     */
    public GLNativeException(int error){
        super(""+error);
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
