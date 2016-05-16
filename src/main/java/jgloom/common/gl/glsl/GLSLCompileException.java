package jgloom.common.gl.glsl;

import jgloom.GLNativeException;
import jgloom.gl.glsl.GLSLShader;

/**
 * Indicates that an error occurred while compiling a {@link GLSLShader},
 * usually an error in the GLSL syntax
 */
public class GLSLCompileException extends GLNativeException {
	private static final long serialVersionUID = 1L;

	public GLSLCompileException(String desc) {
		super(desc);
	}
}
