package jgloom;

import jgloom.gl.glsl.GLSLProgram;
import jgloom.gl.glsl.GLSLShader;
import jgloom.glfw.GLFWWindow;
import org.junit.Test;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import jgloom.common.gl.glsl.GLSLProgramContainer;
import jgloom.common.gl.glsl.GLSLShaderContainer;

public class TestGLSLProgram {
    private String fragmentSrc = "varying vec4 vertColor;\n" +
                                 "\n" +
                                 "void main(){\n" +
                                 "    gl_FragColor = vertColor;\n" +
                                 "}";
    private String vertexSrc = "varying vec4 vertColor;\n" +
                                "\n" +
                                "void main(){\n" +
                                "    gl_Position = gl_ModelViewProjectionMatrix*gl_Vertex;\n" +
                                "    vertColor = vec4(0.6, 0.3, 0.4, 1.0);\n" +
                                "}";

    private GLSLProgramContainer program;

    @Test
    public void testGLSLProgram() {
        TestGLUtil.openContext();

        GLSLShaderContainer fragment = new GLSLShaderContainer(GLSLShader.createShader(GL20.GL_FRAGMENT_SHADER));
        fragment.uploadSource(fragmentSrc);
        fragment.compileShader();
        GLSLShaderContainer vertex = new GLSLShaderContainer(GLSLShader.createShader(GL20.GL_VERTEX_SHADER));
        vertex.uploadSource(vertexSrc);
        vertex.compileShader();

        program = new GLSLProgramContainer(GLSLProgram.createProgram());
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


        program.destroy();
        TestGLUtil.closeContext();
    }
}
