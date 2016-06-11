package jgloom.common.glfw;

import jgloom.glfw.GLFWMonitor;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWGammaRamp;
import org.lwjgl.glfw.GLFWVidMode;

/**
 * A shell class containing functions for manipulating a given {@link jgloom.glfw.GLFWMonitor}
 */
public class GLFWMonitorContainer extends AbstractGLFWMonitor {
    /**
     * Initializes the GLFW monitor container
     * @param monitorInstance The GLFW monitor to track and contain
     */
    public GLFWMonitorContainer(GLFWMonitor monitorInstance){
        super(monitorInstance);
    }

    @Override
    public GLFWVidMode getVidMode() {
        return GLFW.glfwGetVideoMode(getGLFWMonitor());
    }

    @Override
    public String getName() {
        return GLFW.glfwGetMonitorName(getGLFWMonitor());
    }

    @Override
    public int[] getPosition() {
        int[] xpos = null, ypos = null;
        GLFW.glfwGetMonitorPos(getGLFWMonitor(), xpos, ypos);
        return ArrayUtils.addAll(xpos, ypos);
    }

    @Override
    public void setGamma(float gamma) {
        GLFW.glfwSetGamma(getGLFWMonitor(), gamma);
    }

    @Override
    public void setGammaRamp(GLFWGammaRamp ramp) {
        GLFW.glfwSetGammaRamp(getGLFWMonitor(), ramp);
    }
}
