package org.jgloom.io.models;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

/**
 * An interface representing one face of a model, consisting of vertices, gradients, normals, and texture coordinates;
 * this class is subject to be changed or rewritten
 */
public interface ModelFace {
    /**
     * @return Vertices making up the face in 3D space (may be replaced with 4D vector)
     */
    Vector3f[] getVertices();
    
    /**
     * @return Gradient RGBA color values, usually for each of the vertices
     */
    Vector4f[] getGradients();
    
    /**
     * @return Vertex normals which specify which way the face is facing
     */
    Vector3f[] getNormals();
    
    /**
     * @return Texture coordinates, usually for each of the vertices, as a 2D vector
     */
    Vector2f[] getTexCoords();
}
