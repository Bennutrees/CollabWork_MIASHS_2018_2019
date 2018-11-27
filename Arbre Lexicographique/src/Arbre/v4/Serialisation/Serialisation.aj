package Arbre.v4.Serialisation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public aspect Serialisation {

	declare parents : ArbreLexicographique implements Serializable;
	
	public void ArbreLexicographique.sauve(String nomFichier) {
		
		try {
			PrintWriter writer = new PrintWriter(nomFichier);
			writer.println(this.toString());
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void ArbreLexicographique.charge(String nomFichier) {
//		Path path = Paths.get(nomFichier);
//		try {
//			Files.lines(path).forEach();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		File file = new File(nomFichier);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String s;
		try {
			while ((s = br.readLine()) != null) 
			    this.ajout(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
