package jgloom.gl.functions.glslshader;

import jgloom.gl.glsl.GLSLShader;
import jgloom.lwjgl.gl.glsl.GLSLCompileException;

/**
 * Functions for GLSL shaders
 * @see <a href="https://www.opengl.org/wiki/Shader"></a>
 */
public interface GLSLFShader extends GLSLShader {
    /**
     * Compiles the source code strings that have been stored in the shader object specified by shader​.
     * @throws GLSLCompileException When the shader can not successfully compile (usually GLSL errors)
     */
    public void compile();

    /**
     * Frees the memory and invalidates the name associated with the shader object specified by shader. This command
     * effectively undoes the effects of a call to {@link GLSLShader#createShader(int)}.
     */
    public void delete();
}
