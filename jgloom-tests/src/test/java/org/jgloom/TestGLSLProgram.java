package org.jgloom;

import org.jgloom.concurrent.NonConcurrentTest;
import org.jgloom.concurrent.RunInThread;
import org.jgloom.lwjgl.gl.glsl.GLSLProgramContainer;
import org.jgloom.lwjgl.gl.glsl.GLSLShaderContainer;
import org.jgloom.lwjgl.gl.glsl.LWJGLGLSLPrograms;
import org.jgloom.lwjgl.gl.glsl.LWJGLGLSLShaders;
import org.junit.Test;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class TestGLSLProgram extends NonConcurrentTest {
    private String fragmentSrc = "varying vec4 vertColor;\n" +
                                 "void main(){\n" +
                                 "    gl_FragColor = vertColor;\n" +
                                 "}";
    private String vertexSrc = "varying vec4 vertColor;\n" +
                                "void main(){\n" +
                                "    gl_Position = gl_ModelViewProjectionMatrix*gl_Vertex;\n" +
                                "    vertColor = vec4(0.6, 0.3, 0.4, 1.0);\n" +
                                "}";

    private GLSLProgramContainer program;

    @Test
    @RunInThread
    public void testGLSLProgram() {
        TestGLUtil.openContext();

        GLSLShaderContainer fragment = new GLSLShaderContainer(LWJGLGLSLShaders.createShader(GL20.GL_FRAGMENT_SHADER));
        fragment.uploadSource(fragmentSrc);
        fragment.compile();
        GLSLShaderContainer vertex = new GLSLShaderContainer(LWJGLGLSLShaders.createShader(GL20.GL_VERTEX_SHADER));
        vertex.uploadSource(vertexSrc);
        vertex.compile();

        program = new GLSLProgramContainer(LWJGLGLSLPrograms.createProgram());
        program.attachGLSLShader(vertex);
        program.attachGLSLShader(fragment);
        program.link();

        vertex.delete();
        fragment.delete();
        program.use();

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        GL11.glBegin(GL11.GL_TRIANGLES);
        GL11.glVertex2f(-1, -1);
        GL11.glVertex2f(1, -1);
        GL11.glVertex2f(0, 1);
        GL11.glEnd();

        program.delete();

        TestGLUtil.closeContext();
    }
}
