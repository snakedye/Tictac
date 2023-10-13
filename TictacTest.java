import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;

public class TictacTest {
    @Test
    public void testGetNextMoveMinMax() {
        Board board = new Board();

        CPUPlayer cpu = new CPUPlayer(Mark.X);

        ArrayList<Move> moves = cpu.getNextMoveMinMax(board);

        for (Move move: moves) {
            Board currentBoard = new Board();
            currentBoard.play(move, Mark.X);
            float score = currentBoard.evaluate(Mark.X);

            assertNotEquals(-100, score);
        }
    }

    @Test
    public void testGetNextMoveAB() {
        Board board = new Board();

        CPUPlayer cpu = new CPUPlayer(Mark.X);

        ArrayList<Move> moves = cpu.getNextMoveAB(board);

        for (Move move: moves) {
            Board currentBoard = new Board();
            currentBoard.play(move, Mark.X);
            float score = currentBoard.evaluate(Mark.X);

            assertNotEquals(-100, score);
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
    }
}
