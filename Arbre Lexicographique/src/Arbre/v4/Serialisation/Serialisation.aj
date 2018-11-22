package Arbre.v4.Serialisation;

import java.io.IOException;
import java.io.Writer;
import java.io.Serializable;

public aspect Serialisation {

	declare parents : ArbreLexicographique implements Serializable;
	
	void sauve(String nomFichier) {
		
		Writer writer = null;
		try {
			writer.write(this.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void charge(String nomFichier) {
		
	}
}
