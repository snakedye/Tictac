import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;

public class TictacTest {
    @Test
    public void testBoardClone() {
        Board board = new Board();
        Board copyBoard = board.clone();

        board.play(new Move(1, 1), Mark.X);

        System.out.println(board.toString());

        System.out.println(copyBoard.toString());

        assertNotEquals(board.get(1, 1), copyBoard.get(1, 1));
    }
    @Test
    public void testGetNextMoveAB() {
        Board board = new Board();

        Mark mainPlayer = Mark.X;
        Mark opPlayer = Mark.O;

        int round = 0;

        while (board.evaluate(mainPlayer) != 100) {
            Mark player;
            if (round % 2 == 0) {
                player = mainPlayer;
            } else {
                player = opPlayer;
            }
            System.out.println(board);
            CPUPlayer cpu = new CPUPlayer(player);
            ArrayList<Move> movesAB = cpu.getNextMoveAB(board.clone());
            if (movesAB.size() > 0) {
                Move move = movesAB.get(0);
                board.play(move, player);
            } else {
                break;
            }
            round += 1;
        }


        System.out.println(board);

        assertNotEquals(-100, board.evaluate(mainPlayer));
    }

    @Test
    public void testGetNextMoveMinMax() {
        Board board = new Board();

        Mark mainPlayer = Mark.X;
        Mark opPlayer = Mark.O;

        int round = 0;

        while (board.evaluate(mainPlayer) != 100) {
            Mark player;
            if (round % 2 == 0) {
                player = mainPlayer;
            } else {
                player = opPlayer;
            }
            CPUPlayer cpu = new CPUPlayer(player);
            ArrayList<Move> movesMinMax = cpu.getNextMoveMinMax(board.clone());
            System.out.println(movesMinMax.size());
            if (movesMinMax.size() > 0) {
                Move move = movesMinMax.get(0);
                board.play(move, player);
            } else {
                break;
            }
            round += 1;
        }


        System.out.println(board);

        assertNotEquals(-100, board.evaluate(mainPlayer));
    }

    @Test
    public void testGetNextMoveMinMaxAiman() {
        Mark player = Mark.O;
        CPUPlayer cpuPlayer = new CPUPlayer(player);
        Mark[][] marks = {
            {Mark.X, Mark.X, Mark.O},
            {Mark.X, Mark.X, Mark.EMPTY},
            {Mark.O, Mark.O, Mark.EMPTY},
        };
        Board board = new Board(marks);
        ArrayList<Move> moves = cpuPlayer.getNextMoveMinMax(board);
        System.out.println(moves.size());
        for(int i=0;i<moves.size();i++){
            System.out.println(moves.get(i).getRow()+ " " +moves.get(i).getCol());
        }
    }

    @Test
    public void testGetNextMoveABAiman() {
        Mark player = Mark.O;
        CPUPlayer cpuPlayer = new CPUPlayer(player);
        Mark[][] marks = {
            {Mark.X, Mark.O, Mark.EMPTY},
            {Mark.X, Mark.O, Mark.EMPTY},
            {Mark.EMPTY, Mark.EMPTY, Mark.EMPTY},
        };
        Board board = new Board(marks);
        ArrayList<Move> moves = cpuPlayer.getNextMoveAB(board);
        System.out.println(moves.size());
        for(int i=0;i<moves.size();i++){
            System.out.println(moves.get(i).getRow()+ " " +moves.get(i).getCol());
        }
    }

    @Test
    public void testListMoves() {
        Mark[][] listMarks = {
            {Mark.X, Mark.EMPTY, Mark.X},
            {Mark.O, Mark.EMPTY, Mark.EMPTY},
            {Mark.EMPTY, Mark.O, Mark.O}
        };

        Board board = new Board(listMarks);

        for (Move move  : board.listMoves(Mark.X)) {
            int col = move.getCol();
            int row = move.getRow();

            assertEquals(board.get(row, col), Mark.EMPTY);
        }
    }

    @Test
    public void testEvaluate() {
        Mark[][] row = {
            {Mark.X, Mark.X, Mark.X},
            {Mark.O, Mark.O, Mark.O},
            {Mark.O, Mark.O, Mark.O}
        };

        int scoreRow = new Board(row).evaluate(Mark.X);

        assertEquals(100, scoreRow);
        
        Mark[][] col = {
            {Mark.X, Mark.O, Mark.X},
            {Mark.X, Mark.O, Mark.O},
            {Mark.X, Mark.O, Mark.O}
        };

        int scoreCol = new Board(col).evaluate(Mark.X);

        assertEquals(100, scoreCol);
                
        Mark[][] diagonal = {
            {Mark.X, Mark.O, Mark.X},
            {Mark.O, Mark.X, Mark.O},
            {Mark.X, Mark.O, Mark.X}
        };

        int scoreDiagonal = new Board(diagonal).evaluate(Mark.X);

        assertEquals(100, scoreDiagonal);

        int scoreNul = new Board().evaluate(Mark.X);
        
        assertEquals(0, scoreNul);
    }
}
