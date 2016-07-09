package jgloom.io.models.obj;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector4f;

import jgloom.io.models.ModelFace;

// FIXME Add support for more vertex attributes
/**
 * Simple class for retaining wavefront OBJ data while reading and parsing the OBJ file
 */
public class OBJBuilder {
    /**
     * Stored vertices to reference later
     */
    private List<Vector3f> vertices;
    
    /**
     * Stored normals to reference later
     */
    private List<Vector3f> normals;
    
    /**
     * Indices to reference vertices and normals
     */
    private List<Vector2i> indices;

    /**
     * Creates an empty OBJ building object
     */
    public OBJBuilder() {
        vertices = new ArrayList<Vector3f>();
        normals = new ArrayList<Vector3f>();
        indices = new ArrayList<Vector2i>();
    }

    /**
     * @return Data compiled into a list of model faces, taking into account indices
     */
    public List<ModelFace> generateFaces() {
        List<ModelFace> faces = new ArrayList<ModelFace>();

        for (int i = 0; i < indices.size(); i += 3) {
            Vector2i i1 = indices.get(i);
            Vector2i i2 = indices.get(i + 1);
            Vector2i i3 = indices.get(i + 2);
            Vector3f v1 = vertices.get((Integer) i1.x);
            Vector3f n1 = normals.get((Integer) i1.y);
            Vector3f v2 = vertices.get((Integer) i2.x);
            Vector3f n2 = normals.get((Integer) i2.y);
            Vector3f v3 = vertices.get((Integer) i3.x);
            Vector3f n3 = normals.get((Integer) i3.y);
            
            ModelFace face = new ModelFace() {
                @Override
                public Vector3f[] getVertices() {
                    return new Vector3f[] { v1, v2, v3 };
                }
                
                @Override
                public Vector4f[] getGradients() {
                    Vector4f white = new Vector4f(1);
                    return new Vector4f[] { white, white, white };
                }

                @Override
                public Vector3f[] getNormals() {
                    return new Vector3f[] { n1, n2, n3 };
                }

                @Override
                public Vector2f[] getTexCoords() {
                    Vector2f zero = new Vector2f(0);
                    return new Vector2f[] { zero, zero };
                }
            };
            
            faces.add(face);
        }

        return faces;
    }

    /**
     * Empties the stored data
     */
    public void clear() {
        vertices.clear();
        normals.clear();
        indices.clear();
    }

    /**
     * Adds a vertex to the stored vertices
     * @param vertex Vertex to add
     */
    public void appendVertex(Vector3f vertex) {
        vertices.add(vertex);
    }

    /**
     * Adds a vertex to the stored vertices
     * @param x
     * @param y
     * @param z
     */
    public void appendVertex(float x, float y, float z) {
        appendVertex(new Vector3f(x, y, z));
    }

    /**
     * @return Stored vertices to reference later
     */
    public List<Vector3f> getVertices() {
        return vertices;
    }

    /**
     * Adds the normal to the stored normals
     * @param normal Normal to add
     */
    public void appendNormal(Vector3f normal) {
        normals.add(normal);
    }

    /**
     * Adds the normal to the stored normals
     * @param x
     * @param y
     * @param z
     */
    public void appendNormal(float x, float y, float z) {
        appendNormal(new Vector3f(x, y, z));
    }

    /**
     * @return Stored normals to reference later
     */
    public List<Vector3f> getNormals() {
        return normals;
    }

    /**
     * Adds the index for vertex (x) and normal (y)
     * @param index Vector in form <vertex, normal>
     */
    public void appendIndex(Vector2i index) {
        indices.add(index);
    }

    /**
     * Adds the index for vertex and normal
     * @param v Vertex index
     * @param n Normal index
     */
    public void appendIndex(int v, int n) {
        appendIndex(new Vector2i(v, n));
    }

    /**
     * @return Indices to reference vertices and normals
     */
    public List<Vector2i> getIndices() {
        return indices;
    }
}
