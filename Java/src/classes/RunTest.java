package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RunTest {
    private RunTest() {}

    public static boolean run(String inputFile, String outputFile, ITriangleClassifier classifier) {
        return run(inputFile, outputFile, classifier, false);
    }

    public static boolean run(String inputFile, String outputFile, ITriangleClassifier classifier, boolean lazy) {
        boolean result = true;
        List<String> lignes = new ArrayList<>();

        // On initialise le fichier d'entrée et de sortie
        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        ) {
            String ligne;

            while((ligne = reader.readLine()) != null){
                // On sépare les valeurs
                String[] parties = ligne.split(" ");
                if(parties.length != 4) continue;

                int a = Integer.parseInt(parties[0]);    // Argument a
                int b = Integer.parseInt(parties[1]);    // Argument b
                int c = Integer.parseInt(parties[2]);    // Argument c
                int res_attendu = Integer.parseInt(parties[3]); // résultat attendu

                // On appel la fonction de classification sur la classe fournie
                int res_triangle = classifier.classify(a, b, c);

                boolean pass = (res_attendu == res_triangle);
                result &= pass;

                if(lazy && !result) return false;

                lignes.add(a + " " + b + " " + c + " -> " + res_triangle + " [" + (pass ? "PASS" : "FAIL") + "]");
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        if(!lazy || result) {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                for(String l : lignes) {
                    writer.write(l);
                    writer.newLine();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
