package jgloom.io.models;

import org.junit.Test;

import jgloom.io.models.obj.OBJModelLoader;
import jgloom.io.resources.ClasspathResource;
import jgloom.io.resources.Resource;

public class TestOBJModel {
    @Test
    public void testOBJModelLoader() throws Throwable {
        Resource bunny = ClasspathResource.createClasspathResource("models/stanford_bunny/stanford_bunny.obj");
        Model model = new OBJModelLoader().loadModel(bunny);
        bunny.close();
        System.out.println("Loaded " + model.getFaces().length + " faces, " + (model.getFaces().length * 3) + " vertices");
    }
}
