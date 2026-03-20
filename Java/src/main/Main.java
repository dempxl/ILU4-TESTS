package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import classes.RunTest;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) {
		// TODO lire un fichier txt et en renvoyer les résultats obtenu
		try {
			RunTest test = new RunTest();
			test.run(new FileReader("src/main/test.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
