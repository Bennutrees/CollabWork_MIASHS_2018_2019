package baghchal.UI;

import baghchal.Board;
import baghchal.Game;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Board gameBoard = Board.getBoard();



			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,500,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			GameTable table = new GameTable(gameBoard);
			root.getChildren().add(table);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Bagh Chal");
			primaryStage.show();

			Game game= new Game(gameBoard, table);
			game.play();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
