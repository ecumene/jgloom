package jgloom.io;

import jgloom.GLNativeException;
import jgloom.io.concurrent.NonConcurrentTest;
import jgloom.glfw.GLFWWindow;
import jgloom.lwjgl.SharedLibraryLoader;
import jgloom.lwjgl.glfw.GLFWWindowContainer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class TestGLUtil extends NonConcurrentTest {
    private static GLFWWindowContainer window;

    public static void openContext(){
        SharedLibraryLoader.load();
        GLFWWindow.init();
        GLFWWindow.hint(GLFW.GLFW_VISIBLE,               GL11.GL_FALSE);
        window = new GLFWWindowContainer(GLFWWindow.createWindow(640, 480, "GLFW Window", 0L, 0L));

        GLFWWindow.defaultWindowHints();
        GLFWWindow.hint(GLFW.GLFW_SAMPLES, 4);
        GLFWWindow.hint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFWWindow.hint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2);
        GLFWWindow.hint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE);
        GLFWWindow.hint(GLFW.GLFW_OPENGL_PROFILE,        GLFW.GLFW_OPENGL_CORE_PROFILE);
        GLFWWindow.hint(GLFW.GLFW_RESIZABLE,             GL11.GL_FALSE);
        GLFWWindow.makeContextCurrent(window);
        GLFWWindow.setErrorCallback(new GLFWErrorCallback() {
            @Override
            public void invoke(int error, long description) {
                throw new GLNativeException("GLFW Error: " + error + " description: " + description);
            }
        });

        GL.createCapabilities();
    }

    public static GLFWWindowContainer getWindow() {
        return window;
    }

    public static void closeContext(){
        GLNativeException.checkOGL();
        window.destroy();
        GLFWWindow.terminate();
    }
}
