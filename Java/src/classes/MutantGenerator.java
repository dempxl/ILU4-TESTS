package classes;

import javax.tools.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.lang.reflect.*;

public class MutantGenerator {

    private static final String TMP_DIR = "Java/bin/tmp/";
    public record Mutation(String avant, String apres) {
        public String name() {
            return (avant + "_to_" + apres)
                .replace("<=", "LE")
                .replace(">=", "GE")
                .replace("==", "EQ")
                .replace("!=", "NNE")
                .replace(">", "GT")
                .replace("<", "LT")
                .replace("+", "P")
                .replace("-", "M")
                .replace(" ", "")
            ;
        }
    }

    public static ITriangleClassifier generateMutant(String sourceCode, String className, Mutation... mutations) throws Exception {
        for(Mutation m : mutations)
            sourceCode = sourceCode.replace(m.avant(), m.apres());

        sourceCode = sourceCode
            .replace("class TrianglesClassifierCorrected", "class " + className)
            .replace("TrianglesClassifierCorrected()", className + "()");

        Path classesDir = Path.of(TMP_DIR + "classes/");
        Files.createDirectories(classesDir);

        File sourceFile = classesDir.resolve(className + ".java").toFile();
        try(FileWriter fw = new FileWriter(sourceFile)) {
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
        
        Class<?> cls = classLoader.loadClass("classes." + className);
        Method m = cls.getMethod("typeTriangle", int.class, int.class, int.class);
        return (a, b, c) -> {
            try { return (int) m.invoke(null, a, b, c); }
            catch (Exception e) { throw new RuntimeException(e); }
        };
    }
}