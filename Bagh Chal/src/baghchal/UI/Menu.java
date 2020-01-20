package baghchal.UI;

import java.io.IOException;

import baghchal.Board;
import baghchal.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Menu {

	@FXML Button jcjButton;
	@FXML Button playBaghButton;
	@FXML Button playChalButton;
	@FXML Button iaVsIaButton;
	@FXML Label resultLabel;

	private GameTable gameTable;
	private BorderPane root;
	private Board board;

	public Menu(BorderPane root) throws IOException {

        this.board = new Board();
        this.gameTable = new GameTable(board);
        this.root = root;
       
        this.BackToMenu(null);
	}
	
	public void BackToMenu(String winner) throws IOException {
		this.root.getChildren().removeAll(this.root.getChildren());
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/baghchal/UI/Menu.fxml"));
        loader.setController(this);
        VBox pane = loader.load();
        this.root.setCenter(pane);
        
        if (winner != null) {
        	this.resultLabel.setText(winner);
        }
	}

	@FXML
	private void newGameJCJ() throws IOException{
        this.loadGameTable();
		System.out.println("JCJ");
		Game game= new Game(this.gameTable, this.board, this);
		game.play();
	}

	@FXML
	private void newGamePlayBagh() throws IOException{
		this.loadGameTable();
		System.out.println("Play Bagh");
		Game game= new Game(this.gameTable, Game.CHAL_IA, this.board, this);
		game.play();
	}

	@FXML
	private void newGamePlayChal() throws IOException{
		this.loadGameTable();
		System.out.println("Play Chal");
		Game game= new Game(this.gameTable, Game.BAGH_IA, this.board, this);
		game.play();
	}

	@FXML
	private void newGameIaVsIa() throws IOException{
		this.loadGameTable();
		System.out.println("IA VS IA");
		Game game= new Game(this.gameTable,Game.BOTH_IA, this.board, this);
		game.play();
	}

	private void loadGameTable() {
		this.root.getChildren().clear();
        this.root.getChildren().addAll(this.gameTable);
	}
}
