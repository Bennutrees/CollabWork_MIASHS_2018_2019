package baghchal.UI;

import java.awt.Panel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class GameTable extends AnchorPane{

	public static int NB_LIGNE = 5;
	public static int NB_COL = 5;
	private GridPane gp;
	private StackPane buttonTable[][];
	private ImageView tigres[];

	public GameTable() {
		this.buttonTable = new StackPane[5][5];
		this.tigres = new ImageView[4];
		this.drawTable();
		this.tableButtons();
		this.tigres[0] = this.drawTigres(0,0);
		this.tigres[1] = this.drawTigres(4,0);
		this.tigres[2] = this.drawTigres(0,4);
		this.tigres[3] = this.drawTigres(4,4);

		this.drawChevre(1,1);
	}

	private void drawTable() {
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

	private void tableButtons() {

		this.gp = new GridPane();
		this.gp.setPrefSize(500,500);
		this.gp.setMaxSize(500,500);
		this.gp.setMinSize(500,500);
		this.getChildren().add(this.gp);

		for(int i = 0; i < NB_LIGNE; i++) {
			for(int j = 0; j < NB_COL; j++) {
				String s = i + " : " + j;
				StackPane button = new StackPane();

				button.setPrefSize(100,100);
				button.setMaxSize(100,100);
				button.setMinSize(100,100);
				GridPane.setRowIndex(button, i);
				GridPane.setColumnIndex(button,j);
				button.setStyle("-fx-background-color: transparent;");

				button.setOnMouseClicked(new EventHandler<MouseEvent>() {
				    @Override public void handle(MouseEvent e) {
				        System.out.println(e.getTarget());
				    }
				});

				button.setOnMouseEntered(new EventHandler<MouseEvent>() {
				    @Override public void handle(MouseEvent e) {
				        button.setStyle("-fx-border-color: blue;");
				    }
				});
				button.setOnMouseExited(new EventHandler<MouseEvent>() {
				    @Override public void handle(MouseEvent e) {
				        button.setStyle("");
				    }
				});


				this.buttonTable[i][j] = button;
				this.gp.getChildren().add(button);
			}
		}
	}

	private ImageView drawTigres(int i, int j) {

		ImageView image = new ImageView("tigre.png");
		image.setFitWidth(50);
		image.setFitHeight(50);
		image.setMouseTransparent(true);
		StackPane.setAlignment(image,Pos.CENTER);

		this.buttonTable[i][j].getChildren().add(image);
		return image;
	}

	private ImageView drawChevre(int i, int j) {
		ImageView image = new ImageView("chevre.png");
		image.setFitWidth(50);
		image.setFitHeight(50);
		image.setMouseTransparent(true);
		StackPane.setAlignment(image,Pos.CENTER);

		this.buttonTable[i][j].getChildren().add(image);
		return image;
	}
}
