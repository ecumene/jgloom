package jgloom.io.models;

import org.joml.Vector4f;

/**
 * An interface representing a model material; usually loaded from material files and used in model faces
 */
public interface ModelMaterial {
    /**
     * @return Ambient RGBA color value of the material
     */
    Vector4f getAmbient();
    
    /**
     * @return Diffuse RGBA color value of the material
     */
    Vector4f getDiffuse();
    
    /**
     * @return Specular RGBA color value of the material
     */
    Vector4f getSpecular();
}
