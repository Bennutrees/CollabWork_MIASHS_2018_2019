package Arbre;

public class ArbreLexicographique {
	private NoeudAbstrait entree;

	public ArbreLexicographique() {
		entree = null;
	}

	public boolean estVide() {
		return entree == null;
	}

	public boolean contient(String s) {
		if (entree == null)
			return false;
		return entree.contient(s);
	}

	public boolean prefixe(String s) {
		if (entree == null)
			return false;
		return entree.prefixe(s);
	}

	public int nbMots() {
		return (entree == null ? 0 : entree.nbMots());
	}

	public boolean ajout(String s) {
		if (entree == null) {
			entree = new Marque(null);
			entree = entree.ajout(s);
			if (!s.isEmpty())
				entree = entree.suppr("");
			return true;
		}
		try {
			entree = entree.ajout(s);
		} catch (ModificationImpossibleException e) {
			return false;
		}
		return true;
	}

	public boolean suppr(String s) {
		if (entree == null)
			return false;
		try {
			entree = entree.suppr(s);
		} catch (ModificationImpossibleException e) {
			return false;
		}
		return true;
	}

	public String toString() {
		if (entree == null)
			return "";
		return entree.toString("");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
