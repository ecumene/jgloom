package jgloom.gl.functions.glslprogram;

/**
 * Functions for GLSL programs
 * @see <a href="https://www.opengl.org/wiki/Shader"></a>
 */
public interface GLSLFProgramUniformLocation extends GLSLFProgram {
    /**
     * Find the given uniform location
     * @param name The name of the uniform
     * @return The uniform's location
     */
    int getUniformLocation(String name);
}
