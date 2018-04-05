
/**
 *
 * @author ashutosh maurya
 * @author aman verma
 */
import javax.swing.*;       //Java Swing Library
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your name: ");
        String name = reader.readLine();
        System.out.println("Choose White/Black");
        System.out.print("Enter X for White or Y for Black: ");
        user = reader.readLine();*/
        user = "Y";
        GUI ui = new GUI();
        JFrame f = new JFrame("ChessAI");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(ui);
        f.setSize(500, 500);
        f.setVisible(true);
        System.out.println(possibleMoves());
        System.out.println(alphaBeta(globalDepth, 1000000, -1000000, "", 0));
        makeMove("7655 ");
        undoMove("7655 ");
        for (int i=0;i<8;i++) {
            System.out.println(Arrays.toString(chessBoard[i]));
        }
    }
    public static String alphaBeta(int depth, int beta, int alpha, String move, int player) {
        //return in the form of 1234b##########
        //String list=posibleMoves();
        String list="1";
        if (depth==0 || list.length()==0) {return move+(rating()*(player*2-1));}
        list="";
        System.out.print("How many moves are there: ");
        Scanner sc=new Scanner(System.in);
        int temp=sc.nextInt();
        for (int i=0;i<temp;i++) {
            list+="1111b";
        }
        //sort later
        player=1-player;//either 1 or 0
        for (int i=0;i<list.length();i+=5) {
            makeMove(list.substring(i,i+5));
            flipBoard();
            String returnString=alphaBeta(depth-1, beta, alpha, list.substring(i,i+5), player);
            int value=Integer.valueOf(returnString.substring(5));
            flipBoard();
            undoMove(list.substring(i,i+5));
            if (player==0) {
                if (value<=beta) {beta=value; if (depth==globalDepth) {move=returnString.substring(0,5);}}
            } else {
                if (value>alpha) {alpha=value; if (depth==globalDepth) {move=returnString.substring(0,5);}}
            }
            if (alpha>=beta) {
                if (player==0) {return move+beta;} else {return move+alpha;}
            }
        }
        if (player==0) {return move+beta;} else {return move+alpha;}
    }
    public static void flipBoard() {
        
    }
    public static void makeMove(String move) {
        /*public static void makeMove(String move) {
            int xf,yf,xt,yt;
            //x1,y1,x2,y2,captured piece
            if (move.charAt(4)!='C' && move.charAt(4)!='P') {
            {
                xt=Character.getNumericValue(move.charAt(2));
            }
            yt=Character.getNumericValue(move.charAt(3));
            xf=Character.getNumericValue(move.charAt(0));
            yf=Character.getNumericValue(move.charAt(1));
            }
        }*/
        if (move.charAt(4)!='P') {
            chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]=chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))];
            chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]=" ";
        } else {
            //if pawn promotion
            chessBoard[1][Character.getNumericValue(move.charAt(0))]=" ";
            chessBoard[0][Character.getNumericValue(move.charAt(1))]=String.valueOf(move.charAt(3));
        }
    }
    public static void undoMove(String move) {
        if (move.charAt(4)!='P') {
            chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]=chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))];
            chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]=String.valueOf(move.charAt(4));
        } else {
            //if pawn promotion
            chessBoard[1][Character.getNumericValue(move.charAt(0))]="P";
            chessBoard[0][Character.getNumericValue(move.charAt(1))]=String.valueOf(move.charAt(2));
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
