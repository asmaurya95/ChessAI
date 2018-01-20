/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author ashutosh
 */
public class GUI extends JPanel{
    @Override
    // following method overrides 'paint' from javax.swing.JComponent
    public void paint(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(10,10,20,20);
    }
    
}
