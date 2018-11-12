package Arbre.v2_1.AspectJ;

public class NoeudVide extends NoeudAbstrait {

	public NoeudVide() {
		super(null);
	}

	@Override
	public boolean contient(String s) {
		return false;
	}

	@Override
	public boolean prefixe(String s) {
		return false;
	}

	@Override
	public int nbMots() {
		return 0;
	}

	@Override
	public NoeudAbstrait ajout(String s) {
		NoeudAbstrait n = new Marque(new NoeudVide());
		for (int i = s.length() - 1; i >= 0; i --)
			n = new Noeud(new NoeudVide(), n, s.charAt(i));
		return n;
	}

	@Override
	public NoeudAbstrait suppr(String s) {
		throw new ModificationImpossibleException("Suppression impossible");
	}

	@Override
	public String toString(String s) {
		return "";
	}

}
