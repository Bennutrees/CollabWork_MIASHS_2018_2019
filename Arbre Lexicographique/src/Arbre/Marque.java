package Arbre;

public class Marque extends NoeudAbstrait {
	public Marque(NoeudAbstrait frere) {
		super(frere);
	}

	@Override
	public boolean contient(String s) {
		if (s.isEmpty())
			return true;
		if (this.frere == null)
			return false;
		return this.frere.contient(s);
		
//		return s.isEmpty() || (this.frere != null && this.frere.contient(s));
	}

	@Override
	public boolean prefixe(String s) {
		if (s.isEmpty()) 
			return true;
		//
		if (this.frere == null)
			return false;
		return this.frere.prefixe(s);
		
//		return s.isEmpty() || (this.frere != null && this.frere.prefixe(s));
	}

	@Override
	public int nbMots() {
		return 1 + (frere == null ? 0 : frere.nbMots());
	}

	@Override
	public NoeudAbstrait ajout(String s) {
		if (s.isEmpty())
			return this;
		
		if (this.frere == null) {
			NoeudAbstrait n = new Marque(null);
			for (int i = s.length() - 1; i >= 0; i--)
				n = new Noeud(null, n, s.charAt(i));
			this.frere = n;
			return this;
		}
		
		this.frere = this.frere.ajout(s);
		
		return this;
	}

	@Override
	public NoeudAbstrait suppr(String s) {
		return this;
	}

	@Override
	public String toString() {
		if (this.frere != null)
			return " \n" + this.frere.toString();
		return " \n";
	}
}
