package joogl;

/**
 * Signals any errors that can occur in the JOOGL library
 * such as files not being loaded correctly. The OO side of
 * the library.
 */
public class JOOGLException extends Exception {
    /**
     * @param exception The exception to save
     */
    public JOOGLException(String exception){
        super(exception);
    }
}
