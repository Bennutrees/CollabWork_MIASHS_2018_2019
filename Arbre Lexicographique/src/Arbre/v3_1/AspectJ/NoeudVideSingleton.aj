package Arbre.v3_1.AspectJ;

import Arbre.v3_1.AspectJ.NoeudVide;

public aspect NoeudVideSingleton {
	private NoeudVide unique = new NoeudVide();
	
	private pointcut appelConstructeurNoeudVide() : call( NoeudVide.new()) && ! within(NoeudVideSingleton);
	
	NoeudVide around() : appelConstructeurNoeudVide() {
		return NoeudVide.unique;
	}
}
