package jgloom.common.gl.glsl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import jgloom.GLSLCompileException;
import jgloom.gl.glsl.GLSLShader;

/**
 * A shell class containing functions for manipulating a given
 * {@link GLSLShader}
 */
public class GLSLShaderContainer implements GLSLShader {
	private GLSLShader shaderInstance;

	/**
	 * Initializes the GLSL shader container
	 * @param windowInstance The GLSL shader to track and contain
	 */
	public GLSLShaderContainer(GLSLShader shaderInstance) {
		this.shaderInstance = shaderInstance;
	}

	/**
	 * Sets the source of the shader to the given GLSL source code
	 * @param source
	 */
	public void uploadSource(String source) {
		GL20.glShaderSource(shaderInstance.getGLSLShader(), source);
	}

	/**
	 * Compiles the shader's GLSL source code and checks for errors
	 * @throws GLSLCompileException When the shader can not successfully compile
	 */
	public void compileShader() {
		GL20.glCompileShader(shaderInstance.getGLSLShader());
		int compileStatus = GL20.glGetShaderi(shaderInstance.getGLSLShader(), GL20.GL_COMPILE_STATUS);

		if (compileStatus == GL11.GL_FALSE) {
			String errorLog = GL20.glGetShaderInfoLog(shaderInstance.getGLSLShader());
			throw new GLSLCompileException(errorLog);
		}
	}

	/**
	 * Deltes the GLSL shader
	 */
	public void destroy() {
		GL20.glDeleteShader(shaderInstance.getGLSLShader());
	}

	@Override
	public int getGLSLShader() {
		return shaderInstance.getGLSLShader();
	}

	public GLSLShader getShaderInstance() {
		return shaderInstance;
	}
}
