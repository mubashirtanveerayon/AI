package functionalities;

import autotype.AutoType;
import static gui.CommandsWindowUI.*;
import java.awt.Desktop;
import java.io.File;
import java.awt.Color;
import java.awt.Robot;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import main.*;

public class Commands {

    public static final String prefix = "\n>";
    private static final String url = "URL=https://www.bing.com/search?q=";
    private static final String yurl = "URL=https://www.youtube.com/results?search_query=";
    private static final String wurl = "URL=https://en.wikipedia.org/wiki/";

    public static final File junk = new File("src\\Junk");
    public static final File userfiles = new File("User Files");
    public Desktop desk = Desktop.getDesktop();
    public static String operation = "bot";
    public static SysKill killSwitch = new SysKill();
    public Say target = new Say();

    public AutoType autotype = new AutoType();

    public static String com = "";
    public static String source = "";

    public int[] ASCII(String text) {

        text = text.toUpperCase();

        int[] ascii = new int[text.length()];

        for (int i = 0; i < text.length(); i++) {
            ascii[i] = (int) text.charAt(i);
        }

        return ascii;
    }

    public void Operation(String com) {
        if (com.equals("close")) {
            Main.c = 1;
            frame.setVisible(false);
        } else if (com.equals("bot")) {
            title.setText("Alpha-Commands(Bot)");
            title.setBounds(320, 03, 430, 20);
            operation = com;
            botLabel.setIcon(bot);
            fileLabel.setIcon(deffile);
            globeLabel.setIcon(defglobe);
            mathLabel.setIcon(defmath);
        } else if (com.equals("file")) {
            title.setText("Alpha-Commands(Files)");
            title.setBounds(320, 03, 430, 20);
            operation = "file";
            botLabel.setIcon(defbot);
            fileLabel.setIcon(file);
            globeLabel.setIcon(defglobe);
            mathLabel.setIcon(defmath);
        } else if (com.equals("web")) {
            title.setText("Alpha-Commands(Search)");
            title.setBounds(320, 03, 430, 20);
            operation = "globe";
            botLabel.setIcon(defbot);
            fileLabel.setIcon(deffile);
            globeLabel.setIcon(globe);
            mathLabel.setIcon(defmath);
        } else if (com.equals("math")) {
            title.setText("Alpha-Commands(Mathematical Operations)");
            title.setBounds(265, 03, 430, 20);
            operation = "math";
            botLabel.setIcon(defbot);
            fileLabel.setIcon(deffile);
            globeLabel.setIcon(defglobe);
            mathLabel.setIcon(math);
        } else if (com.equals("process")) {
            if (processpanel.isEnabled()) {
                processpanel.setEnabled(false);
                processpanel.setBackground(Color.red);
                textarea.setEditable(false);
                textarea.setCaretColor(Color.black);
                process(textarea.getText());
            }
        }

    }

