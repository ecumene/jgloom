package jgloom.common.gl.glsl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import jgloom.gl.glsl.GLSLShader;

/**
 * A shell class containing functions for manipulating a given
 * {@link GLSLShader}
 */
public class GLSLShaderContainer implements GLSLShader {
    private GLSLShader shaderInstance;

    /**
     * Initializes the GLSL shader container
     * @param shaderInstance The GLSL shader to track and contain
     */
    public GLSLShaderContainer(GLSLShader shaderInstance) {
        this.shaderInstance = shaderInstance;
    }

    /**
     * Sets the source code in shader​ to the source code in the array of strings specified by string​. Any source code
     * previously stored in the shader object is completely replaced. The number of strings in the array is specified
     * by count​. If length​ is NULL, each string is assumed to be null terminated. If length​ is a value other than NULL,
     * it points to an array containing a string length for each of the corresponding elements of string​. Each element in
     * the length​ array may contain the length of the corresponding string (the null character is not counted as part of
     * the string length) or a value less than 0 to indicate that the string is null terminated. The source code strings
     * are not scanned or parsed at this time; they are simply copied into the specified shader object.
     * @param source The source to replace the old shader with
     */
    public void uploadSource(String source) {
        GL20.glShaderSource(getGLSLShader(), source);
    }

    /**
     * Compiles the source code strings that have been stored in the shader object specified by shader​.
     * @throws GLSLCompileException When the shader can not successfully compile (usually GLSL errors)
     */
    public void compileShader() throws GLSLCompileException {
        GL20.glCompileShader(getGLSLShader());
        if(!isCompiled()) throw new GLSLCompileException(getLog());
    }

    /** @return If GL_COMPILE_STATUS is not false */
    public boolean isCompiled(){
        return GL20.glGetShaderi(getGLSLShader(), GL20.GL_COMPILE_STATUS) != GL11.GL_FALSE;
    }

    /** @return The shader's log from glGetShaderInfoLog */
    public String getLog(){
        return GL20.glGetShaderInfoLog(getGLSLShader(), GL20.glGetShaderi(getGLSLShader(), GL20.GL_INFO_LOG_LENGTH));
    }

    /**
     * Frees the memory and invalidates the name associated with the shader object specified by shader. This command
     * effectively undoes the effects of a call to {@link GLSLShader#createShader(int)}.
     */
    public void delete() {
        GL20.glDeleteShader(getGLSLShader());
    }

    @Override
    public int getGLSLShader() {
        return shaderInstance.getGLSLShader();
    }

    public GLSLShader getShaderInstance() {
        return shaderInstance;
    }
}
