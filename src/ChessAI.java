/**
 *
 * @author ashutosh maurya
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
    
    Future Task : Bitboards can be used to implement chess board, which are much faster than arrays
    */
    static String chessBoard[][] = {
        {"r","k","b","q","a","b","k","r"},
        {"p","p","p","p","p","p","p","p"},
        {" "," "," "," "," "," "," "," "},
        {" "," "," "," "," "," "," "," "},
        {" "," "," "," "," "," "," "," "},
        {" "," "," "," "," "," "," "," "},
        {"P","P","P","P","P","P","P","P"},
        {"R","K","B","Q","A","B","K","R"}};
    public static void main(String[] args) {
        /*
        GUI ui=new GUI();
        JFrame f=new JFrame("ChessAI");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(ui);
        f.setSize(500, 500);
        f.setVisible(true);
        */
    }
    

    /*
    MOVE GENERATION
    The following method returns a string which tells the next best possible move 
    String Format : r1 c1 r2 c2 CAPTURED-PIECE
    This means that the chess piece moves from Row1 , Column1 to Row2, Column2 which captured the stated CAPTURED-PIECE
    a space means no capture
    Example : "1234b" represents row1,column2 moves to row3 column4 and capture b
    */
    public static String possibleMoves() {
        String move="";
        for(int i=0;i<64;i++){
            switch(chessBoard[i/8][i%8]) {
                case "P" : move+=possibleMoves_Pawn(i);
                    break;
                case "R" : move+=possibleMoves_Rook(i);
                    break;
                case "K" : move+=possibleMoves_Knight(i);
                    break;
                case "B" : move+=possibleMoves_Bishop(i);
                    break;
                case "Q" : move+=possibleMoves_Queen(i);
                    break;
                case "A" : move+=possibleMoves_King(i);
                    break;
            }
        }
        return move;
    }
 
    public static String possibleMoves_Pawn(int i) {
        return "";
    }
    
    public static String possibleMoves_Rook(int i) {
        return "";
    }
    
    public static String possibleMoves_Knight(int i) {
        return "";
    }
    
    public static String possibleMoves_Bishop(int i) {
        return "";
    }
    
    public static String possibleMoves_King(int i) {
        return "";
    }
    
    public static String possibleMoves_Queen(int i) {
        return "";
    }
}
