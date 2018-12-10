package baghchal.UI;

import java.io.IOException;

import baghchal.Board;
import baghchal.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Menu {

	@FXML Button jcjButton;
	@FXML Button playBaghButton;
	@FXML Button playChalButton;
	@FXML Button iaVsIaButton;

	private Board gameBoard;
	private GameTable gameTable;
	private BorderPane root;

	public Menu(BorderPane root) throws IOException {

		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/baghchal/UI/Menu.fxml"));
        loader.setController(this);
        VBox pane = loader.load();
        root.setCenter(pane);

        this.gameBoard = Board.getBoard();
        this.gameTable = new GameTable();
        this.root = root;
	}

	@FXML
	private void newGameJCJ(){
        this.loadGameTable();
		System.out.println("JCJ");
		Game game= new Game(this.gameTable);
		game.play();
	}

	@FXML
	private void newGamePlayBagh(){
		this.loadGameTable();
		System.out.println("Play Bagh");
		Game game= new Game(this.gameTable, Game.CHAL_IA);
		game.play();
	}

	@FXML
	private void newGamePlayChal(){
		this.loadGameTable();
		System.out.println("Play Chal");
		Game game= new Game(this.gameTable, Game.BAGH_IA);
		game.play();
	}

	@FXML
	private void newGameIaVsIa(){
		this.loadGameTable();
		System.out.println("IA VS IA");
		Game game= new Game(this.gameTable,Game.BOTH_IA);
		game.play();
	}

	private void loadGameTable() {
		this.root.getChildren().clear();
        this.root.getChildren().addAll(this.gameTable);
	}
}
