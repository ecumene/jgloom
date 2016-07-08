package jgloom.gl.functions.glslprogram;

/**
 * Functions for GLSL programs
 * @see <a href="https://www.opengl.org/wiki/Shader"></a>
 */
public interface GLSLFProgramGetLog extends GLSLFProgram {
    /** @return The program's log from glGetProgramInfoLog */
    String getLog();
}
