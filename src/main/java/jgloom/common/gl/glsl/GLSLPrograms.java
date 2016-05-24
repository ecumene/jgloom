package jgloom.common.gl.glsl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import jgloom.gl.glsl.GLSLProgram;

/**
 * Handles operations such as creating and setting the currently used
 * {@link GLSLProgram}
 */
public class GLSLPrograms {

    /**
     * @return The currently bound shader program set by {@link GLSLProgramContainer#use()}
     */
    public static synchronized GLSLProgram getCurrentProgram(){
        return new GLSLProgram() {
            @Override
            public int getGLSLProgram() {
                return GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM);
            }
        };
    }

    /**
     * Creates an empty GLSL program for attaching shaders to and using for
     * rendering
     * @return A generated GLSL program using glCreateProgram
     */
    public static synchronized GLSLProgram createProgram() {
        return new GLSLProgram() {
            @Override
            public int getGLSLProgram() {
                return GL20.glCreateProgram();
            }
        };
    }

}
