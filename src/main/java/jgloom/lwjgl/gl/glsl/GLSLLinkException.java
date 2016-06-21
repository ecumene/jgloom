package jgloom.lwjgl.gl.glsl;

import jgloom.GLNativeException;

/**
 * Indicates that an error occurred while linking a {@link jgloom.gl.glsl.GLSLProgram},
 * usually an error in the GLSL syntax
 */
public class GLSLLinkException extends GLNativeException {
    private static final long serialVersionUID = 1L;

    public GLSLLinkException(String desc) {
        super(desc);
    }
}
