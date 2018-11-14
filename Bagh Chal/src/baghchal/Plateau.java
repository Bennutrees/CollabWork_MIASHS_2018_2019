package baghchal;


import java.util.Random;

public class Plateau {
	
	private static Plateau plateau = new Plateau();
	private ChalPawn[] chevres;
	private BaghPawn[] tigres;
	private int nbChevresAPlacer;
	private int nbChevresMangees;
	
	private Plateau() {
		
	}
	
	public static Plateau getPlateau() {
		return plateau;
	}
	
	
	
	
	private int nbNavires;
	private int taille;
	public Coordinates[] tirsRecus;
	private int nbTirsRecus;
	
	public Plateau(int taille, int[] taillesNavires) {
		this.taille = taille;
		this.nbNavires = 0;
		navires = new Navire[taillesNavires.length];
		placementAuto(taillesNavires);
		tirsRecus = new Coordinates[taille*taille];
		nbTirsRecus = 0;
		
		/*for (int i=0 ; i< nbNavires ; i++) {
			System.out.println(navires[i]);
		}*/
	}
	
	public Plateau(int taille, int nbNavires) {
		this.taille = taille;
		this.nbNavires = 0;
		navires = new Navire[nbNavires];
		tirsRecus = new Coordinates[taille*taille];
		nbTirsRecus = 0;
	}
	
	public int getTaille() {
		return taille;
	}

	public String toString() {
		String[][] grille = new String[this.taille + 1][this.taille + 1];
		String string_grille = "";
		for (int i = 0; i <= this.taille; i++) {
			for (int j = 0; j <= this.taille; j++) {
				if (i == 0 & j == 0) {
					if (this.taille >= 10) grille[i][j] = "   ";
					else grille[i][j] = "  ";
				}
				else if (i == 0 & j > 0) {
					grille[i][j] = " " + (char)('A' + j - 1) + " "; 
				}
				else if (j == 0 & i > 0) {
					if (this.taille >= 10 && i < 10) grille[i][j] = " " + i + " ";
					else grille[i][j] = i + " ";
				}
				else {
					grille[i][j] = " . ";
					int u = i - 1;
					int v = j - 1;
					Coordinates c = new Coordinates(u , v);
					for (int k = 0; k < nbNavires; k++) {
						if (navires[k].contient(c)) {
								grille[i][j] = " # ";
						}
					}
					for (int l = 0; l < nbTirsRecus; l++) {
						if (u == tirsRecus[l].getLigne()
								&& v == tirsRecus[l].getColonne()) {
							grille[i][j] = " O ";
							for (int k = 0; k < nbNavires; k++) {
								if (navires[k].contient(tirsRecus[l])) {
									grille[i][j] = " X ";
								}
							}
						}
					}
				}
				string_grille += grille[i][j];
				if (j == this.taille) {
					string_grille += "\n";
				}
			}
		}
		return string_grille;
	}
	
	public boolean ajouteNavire(Navire n) {
		for (int k = 0; k < nbNavires; k++) {
			if (this.navires[k].chevauche(n)) {
				return false;
				//throw new IndexOutOfBoundsException("Navire déjà présent à cet endroit.");
			}
			else if (this.navires[k].touche(n)) {
				return false;
				//throw new IndexOutOfBoundsException("Navire collatéral.");
			}
		}
		Coordinates Deb = n.getDebut();
		Coordinates Fin = n.getFin();
		if (Deb.getColonne() < 0 || Deb.getColonne() >= this.taille 
				|| Deb.getLigne() < 0 || Deb.getLigne() >= this.taille
				|| Fin.getColonne() < 0 || Fin.getColonne() >= this.taille
				|| Fin.getLigne() < 0 || Fin.getLigne() >= this.taille) {
			return false;
			//throw new IndexOutOfBoundsException("Navire en dehors de la Grille.");
		}
		/**else if (Fin.getColonne() - Deb.getColonne() > this.taille
				|| Fin.getLigne() - Deb.getLigne() > this.taille) {
			
			throw new IndexOutOfBoundsException("Un navire est trop grand.");
		}**/
		else {
			navires[nbNavires] = n;
			nbNavires += 1;
			return true;
		}
	}
	
	public void placementAuto(int[] taillesNavires) {
		for (int k = 0; k < taillesNavires.length; k++) {
			Navire n;
			boolean ajoute = false;
			
			do {
				Random rand = new Random();
				int Debx = rand.nextInt(this.taille );
				int Deby = rand.nextInt(this.taille );
				Coordinates Deb = new Coordinates(Debx, Deby);
			    boolean estVertical = rand.nextBoolean();
			    //String s = "";
			    //if (estVertical) s += "verti"; else s += "horiz";
			    //System.out.println("Bateau " + k + " aux coords (" + Debx + "," + Deby + "), " + s + " et de taille " + taillesNavires[k]);
			    n = new Navire(Deb, taillesNavires[k], estVertical);
			    
			    try {
			    	ajoute = ajouteNavire(n);
			    } catch (Exception e) {
			    	e.printStackTrace();
			    }
			    
			} while(ajoute != true);
		}
	}
	
	private boolean estDansGrille(Coordinates c) {
		if (c.getColonne() > -1 && c.getColonne() < this.taille
				&& c.getLigne() > -1 && c.getLigne() < this.taille) {
			return true;
		}
		else throw new IndexOutOfBoundsException("Coordonnée en dehors de la grille.");
	}
	
	private boolean estDansTirsRecus(Coordinates c) {
		if (this.estDansGrille(c)) {
			for (int l = 0; l < nbTirsRecus; l++) {
				if (tirsRecus[l].equals(c)) return true;
			}
			return false;
		}
		else return this.estDansGrille(c);
	}
	
	private boolean ajouteDansTirsRecus(Coordinates c) {
		if (this.estDansTirsRecus(c)) {
			return false;
		}
		else {
			System.out.println("ajouteDansTirsRecus : "+c);
			tirsRecus[nbTirsRecus] = c;
			nbTirsRecus += 1; 
			return true;
		}
	}
	
	public boolean recoitTir(Coordinates c) {
		if (this.ajouteDansTirsRecus(c)) {
			for (int k = 0; k < nbNavires; k++) {
				if (navires[k].recoitTir(c)) return true;
			}
			return false;
		}
		else return false;
	}
	
	public boolean estTouche(Coordinates c) {
		for (int k = 0; k < nbNavires; k++) {
			if (navires[k].estTouche(c)) return true;
		}
		return false;
	}
	
	public boolean estALEau(Coordinates c) {
		return this.estDansTirsRecus(c) && !this.estTouche(c);
	}
	
	public boolean estCoule(Coordinates c) {
		Navire n = navires[0];
		for (int k = 0; k < nbNavires; k++) {
			if (navires[k].estTouche(c)) n = navires[k];
		}
		return this.estTouche(c) && n.estCoule();
	}
	
	public boolean perdu() {
		for (int k = 0; k < nbNavires; k++) {
			if (!navires[k].estCoule()) return false;
		}
		return true;
	}

/**	public static void main(String[] args) {
		GrilleNavale G = new GrilleNavale(8, new int[] {1,2,8});
		System.out.println(G.toString());
		for (int i = 0; i < G.tirsRecus.length && G.tirsRecus[i] != null; i++) {
			System.out.println(G.tirsRecus[i]);
		}
		G.tirsRecus[0] = new Coordonnee("A4");
		for (int i = 0; i < G.tirsRecus.length && G.tirsRecus[i] != null; i++) {
			System.out.println(G.tirsRecus[i]);
		}
	}**/
	
	
}