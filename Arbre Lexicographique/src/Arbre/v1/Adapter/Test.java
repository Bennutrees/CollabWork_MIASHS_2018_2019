package Arbre.v1.Adapter;

public class Test {

	public static void main(String[] args) {
		ArbreLexicographique arbre = new ArbreLexicographique();
		System.out.println("Ajout de premier : " + arbre.ajout("premier"));
		System.out.println("Ajout de premier : " + arbre.ajout("premier"));
		System.out.println("Ajout de second : " + arbre.ajout("second"));
		System.out.println("nb mots : " + arbre.nbMots());
		System.out.println("----------");
		System.out.println(arbre.toString());
		System.out.println("----------");

		System.out.println("Suppression de second : " + arbre.suppr("second"));
		System.out.println("Suppression de absent : " + arbre.suppr("absent"));
		System.out.println("Ajout de java : " + arbre.ajout("java"));
		System.out.println("Ajout de jar : " + arbre.ajout("jar"));
		System.out.println("Ajout de javanaise : " + arbre.ajout("javanaise"));
		System.out.println("Ajout de jardin : " + arbre.ajout("jardin"));
		System.out.println("Ajout de jouer : " + arbre.ajout("jouer"));
		System.out.println("Ajout de joue : " + arbre.ajout("joue"));
		System.out.println("Ajout de jouet : " + arbre.ajout("jouet"));
		System.out.println("nb mots : " + arbre.nbMots());

		System.out.println("----------");
		System.out.println(arbre.toString());
		System.out.println("----------");
	}

}
