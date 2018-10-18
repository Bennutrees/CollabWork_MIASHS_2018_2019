package Arbre.v0;

public class Marque extends NoeudAbstrait {

	public Marque(NoeudAbstrait frere) {
		super(frere);
	}

	@Override
	public boolean contient(String s) {
//		if (s.isEmpty())
//			return true;
//		if (frere == null)
//			return false;
//		return frere.contient(s);
		return s.isEmpty() || (frere != null && frere.contient(s));
	}

	@Override
	public boolean prefixe(String s) {
		return s.isEmpty() || (frere != null && frere.prefixe(s));
	}

	@Override
	public int nbMots() {
		return 1 + ((frere != null) ? frere.nbMots() : 0);
	}

	@Override
	public NoeudAbstrait ajout(String s) {
		if (s.isEmpty())
			return this;
		if (frere == null) {
			NoeudAbstrait n = new Marque(null);
			for (int i = s.length() - 1; i >= 0; i --)
				n = new Noeud(null, n, s.charAt(i));
			frere = n;
			return this; 
		}
		frere = frere.ajout(s);
		return this;
	}

	@Override
	public NoeudAbstrait suppr(String s) {
		if (s.isEmpty())
			return frere;
		if (frere == null)
			return this; 
		frere = frere.suppr(s);
		return this;
	}

	@Override
	public String toString(String s) {
		if (frere == null)
			return s + "\n";
		return s + "\n" + frere.toString(s);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
