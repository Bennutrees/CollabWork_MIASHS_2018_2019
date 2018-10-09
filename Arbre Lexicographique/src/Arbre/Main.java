package Arbre;

public class Main {

	public static void main(String[] args) {
		NoeudAbstrait n;
		Marque m = new Marque(null);
		n = m.ajout("coucou");
		n = n.ajout("coup");
		n = n.ajout("attention");
		n = n.ajout("attenta");
		n = n.ajout("nul");
		System.out.println(n.toString());
		

	}

}