    private void process(String text) {
        if (operation.equals("globe")) {
            if (text.toLowerCase().contains("youtube->")) {
                try {
                    Say.input = "search youtube";
                    Thread thread = new Thread(target);
                    thread.start();
                    StringBuffer sb = new StringBuffer(text);
                    sb.delete(0, text.indexOf(">") + 1);
                    text = String.valueOf(sb);
                    String dfpath = junk.getPath() + "\\";
                    BufferedWriter bw = new BufferedWriter(new FileWriter(dfpath + text + "_youtube.url"));
                    bw.write("[InternetShortcut]\n" + yurl + text);
                    bw.close();
                    String path = dfpath + text + "_youtube.url";
                    File file = new File(path);
                    desk.open(file);
                } catch (Exception ex) {

                }
            } else if (text.toLowerCase().contains("wikipedia->")) {
                try {
                    Say.input = "search wikipedia";
                    Thread thread = new Thread(target);
                    thread.start();
                    StringBuffer sb = new StringBuffer(text);
                    sb.delete(0, text.indexOf(">") + 1);
                    text = String.valueOf(sb);
                    String dfpath = junk.getPath() + "\\";
                    BufferedWriter bw = new BufferedWriter(new FileWriter(dfpath + text + "_wiki.url"));
                    bw.write("[InternetShortcut]\n" + wurl + text);
                    bw.close();
                    String path = dfpath + text + "_wiki.url";
                    File file = new File(path);
                    desk.open(file);
                } catch (Exception ex) {

                }
            } else {

                try {
                    Say.input = "search bing";
                    Thread thread = new Thread(target);
                    thread.start();
                    String dfpath = junk.getPath() + "\\";
                    BufferedWriter bw = new BufferedWriter(new FileWriter(dfpath + text + ".url"));
                    bw.write("[InternetShortcut]\n" + url + text);
                    bw.close();
                    String path = dfpath + text + ".url";
                    File file = new File(path);
                    desk.open(file);
                } catch (Exception ex) {

                }
            }
        } else if (operation.equals("bot")) {
            String msg = textarea.getText().toLowerCase();
            textarea.setEditable(false);
            if (msg.contains("exit") || msg.contains("shut down")) {
                killSwitch.start();
            } else if (msg.contains("date")) {
                Say.input = "date";
                Thread thread = new Thread(target);
                thread.start();
                Date dt = new Date();
                SimpleDateFormat d = new SimpleDateFormat("EEEE dd/MM/yyyy");
                textarea.append(prefix + "Date : " + d.format(dt));
            } else if (msg.contains("time")) {
                Say.input = "time";
                Thread thread = new Thread(target);
                thread.start();
                Date dt = new Date();
                SimpleDateFormat t = new SimpleDateFormat("hh:mm:ss a");
                textarea.append(prefix + "Time : " + t.format(dt));
            } else if (msg.contains("open") || msg.contains("start")) {
                Say.input = msg;
                Thread thread = new Thread(target);
                thread.start();
                if (msg.contains("youtube")) {

                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(Commands.junk.getPath() + "\\" + "Youtube.url"));
                        bw.write("[InternetShortcut]\n" + "URL=https://youtube.com/");
                        bw.close();
                        File file = new File(Commands.junk.getPath() + "\\" + "Youtube.url");
                        Desktop desk = Desktop.getDesktop();
                        desk.open(file);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
                else if (msg.contains("open")) {
                    
                        StringBuffer sb = new StringBuffer(msg);
                        try {
                            Robot r = new Robot();
                            r.keyPress(524);
                            r.keyRelease(524);
                            autotype.type(String.valueOf(sb.delete(0, msg.indexOf("n") + 1)) + "\n", 100, 10);
                        } catch (Exception ex) {

                        }
                    
                } else {
                        StringBuffer sb = new StringBuffer(msg);
                        try {
                            Robot r = new Robot();
                            r.keyPress(524);
                            r.keyRelease(524);
                            autotype.type(String.valueOf(sb.delete(0, 5)) + "\n", 100, 10);
                        } catch (Exception ex) {
                            
                        }

                }

            } else if (msg.contains("close")) {
                if (msg.contains("browser")) {
                    try {//EDITABLE-->YOU CAN REPLACE "msedge"(MICROSOFT EDGE) WITH THE NAME OF THE PROCESS OF THE BROWSER YOU USE
                        Process pr = Runtime.getRuntime().exec("taskkill /F /IM msedge.exe");
                    } catch (Exception ex) {

                    }
                } else if (msg.contains("code")) {
                    try {
                        Process pr = Runtime.getRuntime().exec("taskkill /F /IM Code.exe");
                    } catch (Exception ex) {

                    }
                } else {
                    StringBuffer sb = new StringBuffer(msg);

                    try {
                        Process pr = Runtime.getRuntime().exec("taskkill /F /IM " + String.valueOf(sb.delete(0, 5)) + ".exe");
                    } catch (Exception ex) {

                    }
                }
            } else if (msg.contains("call cortana")) {
                Say.input = "open cortana";

                try {
                    Thread thread = new Thread(target);
                    thread.start();
                    Robot robot = new Robot();
                    int[] com = ASCII("cortana\n");
                    robot.keyPress(524);
                    robot.keyRelease(524);
                    robot.delay(100);
                    for (int i = 0; i < com.length; i++) {
                        robot.delay(20);
                        robot.keyPress(com[i]);
                        robot.keyRelease(com[i]);
                    }
                } catch (Exception ex) {

                }
            } else if (msg.contains("active thread")) {
                textarea.append(prefix + Thread.activeCount());
            }
        } else if (operation.equals("math")) {
            String op = textarea.getText().toLowerCase();
            if (op.contains("add") || op.contains("sum")) {
                try {
                    StringBuffer sb = new StringBuffer(op);
                    sb.delete(0, op.indexOf(" ") + 1);
                    String p = String.valueOf(sb);
                    String q;
                    String temp = "";
                    double num1;
                    for (int i = 0; i < p.lastIndexOf(" "); i++) {
                        q = String.valueOf(p.charAt(i));
                        temp += q;
                    }
                    num1 = Double.parseDouble(temp);
                    StringBuffer sb0 = new StringBuffer(p);

                    sb0.delete(0, p.lastIndexOf(" ") + 1);
                    double num2 = Double.parseDouble(String.valueOf(sb0));
                    textarea.append(prefix + num1 + " + " + num2 + " = " + String.valueOf(num1 + num2));
                } catch (Exception ex) {

                }
            }

            if (op.contains("sub") || op.contains("substract")) {
                try {
                    StringBuffer sb = new StringBuffer(op);
                    sb.delete(0, op.indexOf(" ") + 1);
                    String p = String.valueOf(sb);
                    String q;
                    double num1;
                    String temp = "";
                    for (int i = 0; i < p.lastIndexOf(" "); i++) {
                        q = String.valueOf(p.charAt(i));
                        temp += q;
                    }
                    num1 = Double.parseDouble(temp);
                    StringBuffer sb0 = new StringBuffer(p);

                    sb0.delete(0, p.lastIndexOf(" ") + 1);
                    double num2 = Double.parseDouble(String.valueOf(sb0));
                    textarea.append(prefix + num1 + " - " + num2 + " = " + String.valueOf(num1 - num2));
                } catch (Exception ex) {

                }
            }

            if (op.contains("div")) {
                try {
                    StringBuffer sb = new StringBuffer(op);
                    sb.delete(0, op.indexOf(" ") + 1);
                    String p = String.valueOf(sb);
                    String q;
                    double num1;
                    String temp = "";
                    for (int i = 0; i < p.lastIndexOf(" "); i++) {
                        q = String.valueOf(p.charAt(i));
                        temp += q;
                    }
                    num1 = Double.parseDouble(temp);
                    StringBuffer sb0 = new StringBuffer(p);

                    sb0.delete(0, p.lastIndexOf(" ") + 1);
                    double num2 = Double.parseDouble(String.valueOf(sb0));
                    textarea.append(prefix + num1 + " / " + num2 + " = " + String.valueOf(num1 / num2));
                } catch (Exception ex) {

                }
            }

            if (op.contains("mul")) {
                try {
                    StringBuffer sb = new StringBuffer(op);
                    sb.delete(0, op.indexOf(" ") + 1);
                    String p = String.valueOf(sb);
                    String q;
                    double num1;
                    String temp = "";
                    for (int i = 0; i < p.lastIndexOf(" "); i++) {
                        q = String.valueOf(p.charAt(i));
                        temp += q;
                    }
                    num1 = Double.parseDouble(temp);
                    StringBuffer sb0 = new StringBuffer(p);

                    sb0.delete(0, p.lastIndexOf(" ") + 1);
                    double num2 = Double.parseDouble(String.valueOf(sb0));
                    textarea.append(prefix + num1 + " x " + num2 + " = " + String.valueOf(num1 * num2));
                } catch (Exception ex) {

                }
            }

            if (op.contains("pow")) {
                try {
                    StringBuffer sb = new StringBuffer(op);
                    sb.delete(0, op.indexOf(" ") + 1);
                    String p = String.valueOf(sb);
                    String q;
                    double num1;
                    String temp = "";
                    for (int i = 0; i < p.lastIndexOf(" "); i++) {
                        q = String.valueOf(p.charAt(i));
                        temp += q;
                    }
                    num1 = Double.parseDouble(temp);
                    StringBuffer sb0 = new StringBuffer(p);

                    sb0.delete(0, p.lastIndexOf(" ") + 1);
                    double num2 = Double.parseDouble(String.valueOf(sb0));
                    textarea.append(prefix + num1 + " ^ " + num2 + " = " + String.valueOf(Math.pow(num1, num2)));
                } catch (Exception ex) {

                }
            }
        } else if (operation.equals("file")) {
            Say.input = "file";
            try {

                String fname = JOptionPane.showInputDialog(null, "Enter the name of the file."
                        + "\nYou'll need to add the proper file extension \nat the end of the file name."
                        + "Otherwise it won't work.", "File Name", 3);

                String maintxt = textarea.getText();

                BufferedWriter bw = new BufferedWriter(new FileWriter(userfiles.getPath() + "\\" + fname));
                bw.write(maintxt);
                bw.close();
                Thread thread = new Thread(target);
                thread.start();
                File file = new File(userfiles.getPath() + "\\" + fname);
                desk.open(file);
            } catch (Exception ex) {
            }
        }
    }

}
