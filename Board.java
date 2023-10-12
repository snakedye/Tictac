import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class Board
{
    private Mark[][] board;

    // Ne pas changer la signature de cette méthode
    public Board() {
        board = new Mark[3][3];
    }

    public Board(Mark[][] board) {
        this.board = board;
    }

    // Place la pièce 'mark' sur le plateau, à la
    // position spécifiée dans Move
    //
    // Ne pas changer la signature de cette méthode
    public void play(Move m, Mark mark){
            this.board[m.getRow()][m.getCol()] = mark;
    }


    // retourne  100 pour une victoire
    //          -100 pour une défaite
    //           0   pour un match nul
    // Ne pas changer la signature de cette méthode
    public int evaluate(Mark mark){
        Mark opPlayer;
        switch (mark) {
            case X:
                opPlayer = Mark.O;
                break;
            case O:
                opPlayer = Mark.X;
                break;
            default:
                opPlayer = Mark.EMPTY;
                break;
        }

        int playerScore = simpleEval(mark);

        if (playerScore == 0) {
            return -simpleEval(opPlayer);
        } else {
            return playerScore;
        }
    }

    public int simpleEval(Mark mark){
        for (int i = 0; i < this.board.length; i++) {
            Mark ca = get(i, 0);
            Mark cb = get(i, 1);
            Mark cc = get(i, 2);

            if (ca.equals(mark) && cb.equals(mark) && cc.equals(mark)) {
                return 100;
            }

            Mark ra = get(i, 0);
            Mark rb = get(i, 1);
            Mark rc = get(i, 2);


            if (ra.equals(mark) && rb.equals(mark) && rc.equals(mark)) {
                return 100;
            }
        }

        Mark la = get(0, 0);
        Mark ce = get(1, 1);
        Mark lc = get(2, 2);

        Mark ra = get(0, 2);
        Mark rc = get(2, 0);

        if (la.equals(mark) && ce.equals(mark) && lc.equals(mark)) {
            return 100;
        }

        if (ra.equals(mark) && ce.equals(mark) && rc.equals(mark)) {
            return 100;
        }

        return 0;
    }

    public ArrayList<Move> listMoves(Mark mark) {
        ArrayList<Move> listMoves = new ArrayList<>();

        for (int row = 0; row < this.board.length; row ++) {
            for (int col = 0; col < this.board[row].length; col ++) {
                if (!get(row, col).equals(mark)) {
                    listMoves.add(new Move(row, col));
                }
            }
        }

        return listMoves;
    }

    public Mark get(int col, int row) {
        return this.board[col][row];
    }
}
