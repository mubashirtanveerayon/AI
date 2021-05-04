/*
 * VERSION 2.0
 * UPDATED ON 24/04/21
 * ~PROJECT FILES SORTED
 ***LAUNCH DATE : 29/04/2021***
 ***SOURCE CODE MADE PUBLIC ON 04/05/2021***
 */
package main;

import Support.Intro;
import gui.*;
import functionalities.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.Cursor;
import java.awt.Desktop;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class Main implements MouseListener, MouseMotionListener {

    public static JFrame frame = new JFrame("Alpha");
    public static JPanel toppan = new JPanel();
    public static JPanel bottompan = new JPanel();

    public static JPanel centerpan = new JPanel() {
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

    public static final ImageIcon definfo = new ImageIcon("src\\imgs\\definfo.png");
    public static final ImageIcon info = new ImageIcon("src\\imgs\\info.png");
    public static final ImageIcon defquit = new ImageIcon("src\\imgs\\defquit.png");
    public static final ImageIcon quit = new ImageIcon("src\\imgs\\quit.png");
    public static final ImageIcon defcmdwindow = new ImageIcon("src\\imgs\\defcmdwindow.png");
    public static final ImageIcon cmdwindow = new ImageIcon("src\\imgs\\cmdwindow.png");
    public static final ImageIcon defvoice = new ImageIcon("src\\imgs\\defvoice.png");
    public static final ImageIcon voice = new ImageIcon("src\\imgs\\voice.png");
    public static final ImageIcon deftutorial = new ImageIcon("src\\imgs\\deftutorial.png");
    public static final ImageIcon tutorial = new ImageIcon("src\\imgs\\tutorial.png");

    public static final JLabel title = new JLabel("Alpha");

    public static JLabel cmdwindowLabel = new JLabel(defcmdwindow);
    public static JLabel voiceLabel = new JLabel(defvoice);

    public static final JLabel styletop = new JLabel();
    public static final JLabel styleright = new JLabel();
    public static final JLabel styleleft = new JLabel();
    public static final JLabel stylebottom = new JLabel();

    public static JLabel infoLabel = new JLabel(definfo);
    public static JLabel tutorialLabel = new JLabel(deftutorial);
    public static JLabel quitLabel = new JLabel(defquit);

    public static final JLabel cmdwindowtextLabel = new JLabel("Alpha-Commands");
    public static final JLabel voicetextLabel = new JLabel("Alpha-Voice Commands");

    public static VoiceUI vui = new VoiceUI();
    public static SysKill killSwitch = new SysKill();

    public static int x, y, c = 0, c0 = 0, c1 = 0;

    public static void main(String[] args) throws Exception {
        
        if (!Commands.junk.exists() && !Commands.userfiles.exists()) {
            new Intro();
        }
        if (!Commands.junk.exists()) {
            Commands.junk.mkdir();
        }
        if (!Commands.userfiles.exists()) {
            Commands.userfiles.mkdir();
        }
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        new Main();
    }

    Main() {
        frame.setBounds(450, 230, 420, 320);
        frame.setLayout(null);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("src\\imgs\\alpha.png").getImage());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        title.setBounds(190, 2, 100, 20);
        title.setFont(new Font("Aerial", Font.PLAIN, 20));
        title.setForeground(Color.white);

        toppan.setBounds(0, 0, 420, 25);
        toppan.setBackground(new Color(0, 0, 0));
        toppan.setLayout(null);
        toppan.addMouseListener(this);
        toppan.addMouseMotionListener(this);

        toppan.add(title);

        //centerpanel-start
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

        centerpan.setLayout(null);
        centerpan.setBounds(0, 25, 420, 210);

        centerpan.add(cmdwindowtextLabel);
        centerpan.add(voicetextLabel);
        centerpan.add(cmdwindowLabel);
        centerpan.add(voiceLabel);
        //centerpanel-end

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

        bottompan.setLayout(null);
        bottompan.setBounds(0, 235, 420, 90);
        bottompan.setBackground(new Color(230, 230, 230));

        bottompan.add(styletop);
        bottompan.add(styleright);
        bottompan.add(styleleft);
        bottompan.add(stylebottom);
        bottompan.add(infoLabel);
        bottompan.add(tutorialLabel);
        bottompan.add(quitLabel);

        frame.add(centerpan);
        frame.add(bottompan);
        frame.add(toppan);
        frame.setVisible(true);
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
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == tutorialLabel) {
            try {
                Desktop desktop = Desktop.getDesktop();
                File file = new File("src\\Support\\Tutorial.txt");
                desktop.open(file);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (me.getSource() == quitLabel) {
            killSwitch.start();
        }
        if (me.getSource() == cmdwindowLabel) {
            if (c <= 0) {
                new CommandsWindowUI();
                c = 1;
            } else if (c == 1) {
                CommandsWindowUI.frame.setVisible(false);
                c = 2;
            } else {
                CommandsWindowUI.frame.setVisible(true);
                c = 1;
            }
        }
        if (me.getSource() == voiceLabel) {
            if (c0 <= 0) {
                vui.VoiceI();
                c0 = 1;
            } else if (c0 == 1) {
                vui.frame.setVisible(false);
                c0 = 2;
            } else {
                vui.frame.setVisible(true);
                c0 = 1;
            }
        }
        if (me.getSource() == infoLabel) {
            if (c1 <= 0) {
                new Information();
                c1 = 1;
            } else if (c1 == 1) {
                Information.f.setVisible(false);
                c1 = 2;
            } else {
                Information.f.setVisible(true);
                c1 = 1;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == cmdwindowLabel) {
            cmdwindowLabel.setIcon(cmdwindow);
        }
        if (me.getSource() == voiceLabel) {
            voiceLabel.setIcon(voice);
        }
        if (me.getSource() == infoLabel) {
            infoLabel.setIcon(info);
        }
        if (me.getSource() == tutorialLabel) {
            tutorialLabel.setIcon(tutorial);
        }
        if (me.getSource() == quitLabel) {
            quitLabel.setIcon(quit);
        }
    }

    @Override
    public void mouseExited(MouseEvent me) {
        if (me.getSource() == cmdwindowLabel) {
            cmdwindowLabel.setIcon(defcmdwindow);
        }
        if (me.getSource() == voiceLabel) {
            voiceLabel.setIcon(defvoice);
        }
        if (me.getSource() == infoLabel) {
            infoLabel.setIcon(definfo);
        }
        if (me.getSource() == tutorialLabel) {
            tutorialLabel.setIcon(deftutorial);
        }
        if (me.getSource() == quitLabel) {
            quitLabel.setIcon(defquit);
        }
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if (me.getSource() == toppan) {
            frame.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
