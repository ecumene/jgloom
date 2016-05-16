package jgloom.common.gl.glsl;

import jgloom.GLNativeException;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import jgloom.gl.glsl.GLSLProgram;
import jgloom.gl.glsl.GLSLShader;

/**
 * A shell class containing functions for manipulating a given
 * {@link GLSLProgram}
 */
public class GLSLProgramContainer implements GLSLProgram {
	private GLSLProgram programInstance;

	/**
	 * Initializes the GLSL program container
	 * @param programInstance The GLSL program to track and contain
	 */
	public GLSLProgramContainer(GLSLProgram programInstance) {
		this.programInstance = programInstance;
	}

	/**
	 * In order to create a complete shader program, there must be a way to specify the list of things that will be
	 * linked together. Program objects provide this mechanism. Shaders that are to be linked together in a program
	 * object must first be attached to that program object. This attaches the shader object specified by shader to
	 * the program object specified by program. This indicates that shader will be included in link operations that
	 * will be performed on program.
	 * @param shader The shader to attach
	 */
	public void attachGLSLShader(GLSLShader shader) {
		GL20.glAttachShader(programInstance.getGLSLProgram(), shader.getGLSLShader());
	}

	/**
	 * Detaches the shader object specified by shader from the program object specified by program. This command can be
	 * used to undo the effect of the command {@link GLSLProgramContainer#attachGLSLShader(GLSLShader)}.
	 * @param shader The shader to detach
	 */
	public void detachGLSLShader(GLSLShader shader) {
		GL20.glDetachShader(programInstance.getGLSLProgram(), shader.getGLSLShader());
	}

	/**
	 * Links the program object specified by program. A shader object of type GL_VERTEX_SHADER attached to program is
	 * used to create an executable that will run on the programmable vertex processor. A shader object of type
	 * GL_FRAGMENT_SHADER attached to program is used to create an executable that will run on the programmable fragment
	 * processor.
	 */
	public void link(){
		GL20.glLinkProgram(getGLSLProgram());
		int linkStatus = GL20.glGetProgrami(getGLSLProgram(), GL20.GL_LINK_STATUS);
		if (linkStatus == GL11.GL_FALSE)
			// I've uploaded a vertex shader as a fragment shader type too many times...
			throw new GLNativeException("Could not link program, invalid shader types?");
	}

	/**
	 * Frees the memory and invalidates the name associated with the program object specified by program. This command
	 * effectively undoes the effects of a call to {@link GLSLPrograms#createProgram()}.
	 */
	public void destroy() {
		GL20.glDeleteProgram(programInstance.getGLSLProgram());
	}

	@Override
	public int getGLSLProgram() {
		return programInstance.getGLSLProgram();
	}

	public GLSLProgram getProgramInstance() {
		return programInstance;
	}
}
