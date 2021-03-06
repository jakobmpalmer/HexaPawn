
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jakepalmer
 */
public class Learner {    

    boolean DEBUG = false;
    BoardList bList = new BoardList();
    Move lastMove;
    Gameboard lastBoard;
    MoveList loseList = new MoveList();

    public Learner() {

    }

    public MoveList Trim(MoveList moveList, Gameboard theBoard) {

        MoveList mList = new MoveList();
        ArrayList<Integer> theBoards = new ArrayList<>();

        for (int i = 0; i < bList.size(); i++) {
            if (bList.get(i).equalsBoard(theBoard)) {
                theBoards.add(i);
                if (DEBUG) {
                    System.out.println(theBoard);
                }
            }
        }

        for (int i : theBoards) {
            mList.add(loseList.get(i));
        }

        for (Move move : mList) {
            Move toRemove = null;

            for (Move aMove : moveList) {
                if (aMove.equalsMove(move)) {
                    toRemove = aMove;
                }
            }
            if (toRemove != null) {
                moveList.remove(toRemove);
            }

        }

        if (DEBUG) {

            System.out.println("Possible Moves after trim");
            for (int i = 0; i < moveList.size(); i++) {
                System.out.println("-----------------------");
                System.out.println(moveList.get(i));
            }
        }
        return moveList;
    }

    //Save the last Board/Move the computer made
    public void YouAreDoomed() {
        loseList.add(lastMove);
        bList.add(lastBoard);

            if (DEBUG) {

                System.out.println("ADDED LOSS: #" + loseList.size());
                System.out.println(lastBoard);
                System.out.println(lastMove);
                System.out.println("Boards: ");

                for (Gameboard board : bList) {
                    System.out.println(board);
                }
            }
    }

    public void setLastMove(Gameboard theBoard, Move m) throws CloneNotSupportedException {
        lastBoard = theBoard.clone();
        lastMove = m.clone();
    }

    public BoardList getBList() {
        return bList;
    }

    public void setBList(BoardList boardList) {
        bList = boardList;
    }

    public MoveList getLoseList() {
        return loseList;
    }

    public void setLoseList(MoveList moveList) {
        loseList = moveList;
    }
}
