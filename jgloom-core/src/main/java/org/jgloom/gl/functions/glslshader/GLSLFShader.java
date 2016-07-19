package org.jgloom.gl.functions.glslshader;

import org.jgloom.gl.glsl.GLSLShader;

/**
 * Functions for GLSL shaders
 * @see <a href="https://www.opengl.org/wiki/Shader"></a>
 */
public interface GLSLFShader extends GLSLShader {
    /**
     * Compiles the source code strings that have been stored in the shader object specified by shader​.
     */
    void compile();

    /**
     * Frees the memory and invalidates the name associated with the shader object specified by shader. This command
     * effectively undoes the effects of a call to the create shader function.
     */
    void delete();
}
