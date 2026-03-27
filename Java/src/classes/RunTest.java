package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RunTest {
	public static void run(String inputFile, String outputFile){
		System.out.print("=== Traitement sur " + inputFile + "...");
		// On créer un fichier de sortie dans le même dossier que le fichier d'entrée
		Path inputPath = Paths.get(inputFile);
		Path outputPath;
		if (inputPath.getParent() != null){
			outputPath = inputPath.getParent().resolve(outputFile);
		}
		else{
			outputPath = Paths.get(outputFile);
		}
		
		// On initialise le fichier d'entrée et de sortie
		 try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {
			String ligne;

			while((ligne = reader.readLine()) != null){
				// On sépare les valeurs
				String[] parties = ligne.split(" ");

				if (parties.length != 4){
					System.out.println("[WARNING] ligne invalide: " + ligne);
					continue;
				}

				int a = Integer.parseInt(parties[0]);	// Argument a
				int b = Integer.parseInt(parties[1]);	// Argument b
				int c = Integer.parseInt(parties[2]);	// Argument c
				int res_attendu = Integer.parseInt(parties[3]); // résultat attendu

				// On appel la fonction
				int res_triangle = TrianglesClassifier.typeTriangle(a, b, c);

				// On compare le résultat avec ce qui est attendu
				String res_valide = (res_attendu == res_triangle)? "PASS" : "FAIL";

				// On écrit le résultat dans le fichier de sortie
				writer.write(a + " " + b + " " + c + " -> " + res_triangle +" [" + res_valide + "]");	// Version détaillée
				// writer.write(res_triangle+"");	// Version compacte
				writer.newLine();	// Un résultat par ligne
			}
			System.out.println("terminé ! ===");
		}catch (IOException e){
			e.printStackTrace();
		}
	
	}
}
