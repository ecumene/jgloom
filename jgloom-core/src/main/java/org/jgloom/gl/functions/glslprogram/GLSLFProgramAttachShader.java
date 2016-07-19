package org.jgloom.gl.functions.glslprogram;

import org.jgloom.gl.glsl.GLSLShader;

/**
 * Functions for GLSL programs
 * @see <a href="https://www.opengl.org/wiki/Shader"></a>
 */
public interface GLSLFProgramAttachShader extends GLSLFProgram {
    /**
     * In order to create a complete shader program, there must be a way to specify the list of things that will be
     * linked together. Program objects provide this mechanism. Shaders that are to be linked together in a program
     * object must first be attached to that program object. This attaches the shader object specified by shader to
     * the program object specified by program. This indicates that shader will be included in link operations that
     * will be performed on program.
     * @param shader The shader to attach
     */
    void attachGLSLShader(GLSLShader shader);

    /**
     * Detaches the shader object specified by shader from the program object specified by program. This command can be
     * used to undo the effect of the command {@link #attachGLSLShader(GLSLShader)}.
     * @param shader The shader to detach
     */
    void detachGLSLShader(GLSLShader shader);
}
