package org.jgloom.lwjgl.gl.glsl;

import org.jgloom.gl.functions.glslprogram.GLSLFProgram;
import org.jgloom.gl.functions.glslprogram.GLSLFProgramAttachShader;
import org.jgloom.gl.functions.glslprogram.GLSLFProgramGetLog;
import org.jgloom.gl.functions.glslprogram.GLSLFProgramUniformLocation;
import org.jgloom.gl.glsl.GLSLProgram;

/**
 * A shell class containing functions for manipulating a given
 * {@link GLSLProgram}
 */
public abstract class AbstractGLSLProgram implements GLSLFProgram, GLSLFProgramAttachShader, GLSLFProgramGetLog,
        GLSLFProgramUniformLocation{
    private GLSLProgram programInstance;

    /** @param programInstance The shader-program to track */
    public AbstractGLSLProgram(GLSLProgram programInstance){
        this.programInstance = programInstance;
    }

    @Override
    public int getGLSLProgram() {
        return programInstance.getGLSLProgram();
    }

    public GLSLProgram getProgramInstance() {
        return programInstance;
    }
}
