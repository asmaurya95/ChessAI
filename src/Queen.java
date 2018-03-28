/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ashutosh
 */
public class Queen {

    public static String queen() {
        if (ChessAI.user.equals("X")) {
            return "q";
        } else {
            return "Q";
        }
    }

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
                            ChessAI.chessBoard[r + temp * j][c + temp * k] = queen();
                            if (King.isKingSafe()) {
                                list = list + r + c + (r + temp * j) + (c + temp * k) + oldPiece;
                            }
                            ChessAI.chessBoard[r][c] = queen();
                            ChessAI.chessBoard[r + temp * j][c + temp * k] = oldPiece;
                            temp++;
                        }
                        if (ChessAI.isOpponent(ChessAI.chessBoard[r + temp * j][c + temp * k].charAt(0))) {
                            oldPiece = ChessAI.chessBoard[r + temp * j][c + temp * k];
                            ChessAI.chessBoard[r][c] = " ";
                            ChessAI.chessBoard[r + temp * j][c + temp * k] = queen();
                            if (King.isKingSafe()) {
                                list = list + r + c + (r + temp * j) + (c + temp * k) + oldPiece;
                            }
                            ChessAI.chessBoard[r][c] = queen();
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

    public static String opponentQueen() {
        if (ChessAI.user.equals("X")) {
            return "Q";
        } else {
            return "q";
        }
    }
}
