package jgloom.io.models;

import org.joml.Vector4f;

public interface ModelMaterial {
    Vector4f getAmbient();
    
    Vector4f getDiffuse();
    
    Vector4f getSpecular();
}
