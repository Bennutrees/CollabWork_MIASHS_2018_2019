package Arbre.v3_1.AspectJ;

public abstract class NoeudAbstrait {

	protected NoeudAbstrait frere;

	public NoeudAbstrait(NoeudAbstrait frere) {
		if (frere == null && (! (this instanceof NoeudVide)))
			throw new IllegalArgumentException("frere ne peut pas être null");
		this.frere = frere;
	}

	public abstract boolean contient(String s);

	public abstract boolean prefixe(String s);

	public abstract int nbMots(); // nombre d'�l�ments

	public abstract NoeudAbstrait ajout(String s);

	public abstract NoeudAbstrait suppr(String s);

	public abstract String toString(String s);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
