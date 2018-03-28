
/**
 *
 * @author ashutosh
 */
public class Pawn {

    public static String pawn() {
        if (ChessAI.user.equals("X")) {
            return "p";
        } else {
            return "P";
        }
    }

    public static String possibleMovesPawn(int i) {
        String list = "", oldPiece;
        int r = i / 8, c = i % 8;
        for (int j = -1; j <= 1; j += 2) {
            try {//capture
                if (ChessAI.isOpponent(ChessAI.chessBoard[r - 1][c + j].charAt(0)) && i >= 16) {
                    oldPiece = ChessAI.chessBoard[r - 1][c + j];
                    ChessAI.chessBoard[r][c] = " ";
                    ChessAI.chessBoard[r - 1][c + j] = "P";
                    if (King.isKingSafe()) {
                        list = list + r + c + (r - 1) + (c + j) + oldPiece;
                    }
                    ChessAI.chessBoard[r][c] = "P";
                    ChessAI.chessBoard[r - 1][c + j] = oldPiece;
                }
            } catch (Exception e) {
            }
            try {//promotion && capture
                if (Character.isLowerCase(ChessAI.chessBoard[r - 1][c + j].charAt(0)) && i < 16) {
                    String[] temp = {"Q", "R", "B", "K"};
                    for (int k = 0; k < 4; k++) {
                        oldPiece = ChessAI.chessBoard[r - 1][c + j];
                        ChessAI.chessBoard[r][c] = " ";
                        ChessAI.chessBoard[r - 1][c + j] = temp[k];
                        if (King.isKingSafe()) {
                            //column1,column2,captured-piece,new-piece,P
                            list = list + c + (c + j) + oldPiece + temp[k] + "P";
                        }
                        ChessAI.chessBoard[r][c] = "P";
                        ChessAI.chessBoard[r - 1][c + j] = oldPiece;
                    }
                }
            } catch (Exception e) {
            }
        }
        try {//move one up
            if (" ".equals(ChessAI.chessBoard[r - 1][c]) && i >= 16) {
                oldPiece = ChessAI.chessBoard[r - 1][c];
                ChessAI.chessBoard[r][c] = " ";
                ChessAI.chessBoard[r - 1][c] = "P";
                if (King.isKingSafe()) {
                    list = list + r + c + (r - 1) + c + oldPiece;
                }
                ChessAI.chessBoard[r][c] = "P";
                ChessAI.chessBoard[r - 1][c] = oldPiece;
            }
        } catch (Exception e) {
        }
        try {//promotion && no capture
            if (" ".equals(ChessAI.chessBoard[r - 1][c]) && i < 16) {
                String[] temp = {"Q", "R", "B", "K"};
                for (int k = 0; k < 4; k++) {
                    oldPiece = ChessAI.chessBoard[r - 1][c];
                    ChessAI.chessBoard[r][c] = " ";
                    ChessAI.chessBoard[r - 1][c] = temp[k];
                    if (King.isKingSafe()) {
                        //column1,column2,captured-piece,new-piece,P
                        list = list + c + c + oldPiece + temp[k] + "P";
                    }
                    ChessAI.chessBoard[r][c] = "P";
                    ChessAI.chessBoard[r - 1][c] = oldPiece;
                }
            }
        } catch (Exception e) {
        }
        try {//move two up
            if (" ".equals(ChessAI.chessBoard[r - 1][c]) && " ".equals(ChessAI.chessBoard[r - 2][c]) && i >= 48) {
                oldPiece = ChessAI.chessBoard[r - 2][c];
                ChessAI.chessBoard[r][c] = " ";
                ChessAI.chessBoard[r - 2][c] = "P";
                if (King.isKingSafe()) {
                    list = list + r + c + (r - 2) + c + oldPiece;
                }
                ChessAI.chessBoard[r][c] = "P";
                ChessAI.chessBoard[r - 2][c] = oldPiece;
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static String opponentPawn() {
        if (ChessAI.user.equals("X")) {
            return "P";
        } else {
            return "p";
        }
    }
}
