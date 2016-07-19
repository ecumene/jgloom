package org.jgloom.lwjgl.gl.glsl;

import org.jgloom.gl.glsl.GLSLShader;
import org.lwjgl.opengl.GL20;

/**
 * A shell class containing LWJGL functions for manipulating a given
 * {@link GLSLShader}
 */
public class GLSLShaderContainer extends AbstractGLSLShader {
    /**
     * Initializes the LWJGL GLSL shader container
     * @param shaderInstance The GLSL shader to track and contain
     */
    public GLSLShaderContainer(GLSLShader shaderInstance) {
        super(shaderInstance);
    }

    @Override
    public void uploadSource(String source) {
        GL20.glShaderSource(getGLSLShader(), source);
    }

    @Override
    public void compile() throws GLSLCompileException {
        GL20.glCompileShader(getGLSLShader());
    }

    @Override
    public String getLog(){
        return GL20.glGetShaderInfoLog(getGLSLShader(), GL20.glGetShaderi(getGLSLShader(), GL20.GL_INFO_LOG_LENGTH));
    }

    @Override
    public void delete() {
        GL20.glDeleteShader(getGLSLShader());
    }

}
