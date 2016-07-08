package jgloom.io.models;

import java.io.IOException;

import jgloom.io.resources.Resource;

public interface ModelLoader {
    Model loadModel(Resource resource) throws IOException;
}
