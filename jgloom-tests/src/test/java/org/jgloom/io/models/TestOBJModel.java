package org.jgloom.io.models;

import org.jgloom.io.models.Model;
import org.jgloom.io.models.mtl.MTLMaterial;
import org.jgloom.io.models.mtl.MTLMaterialLoader;
import org.jgloom.io.models.mtl.MTLMaterials;
import org.jgloom.io.models.obj.OBJModelLoader;
import org.jgloom.io.resources.ClasspathResource;
import org.jgloom.io.resources.Resource;
import org.junit.Test;

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
