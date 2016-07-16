package jgloom;

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
}
