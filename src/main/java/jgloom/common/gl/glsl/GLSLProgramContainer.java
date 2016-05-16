package jgloom.common.gl.glsl;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL20;

import jgloom.GLOOMException;
import jgloom.gl.glsl.GLSLProgram;
import jgloom.gl.glsl.GLSLShader;

/**
 * A shell class containing functions for manipulating a given
 * {@link GLSLProgram}
 */
public class GLSLProgramContainer implements GLSLProgram {
	private GLSLProgram programInstance;
	private List<GLSLShader> attachedShaders;

	/**
	 * Initializes the GLSL program container
	 * @param programInstance The GLSL program to track and contain
	 */
	public GLSLProgramContainer(GLSLProgram programInstance) {
		this.programInstance = programInstance;
		attachedShaders = new ArrayList<GLSLShader>();
	}

	/**
	 * Attaches the given {@link GLSLShader} to the GLSL program to allow the
	 * shader to be used
	 * @param shader
	 */
	public void attachGLSLShader(GLSLShader shader) {
		GL20.glAttachShader(programInstance.getGLSLProgram(), shader.getGLSLShader());
		attachedShaders.add(shader);
	}

	/**
	 * Detaches the given {@link GLSLShader} from the GLSL program so that it no
	 * longer functions along side the GLSL program
	 * @param shader
	 * @throws GLOOMException If the shader wasn't bound in the first place (or
	 *             if the shader was attached before the container was assigned
	 *             to the program)
	 */
	public void detachGLSLShader(GLSLShader shader) throws GLOOMException {
		GL20.glDetachShader(programInstance.getGLSLProgram(), shader.getGLSLShader());
		if (attachedShaders.contains(shader))
			attachedShaders.remove(shader);
		else
			throw new GLOOMException("Shader is not tracked as attached");
	}

	/**
	 * @return Array of all attached {@link GLSLShader}s which were attached
	 *         while the container was handling the program
	 */
	public GLSLShader[] getAttachedShaders() {
		return attachedShaders.toArray(new GLSLShader[0]);
	}

	/**
	 * Deletes the GLSL program
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
