package jgloom.gl.functions.glslshader;

/**
 * Functions for GLSL shaders
 * @see <a href="https://www.opengl.org/wiki/Shader"></a>
 */
public interface GLSLFShaderGetLog extends GLSLFShader {
    /**
     * @return The shader's log from glGetShaderInfoLog
     */
    public String getLog();
}
