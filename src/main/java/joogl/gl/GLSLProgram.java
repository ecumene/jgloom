package joogl.gl;

/**
 * A GLSL Object is an object in the OpenGL API that encapsulates the compiled or linked Shaders that execute portions
 * of the OpenGL Pipeline. These objects represent code written in the OpenGL Shading Language (GLSL). Though they are
 * called "objects", most of them do not fit within the OpenGL Object paradigm.
 */
public interface GLSLProgram {
    /** @return The GLSL program identifier */
    public int getGLSLProgram();
}
