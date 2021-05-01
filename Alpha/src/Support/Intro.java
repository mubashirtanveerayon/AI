package Support;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.*;

public class Intro {

    public JFrame frame = new JFrame("Words from the Author");
    public static JTextArea textarea = new JTextArea();
    public JScrollPane scr;
    public final String message = "\n\n\n"
            + "\t\t\t  hi\n"
            + "\t  i'm alpha, your virtual assistant."
            + "\n\t\t    i'd be pleased\n"
            + "\t\tif you give some time to\n"
            + "\t let the author deliver a short message."
            + "\n\tthis will appear on the first launch only.";

    public final String authorsmessage = "\n"
            + "\t\t\t  HELLO"
            + "\n\t    THIS IS AGENT47,DEVELOPER OF ALPHA."
            + "\n\t   I'M CURRENTLY STUDYING AT NDC,DHAKA."
            + "\nI STARTED WORKING ON THIS PROJECT THIS YEAR IN FEBRUARY."
            + "\n\tI ALWAYS WANTED TO DO SOMETHING LIKE THIS."
            + "\n\tAFTER WORKING RELENTLESSLY ON THIS PROJECT"
            + "\n\t\t  FOR THE PAST 2 MONTHS"
            + "\n\t     ALPHA IS NOW READY TO HELP YOU."
            + "\n   I HOPE YOU WILL FIND ALPHA CO-OPERATIVE AND HELPFUL."
            + "\n\t CONTACT ME FOR ANY KIND OF BUG REPORT."
            + "\n MY EMAIL ADDRESS CAN BE FOUND IN THE READ ME.TXT FILE."
            + "\n\t\tTHANK YOU FOR YOUR TIME.";
    
    public final String warningmessage="\n\n\n"
            + "\t\t\t  Warning!"
            + "\n\t\tEditing, removing or renaming"
            + "\n\t\t\t any file"
            + "\n\t\tmay result in system failure!"
            + "\n\n\t\t\t© Author,"
            + "\n\t\t   All rights reserved.";

    public Intro() {
        
        Rectangle rect=new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setLocation(0,0);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize((int)rect.getWidth(), (int)rect.getHeight());
        
        textarea.setFont(new Font("Monospaced", Font.PLAIN, 40));
        textarea.setWrapStyleWord(true);
        textarea.setLineWrap(true);
        textarea.setForeground(new Color(0, 255, 0));
        textarea.setBackground(new Color(0, 0, 0));
        textarea.setCaretColor(Color.WHITE);
        textarea.getCaret().setBlinkRate(200);
        textarea.setEnabled(false);

        scr = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        frame.add(BorderLayout.CENTER, scr);

        frame.setVisible(true);
        write();
    }

    public void write() {
        
        Thread warning=new Thread(){
            @Override
            public void run(){
                int i=0;
                while(i<warningmessage.length()){
                    try {
                        Thread.sleep(75);
                    } catch (InterruptedException ex) {

                    }
                    textarea.append(String.valueOf(warningmessage.charAt(i)));
                    i++;
                }
                try {
                    Thread.sleep(2000);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        };
        warning.start();

        Thread ai = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < message.length()) {
                    try {
                        Thread.sleep(75);
                    } catch (InterruptedException ex) {

                    }
                    textarea.append(String.valueOf(message.charAt(i)));
                    i++;
                }
                try {
                    Thread.sleep(1500);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }

        };
        try {
            backspaceeffect(warningmessage,warning,10).join();
        } catch (InterruptedException ex) {

        }
        ai.start();

        Thread aut = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < authorsmessage.length()) {
                    try {
                        Thread.sleep(75);
                    } catch (InterruptedException ex) {

                    }
                    textarea.append(String.valueOf(authorsmessage.charAt(i)));
                    i++;
                }
                try {
                    Thread.sleep(1500);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        };
        try {
            backspaceeffect(message,ai,10).join();
        }catch(Exception ex){
            
        }
        aut.start();

       Thread finish = new Thread() {
            @Override
            public void run() {
                frame.dispose();
            }
        };
        try {
            backspaceeffect(authorsmessage,aut,5).join();
        } catch (InterruptedException ex) {

        }
        finish.start();
    }

    public Thread backspaceeffect(String text,Thread prevthread,int delay) {
        Thread thread = new Thread() {
            String temp = text;
            StringBuffer sb = new StringBuffer(temp);

            @Override
            public void run() {
                for(int i=temp.length()-1;i >= 0;i--) {
                    temp = String.valueOf(sb.delete(i, text.length() - 1));
                    textarea.setText(temp);
                    try {
                        Thread.sleep(delay);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        };
        try {
            prevthread.join();
        } catch (InterruptedException ex) {

        }
        thread.start();
        return thread;
    }

}
