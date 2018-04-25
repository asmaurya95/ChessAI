
/**
 *
 * @author ashutosh
 */
public class King {

    public static String possibleMovesKing(int i) {
        String list = "", oldPiece;
        int r = i / 8, c = i % 8;
        for (int j = 0; j < 9; j++) {
            if (j != 4) {
                try {
                    String ch = ChessAI.chessBoard[r - 1 + j / 3][c - 1 + j % 3];
                    if (Character.isLowerCase(ch.charAt(0)) || " ".equals(ch)) {
                        oldPiece = ch;
                        ChessAI.chessBoard[r][c] = " ";
                        ChessAI.chessBoard[r - 1 + j / 3][c - 1 + j % 3] = "A";
                        int temp = ChessAI.kingPosC;
                        ChessAI.kingPosC = i + (j / 3) * 8 + j % 3 - 9;
                        if (isKingSafe()) {
                            list = list + Integer.toString(r) + Integer.toString(c)
                                    + Integer.toString(r - 1 + j / 3)
                                    + Integer.toString(c - 1 + j % 3)
                                    + oldPiece;
                        }
                        ChessAI.chessBoard[r][c] = "A";
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
                    if ("b".equals(ChessAI.chessBoard[ChessAI.kingPosC / 8 + temp * i][ChessAI.kingPosC % 8 + temp * j])
                            || "q".equals(ChessAI.chessBoard[ChessAI.kingPosC / 8 + temp * i][ChessAI.kingPosC % 8 + temp * j])) {
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
                if ("r".equals(ChessAI.chessBoard[ChessAI.kingPosC / 8][ChessAI.kingPosC % 8 + temp * i])
                        || "q".equals(ChessAI.chessBoard[ChessAI.kingPosC / 8][ChessAI.kingPosC % 8 + temp * i])) {
                    return false;
                }
            } catch (Exception e) {
            }
            temp = 1;
            try {
                while (" ".equals(ChessAI.chessBoard[ChessAI.kingPosC / 8 + temp * i][ChessAI.kingPosC % 8])) {
                    temp++;
                }
                if ("r".equals(ChessAI.chessBoard[ChessAI.kingPosC / 8 + temp * i][ChessAI.kingPosC % 8])
                        || "q".equals(ChessAI.chessBoard[ChessAI.kingPosC / 8 + temp * i][ChessAI.kingPosC % 8])) {
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
                    if ("k".equals(ChessAI.chessBoard[ChessAI.kingPosC / 8 + i][ChessAI.kingPosC % 8 + j * 2])) {
                        return false;
                    }
                } catch (Exception e) {
                }
                try {
                    if ("k".equals(ChessAI.chessBoard[ChessAI.kingPosC / 8 + i * 2][ChessAI.kingPosC % 8 + j])) {
                        return false;
                    }
                } catch (Exception e) {
                }
            }
        }
        //pawn
        if (ChessAI.kingPosC >= 16) {
            try {
                if ("p".equals(ChessAI.chessBoard[ChessAI.kingPosC / 80 - 1][ChessAI.kingPosC % 8 - 1])) {
                    return false;
                }
            } catch (Exception e) {
            }
            try {
                if ("p".equals(ChessAI.chessBoard[ChessAI.kingPosC / 80 - 1][ChessAI.kingPosC % 8 + 1])) {
                    return false;
                }
            } catch (Exception e) {
            }
            //king
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 || j != 0) {
                        try {
                            if ("a".equals(ChessAI.chessBoard[ChessAI.kingPosC / 8 + i][ChessAI.kingPosC % 8 + j])) {
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
}
