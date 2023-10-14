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
    private int numExploredNodes = 0;

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
        float maxScore = Float.NEGATIVE_INFINITY;

        ArrayList<Move> bestMoves = new ArrayList<>();

        for (Move move : board.listMoves(player)) {
            numExploredNodes = 0;
            float score = minMax(board.clone(), move, player);
            if (score > maxScore) {
                bestMoves.clear();  // Si le score de ce move est meilleur que les precendents alors ils sont tous pires donc on les retire
                maxScore = score; // Le score de ce move devient le nouveau max.
                bestMoves.add(move);
            } else if (score == maxScore) {
                bestMoves.add(move);
            }
        }

        return bestMoves;
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board){
        float maxScore = Float.NEGATIVE_INFINITY;

        ArrayList<Move> bestMoves = new ArrayList<>(); // Tous les moves devrait avoir le meme score.

        for (Move move : board.listMoves(player)) {
            numExploredNodes = 0;
            float score = alphaBeta(board.clone(), move, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, player);
            if (score > maxScore) {
                bestMoves.clear();  // Si le score de ce move est meilleur que les precendents alors ils sont tous pires donc on les retire
                maxScore = score; // Le score de ce move devient le nouveau max.
                bestMoves.add(move);
            } else if (score == maxScore) {
                bestMoves.add(move);
            }
        }

        return bestMoves;
    }

    public float alphaBeta(Board board, Move move, float alpha, float beta, Mark maxPlayer) {
        numExploredNodes += 1;

        board.play(move, maxPlayer);

        float score = board.evaluate(player);

        if (score != 0) {
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

        float score = board.evaluate(player);

        if (score != 0) {
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
