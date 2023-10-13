import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class CPUPlayer
{
    private Mark player;

    // Contient le nombre de noeuds visités (le nombre
    // d'appel à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.
    private int numExploredNodes;

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu){
        this.player = cpu;
    }

    // Ne pas changer cette méthode
    public int  getNumOfExploredNodes(){
        return numExploredNodes;
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveMinMax(Board board)
    {
        numExploredNodes = 0;

        ArrayList<Move> moves = board.listMoves(player);

        ArrayList<Float> scoreList = new ArrayList<>(moves.stream().map((move) -> minMax(board.clone(), move, player)).toList());

        for (Float score : scoreList) {
            if (scoreList.indexOf(score) != scoreList.lastIndexOf(score)) {
                return moves;
            } 
        }

        return new ArrayList<>();
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board){
        numExploredNodes = 0;

        ArrayList<Move> moves = board.listMoves(player);

        ArrayList<Float> scoreList = new ArrayList<>(moves.stream().map((move) -> alphaBeta(board.clone(), move, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, player)).toList());

        for (Float score : scoreList) {
            if (scoreList.indexOf(score) != scoreList.lastIndexOf(score)) {
                return moves;
            } 
        }

        return new ArrayList<>();
    }

    public float alphaBeta(Board board, Move move, float alpha, float beta, Mark maxPlayer) {
        numExploredNodes += 1;

        board.play(move, maxPlayer);

        float score = board.evaluate(maxPlayer);

        if (score == 100) {
            return score;
        }

        float maxEval;
        float minEval;

        Mark opPlayer;

        switch (maxPlayer) {
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

        if (player.equals(maxPlayer)) {
            maxEval = Float.NEGATIVE_INFINITY;

            for (Move childMove: board.listMoves(maxPlayer)) {
                float eval = alphaBeta(board.clone(), childMove, alpha, beta, opPlayer);
                maxEval = Float.max(maxEval, eval);
                alpha = Float.max(alpha, eval);
                if (beta <= alpha) {
                    break;
                } 
            }

            return maxEval;
        } else {
            minEval = Float.POSITIVE_INFINITY;

            for (Move childMove: board.listMoves(maxPlayer)) {
                float eval = alphaBeta(board.clone(), childMove, alpha, beta, opPlayer);
                minEval = Float.min(minEval, eval);
                beta = Float.min(beta, eval);
                if (beta <= alpha) {
                    break;
                }
            }

            return minEval;
        }
    }

    public float minMax(Board board, Move move, Mark maxPlayer) {
        numExploredNodes += 1;

        board.play(move, maxPlayer);

        float score = board.evaluate(maxPlayer);

        if (score == 100) {
            return score;
        }

        float maxEval;
        float minEval;

        Mark opPlayer;

        switch (maxPlayer) {
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

        if (player.equals(maxPlayer)) {
            maxEval = Float.NEGATIVE_INFINITY;

            for (Move childMove: board.listMoves(maxPlayer)) {
                float eval = minMax(board.clone(), childMove, opPlayer);
                maxEval = Float.max(maxEval, eval);
            }

            return maxEval;
        } else {
            minEval = Float.POSITIVE_INFINITY;

            for (Move childMove: board.listMoves(maxPlayer)) {
                float eval = minMax(board.clone(), childMove, opPlayer);
                minEval = Float.min(minEval, eval);
            }

            return minEval;
        }
    }

}
