/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ashutosh
 */
public class King {

    public static String king() {
        if (ChessAI.user.equals("X")) {
            return "a";
        } else {
            return "A";
        }
    }

    public static String possibleMovesKing(int i) {
        String list = "", oldPiece;
        int r = i / 8, c = i % 8;
        for (int j = 0; j < 9; j++) {
            if (j != 4) {
                try {
                    String ch = ChessAI.chessBoard[r - 1 + j / 3][c - 1 + j % 3];
                    if (ChessAI.isOpponent(ch.charAt(0)) || " ".equals(ch)) {
                        oldPiece = ch;
                        ChessAI.chessBoard[r][c] = " ";
                        ChessAI.chessBoard[r - 1 + j / 3][c - 1 + j % 3] = king();
                        int temp = ChessAI.kingPosC;
                        ChessAI.kingPosC = i + (j / 3) * 8 + j % 3 - 9;
                        if (isKingSafe()) {
                            list = list + Integer.toString(r) + Integer.toString(c)
                                    + Integer.toString(r - 1 + j / 3)
                                    + Integer.toString(c - 1 + j % 3)
                                    + oldPiece;
                        }
                        ChessAI.chessBoard[r][c] = king();
                        ChessAI.chessBoard[r - 1 + j / 3][c - 1 + j % 3] = oldPiece;
                        ChessAI.kingPosC = temp;
                    }
                } catch (Exception e) {
                    /* Do Nothing */ }
            }
        }
        return list;
    }

    public static boolean isKingSafe() {
        //bishop/queen
        int temp = 1;
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
                try {
                    while (" ".equals(ChessAI.chessBoard[ChessAI.kingPosC / 8 + temp * i][ChessAI.kingPosC % 8 + temp * j])) {
                        temp++;
                    }
                    if (Bishop.opponentBishop().equals(ChessAI.chessBoard[ChessAI.kingPosC / 8 + temp * i][ChessAI.kingPosC % 8 + temp * j])
                            || Queen.opponentQueen().equals(ChessAI.chessBoard[ChessAI.kingPosC / 8 + temp * i][ChessAI.kingPosC % 8 + temp * j])) {
                        return false;
                    }
                } catch (Exception e) {
                }
                temp = 1;
            }
        }
        //rook/queen
        for (int i = -1; i <= 1; i += 2) {
            try {
                while (" ".equals(ChessAI.chessBoard[ChessAI.kingPosC / 8][ChessAI.kingPosC % 8 + temp * i])) {
                    temp++;
                }
                if (Rook.opponentRook().equals(ChessAI.chessBoard[ChessAI.kingPosC / 8][ChessAI.kingPosC % 8 + temp * i])
                        || Queen.opponentQueen().equals(ChessAI.chessBoard[ChessAI.kingPosC / 8][ChessAI.kingPosC % 8 + temp * i])) {
                    return false;
                }
            } catch (Exception e) {
            }
            temp = 1;
            try {
                while (" ".equals(ChessAI.chessBoard[ChessAI.kingPosC / 8 + temp * i][ChessAI.kingPosC % 8])) {
                    temp++;
                }
                if (Rook.opponentRook().equals(ChessAI.chessBoard[ChessAI.kingPosC / 8 + temp * i][ChessAI.kingPosC % 8])
                        || Queen.opponentQueen().equals(ChessAI.chessBoard[ChessAI.kingPosC / 8 + temp * i][ChessAI.kingPosC % 8])) {
                    return false;
                }
            } catch (Exception e) {
            }
            temp = 1;
        }
        //knight
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
                try {
                    if (Knight.opponentKnight().equals(ChessAI.chessBoard[ChessAI.kingPosC / 8 + i][ChessAI.kingPosC % 8 + j * 2])) {
                        return false;
                    }
                } catch (Exception e) {
                }
                try {
                    if (Knight.opponentKnight().equals(ChessAI.chessBoard[ChessAI.kingPosC / 8 + i * 2][ChessAI.kingPosC % 8 + j])) {
                        return false;
                    }
                } catch (Exception e) {
                }
            }
        }
        //pawn
        if (ChessAI.kingPosC >= 16) {
            try {
                if (Pawn.opponentPawn().equals(ChessAI.chessBoard[ChessAI.kingPosC / 80 - 1][ChessAI.kingPosC % 8 - 1])) {
                    return false;
                }
            } catch (Exception e) {
            }
            try {
                if (Pawn.opponentPawn().equals(ChessAI.chessBoard[ChessAI.kingPosC / 80 - 1][ChessAI.kingPosC % 8 + 1])) {
                    return false;
                }
            } catch (Exception e) {
            }
            //king
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 || j != 0) {
                        try {
                            if (opponentKing().equals(ChessAI.chessBoard[ChessAI.kingPosC / 8 + i][ChessAI.kingPosC % 8 + j])) {
                                return false;
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
        return true;
    }

    public static String opponentKing() {
        if (ChessAI.user.equals("X")) {
            return "A";
        } else {
            return "a";
        }
    }
}
