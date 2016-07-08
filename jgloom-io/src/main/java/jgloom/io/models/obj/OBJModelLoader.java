package jgloom.io.models.obj;

import java.io.IOException;

import jgloom.io.models.Model;
import jgloom.io.models.ModelLoader;
import jgloom.io.resources.Resource;
import jgloom.io.resources.ResourceReader;

// TODO: Add support for multiple forms of split data lines
// TODO: Add support for material files
// TODO: Add support for texture coordinates
public class OBJModelLoader implements ModelLoader {
    private static final String OBJ_COMMENT = "#";
    private static final String OBJ_VERTEX = "v ";
    private static final String OBJ_NORMAL = "vn ";
    private static final String OBJ_INDEX = "f ";

    @Override
    public Model loadModel(Resource resource) throws IOException {
        OBJBuilder builder = new OBJBuilder();
        startReading(resource, builder);
        return new OBJModel(builder);
    }

    private void startReading(Resource resource, OBJBuilder builder) throws IOException {
        ResourceReader.readIndividualLines(resource, (String line) -> {
            parseLine(line, builder);
        });
    }

    private void parseLine(String line, OBJBuilder builder) {
        if (line.replace(" ", "").replace("\t", "").equals(""))
            return;
        else if (line.startsWith(OBJ_COMMENT))
            return;
        String[] args = null;

        if (line.contains(" ")) {
            String argAssem = line.split(" ", 2)[1];
            args = argAssem.split(" ");
        }

        if (line.startsWith(OBJ_VERTEX))
            parseVertexLine(args, builder);
        else if (line.startsWith(OBJ_NORMAL))
            parseNormalLine(args, builder);
        else if (line.startsWith(OBJ_INDEX))
            parseIndexLine(args, builder);
    }

    private void parseVertexLine(String[] args, OBJBuilder builder) {
        float x = Float.valueOf(args[0]);
        float y = Float.valueOf(args[1]);
        float z = Float.valueOf(args[2]);
        builder.appendVertex(x, y, z);
    }

    private void parseNormalLine(String[] args, OBJBuilder builder) {
        float x = Float.valueOf(args[0]);
        float y = Float.valueOf(args[1]);
        float z = Float.valueOf(args[2]);
        builder.appendNormal(x, y, z);
    }

    private void parseIndexLine(String[] args, OBJBuilder builder) {
        String[] i1 = args[0].split("//");
        String[] i2 = args[1].split("//");
        String[] i3 = args[2].split("//");
        int v1 = Integer.valueOf(i1[0]) - 1;
        int n1 = Integer.valueOf(i1[1]) - 1;
        int v2 = Integer.valueOf(i2[0]) - 1;
        int n2 = Integer.valueOf(i2[1]) - 1;
        int v3 = Integer.valueOf(i3[0]) - 1;
        int n3 = Integer.valueOf(i3[1]) - 1;
        builder.appendIndex(v1, n1);
        builder.appendIndex(v2, n2);
        builder.appendIndex(v3, n3);
    }
}
