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
	
	public void setPosition(int x, int y) {
		this.ligne = x;
		this.colonne = y;
	}
	
	
	
	public boolean equals(Coordinates position) {
		return position.getColonne() == this.getColonne() && position.getLigne() == this.getLigne();
	}

	public int compareTo(Coordinates position) {
		int res ;
		if ((res = this.getLigne() - position.getLigne()) != 0 )
			return (res);
		else
			return (this.getColonne() - position.getColonne());
	}
	

	public String toString() {

		String coord = "";

		coord += this.getColonne() + ",";
		coord += this.getLigne();

		return coord;
	}
	
}