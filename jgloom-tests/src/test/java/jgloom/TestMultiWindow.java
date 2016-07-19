package jgloom;

import static org.lwjgl.opengl.GL11.*;

import jgloom.lwjgl.SharedLibraryLoader;
import jgloom.concurrent.NonConcurrentTest;
import jgloom.concurrent.RunInThread;
import jgloom.lwjgl.glfw.GLFWWindow;
import jgloom.lwjgl.glfw.GLFWWindowContainer;

import org.junit.Test;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class TestMultiWindow extends NonConcurrentTest {
    public GLFWWindow window1;
    public GLFWWindow window2;

    @Test
    @RunInThread
    public void testGLMultiWindow() {
        SharedLibraryLoader.load();
        GLFWWindow.init();
        GLFWWindow.hint(GLFW.GLFW_VISIBLE, GL11.GL_FALSE);
        window1 = new GLFWWindowContainer(GLFWWindow.createWindow(800, 450, "Window 1", 0L, 0L));
        GLFWWindow.makeContextCurrent(window1);
        GL.createCapabilities();
        window2 = new GLFWWindowContainer(GLFWWindow.createWindow(800, 450, "Window 2", 0L, 0L));
        GLFWWindow.makeContextCurrent(window2);
        GL.createCapabilities();

        GLFWWindow.makeContextCurrent(window1);
        glClearColor(1, 0, 0, 1);
        glClear(GL_COLOR_BUFFER_BIT);
        ((GLFWWindowContainer) window1).swapBuffers();

        GLFWWindow.makeContextCurrent(window2);
        glClearColor(0, 1, 0, 1);
        glClear(GL_COLOR_BUFFER_BIT);
        ((GLFWWindowContainer) window2).swapBuffers();

        ((GLFWWindowContainer) window1).destroy();
        ((GLFWWindowContainer) window2).destroy();

        GLFWWindow.terminate();
    }
}
