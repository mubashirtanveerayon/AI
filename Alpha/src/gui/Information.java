package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Information implements MouseListener ,MouseMotionListener{

    public static JFrame f = new JFrame();
    ImageIcon ico,img;
    JLabel inf1,ver,earlyrelease,txt1,txt2;
    Container c;
    public int x,y;
    public Information(){
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.getContentPane().setBackground(Color.DARK_GRAY);       
        ico = new ImageIcon("src\\imgs\\info.png");
        f.setIconImage(ico.getImage());
        f.setBounds(550,320,270,125);
        f.setUndecorated(true);
        f.setResizable(false);
        f.setTitle("Information");
        f.addMouseListener(this);
        f.addMouseMotionListener(this);
       
        img = new ImageIcon("src\\imgs\\info1.png");
        inf1 = new JLabel(img);
        inf1.setBounds(25,20,40,40);
        f.add(inf1);
        
        ver = new JLabel();
        ver.setText("Version 2.0");
        ver.setForeground(Color.white);
        ver.setFont(new Font("Aerial",Font.ITALIC,15));
        ver.setBounds(170,90,80,20);
        f.add(ver);
        
        txt1 = new JLabel("Created and developed");
        txt1.setForeground(Color.white);
        txt1.setFont(new Font("System",Font.PLAIN,17));
        txt1.setBounds(70,27,180,20);
        f.add(txt1);
        
        txt2 = new JLabel("by Agent47.");
        txt2.setForeground(Color.white);
        txt2.setFont(new Font("System",Font.PLAIN,16));
        txt2.setBounds(115,50,100,20);
        f.add(txt2);
        
        earlyrelease = new JLabel("Under Development");
        earlyrelease.setForeground(Color.white);
        earlyrelease.setFont(new Font("Aerial",Font.PLAIN,15));
        earlyrelease.setBounds(15,90,130,20);
        f.add(earlyrelease);
        
        f.setVisible(true);
    }
    
    
    @Override
    public void mouseDragged(MouseEvent me) {
        if (me.getSource() == f) {
            
            f.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        f.setCursor(new Cursor(Cursor.MOVE_CURSOR));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        f.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
