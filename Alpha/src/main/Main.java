package main;

import functionalities.Commands;
import gui.CommandsUI;
import gui.Information;
import gui.VoiceUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import resourceloader.ResourceLoader;


public class Main extends JFrame implements MouseListener,MouseMotionListener{
    
    //variable declaration
    private final ResourceLoader rsc=new ResourceLoader();
    
    //panels
    public final JPanel topPanel=new JPanel(null);
    public final JPanel bottomPanel=new JPanel(null);
    public final JPanel centerPanel=new JPanel(null){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            int w = getWidth();
            int h = getHeight();
            Color color1 = new Color(0, 196, 204);
            Color color2 = new Color(124, 42, 232);
            GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);

        }
    };
    
    //resources
    public final ImageIcon definfo = new ImageIcon(rsc.load("res/definfo.png"));
    public final ImageIcon info = new ImageIcon(rsc.load("res/info.png"));
    public final ImageIcon defquit = new ImageIcon(rsc.load("res/defquit.png"));
    public final ImageIcon quit = new ImageIcon(rsc.load("res/quit.png"));
    public final ImageIcon defcmdwindow = new ImageIcon(rsc.load("res/defcmdwindow.png"));
    public final ImageIcon cmdwindow = new ImageIcon(rsc.load("res/cmdwindow.png"));
    public final ImageIcon defvoice = new ImageIcon(rsc.load("res/defvoice.png"));
    public final ImageIcon voice = new ImageIcon(rsc.load("res/voice.png"));
    public final ImageIcon deftutorial = new ImageIcon(rsc.load("res/deftutorial.png"));
    public final ImageIcon tutorial = new ImageIcon(rsc.load("res/tutorial.png"));
    
    public final JLabel title = new JLabel("Alpha");
    
    //texts
    public JLabel cmdwindowLabel = new JLabel(defcmdwindow);
    public JLabel voiceLabel = new JLabel(defvoice);
    public final JLabel cmdwindowtextLabel = new JLabel("Alpha-Commands");
    public final JLabel voicetextLabel = new JLabel("Alpha-Voice Commands");
    
    //styling
    public final JLabel styletop = new JLabel();
    public final JLabel styleright = new JLabel();
    public final JLabel styleleft = new JLabel();
    public final JLabel stylebottom = new JLabel();

    //imageLabels
    public JLabel infoLabel = new JLabel(definfo);
    public JLabel tutorialLabel = new JLabel(deftutorial);
    public JLabel quitLabel = new JLabel(defquit);
    
    //other gui components
    CommandsUI cui=new CommandsUI();
    VoiceUI vui=new VoiceUI();
    Information inf=new Information();

    public int x, y;
    
    private final Commands com=new Commands();
    
    public Main(){
        
        //setting up the main window
        super("Alpha");
        this.setBounds(450, 230, 420, 320);
        this.setLayout(null);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setIconImage(new ImageIcon(rsc.load("res/alpha.png")).getImage());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        //toppanel
        topPanel.setBounds(0, 0, 420, 25);
        topPanel.setBackground(new Color(0, 0, 0));
        topPanel.addMouseListener(this);
        topPanel.addMouseMotionListener(this);
        
        title.setBounds(190, 2, 100, 20);
        title.setFont(new Font("Aerial", Font.PLAIN, 20));
        title.setForeground(Color.white);
        
        topPanel.add(title);
        
        //centerpanel
        cmdwindowLabel.setBounds(250, 50, 100, 100);
        cmdwindowLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdwindowLabel.addMouseListener(this);

        cmdwindowtextLabel.setBounds(230, 160, 155, 20);
        cmdwindowtextLabel.setFont(new Font("Palatino Linotype", Font.BOLD, 17));
        cmdwindowtextLabel.setForeground(Color.WHITE);

        voiceLabel.setBounds(70, 50, 100, 100);
        voiceLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        voiceLabel.addMouseListener(this);

        voicetextLabel.setBounds(20, 160, 215, 20);
        voicetextLabel.setFont(new Font("Palatino Linotype", Font.BOLD, 17));
        voicetextLabel.setForeground(Color.WHITE);

        centerPanel.setBounds(0, 25, 420, 210);

        centerPanel.add(cmdwindowtextLabel);
        centerPanel.add(voicetextLabel);
        centerPanel.add(cmdwindowLabel);
        centerPanel.add(voiceLabel);
        
        //styling
        styletop.setOpaque(true);
        styletop.setBounds(0, 0, 420, 2);
        styletop.setBackground(Color.black);

        styleright.setOpaque(true);
        styleright.setBounds(418, 0, 2, 90);
        styleright.setBackground(Color.black);

        styleleft.setOpaque(true);
        styleleft.setBounds(0, 0, 2, 90);
        styleleft.setBackground(Color.black);

        stylebottom.setOpaque(true);
        stylebottom.setBounds(0, 83, 420, 2);
        stylebottom.setBackground(Color.black);
        
        //bottompanel
        infoLabel.setBounds(80, 8, 70, 70);
        infoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        infoLabel.setToolTipText("About");
        infoLabel.addMouseListener(this);

        tutorialLabel.setBounds(180, 8, 70, 70);
        tutorialLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tutorialLabel.setToolTipText("How to use");
        tutorialLabel.addMouseListener(this);

        quitLabel.setBounds(280, 8, 70, 70);
        quitLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        quitLabel.setToolTipText("Exit");
        quitLabel.addMouseListener(this);

        bottomPanel.setBounds(0, 235, 420, 90);
        bottomPanel.setBackground(new Color(230, 230, 230));

        bottomPanel.add(styletop);
        bottomPanel.add(styleright);
        bottomPanel.add(styleleft);
        bottomPanel.add(stylebottom);
        bottomPanel.add(infoLabel);
        bottomPanel.add(tutorialLabel);
        bottomPanel.add(quitLabel);
        
        //adding panels to main window
        this.add(topPanel);
        this.add(centerPanel);
        this.add(bottomPanel);
        
    }
    
    public void disposeWindow(){
        this.setVisible(false);
    }
    
    public static void main(String[] args) {
        Main m=new Main();
        m.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(me.getSource()==cmdwindowLabel){
            if(cui.isVisible()){
                cui.setVisible(false);
            }else{
                cui.setVisible(true);
            }
        }else if(me.getSource()==voiceLabel){
            if(vui.isVisible()){
                vui.setVisible(false);
            }else{
                vui.setVisible(true);
            }
        }else if(me.getSource()==infoLabel){
            if(inf.isVisible()){
                inf.setVisible(false);
            }else{
                inf.setVisible(true);
            }
        }else if(me.getSource()==tutorialLabel){
            try {
                Desktop d=Desktop.getDesktop();
                d.open(new File("src/Support/Tutorial.txt"));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }else if(me.getSource()==quitLabel){
            com.operation("bot", "exit");
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(me.getSource()==topPanel){
            x=me.getX();
            y=me.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == cmdwindowLabel) {
            cmdwindowLabel.setIcon(cmdwindow);
        }
        else if (me.getSource() == voiceLabel) {
            voiceLabel.setIcon(voice);
        }
        else if (me.getSource() == infoLabel) {
            infoLabel.setIcon(info);
        }
        else if (me.getSource() == tutorialLabel) {
            tutorialLabel.setIcon(tutorial);
        }
        else if (me.getSource() == quitLabel) {
            quitLabel.setIcon(quit);
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
        if (me.getSource() == cmdwindowLabel) {
            cmdwindowLabel.setIcon(defcmdwindow);
        }
        else if (me.getSource() == voiceLabel) {
            voiceLabel.setIcon(defvoice);
        }
        else if (me.getSource() == infoLabel) {
            infoLabel.setIcon(definfo);
        }
        else if (me.getSource() == tutorialLabel) {
            tutorialLabel.setIcon(deftutorial);
        }
        else if (me.getSource() == quitLabel) {
            quitLabel.setIcon(defquit);
        }
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if(me.getSource()==topPanel){
            this.setLocation(me.getXOnScreen()-x, me.getYOnScreen()-y);
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {

    }
    
    
    
}
