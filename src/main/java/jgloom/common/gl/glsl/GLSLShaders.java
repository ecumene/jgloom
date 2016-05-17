package jgloom.common.gl.glsl;

import org.lwjgl.opengl.GL20;

import jgloom.gl.glsl.GLSLShader;

/**
 * Handles operations such as creation and binding of {@link GLSLShader}s
 */
public class GLSLShaders {
	/**
	 * Creates an empty {@link GLSLShader} of the given type
	 * @param type
	 * @return New {@link GLSLShader} object
	 */
	public static synchronized GLSLShader createShader(int type) {
		final int shader = GL20.glCreateShader(type);
		return new GLSLShader() {
			@Override
			public int getGLSLShader() {
				return shader;
			}
		};
	}
}
