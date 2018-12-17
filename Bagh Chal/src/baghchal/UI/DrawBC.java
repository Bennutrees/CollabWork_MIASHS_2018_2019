package baghchal.UI;

import baghchal.Coordinates;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;

public class DrawBC {

	private GameTable gt;

	public DrawBC(GameTable gameTable) {
		this.gt = gameTable;
		this.drawTable();
	}

	/*Draw the table of the game*/
	private void drawTable() {
		Line l;
		for(int i = 0; i < GameTable.NB_LIGNE; i++) {
			l = new Line((i*100)+50, 50, (i*100)+50, 450);
			this.gt.getChildren().add(l);
		}
		for(int i = 0; i < GameTable.NB_LIGNE; i++) {
			l = new Line(50, (i*100)+50, 450, (i*100)+50);
			this.gt.getChildren().add(l);
		}
		Line l1 = new Line(50, 50, 450, 450);
		Line l2 = new Line(450, 50, 50, 450);
		Line l3 = new Line(250, 50, 450, 250);
		Line l4 = new Line(250, 50, 50, 250);
		Line l5 = new Line(50, 250, 250, 450);
		Line l6 = new Line(250, 450, 450, 250);
		this.gt.getChildren().addAll(l1, l2, l3, l4, l5, l6);
	}

	/*Draw the Bagh image on the table with his (x,y) value. */
	public ImageView drawBagh(int i, int j) {

		ImageView image = new ImageView("tigre.png");
		this.imageOptions(image);

		this.gt.getButtonTable()[i][j].getChildren().add(image);
		return image;
	}

	/*Draw the Bagh image on the table with his Coordinates. */
	public ImageView drawBagh(Coordinates coords) {

		ImageView image = new ImageView("tigre.png");
		this.imageOptions(image);

		this.gt.getButtonTable()[coords.getX()][coords.getY()].getChildren().add(image);
		return image;
	}

	/*Draw the Chal image on the table with his (x,y) value. */
	public ImageView drawChal(int i, int j) {
		ImageView image = new ImageView("chevre.png");
		this.imageOptions(image);

		this.gt.getButtonTable()[i][j].getChildren().add(image);
		return image;
	}

	/*Draw the Chal image on the table with his Coordinates.*/
	public ImageView drawChevre(Coordinates coords) {

		ImageView image = new ImageView("chevre.png");
		this.imageOptions(image);

		this.gt.getButtonTable()[coords.getX()][coords.getY()].getChildren().add(image);
		return image;
	}

	/*That method set the size and properties of Bagh and Chal images.*/
	private void imageOptions(ImageView image) {
		image.setFitWidth(50);
		image.setFitHeight(50);
		image.setMouseTransparent(true);
		StackPane.setAlignment(image,Pos.CENTER);
	}

	/*Remove image on (x,y) position.*/
	public void removeDraw(int i, int j) {
		this.gt.getButtonTable()[i][j].getChildren().clear();
	}

	/*Remove image on the selected MyPane.*/
	public void removeDraw(MyPane selectedPane) {
		selectedPane.getChildren().clear();
	}
}


