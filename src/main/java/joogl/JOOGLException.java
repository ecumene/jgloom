package joogl;

/**
 * Signals any errors that can occur in the JOOGL library
 * such as files not being loaded correctly. The OO side of
 * the library.
 */
public class JOOGLException extends Exception {
	private static final long serialVersionUID = 8955584159617543269L;

	/**
     * @param exception The exception to save
     */
    public JOOGLException(String exception){
        super(exception);
    }
}
