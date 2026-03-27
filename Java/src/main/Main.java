package main;

import classes.RunTest;
import classes.TrianglesClassifier;

public class Main {
	private Main() {}
	
	public static void main(String[] args) {
		// On lit un fichier en entrée et on renvoie les résultats obtenus dans un autre fichier
		if(args.length < 2 || args.length > 3){
			args = new String[] {
				"./sources/valeurs_test_IN.txt",
				"./sources/valeurs_test_OUT.txt"
			};
		}

		Class<?> classifierClass = TrianglesClassifier.class;
		if (args.length == 3) {
			try {
				classifierClass = Class.forName(args[2]);
			} catch (ClassNotFoundException e) {
				System.out.println("[WARNING] Classe introuvable: " + args[2] + ". Utilisation de classes.TrianglesClassifier.");
			}
		}

		RunTest.run(args[0], args[1], classifierClass);
	}
}
