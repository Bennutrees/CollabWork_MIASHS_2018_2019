package Arbre.v2_1.AspectJ;

public class ArbreLexicographique {
	private NoeudAbstrait entree;

	public ArbreLexicographique() {
		entree = new NoeudVide();
	}

	public boolean estVide() {
		return ! (entree instanceof NoeudVide);
	}

	public boolean contient(String s) {
		return entree.contient(s);
	}

	public boolean prefixe(String s) {
		return entree.prefixe(s);
	}

	public int nbMots() {
		return entree.nbMots();
	}

	public boolean ajout(String s) {
		try {
			entree = entree.ajout(s);
		} catch (ModificationImpossibleException e) {
			return false;
		}
		return true;
	}

	public boolean suppr(String s) {
		try {
			entree = entree.suppr(s);
		} catch (ModificationImpossibleException e) {
			return false;
		}
		return true;
	}

	public String toString() {
		return entree.toString("");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
