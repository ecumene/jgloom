package jgloom.gl.functions.glslprogram;

import jgloom.gl.glsl.GLSLProgram;
import jgloom.lwjgl.gl.glsl.GLSLLinkException;
import jgloom.lwjgl.gl.glsl.GLSLProgramContainer;
import jgloom.lwjgl.gl.glsl.GLSLShaderContainer;

/**
 * Functions for GLSL programs
 * @see <a href="https://www.opengl.org/wiki/Shader"></a>
 */
public interface GLSLFProgram extends GLSLProgram {
    /**
     * Installs the program object specified by program​ as part of current rendering state. One or more executables are
     * created in a program object by successfully attaching shader objects to it with
     * {@link GLSLProgramContainer#attachGLSLShader(GLSLShader)}, successfully compiling the shader objects with
     * {@link GLSLShaderContainer#compileShader()}, and successfully linking the program object with
     * {@link GLSLProgramContainer#link()}
     */
    void use();

    /**
     * Validates the program
     */
    void validate();

    /**
     * Links the program object specified by program. A shader object of type GL_VERTEX_SHADER attached to program is
     * used to create an executable that will run on the programmable vertex processor. A shader object of type
     * GL_FRAGMENT_SHADER attached to program is used to create an executable that will run on the programmable fragment
     * processor.
     * @throws GLSLLinkException When the link status is false, usually because the program doesn't have a vertex and
     *                           fragment shader. Can also be because of the OpenGL context not supporting shaders or
     *                           not being created
     */
    void link();

    /**
     * Frees the memory and invalidates the name associated with the program object specified by program. This command
     * effectively undoes the effects of a call to {@link GLSLProgram#createProgram()}.
     */
    void delete();

}
