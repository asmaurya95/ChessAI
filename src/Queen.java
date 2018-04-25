
/**
 *
 * @author ashutosh
 */
public class Queen {

    public static String possibleMovesQueen(int i) {
        String list = "", oldPiece;
        int r = i / 8, c = i % 8;
        int temp = 1;
        for (int j = -1; j <= 1; j++) {
            for (int k = -1; k <= 1; k++) {
                if (j != 0 || k != 0) {
                    try {
                        while (" ".equals(ChessAI.chessBoard[r + temp * j][c + temp * k])) {
                            oldPiece = ChessAI.chessBoard[r + temp * j][c + temp * k];
                            ChessAI.chessBoard[r][c] = " ";
                            ChessAI.chessBoard[r + temp * j][c + temp * k] = "Q";
                            if (King.isKingSafe()) {
                                list = list + r + c + (r + temp * j) + (c + temp * k) + oldPiece;
                            }
                            ChessAI.chessBoard[r][c] = "Q";
                            ChessAI.chessBoard[r + temp * j][c + temp * k] = oldPiece;
                            temp++;
                        }
                        if (Character.isLowerCase(ChessAI.chessBoard[r + temp * j][c + temp * k].charAt(0))) {
                            oldPiece = ChessAI.chessBoard[r + temp * j][c + temp * k];
                            ChessAI.chessBoard[r][c] = " ";
                            ChessAI.chessBoard[r + temp * j][c + temp * k] = "Q";
                            if (King.isKingSafe()) {
                                list = list + r + c + (r + temp * j) + (c + temp * k) + oldPiece;
                            }
                            ChessAI.chessBoard[r][c] = "Q";
                            ChessAI.chessBoard[r + temp * j][c + temp * k] = oldPiece;

                        }
                    } catch (Exception e) {
                        /*Do Nothing*/ }
                    temp = 1;
                }
            }
        }
        return list;
    }

}
