package jgloom.io.models;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public interface ModelFace {
    Vector3f[] getVertices();
    
    Vector4f[] getGradients();
    
    Vector3f[] getNormals();
    
    Vector2f[] getTexCoords();
}
