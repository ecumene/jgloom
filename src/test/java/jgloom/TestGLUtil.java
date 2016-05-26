package jgloom;

import jgloom.common.SharedLibraryLoader;
import jgloom.common.glfw.GLFWWindowContainer;
import jgloom.glfw.GLFWWindow;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public class TestGLUtil {
    private static GLFWWindowContainer window;

    public static void openContext(){
        SharedLibraryLoader.load();
        GLFWWindow.init();
        window = new GLFWWindowContainer(GLFWWindow.createWindow(640, 480, "GLFW Window", 0L, 0L));

        GLFWWindow.defaultWindowHints();
        GLFWWindow.hint(GLFW.GLFW_SAMPLES, 4);
        GLFWWindow.hint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFWWindow.hint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2);
        GLFWWindow.hint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE);
        GLFWWindow.hint(GLFW.GLFW_OPENGL_PROFILE,        GLFW.GLFW_OPENGL_CORE_PROFILE);
        GLFWWindow.hint(GLFW.GLFW_RESIZABLE,             GL11.GL_FALSE);
        GLFWWindow.makeContextCurrent(window);

        GLContext.createFromCurrent();
    }

    public static void closeContext(){
        window.destroy();
        GLFWWindow.terminate();
    }
}