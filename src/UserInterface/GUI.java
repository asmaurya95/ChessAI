/**
 *
 * @author ashutosh
 */
package UserInterface;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JPanel implements MouseListener, MouseMotionListener{
    static int x,y;
    static int squareSize=50;
    // following method overrides 'paint' from javax.swing.JComponent
    @Override
    public void paint(Graphics g){
        super.paint(g);
        this.setBackground(Color.yellow);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        // The following for loop colors the window like a chess board pattern
        for(int i=0;i<64;i+=2){
            g.setColor(new Color(255,200,100));
            g.fillRect((i%8+(i/8)%2)*squareSize, (i/8)*squareSize, squareSize, squareSize);
            g.setColor(new Color(150,50,30));
            g.fillRect(((i+1)%8-((i+1)/8)%2)*squareSize, ((i+1)/8)*squareSize, squareSize, squareSize);
        }
    }
    @Override
    public void mouseMoved(MouseEvent e){
        
    }
    @Override
    public void mousePressed(MouseEvent e){
        
    }
    @Override
    public void mouseReleased(MouseEvent e){
        
    }
    @Override
    public void mouseClicked(MouseEvent e){
        
    }
    @Override
    public void mouseDragged(MouseEvent e){
        
    }
    @Override
    public void mouseEntered(MouseEvent e){
        
    }
    @Override
    public void mouseExited(MouseEvent e){
        
    }
}
