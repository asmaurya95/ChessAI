/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        return "";
    }

    public static String opponentPawn() {
        if (ChessAI.user.equals("X")) {
            return "P";
        } else {
            return "p";
        }
    }
}
