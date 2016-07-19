package jgloom.io.models;

import org.junit.Test;

import jgloom.io.models.mtl.MTLMaterial;
import jgloom.io.models.mtl.MTLMaterialLoader;
import jgloom.io.models.mtl.MTLMaterials;
import jgloom.io.models.obj.OBJModelLoader;
import jgloom.io.resources.ClasspathResource;
import jgloom.io.resources.Resource;

public class TestOBJModel {
    @Test
    public void testOBJModelLoader() throws Throwable {
        Resource bunny = ClasspathResource.createClasspathResource("models/stanford_bunny/stanford_bunny.obj");
        Model model = OBJModelLoader.loadModel(bunny);
        bunny.close();
        System.out.println("Loaded " + model.getFaces().length + " faces, " + (model.getFaces().length * 3) + " vertices");
    }
    
    @Test
    public void testMTLMaterialLoader() throws Throwable {
        Resource bunnyMTL = ClasspathResource.createClasspathResource("models/stanford_bunny/stanford_bunny.mtl");
        MTLMaterials mtl = MTLMaterialLoader.loadMaterials(bunnyMTL);
        bunnyMTL.close();
        System.out.println("Loaded " + mtl.getMaterials().size() + " materials:");
        for (MTLMaterial mat : mtl.getMaterials())
            System.out.println(mat.getName() + mat.getAmbient() + mat.getDiffuse() + mat.getSpecular());
    }
}
