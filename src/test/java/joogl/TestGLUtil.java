package joogl;

import joogl.SharedLibraryLoader;
import org.lwjgl.opengl.GLContext;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.system.MemoryUtil.NULL;

public class TestGLUtil {
    private static long windowID = 0L;

    public static void openContext(){
        SharedLibraryLoader.load();
        if (glfwInit() != GL_TRUE)
            throw new RuntimeException("Error creating GLFW context...");
        windowID = glfwCreateWindow(640, 480, "GLFW Window", NULL, NULL);
        if (windowID == NULL)
            throw new RuntimeException("Couldn't create GLFW window"); // Unchecked exception

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_SAMPLES, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
        glfwMakeContextCurrent(windowID);

        GLContext.createFromCurrent();
        glfwSwapInterval(1);
    }

    public static void closeContext(){
        glfwDestroyWindow(windowID);
        glfwTerminate();
    }
}
