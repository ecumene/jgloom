package org.jgloom.lwjgl;

import org.jgloom.io.images.TextureLoader;
import org.junit.Assert;
import org.junit.Test;
import org.lwjgl.opengl.GL11;

/**
 * Created by mh on 7/19/16.
 */
public class LWJGLEnumTest {
    @Test
    public void testLWJGLEnums() throws ReflectiveOperationException {
        LWJGLEnumStore.getInstance().store(TextureLoader.getInstance());
        Assert.assertEquals(GL11.GL_RGB8, TextureLoader.getInstance().RGB8);
        Assert.assertEquals(GL11.GL_RGBA8, TextureLoader.getInstance().RGBA8);
    }
}
