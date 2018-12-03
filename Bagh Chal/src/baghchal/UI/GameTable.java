package baghchal.UI;

import java.awt.Panel;

import baghchal.BaghPawn;
import baghchal.Board;
import baghchal.ChalPawn;
import baghchal.Coordinates;
import baghchal.Move;
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
	private DrawBC drawer;
	private GridPane gp;
	private MyPane buttonTable[][];
	private ImageView tigres[];

	private Board gameBoard;

	private MyPane selectedPane = null;

	public GameTable(Board board) {
		this.gameBoard = board;
		this.buttonTable = new MyPane[5][5];
		this.tigres = new ImageView[4];
		this.drawer = new DrawBC(this);

		this.tableButtons();

		for(int i=0; i<4; i++) {
			BaghPawn bp = gameBoard.getBaghOnBoard()[i];
			this.tigres[i] = this.drawer.drawTigre(bp.getPosition().getLigne(), bp.getPosition().getColonne());
		}
	}

	public StackPane[][] getButtonTable() {
		return buttonTable;
	}

	public void drawBagh(int i, int j){
		this.drawer.drawTigre(i,j);
	}

	public void drawChal(int i, int j){
		this.drawer.drawChevre(i,j);
	}

	private void tableButtons() {

		this.gp = new GridPane();
		this.gp.setPrefSize(500,500);
		this.gp.setMaxSize(500,500);
		this.gp.setMinSize(500,500);
		this.getChildren().add(this.gp);

		for(int i = 0; i < NB_LIGNE; i++) {
			for(int j = 0; j < NB_COL; j++) {
				MyPane button = new MyPane(this.gameBoard.getSquaresOnBoard()[i][j]);
				button.setPrefSize(100,100);
				button.setMaxSize(100,100);
				button.setMinSize(100,100);
				GridPane.setRowIndex(button, i);
				GridPane.setColumnIndex(button,j);
				button.setStyle("-fx-background-color: transparent;");

				button.setOnMouseEntered(new EventHandler<MouseEvent>() {
				    @Override public void handle(MouseEvent e) {
				        button.setStyle("-fx-border-color: red;");
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

	private void defaultMouseEvent(MyPane p) {
		p.setOnMouseEntered(new EventHandler<MouseEvent>() {
		    @Override public void handle(MouseEvent e) {
		        p.setStyle("-fx-border-color: red;");
		    }
		});
		p.setOnMousePressed(null);
		p.setOnMouseReleased(null);
	}

	public void chalPlayerPlacement(EventHandler<MouseEvent> event) {

		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {

				MyPane p = this.buttonTable[i][j];
				if(p.getSquare().isAvailable()){
					p.setOnMouseEntered(new EventHandler<MouseEvent>() {
					    @Override public void handle(MouseEvent e) {
					        p.setStyle("-fx-border-color: green;");
					    }
					});
					p.setOnMousePressed(new EventHandler<MouseEvent>() {
					    @Override public void handle(MouseEvent e) {
					        selectedPane = (MyPane) e.getTarget();
					        if(selectedPane.getSquare().isAvailable()) {
					        	Coordinates posi = selectedPane.getSquare().getPosition();
					        	gameBoard.addChal(posi);
					        	drawer.drawChevre(posi.getLigne(), posi.getColonne());
					        	selectedPane = null;
					        }
					    }
					});
					p.setOnMouseReleased(event);
				}
				else {
					this.defaultMouseEvent(p);
				}
			}
		}
	}

	public void baghPlayerSelect(EventHandler<MouseEvent> event) {
//		for(BaghPawn bp : this.gameBoard.getBaghOnBoard()) {
//			MyPane p = this.buttonTable[bp.getPosition().getLigne()][bp.getPosition().getColonne()];
//			if(p.getSquare().getPawn() instanceof BaghPawn) {
//				p.setOnMouseEntered(new EventHandler<MouseEvent>() {
//				    @Override public void handle(MouseEvent e) {
//				        p.setStyle("-fx-border-color: green;");
//				    }
//				});
//				p.setOnMousePressed(new EventHandler<MouseEvent>() {
//				    @Override public void handle(MouseEvent e) {
//				        selectedPane = (MyPane) e.getTarget();
//				        //TODO: Animation de la selection
//				    }
//				});
//				p.setOnMouseReleased(event);
//			}
//		}

		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				MyPane p = this.buttonTable[i][j];
				if(p.getSquare().getPawn() instanceof BaghPawn) {
					p.setOnMouseEntered(new EventHandler<MouseEvent>() {
					    @Override public void handle(MouseEvent e) {
					        p.setStyle("-fx-border-color: green;");
					    }
					});
					p.setOnMousePressed(new EventHandler<MouseEvent>() {
					    @Override public void handle(MouseEvent e) {
					        selectedPane = (MyPane) e.getTarget();
					        //TODO: Animation de la selection
					    }
					});
					p.setOnMouseReleased(event);
				}
				else {
					this.defaultMouseEvent(p);
				}
			}
		}
	}

	public void baghPlayerMove(EventHandler<MouseEvent> event) {

		BaghPawn bp = (BaghPawn) this.selectedPane.getSquare().getPawn();

		for(Coordinates c : bp.allPosibleMoves()) {
			MyPane p = this.buttonTable[c.getLigne()][c.getColonne()];
			p.setOnMouseEntered(new EventHandler<MouseEvent>() {
			    @Override public void handle(MouseEvent e) {
			        p.setStyle("-fx-border-color: green;");
			    }
			});
			p.setOnMousePressed(new EventHandler<MouseEvent>() {
			    @Override public void handle(MouseEvent e) {
			        MyPane targetPane = (MyPane) e.getTarget();
			        Move mv = new Move(selectedPane.getSquare().getPosition(), targetPane.getSquare().getPosition());
			        if(mv.isEatingMove()) {
			        	Coordinates eatenChal = mv.getBoardCoordsOfGoatBeingEaten();
			        	gameBoard.eatChal(eatenChal);
			        	drawer.removeDraw(eatenChal.getLigne(), eatenChal.getColonne());
			        }
			        mv.doMove();
			        drawer.removeDraw(selectedPane);
			        Coordinates coord = targetPane.getSquare().getPosition();
			        drawer.drawTigre(coord.getLigne(),coord.getColonne());
			        //TODO: bouger les pions tigres
			    }
			});
			p.setOnMouseReleased(event);
		}
	}


}
