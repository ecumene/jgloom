package jgloom;

import jgloom.glfw.GLFWWindowContainer;
import jgloom.glfw.GLFWWindows;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public class TestGLUtil {
    private static GLFWWindowContainer window;

    public static void openContext(){
        SharedLibraryLoader.load();
        GLFWWindows.init();
        window = new GLFWWindowContainer(GLFWWindows.createWindow(640, 480, "GLFW Window", 0L, 0L));

        GLFWWindows.defaultWindowHints();
        GLFWWindows.hint(GLFW.GLFW_SAMPLES, 4);
        GLFWWindows.hint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFWWindows.hint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2);
        GLFWWindows.hint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE);
        GLFWWindows.hint(GLFW.GLFW_OPENGL_PROFILE,        GLFW.GLFW_OPENGL_CORE_PROFILE);
        GLFWWindows.hint(GLFW.GLFW_RESIZABLE,             GL11.GL_FALSE);
        GLFWWindows.makeContextCurrent(window);

        GLContext.createFromCurrent();
    }

    public static void closeContext(){
        window.destroy();
        GLFWWindows.terminate();
    }
}