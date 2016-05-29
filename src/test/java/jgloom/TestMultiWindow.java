package jgloom;

import static org.lwjgl.opengl.GL11.*;

import jgloom.common.SharedLibraryLoader;
import jgloom.glfw.GLFWWindow;
import jgloom.common.glfw.GLFWWindowContainer;

import org.junit.Test;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public class TestMultiWindow {
    public GLFWWindow window1;
    public GLFWWindow window2;

    @Test
    public void testGLMultiWindow() {
        SharedLibraryLoader.load();
        GLFWWindow.init();
        GLFWWindow.hint(GLFW.GLFW_VISIBLE, GL11.GL_FALSE);
        window1 = new GLFWWindowContainer(GLFWWindow.createWindow(800, 450, "Window 1", 0L, 0L));
        GLFWWindow.makeContextCurrent(window1);
        GLContext.createFromCurrent();
        window2 = new GLFWWindowContainer(GLFWWindow.createWindow(800, 450, "Window 2", 0L, 0L));
        GLFWWindow.makeContextCurrent(window2);
        GLContext.createFromCurrent();

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
