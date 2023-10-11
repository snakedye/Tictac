import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TictacTest {
    @Test
    public void testGetNextMoveMinMax() {
    }

    @Test
    public void testGetNextMoveAB() {
        
    }

    @Test
    public void evaluate() {
        Mark[][] arr = {{Mark.X, Mark.X, Mark.X},{Mark.X, Mark.X, Mark.X},{Mark.X, Mark.X, Mark.X}};
        Board board = new Board(arr);
        
        int score = board.evaluate(Mark.X);

        assertEquals(score, 100);
    }
}
