package baghchal.UI;

import java.awt.Panel;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class GameTable extends AnchorPane{

	public static int NB_LIGNE = 5;
	public static int NB_COL = 5;

	public GameTable() {
		dessinTable();


	}

	private void dessinTable() {
		Line l;
		for(int i = 0; i < NB_LIGNE; i++) {
			l = new Line((i*100)+50, 50, (i*100)+50, 450);
			this.getChildren().add(l);
		}
		for(int i = 0; i < NB_LIGNE; i++) {
			l = new Line(50, (i*100)+50, 450, (i*100)+50);
			this.getChildren().add(l);
		}
		Line l1 = new Line(50, 50, 450, 450);
		Line l2 = new Line(450, 50, 50, 450);
		Line l3 = new Line(250, 50, 450, 250);
		Line l4 = new Line(250, 50, 50, 250);
		Line l5 = new Line(50, 250, 250, 450);
		Line l6 = new Line(250, 450, 450, 250);
		this.getChildren().addAll(l1, l2, l3, l4, l5, l6);
	}
}
