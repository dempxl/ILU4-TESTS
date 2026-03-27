package main;

import classes.RunTest;

public class Main {
	public static void main(String[] args) {
		// On lit un fichier en entrée et on renvoie les résultats obtenus dans un autre fichier
		if(args.length != 2){
			args = new String[] {
				"Java/src/valeurs_test_IN.txt",
				"Java/src/valeurs_test_OUT.txt"
			};
		}
		
		RunTest.run(args[0], args[1]);
	}
}
