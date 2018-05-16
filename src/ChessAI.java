
/**
 *
 * @author ashutosh maurya
 * @author aman verma
 */
import javax.swing.*;
import java.io.IOException;

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
    
    Future Task : Bitboard can be used to implement the chess board, 
    which is much faster than a 2-D array
     */
    static int kingPosL, kingPosC;
    static int globalDepth = 4;
    static String user;
    static String chessBoard[][] = {
        {"r", "k", "b", "q", "a", "b", "k", "r"},
        {"p", "p", "p", "p", "p", "p", "p", "p"},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {"P", "P", "P", "P", "P", "P", "P", "P"},
        {"R", "K", "B", "Q", "A", "B", "K", "R"}};

    static int humanasWhite = -1;//1= Human as White 0=Black 
    static String playerName;

    public static void main(String[] args) throws IOException {
        // Prepare the Database
        Database.createDatabase();
        Database.createTable();

        // first get white king's location
        while (!"A".equals(chessBoard[kingPosC / 8][kingPosC % 8])) {
            kingPosC++;
        }
        // now get black king's location
        while (!"a".equals(chessBoard[kingPosL / 8][kingPosL % 8])) {
            kingPosL++;
        }
        playerName = JOptionPane.showInputDialog(null, "Enter your name :");
        GUI ui = new GUI();
        JFrame f = new JFrame("ChessAI");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(ui);
        f.setSize(500, 500);
        f.setVisible(true);
        System.out.println(possibleMoves());
        Object[] option = {"NO", "YES"};
        humanasWhite = JOptionPane.showOptionDialog(null, "Would you like to make the first move?", "Starting the game....", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
        System.out.println(humanasWhite);
        if (humanasWhite == 0) {
            makeMove(alphaBeta(globalDepth, 1000000, -1000000, "", 0));
            flipBoard();
            f.repaint();
        }
        /*makeMove("7655 ");
        undoMove("7655 ");
        for (int i = 0; i < 8; i++) {
            System.out.println(Arrays.toString(chessBoard[i]));
        }
         */
    }

    public static String alphaBeta(int depth, int beta, int alpha, String move, int player) {
        //return in the form of 1234b##########
        String list = possibleMoves();
        if (depth == 0 || list.length() == 0) {
            return move + (Rating.rating(list.length(), depth) * (player * 2 - 1));
        }
        //sort the moves
        list = sortMoves(list);
        player = 1 - player;//either 1 or 0
        for (int i = 0; i < list.length(); i += 5) {
            makeMove(list.substring(i, i + 5));
            flipBoard();
            String returnString = alphaBeta(depth - 1, beta, alpha, list.substring(i, i + 5), player);
            int value = Integer.valueOf(returnString.substring(5));
            flipBoard();
            undoMove(list.substring(i, i + 5));
            if (player == 0) {
                if (value <= beta) {
                    beta = value;
                    if (depth == globalDepth) {
                        move = returnString.substring(0, 5);
                    }
                }
            } else if (value > alpha) {
                alpha = value;
                if (depth == globalDepth) {
                    move = returnString.substring(0, 5);
                }
            }
            if (alpha >= beta) {
                if (player == 0) {
                    return move + beta;
                } else {
                    return move + alpha;
                }
            }
        }
        if (player == 0) {
            return move + beta;
        } else {
            return move + alpha;
        }
    }

    public static void flipBoard() {
        String temp;
        for (int i = 0; i < 32; i++) {
            int r = i / 8, c = i % 8;
            if (Character.isUpperCase(chessBoard[r][c].charAt(0))) {
                temp = chessBoard[r][c].toLowerCase();
            } else {
                temp = chessBoard[r][c].toUpperCase();
            }
            if (Character.isUpperCase(chessBoard[7 - r][7 - c].charAt(0))) {
                chessBoard[r][c] = chessBoard[7 - r][7 - c].toLowerCase();
            } else {
                chessBoard[r][c] = chessBoard[7 - r][7 - c].toUpperCase();
            }
            chessBoard[7 - r][7 - c] = temp;
        }
        int kingTemp = kingPosC;
        kingPosC = 63 - kingPosL;
        kingPosL = 63 - kingTemp;
    }

    public static void makeMove(String move) {
        if (move.charAt(4) != 'P') {
            chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))];
            chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = " ";
            if ("A".equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])) {
                kingPosC = 8 * Character.getNumericValue(move.charAt(2)) + Character.getNumericValue(move.charAt(3));
            }
        } else {
            //if pawn promotion
            chessBoard[1][Character.getNumericValue(move.charAt(0))] = " ";
            chessBoard[0][Character.getNumericValue(move.charAt(1))] = String.valueOf(move.charAt(3));
        }
    }

    public static void undoMove(String move) {
        if (move.charAt(4) != 'P') {
            chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))];
            chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = String.valueOf(move.charAt(4));
            if ("A".equals(chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))])) {
                kingPosC = 8 * Character.getNumericValue(move.charAt(0)) + Character.getNumericValue(move.charAt(1));
            }
        } else {
            //if pawn promotion
            chessBoard[1][Character.getNumericValue(move.charAt(0))] = "P";
            chessBoard[0][Character.getNumericValue(move.charAt(1))] = String.valueOf(move.charAt(2));
        }
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
            String currentPiece = chessBoard[i / 8][i % 8];
            switch (currentPiece) {
                case "P":
                    move += Pawn.possibleMovesPawn(i);
                    break;
                case "Q":
                    move += Queen.possibleMovesQueen(i);
                    break;
                case "A":
                    move += King.possibleMovesKing(i);
                    break;
                case "B":
                    move += Bishop.possibleMovesBishop(i);
                    break;
                case "K":
                    move += Knight.possibleMovesKnight(i);
                    break;
                case "R":
                    move += Rook.possibleMovesRook(i);
                    break;
            }
        }
        return move;
    }

    // Sorting the Moves 
    public static String sortMoves(String list) {
        int[] score = new int[list.length() / 5];
        for (int i = 0; i < list.length(); i += 5) {
            makeMove(list.substring(i, i + 5));
            score[i / 5] = -Rating.rating(-1, 0);
            undoMove(list.substring(i, i + 5));
        }
        String newListA = "", newListB = list;
        for (int i = 0; i < Math.min(6, list.length() / 5); i++) {//first few moves only
            int max = -1000000, maxLocation = 0;
            for (int j = 0; j < list.length() / 5; j++) {
                if (score[j] > max) {
                    max = score[j];
                    maxLocation = j;
                }
            }
            score[maxLocation] = -1000000;
            newListA += list.substring(maxLocation * 5, maxLocation * 5 + 5);
            newListB = newListB.replace(list.substring(maxLocation * 5, maxLocation * 5 + 5), "");
        }
        return newListA + newListB;
    }
}
