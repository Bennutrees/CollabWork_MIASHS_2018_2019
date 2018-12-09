package baghchal.UI;

import java.io.IOException;

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

	public Menu(BorderPane root) throws IOException {

		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/baghchal/UI/Menu.fxml"));
        loader.setController(this);
        VBox pane = loader.load();
        root.setCenter(pane);
	}

	@FXML
	private void newGameJCJ(){
		System.out.println("JCJ");
	}

	@FXML
	private void newGamePlayBagh(){
		System.out.println("Play Bagh");
	}

	@FXML
	private void newGamePlayChal(){
		System.out.println("Play Chal");
	}

	@FXML
	private void newGameIaVsIa(){
		System.out.println("IA VS IA");
	}
}
