package org.jgloom.io.models.obj;

import java.util.List;

import org.jgloom.io.models.Model;
import org.jgloom.io.models.ModelFace;
import org.jgloom.io.models.mtl.MTLMaterials;

// FIXME Implement model rendering system
/**
 * Implementation of {@link Model} which contains OBJ loaded mesh data and MTL loaded material data
 */
public class OBJModel implements Model {
    /**
     * List of model faces making up the model
     */
    private List<ModelFace> faces;
    
    /**
     * Collection of materials loaded from a MTL file used in the model
     */
    private MTLMaterials materials;
    
    public OBJModel(OBJBuilder builder, MTLMaterials mtl) {
        faces = builder.generateFaces();
        materials = mtl;
    }
    
    /**
     * @return Array of model faces making up the model
     */
    @Override
    public ModelFace[] getFaces() {
        return faces.toArray(new ModelFace[0]);
    }
    
    /**
     * @return Collection of materials loaded from a MTL file used in the model
     */
    public MTLMaterials getMaterials() {
        return materials;
    }

    /**
     * Renders the model (not yet implemented, to be replaced)
     */
    @Override
    public void render() {
        throw new UnsupportedOperationException("OBJ model rendering is not supported until rendering systems are created");
    }
}
