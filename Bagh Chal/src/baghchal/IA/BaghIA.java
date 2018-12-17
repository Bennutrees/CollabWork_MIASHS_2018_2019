package baghchal.IA;

import java.util.*;
import baghchal.*;

public class BaghIA extends IAPlayer{
	
	public BaghIA(Board board) {
		super(board);
	}

	@Override
	public Move iaAction() {
		BaghChalMinMax minmaxIA = new BaghChalMinMax(new Board(this.board), BaghChalMinMax.BAGH_TURN);
		int IA_level = 2;
		Move move = this.randomEatingMove();
		if(move == null) {
			move = minmaxIA.selectBestMove(100,IA_level);
		}
		if(move == null) {
//			move = this.bestHuntingMove();
			move = new Move(move.getStart(), move.getFinish(), this.board);
		}
		System.out.println("Selected move : " + move);
		return move;
	}

	/****************************************************************************************************/
	/********************************************* Heritage *********************************************/
	/****************************************************************************************************/
	@Override
	protected Move randomMoves() {
		List<Move> moves = this.everyPossibleMoves();
		if(!moves.isEmpty()) {
			int rand = (int)(Math.random() * moves.size());
			return moves.get(rand);
		}
		return null;
	}
	@Override
	protected List<Move> everyPossibleMoves() {
		return BaghPawn.everyBaghPossibleMoves(this.board);
	}
	
	/****************************************************************************************************/
	/******************************************* Random eating ******************************************/
	/****************************************************************************************************/
	private Move randomEatingMove() {
		List<Move> moves = this.everyBaghEatingMoves();
		if(!moves.isEmpty()) {
			int rand = (int)(Math.random() * moves.size());
			return moves.get(rand);
		}
		return null;
	}
	
	private Move randomHuntingMove() {
		List<Move> moves = this.everyPossibleMoves();
		if(!moves.isEmpty()) {
			int rand = (int)(Math.random() * moves.size());
			return moves.get(rand);
		}
		return null;
	}

	public List<Move> everyBaghEatingMoves() {
		BaghPawn[] baghsOnBoard = this.board.getBaghsOnBoard();
		
		List<Move> eatMoves = new ArrayList<Move>();
		for(BaghPawn baghPawn : baghsOnBoard) {
			eatMoves.addAll(baghPawn.possibleEatMoves());
		}
		return eatMoves;
	}

	public List<Move> everyBaghSimpleMoves() {
		BaghPawn[] baghsOnBoard = this.board.getBaghsOnBoard();
		
		List<Move> moves = new ArrayList<Move>();
		for(BaghPawn baghPawn : baghsOnBoard) {
			moves.addAll(baghPawn.possibleMoves());
		}
		return moves;
	}
	
	public List<Move> huntingChalsTrail() {
		List<Move> chalTrail = new ArrayList<Move>();
		ArrayList<ChalPawn> chalsOnBoard = this.board.getChalsOnBoard();
		BaghPawn[] baghsOnBoard = this.board.getBaghsOnBoard();
		Square[][] squaresOnBoard = this.board.getSquaresOnBoard();
		
		//Random initialization
		int shortestTrail = Integer.MAX_VALUE;
		BaghPawn selectedBaghPawn = baghsOnBoard[0];
		Square squareToReach = squaresOnBoard[selectedBaghPawn.getPosition().getX()][selectedBaghPawn.getPosition().getY()];
		//Seek for shortest trail
		for (ChalPawn chalPawn : chalsOnBoard) {
			for (Square freeSquareAroundChal : chalPawn.getAnglesOfAttack()) {
				for(BaghPawn baghPawn : baghsOnBoard) {
					int newTrail = baghPawn.manhattanDistance(freeSquareAroundChal);
					if (newTrail < shortestTrail) {
						shortestTrail = newTrail;
						selectedBaghPawn = baghPawn;
						squareToReach = freeSquareAroundChal;
					}
				}
			}
		}
		for (Move move : selectedBaghPawn.selectedPawnEveryPossibleMoves()) {
			Square newSquare = new Square(move.getFinish().getX(),move.getFinish().getY());
			if (selectedBaghPawn.manhattanDistance(squareToReach) < shortestTrail) {
				chalTrail.add(move);
			}
		}
		return chalTrail;
	}
	
	//TODO Finish buildTrail with recursive function
	public List<Move> buildTrail(BaghPawn baghPawn, Square squareToReach) {
		List<Move> trail = new ArrayList<Move>();
		Board temporaryBoard = new Board(this.board);
		int trailDistance = baghPawn.manhattanDistance(squareToReach);
		
		
		for (Move move : baghPawn.selectedPawnEveryPossibleMoves()) {
			Square newSquare = new Square(move.getFinish().getX(),move.getFinish().getY());
			if (baghPawn.manhattanDistance(squareToReach) < trailDistance) {
//				chalTrail.add(move);
			}
		}
		return trail;
	}
}
