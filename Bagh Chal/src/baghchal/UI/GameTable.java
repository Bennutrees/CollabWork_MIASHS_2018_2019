package baghchal.UI;

import java.awt.Panel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;

public class GameTable extends AnchorPane{

	public static int NB_LIGNE = 5;
	public static int NB_COL = 5;

	public GameTable() {
		dessinTable();

		GridPane gp = new GridPane();
		gp.setPrefSize(500,500);
		gp.setMaxSize(500,500);
		gp.setMinSize(500,500);
		this.getChildren().add(gp);

		for(int i = 0; i < NB_LIGNE; i++) {
			for(int j = 0; j < NB_COL; j++) {
				String s = i + " : " + j;
				Button button = new Button(s);
				button.setPrefSize(100,100);
				button.setMaxSize(100,100);
				button.setMinSize(100,100);
				GridPane.setRowIndex(button, i);
				GridPane.setColumnIndex(button,j);
				button.setStyle("-fx-background-color: transparent;");

				button.setOnAction(new EventHandler<ActionEvent>() {
				    @Override public void handle(ActionEvent e) {
				        System.out.println(s);
				    }
				});

				gp.getChildren().add(button);
			}
		}

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
