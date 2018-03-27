
/**
 *
 * @author ashutosh maurya
 * @author aman verma
 */
import UserInterface.GUI;   //API for Chess GUI
import javax.swing.*;       //Java Swing Library

public class ChessAI {

    /**
     * @param args
     * @param i
     * @return
     */

    /* The Chess Board is represented by a 8*8 2D Array
    r/R : Rook
    k/K : King
    b/B : Bishop
    q/Q : Queen
    a/A : King 
    p/P : Pawn
    
    Idea : Bitboard can be used to implement the chess board, 
    which is much faster than a 2-D array
     */
    static int kingPosL, kingPosC;
    static String chessBoard[][] = {
        {"r", "k", "b", "q", "a", "b", "k", "r"},
        {"p", "p", "p", "p", "p", "p", "p", "p"},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {"P", "P", "P", "P", "P", "P", "P", "P"},
        {"R", "K", "B", "Q", "A", "B", "K", "R"}};

    public static void main(String[] args) {

        /* GUI ui = new GUI();
        JFrame f = new JFrame("ChessAI");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(ui);
        f.setSize(500, 500);
        f.setVisible(true); */
        System.out.println(possibleMoves());
    }


    /*
    MOVE GENERATION
    The following method returns a string which tells the next best possible move 
    String Format : r1 c1 r2 c2 CAPTURED-PIECE
    This means that the chess piece moves from Row1 , Column1 to Row2, Column2 
    which captured the stated CAPTURED-PIECE
    a space means no capture
    Example : "1234b" represents row1,column2 moves to row3 column4 and capture b
     */
    public static String possibleMoves() {
        String move = "";
        for (int i = 0; i < 64; i++) {
            switch (chessBoard[i / 8][i % 8]) {
                case "P":
                    move += possibleMovesPawn(i);
                    break;
                case "R":
                    move += possibleMovesRook(i);
                    break;
                case "K":
                    move += possibleMovesKnight(i);
                    break;
                case "B":
                    move += possibleMovesBishop(i);
                    break;
                case "Q":
                    move += possibleMovesQueen(i);
                    break;
                case "A":
                    move += possibleMovesKing(i);
                    break;
            }
        }
        return move;
    }

    public static String possibleMovesPawn(int i) {
        return "";
    }

    public static String possibleMovesKnight(int i) {
        String list = "", oldPiece;
        int r = i / 8, c = i % 8;
        for (int j = -1; j <= 1; j += 2) {
            for (int k = -1; k <= 1; k += 2) {
                try {
                    if (" ".equals(chessBoard[r + j][c + k * 2])
                            || Character.isLowerCase(chessBoard[r + j][c + k * 2].charAt(0))) {
                        oldPiece = chessBoard[r + j][c + k * 2];
                        chessBoard[r][c] = " ";
                        chessBoard[r + j][c + k * 2] = "K";
                        if (isKingSafe()) {
                            list = list + r + c + (r + j) + (c + k * 2) + oldPiece;
                        }
                        chessBoard[r][c] = "K";
                        chessBoard[r + j][c + k * 2] = oldPiece;
                    }
                } catch (Exception e) {
                }
                try {
                    if (" ".equals(chessBoard[r + j * 2][c + k])
                            || Character.isLowerCase(chessBoard[r + j * 2][c + k].charAt(0))) {
                        oldPiece = chessBoard[r + j * 2][c + k];
                        chessBoard[r][c] = " ";
                        chessBoard[r + j * 2][c + k] = "K";
                        if (isKingSafe()) {
                            list = list + r + c + (r + j * 2) + (c + k) + oldPiece;
                        }
                        chessBoard[r][c] = "K";
                        chessBoard[r + j * 2][c + k] = oldPiece;
                    }
                } catch (Exception e) {
                }
            }
        }
        return list;
    }

    public static String possibleMovesRook(int i) {
        String list = "", oldPiece;
        int r = i / 8, c = i % 8;
        int temp = 1;
        for (int j = -1; j <= 1; j += 2) {
            try {
                while (" ".equals(chessBoard[r][c + temp * j])) {
                    oldPiece = chessBoard[r][c + temp * j];
                    chessBoard[r][c] = " ";
                    chessBoard[r][c + temp * j] = "R";
                    if (isKingSafe()) {
                        list = list + r + c + (r) + (c + temp * j) + oldPiece;
                    }
                    chessBoard[r][c] = "R";
                    chessBoard[r][c + temp * j] = oldPiece;
                    temp++;
                }
                if (Character.isLowerCase(chessBoard[r][c + temp * j].charAt(0))) {
                    oldPiece = chessBoard[r][c + temp * j];
                    chessBoard[r][c] = " ";
                    chessBoard[r][c + temp * j] = "R";
                    if (isKingSafe()) {
                        list = list + r + c + (r) + (c + temp * j) + oldPiece;
                    }
                    chessBoard[r][c] = "R";
                    chessBoard[r][c + temp * j] = oldPiece;

                }
            } catch (Exception e) {
                /*Do Nothing*/ }
            // Now reset temp to 1
            temp = 1;
            try {
                while (" ".equals(chessBoard[r + temp * j][c])) {
                    oldPiece = chessBoard[r + temp * j][c];
                    chessBoard[r][c] = " ";
                    chessBoard[r + temp * j][c] = "R";
                    if (isKingSafe()) {
                        list = list + r + c + (r + temp * j) + (c) + oldPiece;
                    }
                    chessBoard[r][c] = "R";
                    chessBoard[r + temp * j][c] = oldPiece;
                    temp++;
                }
                if (Character.isLowerCase(chessBoard[r + temp * j][c].charAt(0))) {
                    oldPiece = chessBoard[r + temp * j][c];
                    chessBoard[r][c] = " ";
                    chessBoard[r + temp * j][c] = "R";
                    if (isKingSafe()) {
                        list = list + r + c + (r + temp * j) + (c) + oldPiece;
                    }
                    chessBoard[r][c] = "R";
                    chessBoard[r + temp * j][c] = oldPiece;

                }
            } catch (Exception e) {
                /*Do Nothing*/ }
            // Now reset temp to 1
            temp = 1;
        }
        return list;

    }

