package jgloom.common.gl.glsl;

import jgloom.gl.glsl.GLSLShader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import jgloom.gl.glsl.GLSLProgram;

/**
 * Handles operations such as creating and setting the currently used
 * {@link GLSLProgram}
 */
public class GLSLPrograms {

	/**
	 * @return The currently bound shader program set by {@link GLSLPrograms#useProgram(GLSLProgram)}
     */
	public static synchronized GLSLProgram getCurrentProgram(){
		return new GLSLProgram() {
			@Override
			public int getGLSLProgram() {
				return GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
			}
		};
	}

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
	 * Installs the program object specified by program​ as part of current rendering state. One or more executables are
	 * created in a program object by successfully attaching shader objects to it with
	 * {@link GLSLProgramContainer#attachGLSLShader(GLSLShader)}, successfully compiling the shader objects with
	 * {@link GLSLShaderContainer#compileShader()}, and successfully linking the program object with
	 * {@link GLSLProgramContainer#link()}
	 * @param program The program to use
	 */
	public static synchronized void useProgram(GLSLProgram program) {
		if (program == null) GL20.glUseProgram(0);
		else                 GL20.glUseProgram(program.getGLSLProgram());
	}

}
