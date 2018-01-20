/**
 *
 * @author ashutosh maurya
 */

import UserInterface.GUI;   //API for adding a GUI to ChessAI
import javax.swing.*;       //Java Swing Library

public class ChessAI {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        GUI ui=new GUI();
        JFrame f=new JFrame("ChessAI");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(ui);
        f.setSize(500, 500);
        f.setVisible(true);
    }
    
}
