package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import functionalities.Commands;

public class CommandsWindowUI extends Commands implements MouseListener, MouseMotionListener, ActionListener {
    
    public static JTextArea textarea = new JTextArea();
    public static JFrame frame = new JFrame("Commands Window");
    
    public static JPanel toppan = new JPanel();
    public static final JLabel title = new JLabel("Alpha-Commands(Bot)");

    public static JPanel controlpanel = new JPanel();

    public static JScrollPane scr;

    public static final ImageIcon bot = new ImageIcon("src\\imgs\\bot.png");
    public static final ImageIcon defbot = new ImageIcon("src\\imgs\\defbot.png");
    public static final ImageIcon deffile = new ImageIcon("src\\imgs\\deffile.png");
    public static final ImageIcon file = new ImageIcon("src\\imgs\\file.png");
    public static final ImageIcon globe = new ImageIcon("src\\imgs\\globe.png");
    public static final ImageIcon defglobe = new ImageIcon("src\\imgs\\defglobe.png");
    public static final ImageIcon math = new ImageIcon("src\\imgs\\math.png");
    public static final ImageIcon defmath = new ImageIcon("src\\imgs\\defmath.png");
    public static final ImageIcon process = new ImageIcon("src\\imgs\\process.png");
    public static final ImageIcon reset = new ImageIcon("src\\imgs\\reset.png");
    public static final ImageIcon close = new ImageIcon("src\\imgs\\close.png");

    public static final JLabel closeLabel = new JLabel(close);
    public static JLabel resetLabel = new JLabel(reset);

    public static JLabel botLabel = new JLabel(bot);
    public static JLabel fileLabel = new JLabel(deffile);
    public static JLabel globeLabel = new JLabel(defglobe);
    public static JLabel mathLabel = new JLabel(defmath);

    public static final JLabel processIconLabel = new JLabel(process);
    public static final JLabel processtextLabel = new JLabel("Process");
    public static JPanel processpanel = new JPanel();
    
    public int x, y;
  
    public CommandsWindowUI(){
        frame.setSize(800, 470);
        frame.setResizable(true);
        frame.setLocation(283, 184);
        frame.setLayout(null);
        frame.setUndecorated(true);
        frame.setOpacity(1.0f);
        frame.setIconImage(new ImageIcon("src\\imgs\\cmdwindow.png").getImage());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        toppan.setBounds(0, 0, 800, 25);
        toppan.setLayout(null);
        toppan.setBackground(new Color(166, 166, 166));
        toppan.addMouseMotionListener(this);
        toppan.addMouseListener(this);

        title.setFont(new Font("Magic", Font.BOLD, 15));
        title.setForeground(Color.black);
        title.setBounds(320, 03, 430, 20);

        closeLabel.setBounds(780, 02, 20, 20);
        closeLabel.addMouseListener(this);

        toppan.add(closeLabel);
        toppan.add(title);

        botLabel.setBounds(305, 5, 30, 30);
        botLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botLabel.setToolTipText("Bot-Commands");
        botLabel.addMouseListener(this);

        fileLabel.setBounds(355, 5, 30, 30);
        fileLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fileLabel.setToolTipText("Files");
        fileLabel.addMouseListener(this);

        globeLabel.setBounds(405, 5, 30, 30);
        globeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        globeLabel.setToolTipText("Web Search");
        globeLabel.addMouseListener(this);

        mathLabel.setBounds(455, 5, 30, 30);
        mathLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mathLabel.setToolTipText("Mathematical Operations");
        mathLabel.addMouseListener(this);

        resetLabel.setBounds(760, 0, 40, 40);
        resetLabel.setFocusable(false);
        resetLabel.setBackground(Color.white);
        resetLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetLabel.setToolTipText("Reset Textarea");
        resetLabel.addMouseListener(this);

        controlpanel.setBounds(0, 25, 800, 35);
        controlpanel.setBackground(new Color(255, 255, 255));
        controlpanel.setLayout(null);
        controlpanel.add(resetLabel);
        controlpanel.add(botLabel);
        controlpanel.add(fileLabel);
        controlpanel.add(globeLabel);
        controlpanel.add(mathLabel);

        textarea.setFont(new Font("Monospaced", Font.PLAIN, 25));
        textarea.setWrapStyleWord(true);
        textarea.setForeground(new Color(0, 255, 0));
        textarea.setBackground(new Color(0, 0, 0));
        textarea.setCaretColor(Color.WHITE);
        textarea.getCaret().setBlinkRate(200);
        textarea.setEditable(true);
        textarea.addMouseListener(this);

        scr = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scr.setBounds(0, 60, 800, 360);

        processIconLabel.setBounds(330, 0, 46, 46);

        processtextLabel.setBounds(380, 10, 150, 20);
        processtextLabel.setFont(new Font("Monospaced", Font.PLAIN, 25));
        processtextLabel.setForeground(Color.black);

        processpanel.setBounds(0, 420, 800, 50);
        processpanel.setLayout(null);
        processpanel.setBackground(Color.green);
        processpanel.addMouseListener(this);

        processpanel.add(processIconLabel);
        processpanel.add(processtextLabel);

        frame.add(toppan);
        frame.add(controlpanel);
        frame.add(scr);
        frame.add(processpanel);
        frame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==closeLabel){
            Operation("close");
        }
        if(e.getSource()==botLabel){
            Operation("bot");
        }
        if(e.getSource()==fileLabel){
            Operation("file");
        }
        if(e.getSource()==globeLabel){
            Operation("web");
        }
        if(e.getSource()==mathLabel){
            Operation("math");
        }
        if(e.getSource()==resetLabel){
            textarea.setText(null);
            textarea.setCaretColor(Color.white);
            textarea.setEditable(true);
            processpanel.setEnabled(true);
        }
        if(e.getSource()==processpanel){
            Operation("process");
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource()==toppan){
            x=e.getX();
            y=e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == processpanel) {
            if (!textarea.isEditable()) {
                processpanel.setBackground(Color.red);
            } else {
                processpanel.setBackground(new Color(0, 200, 0));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == processpanel) {
            processpanel.setBackground(Color.green);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(e.getSource()==toppan){
            frame.setLocation(e.getXOnScreen()-x,e.getYOnScreen()-y);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    
}
