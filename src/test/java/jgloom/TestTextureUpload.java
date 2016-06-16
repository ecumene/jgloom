package jgloom;

import jgloom.common.gl.GLTextureContainer;
import jgloom.gl.GLTexture;
import org.junit.Test;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;

public class TestTextureUpload {
    @Test
    public void testTextureUpload(){
        TestGLUtil.openContext();
        float[] texture = new float[]{
                0.1f, 0.5f, 0.5f,
                0.1f, 0.5f, 0.5f,
                0.1f, 0.5f, 0.5f,

                0.0f, 0.0f, 0.1f,
                0.0f, 0.0f, 0.1f,
                0.0f, 0.0f, 0.1f,

                0.0f, 0.1f, 0.0f,
                0.0f, 0.1f, 0.0f,
                0.0f, 0.1f, 0.0f,
        };

        FloatBuffer buffer = BufferUtils.createFloatBuffer(3*3*3);
        buffer.put(texture);
        buffer.flip();

        GLTextureContainer textureContainer = new GLTextureContainer(GLTexture.createTexture());
        textureContainer.bind(GL11.GL_TEXTURE_2D);
        textureContainer.image2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, 3, 3, 0, GL11.GL_RGB, GL11.GL_FLOAT, buffer);
        textureContainer.parameter(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        textureContainer.parameter(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);

        textureContainer.delete();

        TestGLUtil.closeContext();
    }
}
