package jgloom.common.gl.glsl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import jgloom.GLNativeException;
import jgloom.gl.glsl.GLSLProgram;

/**
 * Handles operations such as creating and setting the currently used
 * {@link GLSLProgram}
 */
public class GLSLPrograms {
	/**
	 * Creates an empty GLSL program for attaching shaders to and using for
	 * rendering
	 * @return
	 */
	public static synchronized GLSLProgram createProgram() {
		final int program = GL20.glCreateProgram();
		return new GLSLProgram() {
			@Override
			public int getGLSLProgram() {
				return program;
			}
		};
	}

	/**
	 * Links the given GLSL program, allocating locations for its uniform
	 * variables, setting all user-defined GLSL variables to 0, and more
	 * @param program
	 * @throws GLNativeException When the program can not be linked
	 */
	public static synchronized void linkProgram(GLSLProgram program) {
		GL20.glLinkProgram(program.getGLSLProgram());
		int linkStatus = GL20.glGetProgrami(program.getGLSLProgram(), GL20.GL_LINK_STATUS);
		if (linkStatus == GL11.GL_FALSE)
			throw new GLNativeException("Could not link program");
	}

	/**
	 * Tells OpenGL to use the given {@link GLSLProgram}; if null is specified,
	 * the program 0 is bound, meaning no program is bound
	 * @param program
	 */
	public static synchronized void useProgram(GLSLProgram program) {
		if (program == null)
			GL20.glUseProgram(0);
		else
			GL20.glUseProgram(program.getGLSLProgram());
	}
}
