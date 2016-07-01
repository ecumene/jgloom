package jgloom.lwjgl.gl.glsl;

import jgloom.gl.functions.glslshader.GLSLFShader;
import jgloom.gl.functions.glslshader.GLSLFShaderGetLog;
import jgloom.gl.functions.glslshader.GLSLFShaderUploadSource;
import jgloom.gl.glsl.GLSLProgram;
import jgloom.gl.glsl.GLSLShader;

/**
 * A shell class containing functions for manipulating a given
 * {@link GLSLProgram}
 */
public abstract class AbstractGLSLShader implements GLSLFShader, GLSLFShaderGetLog, GLSLFShaderUploadSource {
    private GLSLShader shaderInstance;

    /**
     * Constructs a shell class containing functions for manipulating a given
     * {@link GLSLProgram}
     */
    public AbstractGLSLShader(GLSLShader shaderInstance){
        this.shaderInstance = shaderInstance;
    }

    @Override
    public int getGLSLShader() {
        return shaderInstance.getGLSLShader();
    }

    public GLSLShader getShaderInstance() {
        return shaderInstance;
    }
}
