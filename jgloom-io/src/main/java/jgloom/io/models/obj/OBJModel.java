package jgloom.io.models.obj;

import java.util.List;

import jgloom.io.models.Model;
import jgloom.io.models.ModelFace;
import jgloom.io.models.mtl.MTLMaterials;

// TODO: Implement model rendering system
public class OBJModel implements Model {
    private List<ModelFace> faces;
    private MTLMaterials materials;
    
    public OBJModel(OBJBuilder builder, MTLMaterials mtl) {
        faces = builder.generateFaces();
        materials = mtl;
    }
    
    @Override
    public ModelFace[] getFaces() {
        return faces.toArray(new ModelFace[0]);
    }
    
    public MTLMaterials getMaterials() {
        return materials;
    }

    @Override
    public void render() {
        throw new UnsupportedOperationException("OBJ model rendering is not supported until rendering systems are created");
    }
}
