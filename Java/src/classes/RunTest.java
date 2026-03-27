package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class RunTest {
	private RunTest(){}

	public static void run(String inputFile, String outputFile, Class<?> classe){
		System.out.print("=== Traitement sur " + inputFile + "...");
		Method typeTriangleMethod;

		try {
			typeTriangleMethod = classe.getMethod("typeTriangle", int.class, int.class, int.class);
			if (!Modifier.isStatic(typeTriangleMethod.getModifiers())) {
				throw new IllegalArgumentException("La methode typeTriangle doit etre statique dans " + classe.getName());
			}
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException("La classe " + classe.getName() + " doit definir typeTriangle(int, int, int)", e);
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

				// On appel la fonction de classification sur la classe fournie
				int res_triangle;
				try {
					res_triangle = (int) typeTriangleMethod.invoke(null, a, b, c);
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new RuntimeException("Impossible d'appeler typeTriangle sur " + classe.getName(), e);
				}

				// On compare le résultat avec ce qui est attendu
				String res_valide = (res_attendu == res_triangle)? "PASS" : "FAIL";

				// On écrit le résultat dans le fichier de sortie
				writer.write(a + " " + b + " " + c + " -> " + res_triangle +" [" + res_valide + "]");	// Version détaillée
				// writer.write(res_triangle+"");	// Version compacte
				writer.newLine();	// Un résultat par ligne
			}
			System.out.println("terminé ! ===");
		} catch (IOException e){
			e.printStackTrace();
		}
	
	}
}
