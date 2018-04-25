
/**
 *
 * @author ashutosh maurya
 * @author aman verma
 */
import javax.swing.*;       //Java Swing Library
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

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

    public static void main(String[] args) throws IOException {
        // first get white king's location
        while (!"A".equals(chessBoard[kingPosC / 8][kingPosC % 8])) {
            kingPosC++;
        }
        // now get black king's location
        while (!"a".equals(chessBoard[kingPosL / 8][kingPosL % 8])) {
            kingPosL++;
        }
        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your name: ");
        String name = reader.readLine();
        System.out.println("Choose White/Black");
        System.out.print("Enter X for White or Y for Black: ");
        user = reader.readLine();*/
        user = "Y";
        /*GUI ui = new GUI();
        JFrame f = new JFrame("ChessAI");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(ui);
        f.setSize(500, 500);
        f.setVisible(true);*/
        System.out.println(possibleMoves());
        makeMove(alphaBeta(globalDepth, 1000000, -1000000, "", 0));
        makeMove("7655 ");
        undoMove("7655 ");
        for (int i = 0; i < 8; i++) {
            System.out.println(Arrays.toString(chessBoard[i]));
        }
    }

    public static int rating() {
        return 0;
    }

    public static String alphaBeta(int depth, int beta, int alpha, String move, int player) {
        //return in the form of 1234b##########
        String list = possibleMoves();
        if (depth == 0 || list.length() == 0) {
            return move + (rating() * (player * 2 - 1));
        }
        //sort later
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
        if (move.charAt(4) != Pawn.pawn().charAt(0)) {
            chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))];
            chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = " ";
            if (King.king().equals(chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])) {
                kingPosC = 8 * Character.getNumericValue(move.charAt(2)) + Character.getNumericValue(move.charAt(3));
            }
        } else {
            //if pawn promotion
            chessBoard[1][Character.getNumericValue(move.charAt(0))] = " ";
            chessBoard[0][Character.getNumericValue(move.charAt(1))] = String.valueOf(move.charAt(3));
        }
    }

    public static void undoMove(String move) {
        if (move.charAt(4) != Pawn.pawn().charAt(0)) {
            chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))] = chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))];
            chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))] = String.valueOf(move.charAt(4));
            if (King.king().equals(chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))])) {
                kingPosC = 8 * Character.getNumericValue(move.charAt(0)) + Character.getNumericValue(move.charAt(1));
            }
        } else {
            //if pawn promotion
            chessBoard[1][Character.getNumericValue(move.charAt(0))] = Pawn.pawn();
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
        String myPawn = Pawn.pawn(), myRook = Rook.rook(), myKnight = Knight.knight(), myBishop = Bishop.bishop(),
                myQueen = Queen.queen(), myKing = King.king();
        for (int i = 0; i < 64; i++) {
            String currentPiece = chessBoard[i / 8][i % 8];
            if (currentPiece.equals(myPawn)) {
                move += Pawn.possibleMovesPawn(i);
            }
            if (currentPiece.equals(myRook)) {
                move += Rook.possibleMovesRook(i);
            }
            if (currentPiece.equals(myKnight)) {
                move += Knight.possibleMovesKnight(i);
            }
            if (currentPiece.equals(myBishop)) {
                move += Bishop.possibleMovesBishop(i);
            }
            if (currentPiece.equals(myQueen)) {
                move += Queen.possibleMovesQueen(i);
            }
            if (currentPiece.equals(myKing)) {
                move += King.possibleMovesKing(i);
            }
        }
        return move;
    }

    public static boolean isOpponent(char piece) {
        if ((user.equals("X") && Character.isUpperCase(piece))
                || (user.equals("Y") && Character.isLowerCase(piece))) {
            return true;
        } else {
            return false;
        }
    }
}
