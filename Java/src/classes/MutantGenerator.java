package classes;

import javax.tools.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;

public class MutantGenerator {

    private static final String TMP_DIR = "Java/bin/tmp/";

    public static Class<?> generateMutant(String sourceCode, String className) throws Exception {
        Path classesDir = Path.of(TMP_DIR + "classes/");
        Files.createDirectories(classesDir);

        File sourceFile = classesDir.resolve(className + ".java").toFile();
        try (FileWriter fw = new FileWriter(sourceFile)) {
            fw.write(sourceCode);
        }

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fm = compiler.getStandardFileManager(null, null, null);

        Iterable<? extends JavaFileObject> units = fm.getJavaFileObjects(sourceFile);

        JavaCompiler.CompilationTask task = compiler.getTask(
            null, fm, null,
            java.util.List.of("-d", TMP_DIR),
            null, units
        );

        if(!task.call()) throw new RuntimeException("Compilation du mutant échouée : " + className);
        fm.close();

        URLClassLoader classLoader = URLClassLoader.newInstance(
            new URL[]{ new File(TMP_DIR).toURI().toURL() }
        );

        return classLoader.loadClass("classes." + className);
    }
}