package Arbre.v2_1.AspectJ;

import Arbre.v2_1.AspectJ.NoeudVide;

public aspect NoeudVideSingleton {
	private NoeudVide unique = new NoeudVide();
	
	private pointcut appelConstructeurNoeudVide() : call( NoeudVide.new()) && ! within(NoeudVideSingleton);
	
	NoeudVide around() : appelConstructeurNoeudVide() {
		return NoeudVide.unique;
	}
}
