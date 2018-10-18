package Arbre.v0;

public class Test {

	public static void main(String[] args) {
		NoeudAbstrait arbre = new Marque(null);
		arbre = arbre.ajout("premier");
		arbre = arbre.ajout("premier");
		arbre = arbre.ajout("second");
		System.out.println("nb mots : " + arbre.nbMots());
		System.out.println("----------");
		System.out.println(arbre.toString(""));
		System.out.println("----------");

		arbre = arbre.suppr("");
		arbre = arbre.suppr("second");
		arbre = arbre.ajout("java");
		arbre = arbre.suppr("absent");
		arbre = arbre.ajout("jar");
		arbre = arbre.ajout("javanaise");
		arbre = arbre.ajout("jardin");
		arbre = arbre.ajout("jouer");
		arbre = arbre.ajout("joue");
		
		System.out.println("nb mots : " + arbre.nbMots());
		System.out.println("----------");
		System.out.println(arbre.toString(""));
		System.out.println("----------");
	}

}
