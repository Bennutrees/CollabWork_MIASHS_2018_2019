package baghchal.UI;

import baghchal.AbstractPawn;
import baghchal.BaghPawn;
import baghchal.Board;
import baghchal.ChalPawn;
import baghchal.Coordinates;
import baghchal.Move;
import baghchal.Square;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class GameTable extends AnchorPane{

    public static int NB_LIGNE = 5;
    public static int NB_COL = 5;
    private DrawBC drawer;
    private GridPane gp;
    private MyPane buttonTable[][];
    private ImageView tigres[];

    private Board gameBoard;

    private MyPane selectedPane = null;

    public GameTable() {
        this.gameBoard = Board.getBoard();
        this.buttonTable = new MyPane[5][5];
        this.tigres = new ImageView[4];
        this.drawer = new DrawBC(this);

        this.tableButtons();

        for(int i=0; i<4; i++) {
            BaghPawn bp = gameBoard.getBaghsOnBoard()[i];
            this.tigres[i] = this.drawer.drawTigre(bp.getPosition().getX(), bp.getPosition().getY());
        }
    }

    public StackPane[][] getButtonTable() {
        return buttonTable;
    }

    public void endGame() {
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                MyPane p = this.buttonTable[i][j];
                p.setOnMouseEntered(null);
                p.setOnMousePressed(null);
                p.setOnMouseReleased(null);
            }
        }
    }

	public void drawMoves(Coordinates[] movement, boolean isChal) {
		if(isChal) {

			if(movement[1] == null) {
				this.drawer.drawChevre(movement[0]);
			}
			else {
				this.drawer.removeDraw(this.buttonTable[movement[0].getX()][movement[0].getY()]);
				this.drawer.drawChevre(movement[1]);
			}

		}
		else {
			this.drawer.removeDraw(buttonTable[movement[0].getX()][movement[0].getY()]);
			this.drawer.drawTigre(movement[1]);
		}
	}
	
	public void drawMoves(Move move, boolean isChal) {
		Coordinates start = move.getStart();
		this.drawer.removeDraw(this.buttonTable[start.getX()][start.getY()]);
		if(isChal) {
			this.drawer.drawChevre(move.getFinish());
		}
		else {
			this.drawer.drawTigre(move.getFinish());
		}
	}

	public void removeDraw(Coordinates coord) {
		this.drawer.removeDraw(coord.getX(), coord.getY());
	}
	
	

	/****************************************************************************************************/
	/***************************************** private methods ******************************************/
	/****************************************************************************************************/

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

	private void defaultMouseEvent() {
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				MyPane p = this.buttonTable[i][j];
				p.setOnMouseEntered(new EventHandler<MouseEvent>() {
				    @Override public void handle(MouseEvent e) {
				        p.setStyle("-fx-border-color: red;");
				    }
				});
				p.setOnMousePressed(null);
				p.setOnMouseReleased(null);
			}
		}
	}

	/**********************************************************************/
	/******************************  Chals   ******************************/
	/**********************************************************************/
	public void chalPlayerPlacement(EventHandler<MouseEvent> event) {
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				MyPane p = this.buttonTable[i][j];
				if(p.getSquare().getIsAvailable()){
					p.setOnMouseEntered(new EventHandler<MouseEvent>() {
					    @Override public void handle(MouseEvent e) {
					        p.setStyle("-fx-border-color: green;");
					    }
					});
					p.setOnMousePressed(new EventHandler<MouseEvent>() {
					    @Override public void handle(MouseEvent e) {
					        selectedPane = (MyPane) e.getTarget();
					        if(selectedPane.getSquare().getIsAvailable()) {
					        	Coordinates posi = selectedPane.getSquare().getPosition();
					        	gameBoard.addChal(posi);
					        	drawer.drawChevre(posi.getX(), posi.getY());
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

	public void chalPlayerTurnSelect(EventHandler<MouseEvent> event) {
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				MyPane p = this.buttonTable[i][j];
				AbstractPawn pawn = this.gameBoard.getPawnsMap().get(p.getSquare());
				if(pawn instanceof ChalPawn) {
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

	public void chalPlayerMove(EventHandler<MouseEvent> event) {
		ChalPawn cp = (ChalPawn) this.gameBoard.getPawnsMap().get(this.selectedPane.getSquare());

		for(Move c : cp.possibleMoves()) {
			MyPane p = this.buttonTable[c.getFinish().getX()][c.getFinish().getY()];
			p.setOnMouseEntered(new EventHandler<MouseEvent>() {
			    @Override public void handle(MouseEvent e) {
			        p.setStyle("-fx-border-color: green;");
			    }
			});
			p.setOnMousePressed(new EventHandler<MouseEvent>() {
			    @Override public void handle(MouseEvent e) {
			        MyPane targetPane = (MyPane) e.getTarget();
			        Move mv = new Move(selectedPane.getSquare().getPosition(), targetPane.getSquare().getPosition());

			        mv.doMove();
			        drawer.removeDraw(selectedPane);
			        Coordinates coord = targetPane.getSquare().getPosition();
			        drawer.drawChevre(coord.getX(),coord.getY());
			    }
			});
			p.setOnMouseReleased(event);
		}
	}


	/**********************************************************************/
	/******************************  Bagh    ******************************/
	/**********************************************************************/

	private void selectBaghPawn(EventHandler<MouseEvent> event) {
		for(BaghPawn bp : this.gameBoard.getBaghsOnBoard()) {
			MyPane p = this.buttonTable[bp.getPosition().getX()][bp.getPosition().getY()];
			AbstractPawn pawn = this.gameBoard.getPawnsMap().get(p.getSquare());
			if(pawn instanceof BaghPawn) {
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
		}
	}
	public void baghPlayerSelect(EventHandler<MouseEvent> event) {
		this.defaultMouseEvent();
		this.selectBaghPawn(event);
	}

	public void baghPlayerMove(EventHandler<MouseEvent> event, EventHandler<MouseEvent> postEvent) {

		this.defaultMouseEvent();
		this.selectBaghPawn(postEvent);

		BaghPawn bp = (BaghPawn) this.gameBoard.getPawnsMap().get(this.selectedPane.getSquare());

		for(Move c : bp.allPossibleMoves()) {
			MyPane p = this.buttonTable[c.getFinish().getX()][c.getFinish().getY()];
			p.setOnMouseEntered(new EventHandler<MouseEvent>() {
			    @Override public void handle(MouseEvent e) {
			        p.setStyle("-fx-border-color: green;");
			    }
			});
			p.setOnMousePressed(new EventHandler<MouseEvent>() {
			    @Override public void handle(MouseEvent e) {
			        MyPane targetPane = (MyPane) e.getTarget();
			        Move mv = new Move(selectedPane.getSquare().getPosition(), targetPane.getSquare().getPosition());
			        Coordinates eatenChal = mv.doMove();
			        if(eatenChal != null)
			        	drawer.removeDraw(eatenChal.getX(), eatenChal.getY());

			        drawer.removeDraw(selectedPane);
			        Coordinates coord = targetPane.getSquare().getPosition();
			        drawer.drawTigre(coord.getX(),coord.getY());
			    }
			});
			p.setOnMouseReleased(event);
		}
	}


}
