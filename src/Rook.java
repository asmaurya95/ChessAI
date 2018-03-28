
/**
 *
 * @author ashutosh
 */
public class Rook {

    public static String rook() {
        if (ChessAI.user.equals("X")) {
            return "r";
        } else {
            return "R";
        }
    }

    public static String possibleMovesRook(int i) {
        String list = "", oldPiece;
        int r = i / 8, c = i % 8;
        int temp = 1;
        for (int j = -1; j <= 1; j += 2) {
            try {
                while (" ".equals(ChessAI.chessBoard[r][c + temp * j])) {
                    oldPiece = ChessAI.chessBoard[r][c + temp * j];
                    ChessAI.chessBoard[r][c] = " ";
                    ChessAI.chessBoard[r][c + temp * j] = rook();
                    if (King.isKingSafe()) {
                        list = list + r + c + (r) + (c + temp * j) + oldPiece;
                    }
                    ChessAI.chessBoard[r][c] = rook();
                    ChessAI.chessBoard[r][c + temp * j] = oldPiece;
                    temp++;
                }
                if (ChessAI.isOpponent(ChessAI.chessBoard[r][c + temp * j].charAt(0))) {
                    oldPiece = ChessAI.chessBoard[r][c + temp * j];
                    ChessAI.chessBoard[r][c] = " ";
                    ChessAI.chessBoard[r][c + temp * j] = rook();
                    if (King.isKingSafe()) {
                        list = list + r + c + (r) + (c + temp * j) + oldPiece;
                    }
                    ChessAI.chessBoard[r][c] = rook();
                    ChessAI.chessBoard[r][c + temp * j] = oldPiece;

                }
            } catch (Exception e) {
                /*Do Nothing*/ }
            // Now reset temp to 1
            temp = 1;
            try {
                while (" ".equals(ChessAI.chessBoard[r + temp * j][c])) {
                    oldPiece = ChessAI.chessBoard[r + temp * j][c];
                    ChessAI.chessBoard[r][c] = " ";
                    ChessAI.chessBoard[r + temp * j][c] = rook();
                    if (King.isKingSafe()) {
                        list = list + r + c + (r + temp * j) + (c) + oldPiece;
                    }
                    ChessAI.chessBoard[r][c] = rook();
                    ChessAI.chessBoard[r + temp * j][c] = oldPiece;
                    temp++;
                }
                if (ChessAI.isOpponent(ChessAI.chessBoard[r + temp * j][c].charAt(0))) {
                    oldPiece = ChessAI.chessBoard[r + temp * j][c];
                    ChessAI.chessBoard[r][c] = " ";
                    ChessAI.chessBoard[r + temp * j][c] = rook();
                    if (King.isKingSafe()) {
                        list = list + r + c + (r + temp * j) + (c) + oldPiece;
                    }
                    ChessAI.chessBoard[r][c] = rook();
                    ChessAI.chessBoard[r + temp * j][c] = oldPiece;

                }
            } catch (Exception e) {
                /*Do Nothing*/ }
            // Now reset temp to 1
            temp = 1;
        }
        return list;
    }

    public static String opponentRook() {
        if (ChessAI.user.equals("X")) {
            return "R";
        } else {
            return "r";
        }
    }
}
