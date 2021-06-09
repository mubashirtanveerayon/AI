package gui;

import functionalities.SpeechRecognizer;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import resourceloader.ResourceLoader;

public class VoiceUI extends JFrame implements MouseListener, MouseMotionListener {
    
    //resources
    private static final ResourceLoader rsc=new ResourceLoader();
    public static final ImageIcon mute = new ImageIcon(rsc.load("res\\mute.png"));
    private final ImageIcon gif = new ImageIcon(rsc.load("res\\loader.gif"));
    private final ImageIcon close = new ImageIcon(rsc.load("res\\close.png"));
    private final ImageIcon icon = new ImageIcon(rsc.load("res\\voice.png"));
    
    //labels
    private final JLabel closeLabel = new JLabel(close);
    public static final JLabel voiceLabel = new JLabel(mute);
    private final JLabel gifLabel = new JLabel(gif);
    public static final JLabel listenLabel = new JLabel("Listening");
    
    //panels
    private final JPanel topPanel = new JPanel();
    private final JLabel title = new JLabel("Alpha-Voice Commands");

    private int x, y;
        
    private final SpeechRecognizer sr=new SpeechRecognizer();
    private boolean firstEnabled=true;
    
    public VoiceUI(){
        super("Alpha-Voice Commands");
        initComponents();
    }
    
    private void initComponents(){
        
        this.setResizable(false);
        this.getContentPane().setBackground(Color.black);
        this.setBounds(500, 180, 420, 390);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setIconImage(icon.getImage());

        listenLabel.setBounds(145, 310, 140, 40);
        listenLabel.setFont(new Font("Monospaced", Font.PLAIN, 25));
        listenLabel.setForeground(Color.black);

        voiceLabel.setBounds(350, 310, 45, 54);
        voiceLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        voiceLabel.addMouseListener(this);

        title.setFont(new Font("Magic", Font.BOLD, 15));
        title.setForeground(Color.black);
        title.setBounds(135, 03, 200, 15);

        topPanel.setBounds(0, 0, 460, 25);
        topPanel.setLayout(null);
        topPanel.setBackground(new Color(166, 166, 166));
        topPanel.addMouseMotionListener(this);
        topPanel.addMouseListener(this);

        closeLabel.setBounds(400, 02, 20, 20);
        closeLabel.addMouseListener(this);

        topPanel.add(closeLabel);
        topPanel.add(title);

        this.add(topPanel);
        this.add(voiceLabel);
        this.add(listenLabel);
        this.add(gifLabel);
    }
    
    public void disposeWindow(){
        this.setVisible(false);
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == closeLabel) {
            this.setVisible(false);
        }else if(me.getSource()==voiceLabel&&voiceLabel.getIcon()==mute){
            voiceLabel.setIcon(null);
            voiceLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            listenLabel.setForeground(Color.green);
            if(firstEnabled){
                firstEnabled=false;
                Thread thread = new Thread(sr);
                thread.start();
            }else{
                sr.paused=false;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getSource() == topPanel) {
            x = me.getX();
            y = me.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if (me.getSource() == topPanel) {
            this.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {

    }
}
