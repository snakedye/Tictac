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
        int result = 0;
        Mark minMark = Mark.EMPTY;

        if(mark == Mark.X){
            minMark = Mark.O;
        }else if (mark == Mark.O){
            minMark = Mark.X;
        }

        for(int i=0; i<3; i++){
            if(board[i][0] == mark && board[i][1]  == mark && board[i][2]  == mark){
                result = 100;
            }
            if(board[0][i] == mark && board[1][i]  == mark && board[2][i]  == mark){
                result = 100;
            }
            if(board[i][0] == minMark && board[i][1]  == minMark && board[i][2]  == minMark){
                result = -100;
            }
            if(board[0][i] == minMark && board[1][i]  == minMark && board[2][i]  == minMark){
                result = -100;
            }
        }

        return result;
    }
}
