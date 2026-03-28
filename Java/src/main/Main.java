package main;

import classes.*;
import classifier.*;

import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    private Main() {}

    record ArgsTest(String fileIn, String fileOut, ITriangleClassifier classifier) {}

    static final String FILE_IN  = "./sources/valeurs_test_IN.txt";
    static final String FILE_OUT = "./sources/valeurs_test_OUT";

    static final MutantGenerator.Mutation[] MUTATIONS = {
        new MutantGenerator.Mutation("a <= 0"    , "a < 0"     ),
        new MutantGenerator.Mutation("b <= 0"    , "b < 0"     ),
        new MutantGenerator.Mutation("c <= 0"    , "c < 0"     ),
        new MutantGenerator.Mutation("a == b"    , "a != b"    ),
        new MutantGenerator.Mutation("a == c"    , "a != c"    ),
        new MutantGenerator.Mutation("b == c"    , "b != c"    ),
        new MutantGenerator.Mutation("type == 0" , "type != 0" ),
        new MutantGenerator.Mutation("a + b <= c", "a + b < c" ),
        new MutantGenerator.Mutation("a + c <= b", "a + c < b" ),
        new MutantGenerator.Mutation("b + c <= a", "b + c < a" ),
        new MutantGenerator.Mutation("type > 3"  , "type <= 3" ),
        new MutantGenerator.Mutation("type == 1" , "type != 1" ),
        new MutantGenerator.Mutation("a + b > c" , "a + b <= c"),
        new MutantGenerator.Mutation("type == 2" , "type != 2" ),
        new MutantGenerator.Mutation("a + c > b" , "a + c <= b"),
        new MutantGenerator.Mutation("type == 3" , "type != 3" ),
        new MutantGenerator.Mutation("b + c > a" , "b + c <= a"),
        new MutantGenerator.Mutation("a <= 0"    , "a >= 0"    ),
        new MutantGenerator.Mutation("b <= 0"    , "b >= 0"    ),
        new MutantGenerator.Mutation("c <= 0"    , "c >= 0"    ),
        new MutantGenerator.Mutation("a + b <= c", "a + b > c" ),
        new MutantGenerator.Mutation("a + c <= b", "a + c > b" ),
        new MutantGenerator.Mutation("b + c <= a", "b + c > a" ),
        new MutantGenerator.Mutation("type > 3"  , "type >= 3" ),
        new MutantGenerator.Mutation("type > 3"  , "type == 3" ),
        new MutantGenerator.Mutation("a == b"    , "a <= b"    ),
        new MutantGenerator.Mutation("a == c"    , "a <= c"    ),
        new MutantGenerator.Mutation("b == c"    , "b <= c"    ),
    };

    public static void main(String[] args) throws Exception {
        List<ArgsTest> tests = new ArrayList<>(List.of(
            new ArgsTest(FILE_IN, FILE_OUT + ".txt"  , TrianglesClassifier::typeTriangle),
            new ArgsTest(FILE_IN, FILE_OUT + "_C.txt", TrianglesClassifierCorrected::typeTriangle)
        ));

        String originalSource = Files.readString(Path.of("Java/src/classifier/TrianglesClassifierCorrected.java"));

        for(MutantGenerator.Mutation m : MUTATIONS) {
            String name = "M" + m.name();
            ITriangleClassifier mutant = MutantGenerator.generateMutant(originalSource, name, m);
            tests.add(new ArgsTest(FILE_IN, FILE_OUT + "_" + name + ".txt", mutant));
        }

        for(MutantGenerator.Mutation m1 : MUTATIONS) {
            for(MutantGenerator.Mutation m2 : MUTATIONS) {
                String name = "M" + m1.name() + "_M" + m2.name();
                ITriangleClassifier mutant = MutantGenerator.generateMutant(originalSource, name, m1, m2);
                tests.add(new ArgsTest(FILE_IN, FILE_OUT + "_" + name + ".txt", mutant));
            }
        }

        System.out.println("| input file | output file | pass for all tests |");
        System.out.println("|-|-|-|");
        for(ArgsTest t : tests) {
            boolean allPassed = RunTest.run(t.fileIn(), t.fileOut(), t.classifier(), true);
            if(!allPassed) continue;
            System.out.printf("| %s | %-75s | %s |%n",
                t.fileIn(),
                t.fileOut(),
                allPassed ? "✅" : "❌"
            );
        }
    }
}