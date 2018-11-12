package Arbre.v2_1.AspectJ;

public privileged aspect Clonage {
	declare parents : NoeudAbstrait || ArbreLexicographique implements Cloneable;

	public Object ArbreLexicographique.clone() {
		ArbreLexicographique a = new ArbreLexicographique();
		a.entree = (NoeudAbstrait)(entree.clone());
		return a;
	}
	
	public Object NoeudAbstrait.clone() {
		NoeudAbstrait n = null;
		try {
			n = (NoeudAbstrait)(super.clone());
			n.frere = (NoeudAbstrait)(frere.clone());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return n;
	}
	
	public Object Noeud.clone() {
		Noeud n = (Noeud)(super.clone());
		n.fils = (Noeud)(fils.clone());
		return n;
	}
}
