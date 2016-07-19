package org.jgloom.gl.glsl;

/**
 * A GLSL Object is an object in the OpenGL API that encapsulates the compiled or linked Shaders that execute portions
 * of the OpenGL Pipeline. These objects represent code written in the OpenGL Shading Language (GLSL). Though they are
 * called "objects", most of them do not fit within the OpenGL Object paradigm.
 * @see <a href=https://www.opengl.org/wiki/GLSL_Object>opengl.org - GLSL Objects</a>
 */
@FunctionalInterface
public interface GLSLProgram {
    /** @return The GLSL program identifier */
    int getGLSLProgram();
}
