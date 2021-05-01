package gui;

import main.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import functionalities.SpeechRecognizer;

public class VoiceUI implements MouseListener, MouseMotionListener {

    public static final ImageIcon mute = new ImageIcon("src\\imgs\\mute.png");
    public static final ImageIcon gif = new ImageIcon("src\\imgs\\loader.gif");

    public static final ImageIcon close = new ImageIcon("src\\imgs\\close.png");
    public static final JLabel closeLabel = new JLabel(close);
    public static JFrame frame = new JFrame("Alpha-Voice Commands");
    public static JLabel voiceLabel = new JLabel(mute);
    public static JLabel gifLabel = new JLabel(gif);
    public static JLabel listenLabel = new JLabel("Listening");

    public static final JPanel toppan = new JPanel();
    public static final JLabel title = new JLabel("Alpha-Voice Commands");

    public int x, y, count = 0;
    public SpeechRecognizer sr = new SpeechRecognizer();

    public void VoiceI() {
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.black);
        frame.setBounds(500, 180, 420, 390);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setIconImage(new ImageIcon("src\\imgs\\voice.png").getImage());

        listenLabel.setBounds(145, 310, 140, 40);
        listenLabel.setFont(new Font("Monospaced", Font.PLAIN, 25));
        listenLabel.setForeground(Color.black);

        voiceLabel.setBounds(350, 310, 45, 54);
        voiceLabel.addMouseListener(this);

        title.setFont(new Font("Magic", Font.BOLD, 15));
        title.setForeground(Color.black);
        title.setBounds(135, 03, 200, 15);

        toppan.setBounds(0, 0, 460, 25);
        toppan.setLayout(null);
        toppan.setBackground(new Color(166, 166, 166));
        toppan.addMouseMotionListener(this);
        toppan.addMouseListener(this);

        closeLabel.setBounds(400, 02, 20, 20);
        closeLabel.addMouseListener(this);

        toppan.add(closeLabel);
        toppan.add(title);

        frame.add(toppan);
        frame.add(voiceLabel);
        frame.add(listenLabel);
        frame.add(gifLabel);
        frame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == closeLabel) {
            Main.c0 = 2;
            frame.setVisible(false);
        }

        if (me.getSource() == voiceLabel) {
            if (listenLabel.getForeground() == Color.black) {
                voiceLabel.setIcon(null);
                voiceLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                listenLabel.setForeground(Color.green);
                if (count == 0) {
                    Thread thread = new Thread(sr);
                    thread.start();
                    count = 1;
                } else {
                    SpeechRecognizer.resume = true;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getSource() == toppan) {
            x = me.getX();
            y = me.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == voiceLabel) {
            if (listenLabel.getForeground() == Color.black) {
                voiceLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
        if (me.getSource() == voiceLabel) {
            voiceLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if (me.getSource() == toppan) {
            frame.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {

    }
}
