package main;

import classes.RunTest;
import classes.TrianglesClassifier;
import classes.TrianglesClassifierCorrected;
import classes.MutantGenerator;

import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    private Main() {}

    record ArgsTest(String fileIn, String fileOut, Class<?> classifierClass) {}
    record Mutation(String avant, String apres) {}

    static final String FILE_IN  = "./sources/valeurs_test_IN.txt";
    static final String FILE_OUT = "./sources/valeurs_test_OUT";

    static final Mutation[] MUTATIONS = {
        new Mutation("a <= 0"    , "a < 0"     ),
        new Mutation("b <= 0"    , "b < 0"     ),
        new Mutation("c <= 0"    , "c < 0"     ),
        new Mutation("a == b"    , "a != b"    ),
        new Mutation("a == c"    , "a != c"    ),
        new Mutation("b == c"    , "b != c"    ),
        new Mutation("type == 0" , "type != 0" ),
        new Mutation("a + b <= c", "a + b < c" ),
        new Mutation("a + c <= b", "a + c < b" ),
        new Mutation("b + c <= a", "b + c < a" ),
        new Mutation("type > 3"  , "type <= 3" ),
        new Mutation("type == 1" , "type != 1" ),
        new Mutation("a + b > c" , "a + b <= c"),
        new Mutation("type == 2" , "type != 2" ),
        new Mutation("a + c > b" , "a + c <= b"),
        new Mutation("type == 3" , "type != 3" ),
        new Mutation("b + c > a" , "b + c <= a"),
    };

    public static void main(String[] args) throws Exception {
        List<ArgsTest> tests = new ArrayList<>(List.of(
            new ArgsTest(FILE_IN, FILE_OUT + ".txt"  , TrianglesClassifier.class),
            new ArgsTest(FILE_IN, FILE_OUT + "_C.txt", TrianglesClassifierCorrected.class)
        ));

        String originalSource = Files.readString(Path.of("Java/src/classes/TrianglesClassifierCorrected.java"));

        int i = 1;
        for (Mutation m : MUTATIONS) {
            String name = "TriangleMutant_" + i++;
            String sourceMute = originalSource
                .replace(m.avant(), m.apres())
                .replace("class TrianglesClassifierCorrected", "class " + name);
            Class<?> mutant = MutantGenerator.generateMutant(sourceMute, name);
            tests.add(new ArgsTest(FILE_IN, FILE_OUT + "_" + name + ".txt", mutant));
        }

        for (ArgsTest t : tests) {
            System.out.printf("input : %-45s | output : %-45s | classifier : %s%n",
                t.fileIn(), t.fileOut(), t.classifierClass().getSimpleName());
            RunTest.run(t.fileIn(), t.fileOut(), t.classifierClass());
        }
    }
}