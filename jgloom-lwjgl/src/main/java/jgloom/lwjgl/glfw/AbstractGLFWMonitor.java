package jgloom.lwjgl.glfw;

import jgloom.glfw.GLFWMonitor;
import jgloom.glfw.GLFWWindow;
import org.lwjgl.glfw.GLFWGammaRamp;
import org.lwjgl.glfw.GLFWVidMode;

/**
 * A shell class containing functions for manipulating a given {@link GLFWWindow}
 */
public abstract class AbstractGLFWMonitor implements GLFWMonitor {
    private GLFWMonitor monitorInstance;

    /**
     * @param monitorInstance
     */
    public AbstractGLFWMonitor(GLFWMonitor monitorInstance){
        this.monitorInstance = monitorInstance;
    }

    /**This function returns the current video mode of the specified monitor. If you have created a full screen window
     * for that monitor, the return value will depend on whether that window is iconified.
     * @return The current mode of the monitor, or NULL if an error occurred.*/
    public abstract GLFWVidMode getVidMode();

    /**This function returns a human-readable name, encoded as UTF-8, of the specified monitor. The name typically
     * reflects the make and model of the monitor and is not guaranteed to be unique among the connected monitors.
     * @return The UTF-8 encoded name of the monitor, or NULL if an error occurred.*/
    public abstract String getName();

    /**
     * This function returns the position, in screen coordinates, of the upper-left corner of the specified monitor.
     * @return A concatenated integer array containing the monitors position (x,y or y,z or x,y,z ...)
     */
    public abstract int[] getPosition();

    /**
     * This function generates a 256-element gamma ramp from the specified exponent and then calls glfwSetGammaRamp with
     * it. The value must be a finite number greater than zero.
     * @param gamma The desired exponent
     */
    public abstract void setGamma(float gamma);

    /**
     * This function sets the current gamma ramp for the specified monitor. The original gamma ramp for that monitor is
     * saved by GLFW the first time this function is called and is restored by glfwTerminate.
     * @param ramp The gamma ramp to use.
     */
    public abstract void setGammaRamp(GLFWGammaRamp ramp);

    @Override
    public long getGLFWMonitor() {
        return monitorInstance.getGLFWMonitor();
    }

    public GLFWMonitor getMonitorInstance() {
        return monitorInstance;
    }
}