    public static String possibleMovesBishop(int i) {
        String list = "", oldPiece;
        int r = i / 8, c = i % 8;
        int temp = 1;
        for (int j = -1; j <= 1; j += 2) {
            for (int k = -1; k <= 1; k += 2) {
                try {
                    while (" ".equals(chessBoard[r + temp * j][c + temp * k])) {
                        oldPiece = chessBoard[r + temp * j][c + temp * k];
                        chessBoard[r][c] = " ";
                        chessBoard[r + temp * j][c + temp * k] = "B";
                        if (isKingSafe()) {
                            list = list + r + c + (r + temp * j) + (c + temp * k) + oldPiece;
                        }
                        chessBoard[r][c] = "B";
                        chessBoard[r + temp * j][c + temp * k] = oldPiece;
                        temp++;
                    }
                    if (Character.isLowerCase(chessBoard[r + temp * j][c + temp * k].charAt(0))) {
                        oldPiece = chessBoard[r + temp * j][c + temp * k];
                        chessBoard[r][c] = " ";
                        chessBoard[r + temp * j][c + temp * k] = "B";
                        if (isKingSafe()) {
                            list = list + r + c + (r + temp * j) + (c + temp * k) + oldPiece;
                        }
                        chessBoard[r][c] = "B";
                        chessBoard[r + temp * j][c + temp * k] = oldPiece;

                    }
                } catch (Exception e) {
                    /*Do Nothing*/ }
                // Now reset temp to 1
                temp = 1;
            }
        }
        return list;
    }

    public static String possibleMovesQueen(int i) {
        String list = "", oldPiece;
        int r = i / 8, c = i % 8;
        int temp = 1;
        for (int j = -1; j <= 1; j++) {
            for (int k = -1; k <= 1; k++) {
                if (j != 0 || k != 0) {
                    try {
                        while (" ".equals(chessBoard[r + temp * j][c + temp * k])) {
                            oldPiece = chessBoard[r + temp * j][c + temp * k];
                            chessBoard[r][c] = " ";
                            chessBoard[r + temp * j][c + temp * k] = "Q";
                            if (isKingSafe()) {
                                list = list + r + c + (r + temp * j) + (c + temp * k) + oldPiece;
                            }
                            chessBoard[r][c] = "Q";
                            chessBoard[r + temp * j][c + temp * k] = oldPiece;
                            temp++;
                        }
                        if (Character.isLowerCase(chessBoard[r + temp * j][c + temp * k].charAt(0))) {
                            oldPiece = chessBoard[r + temp * j][c + temp * k];
                            chessBoard[r][c] = " ";
                            chessBoard[r + temp * j][c + temp * k] = "Q";
                            if (isKingSafe()) {
                                list = list + r + c + (r + temp * j) + (c + temp * k) + oldPiece;
                            }
                            chessBoard[r][c] = "Q";
                            chessBoard[r + temp * j][c + temp * k] = oldPiece;

                        }
                    } catch (Exception e) {
                        /*Do Nothing*/ }
                    temp = 1;
                }
            }
        }
        return list;
    }

    public static String possibleMovesKing(int i) {
        String list = "", oldPiece;
        int r = i / 8, c = i % 8;
        for (int j = 0; j < 9; j++) {
            if (j != 4) {
                try {
                    String ch = chessBoard[r - 1 + j / 3][c - 1 + j % 3];
                    if (Character.isLowerCase(ch.charAt(0)) || " ".equals(ch)) {
                        oldPiece = ch;
                        chessBoard[r][c] = " ";
                        chessBoard[r - 1 + j / 3][c - 1 + j % 3] = "A";
                        int temp = kingPosC;
                        kingPosC = i + (j / 3) * 8 + j % 3 - 9;
                        if (isKingSafe()) {
                            list = list + Integer.toString(r) + Integer.toString(c)
                                    + Integer.toString(r - 1 + j / 3)
                                    + Integer.toString(c - 1 + j % 3)
                                    + oldPiece;
                        }
                        chessBoard[r][c] = "A";
                        chessBoard[r - 1 + j / 3][c - 1 + j % 3] = oldPiece;
                        kingPosC = temp;
                    }
                } catch (Exception e) {
                    /* Do Nothing */ }
            }
        }
        return list;
    }

    public static boolean isKingSafe() {
        return true;
    }
}
