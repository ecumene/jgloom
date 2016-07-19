package org.jgloom;

/**
 * Signals any errors that can occur in the JGLOOM library
 * such as files not being loaded correctly. The OO side of
 * the library.
 */
public class GLOOMException extends Exception {
    private static final long serialVersionUID = 8955584159617543269L;

    /**
     * @param exception The exception to save
     */
    public GLOOMException(String exception){
        super(exception);
    }
}
