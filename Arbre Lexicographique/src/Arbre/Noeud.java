package Arbre;

public class Noeud extends NoeudAbstrait {
	
	private char valeur;
	private NoeudAbstrait fils;
	
	public Noeud(NoeudAbstrait frere, NoeudAbstrait	fils, char valeur) {
		super(frere);
		this.valeur = valeur;
		if (fils == null)
			throw new IllegalArgumentException("fils ne peut pas être null");
		this.fils = fils;
	}

	@Override
	public boolean contient(String s) {
		if (s.isEmpty()) 
			return false;
		
		char c = s.charAt(0);
		if (c < this.valeur) 
			return false;
		if (c == this.valeur)
			return fils.contient(s.substring(1)); //appelle soit .contient() d'un noeud soit d'une marque
		if (this.frere == null) 
			return false;
		return frere.contient(s);
		
	}

	@Override
	public boolean prefixe(String s) {
		if (s.isEmpty()) 
			return true;
		
		char c = s.charAt(0);
		if (c < this.valeur) 
			return false;
		if (c == this.valeur)
			return fils.prefixe(s.substring(1));
		if (this.frere == null) 
			return false;
		return frere.prefixe(s);
	}

	@Override
	public int nbMots() {
		return fils.nbMots() + (frere == null ? 0 : frere.nbMots());
//		if (this.frere != null)
//			return this.fils.nbMots() + this.frere.nbMots();
//		return this.fils.nbMots();
	}

	@Override
	public NoeudAbstrait ajout(String s) {
		if (s.isEmpty()) {
			return new Marque(this);
		}
		
		char c = s.charAt(0);
		if (c < this.valeur) {
			NoeudAbstrait n = new Marque(null);
			for (int i = s.length() - 1; i >= 0; i--)
				n = new Noeud(null, n, s.charAt(i));
			n.frere = this;
			return n;
		}
		
		if (c == this.valeur) {
			fils = fils.ajout(s.substring(1));
			return this;
		}
		
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
		if(s.isEmpty() || s.charAt(0) < this.valeur) {
			return this;
		}

		if(s.charAt(0) > this.valeur) {
			this.frere = this.frere.suppr(s);
			return this;
		}
		NoeudAbstrait n = this.fils.suppr(s.substring(1));
		if (n == null) {
			if(this.frere != null)
				return this.frere;
			return null;
		}
		return null;
	}

	@Override
	public String toString() {		
		String s = "";
		String[] split = new String[100]; //TODO: pas correct
		split = this.fils.toString().split("\n");
		
		for (String sub : split) {
			s+= this.valeur + sub + "\n";
		}
		if(this.frere != null)
			s += this.frere.toString();
		return s;
	}
	
	@Override
	public String toString(String s) {
		s+= s + this.valeur;
		return this.fils.toString(s) + ((this.frere == null) ? "" : this.frere.toString(s));
	}
}
