package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;

public class Information implements MouseListener ,MouseMotionListener{

    public static JFrame f = new JFrame();
    ImageIcon ico,img;
    JLabel inf1,ver,earlyrelease,txt1,txt2;
    Container c;
    public int x,y;
    public JLabel linkLabel=new JLabel("Source Code");
    public JLabel linkStyle=new JLabel();
    
    public Information(){
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.getContentPane().setBackground(Color.DARK_GRAY);       
        ico = new ImageIcon("src\\imgs\\info.png");
        f.setIconImage(ico.getImage());
        f.setBounds(550,320,270,152);
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
        ver.setBounds(170,117,80,20);
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
        earlyrelease.setBounds(15,117,130,20);
        f.add(earlyrelease);
       
        linkLabel.setFont(new Font("Monospaced",Font.PLAIN,17));
        linkLabel.setBounds(85,85,115,15);
        linkLabel.setForeground(new Color(88,180,255));
        linkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        linkLabel.addMouseListener(this);
        f.add(linkLabel);
        
        linkStyle.setBounds(84,99,115,1);
        linkStyle.setOpaque(true);
        linkStyle.setBackground(new Color(88,180,255));
        linkStyle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        linkStyle.addMouseListener(this);
        f.add(linkStyle);
        
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
        if(e.getSource()==linkLabel||e.getSource()==linkStyle){
            Desktop d=Desktop.getDesktop();
            try{
                d.browse(new URI("https://github.com/mubashirtanveerayon/AI.git"));
            }catch(Exception ex){
                
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
