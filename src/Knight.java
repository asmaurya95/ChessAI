/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ashutosh
 */
public class Knight {

    public static String knight() {
        if (ChessAI.user.equals("X")) {
            return "k";
        } else {
            return "K";
        }
    }

    public static String possibleMovesKnight(int i) {
        String list = "", oldPiece;
        int r = i / 8, c = i % 8;
        for (int j = -1; j <= 1; j += 2) {
            for (int k = -1; k <= 1; k += 2) {
                try {
                    if (" ".equals(ChessAI.chessBoard[r + j][c + k * 2])
                            || ChessAI.isOpponent(ChessAI.chessBoard[r + j][c + k * 2].charAt(0))) {
                        oldPiece = ChessAI.chessBoard[r + j][c + k * 2];
                        ChessAI.chessBoard[r][c] = " ";
                        ChessAI.chessBoard[r + j][c + k * 2] = knight();
                        if (King.isKingSafe()) {
                            list = list + r + c + (r + j) + (c + k * 2) + oldPiece;
                        }
                        ChessAI.chessBoard[r][c] = knight();
                        ChessAI.chessBoard[r + j][c + k * 2] = oldPiece;
                    }
                } catch (Exception e) {
                }
                try {
                    if (" ".equals(ChessAI.chessBoard[r + j * 2][c + k])
                            || ChessAI.isOpponent(ChessAI.chessBoard[r + j * 2][c + k].charAt(0))) {
                        oldPiece = ChessAI.chessBoard[r + j * 2][c + k];
                        ChessAI.chessBoard[r][c] = " ";
                        ChessAI.chessBoard[r + j * 2][c + k] = knight();
                        if (King.isKingSafe()) {
                            list = list + r + c + (r + j * 2) + (c + k) + oldPiece;
                        }
                        ChessAI.chessBoard[r][c] = knight();
                        ChessAI.chessBoard[r + j * 2][c + k] = oldPiece;
                    }
                } catch (Exception e) {
                }
            }
        }
        return list;
    }

    public static String opponentKnight() {
        if (ChessAI.user.equals("X")) {
            return "K";
        } else {
            return "k";
        }
    }
}
