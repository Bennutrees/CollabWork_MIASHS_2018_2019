package baghchal.UI;

import baghchal.Square;
import javafx.scene.layout.StackPane;

public class MyPane extends StackPane{
    private Square square;

    public MyPane(Square square) {
        this.square = square;
    }

    public Square getSquare() {
    	return this.square;
    }
}
