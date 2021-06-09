package gui;

import functionalities.Commands;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import resourceloader.ResourceLoader;

public class CommandsUI extends JFrame implements MouseListener,MouseMotionListener{
    
    public JTextArea textarea = new JTextArea();
    public JScrollPane scr;
    
    //resources
    private final ResourceLoader rsc=new ResourceLoader();
    private final ImageIcon bot = new ImageIcon(rsc.load("res\\bot.png"));
    private final ImageIcon defbot = new ImageIcon(rsc.load("res\\defbot.png"));
    private final ImageIcon deffile = new ImageIcon(rsc.load("res\\deffile.png"));
    private final ImageIcon file = new ImageIcon(rsc.load("res\\file.png"));
    private final ImageIcon globe = new ImageIcon(rsc.load("res\\globe.png"));
    private final ImageIcon defglobe = new ImageIcon(rsc.load("res\\defglobe.png"));
    private final ImageIcon math = new ImageIcon(rsc.load("res\\math.png"));
    private final ImageIcon defmath = new ImageIcon(rsc.load("res\\defmath.png"));
    private final ImageIcon process = new ImageIcon(rsc.load("res\\process.png"));
    private final ImageIcon reset = new ImageIcon(rsc.load("res\\reset.png"));
    private final ImageIcon close = new ImageIcon(rsc.load("res\\close.png"));
    private final ImageIcon icon=new ImageIcon(rsc.load("res\\cmdwindow.png"));

    //toppanel
    private final JPanel topPanel = new JPanel(null);
    private final JLabel title = new JLabel("Alpha-Commands(Bot)");
    private final JLabel closeLabel = new JLabel(close);
    
    //controlpanel
    public JPanel controlPanel = new JPanel(null);
    public JLabel resetLabel = new JLabel(reset);
    public JLabel botLabel = new JLabel(bot);
    public JLabel fileLabel = new JLabel(deffile);
    public JLabel globeLabel = new JLabel(defglobe);
    public JLabel mathLabel = new JLabel(defmath);
    
    //processlabel
    private final JLabel processLabel=new JLabel("Process",process,JLabel.CENTER);
    
    private int x, y;
    private String type="bot";
    
    private final Commands com=new Commands();
    
    public CommandsUI(){
        super("Alpha-Commands Window");
        initComponents();
    }
    
    private void initComponents(){
        
        //setting up the main wondow
        this.setSize(800, 470);
        this.setResizable(true);
        this.setLocation(283, 184);
        this.setLayout(null);
        this.setUndecorated(true);
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        //toppanel
        topPanel.setBounds(0, 0, 800, 25);
        topPanel.setBackground(new Color(166, 166, 166));
        topPanel.addMouseMotionListener(this);
        topPanel.addMouseListener(this);
        
        title.setFont(new Font("Magic", Font.BOLD, 15));
        title.setForeground(Color.black);
        title.setBounds(320, 03, 430, 20);

        closeLabel.setBounds(780, 02, 20, 20);
        closeLabel.addMouseListener(this);

        topPanel.add(closeLabel);
        topPanel.add(title);
        
        //controlpanel
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

        controlPanel.setBounds(0, 25, 800, 35);
        controlPanel.setBackground(new Color(255, 255, 255));
        controlPanel.add(resetLabel);
        controlPanel.add(botLabel);
        controlPanel.add(fileLabel);
        controlPanel.add(globeLabel);
        controlPanel.add(mathLabel);
        
        //textarea
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
        
        //processlabel
        processLabel.setBounds(0, 420, 800, 50);
        processLabel.setOpaque(true);
        processLabel.setBackground(Color.green);
        processLabel.setFont(new Font("Monospaced", Font.PLAIN, 25));
        processLabel.setForeground(Color.black);
        processLabel.addMouseListener(this);
        
        //adding components
        this.add(topPanel);
        this.add(controlPanel);
        this.add(scr);
        this.add(processLabel);
        
    }
    
    public void disposeWindow(){
        this.setVisible(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==closeLabel){
            this.setVisible(false);
        }else if(e.getSource()==botLabel){
            type="bot";
            title.setText("Alpha-Commands(Bot)");
            title.setBounds(320, 03, 430, 20);
            botLabel.setIcon(bot);
            fileLabel.setIcon(deffile);
            globeLabel.setIcon(defglobe);
            mathLabel.setIcon(defmath);
        }else if(e.getSource()==fileLabel){
            type="file";
            title.setText("Alpha-Commands(Files)");
            title.setBounds(320, 03, 430, 20);
            botLabel.setIcon(defbot);
            fileLabel.setIcon(file);
            globeLabel.setIcon(defglobe);
            mathLabel.setIcon(defmath);
        }else if(e.getSource()==globeLabel){
            type="web";
            title.setText("Alpha-Commands(Search)");
            title.setBounds(320, 03, 430, 20);
            botLabel.setIcon(defbot);
            fileLabel.setIcon(deffile);
            globeLabel.setIcon(globe);
            mathLabel.setIcon(defmath);
        }else if(e.getSource()==mathLabel){
            type="math";
            title.setText("Alpha-Commands(Mathematical Operations)");
            title.setBounds(265, 03, 430, 20);
            botLabel.setIcon(defbot);
            fileLabel.setIcon(deffile);
            globeLabel.setIcon(defglobe);
            mathLabel.setIcon(math);
        }else if(e.getSource()==resetLabel){
            textarea.setText(null);
            textarea.setCaretColor(Color.white);
            textarea.setEditable(true);
        }else if(e.getSource()==processLabel&&textarea.isEditable()){
            processLabel.setBackground(Color.red);            
            textarea.setCaretColor(Color.black);
            String text=(com.operation(type, textarea.getText()));
            textarea.setEditable(false);
            if(text!=null){
                textarea.append("\n>"+text);
            }
            
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
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == processLabel) {
            if (textarea.isEditable()) {
                processLabel.setBackground(Color.green);
            } else {
                processLabel.setBackground(Color.red);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == processLabel) {
            processLabel.setBackground(Color.green);
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
