package jgloom.gl.functions.glslprogram;

import jgloom.gl.glsl.GLSLProgram;
/**
 * Functions for GLSL programs
 * @see <a href="https://www.opengl.org/wiki/Shader"></a>
 */
public interface GLSLFProgram extends GLSLProgram {
    /**
     * Installs the program object specified by program​ as part of current rendering state. One or more executables are
     * created in a program object by successfully attaching shader objects to it with
     * attaching a program, successfully compiling the shader objects with
     * {@link jgloom.gl.functions.glslshader.GLSLFShader#compile()}, and successfully linking the program object with
     * {@link #link()}
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
     */
    void link();

    /**
     * Frees the memory and invalidates the name associated with the program object specified by program. This command
     * effectively undoes the effects of a call to the create program method.
     */
    void delete();

}
