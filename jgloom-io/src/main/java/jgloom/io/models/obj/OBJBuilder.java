package jgloom.io.models.obj;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector4f;

import jgloom.io.models.ModelFace;

// TODO: Add support for more vertex attributes
public class OBJBuilder {
    private List<Vector3f> vertices;
    private List<Vector3f> normals;
    private List<Vector2i> indices;

    public OBJBuilder() {
        vertices = new ArrayList<Vector3f>();
        normals = new ArrayList<Vector3f>();
        indices = new ArrayList<Vector2i>();
    }

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

    public void clear() {
        vertices.clear();
        normals.clear();
        indices.clear();
    }

    public void appendVertex(Vector3f vertex) {
        vertices.add(vertex);
    }

    public void appendVertex(float x, float y, float z) {
        appendVertex(new Vector3f(x, y, z));
    }

    public List<Vector3f> getVertices() {
        return vertices;
    }

    public void appendNormal(Vector3f normal) {
        normals.add(normal);
    }

    public void appendNormal(float x, float y, float z) {
        appendNormal(new Vector3f(x, y, z));
    }

    public List<Vector3f> getNormals() {
        return normals;
    }

    public void appendIndex(Vector2i index) {
        indices.add(index);
    }

    public void appendIndex(int v, int n) {
        appendIndex(new Vector2i(v, n));
    }

    public List<Vector2i> getIndices() {
        return indices;
    }
}
