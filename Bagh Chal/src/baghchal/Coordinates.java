package baghchal;

public class Coordinates implements Comparable<Coordinates> {
	private int ligne;
	private int colonne;

	public Coordinates(int ligne, int colonne) {

		this.ligne = ligne;
		this.colonne = colonne;
		if (colonne < 0 || colonne > 4 || ligne > 4 || ligne < 0) {
			throw new IllegalArgumentException("Coordonnées en dehors du Plateau");
		}
	}

	public int getLigne() {
		return ligne;
	}

	public int getColonne() {
		return colonne;
	}	
	
	public boolean equals(Object obj) {
		if (obj instanceof Coordinates) {
			Coordinates obj1 = (Coordinates) obj;
			return (obj1.colonne == this.colonne && obj1.ligne == this.ligne);
		}
		return false;
	}
	

	public int compareTo(Coordinates c) {
		int res ;
		if ((res = this.ligne - c.ligne) !=0 )
			return (res);
		else
			return (this.colonne - c.colonne);
	}
	

	public String toString() {

		String coord = "";

		coord += this.colonne + ",";
		coord += this.ligne;

		return coord;
	}
	
}