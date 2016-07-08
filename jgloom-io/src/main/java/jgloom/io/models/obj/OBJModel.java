package jgloom.io.models.obj;

import java.util.List;

import jgloom.io.models.Model;
import jgloom.io.models.ModelFace;

// TODO: Implement model rendering system
public class OBJModel implements Model {
    private List<ModelFace> faces;
    
    public OBJModel(OBJBuilder builder) {
        faces = builder.generateFaces();
    }
    
    @Override
    public ModelFace[] getFaces() {
        return faces.toArray(new ModelFace[0]);
    }

    @Override
    public void render() {
        throw new UnsupportedOperationException("OBJ model rendering is not supported until rendering systems are created");
    }
}
