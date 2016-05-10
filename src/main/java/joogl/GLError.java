package joogl;

import org.lwjgl.opengl.GL11;

/**
 * Signals that an OpenGL method has been invoked / used at an incorrect
 * or inappropriate time.
 */
public class GLError extends IllegalStateException {

    /**
     * Trow an OpenGL error with the description given
     * @param desc The description
     */
    public GLError(String desc){
        super(desc);
    }

    /**
     * Throw an OpenGL error with the OpenGL error flag (doesn't
     * interprets errors just prints them)
     * @param error The error to save
     */
    public GLError(int error){
        super(""+error);
    }

    public static void checkOGL() throws GLError {
        if(GL11.glGetError() != GL11.GL_NO_ERROR)
            throw new GLError(GL11.glGetError());
    }
}
