package jgloom;

import jgloom.common.gl.GLBufferContainer;
import jgloom.common.gl.legacy.ColorArrayPointer;
import jgloom.common.gl.legacy.NormalArrayPointer;
import jgloom.common.gl.legacy.TexCoordArrayPointer;
import jgloom.common.gl.legacy.VertexArrayPointer;
import jgloom.gl.GLBuffer;
import jgloom.glfw.GLFWWindow;
import org.junit.Test;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class TestGLLegacyVertexArray {
    @Test
    public void testGLLegacyVertexUpload() throws GLOOMException {
        TestGLUtil.openContext();

        float[] vertices = new float[]{
                0, 0, 0, 1,
                1, 0, 0, 1,
                0, 1, 0, 1
        };

        float[] normals = new float[]{
                0, 0, 1,
                0, 0, 1,
                0, 0, 1
        };

        short[] texCoords = new short[]{
                0, 0,
                1, 0,
                0, 1
        };

        float[] colors = new float[]{
                0xF, 0, 0xF,
                0,   0, 0xF,
                0xF, 0, 0
        };

        GLBufferContainer verticesBuffer = new GLBufferContainer(GLBuffer.createBuffer());
        verticesBuffer.bind(GL15.GL_ARRAY_BUFFER);
        verticesBuffer.data(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);
        GLBufferContainer normalsBuffer  = new GLBufferContainer(GLBuffer.createBuffer());
        normalsBuffer.bind(GL15.GL_ARRAY_BUFFER);
        normalsBuffer.data(GL15.GL_ARRAY_BUFFER, normals, GL15.GL_STATIC_DRAW);
        GLBufferContainer texCoordBuffer = new GLBufferContainer(GLBuffer.createBuffer());
        texCoordBuffer.bind(GL15.GL_ARRAY_BUFFER);
        texCoordBuffer.bind(GL15.GL_ARRAY_BUFFER);
        texCoordBuffer.data(GL15.GL_ARRAY_BUFFER, texCoords, GL15.GL_STATIC_DRAW);
        GLBufferContainer colorBuffer    = new GLBufferContainer(GLBuffer.createBuffer());
        colorBuffer.bind(GL15.GL_ARRAY_BUFFER);
        colorBuffer.bind(GL15.GL_ARRAY_BUFFER);
        colorBuffer.data(GL15.GL_ARRAY_BUFFER, colors, GL15.GL_STATIC_DRAW);

        VertexArrayPointer   vertexPointer      = new VertexArrayPointer  (3, GL11.GL_FLOAT, 0, 0);
        TexCoordArrayPointer texCoordsPointer   = new TexCoordArrayPointer(2, GL11.GL_SHORT, 0, 0);
        ColorArrayPointer    colorArrayPointer  = new ColorArrayPointer   (3, GL11.GL_FLOAT,  0, 0);
        NormalArrayPointer   normalArrayPointer = new NormalArrayPointer  (   GL11.GL_FLOAT, 0, 0);

        GL11.glClearColor(1, 1, 1, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        verticesBuffer.bind(GL15.GL_ARRAY_BUFFER);
        vertexPointer.enable();

        texCoordBuffer.bind(GL15.GL_ARRAY_BUFFER);
        texCoordsPointer.enable();

        colorBuffer.bind(GL15.GL_ARRAY_BUFFER);
        colorArrayPointer.enable();

        normalsBuffer.bind(GL15.GL_ARRAY_BUFFER);
        normalArrayPointer.enable();

        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);

        vertexPointer.disable();


        TestGLUtil.getWindow().swapBuffers();
        GLFWWindow.pollEvents();
        TestGLUtil.closeContext();
    }

    public static void main(String[] args) throws Throwable {
        new TestGLLegacyVertexArray().testGLLegacyVertexUpload();
    }
}
