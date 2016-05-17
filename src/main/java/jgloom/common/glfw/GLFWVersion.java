package jgloom.common.glfw;

/**
 * Describes the 3 components of a GLFW version (major, minor, and patch)
 */
public interface GLFWVersion {
    public int getMajorVersion();
    public int getMinorVersion();
    public int getPatchVersion();
}
