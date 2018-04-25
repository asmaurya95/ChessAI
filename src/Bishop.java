
/**
 *
 * @author ashutosh
 */
public class Bishop {

    public static String possibleMovesBishop(int i) {
        String list = "", oldPiece;
        int r = i / 8, c = i % 8;
        int temp = 1;
        for (int j = -1; j <= 1; j += 2) {
            for (int k = -1; k <= 1; k += 2) {
                try {
                    while (" ".equals(ChessAI.chessBoard[r + temp * j][c + temp * k])) {
                        oldPiece = ChessAI.chessBoard[r + temp * j][c + temp * k];
                        ChessAI.chessBoard[r][c] = " ";
                        ChessAI.chessBoard[r + temp * j][c + temp * k] = "B";
                        if (King.isKingSafe()) {
                            list = list + r + c + (r + temp * j) + (c + temp * k) + oldPiece;
                        }
                        ChessAI.chessBoard[r][c] = "B";
                        ChessAI.chessBoard[r + temp * j][c + temp * k] = oldPiece;
                        temp++;
                    }
                    if (Character.isLowerCase(ChessAI.chessBoard[r + temp * j][c + temp * k].charAt(0))) {
                        oldPiece = ChessAI.chessBoard[r + temp * j][c + temp * k];
                        ChessAI.chessBoard[r][c] = " ";
                        ChessAI.chessBoard[r + temp * j][c + temp * k] = "B";
                        if (King.isKingSafe()) {
                            list = list + r + c + (r + temp * j) + (c + temp * k) + oldPiece;
                        }
                        ChessAI.chessBoard[r][c] = "B";
                        ChessAI.chessBoard[r + temp * j][c + temp * k] = oldPiece;

                    }
                } catch (Exception e) {
                    /*Do Nothing*/ }
                // Now reset temp to 1
                temp = 1;
            }
        }
        return list;
    }
}
