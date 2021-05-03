package functionalities;

import autotype.AutoType;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import main.*;
import gui.*;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.imageio.ImageIO;

public class SpeechRecognizer implements Runnable {

    public AutoType autotype = new AutoType();

    public String speech;
    public Configuration config = new Configuration();
    public SpeechResult speechresult;
    public static int count = 0;
    public static boolean resume = true;

    public Say target = new Say();
    public SysKill killSwitch = new SysKill();

    @Override
    public void run() {
        SpeechRecognizerProperties();
        Recognize();
    }

    public void SpeechRecognizerProperties() {
        config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        config.setDictionaryPath("Library\\Speech Recognition\\7896.dic");
        config.setLanguageModelPath("Library\\Speech Recognition\\7896.lm");
    }

    public void Recognize() {
        try {

            LiveSpeechRecognizer speechrecognizer = new LiveSpeechRecognizer(config);

            speechrecognizer.startRecognition(true);

            while ((speechresult = speechrecognizer.getResult()) != null) {
                System.out.println(speech);
                speech = speechresult.getHypothesis().toLowerCase();
                if (speech.contains("open") || speech.contains("start")) {
                    Say.input = speech;
                    Thread thread = new Thread(target);
                    thread.start();
                    if (speech.contains("youtube")) {

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
                    } else if (speech.contains("open")) {

                        StringBuffer sb = new StringBuffer(speech);
                        try {
                            Robot r = new Robot();
                            r.keyPress(524);
                            r.keyRelease(524);
                            autotype.type(String.valueOf(sb.delete(0, speech.indexOf("n") + 1)) + "\n", 100, 10);
                        } catch (Exception ex) {

                        }

                    } else {
                        StringBuffer sb = new StringBuffer(speech);
                        try {
                            Robot r = new Robot();
                            r.keyPress(524);
                            r.keyRelease(524);
                            autotype.type(String.valueOf(sb.delete(0, 5)) + "\n", 100, 10);
                        } catch (Exception ex) {

                        }

                    }
                } else if (speech.contains("sleep")) {
                    VoiceUI.voiceLabel.setIcon(VoiceUI.mute);
                    VoiceUI.listenLabel.setForeground(Color.black);
                    resume = false;
                    while (true) {
                        if (resume) {
                            break;
                        }
                        Thread.sleep(10);
                    }
                } else if (speech.contains("screenshot")) {
                    String filename = "screenshot";
                    int i = 0;
                    Say.input = "screenshot";
                    Thread thread = new Thread(target);
                    thread.start();
                    try {
                        Robot robot = new Robot();
                        robot.delay(1000);
                        Rectangle rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                        BufferedImage bi = robot.createScreenCapture(rect);
                        while (true) {
                            if (!new File(Commands.userfiles.getPath() + "\\" + filename + String.valueOf(i) + ".png").exists()) {
                                filename += i;
                                break;
                            }
                            i++;
                        }
                        ImageIO.write(bi, "PNG", new File(Commands.userfiles.getPath() + "\\" + filename + ".png"));
                        Desktop desktop = Desktop.getDesktop();
                        File file = new File(Commands.userfiles.getPath() + "\\" + filename + ".png");
                        desktop.open(file);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                } else if (speech.contains("change")) {
                    if (speech.contains("window")) {
                        try {
                            Robot robot = new Robot();
                            robot.delay(500);
                            robot.keyPress(KeyEvent.VK_ALT);
                            robot.keyPress(KeyEvent.VK_TAB);
                            robot.delay(100);
                            robot.keyRelease(KeyEvent.VK_ALT);
                            robot.keyRelease(KeyEvent.VK_TAB);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    } else if (speech.contains("tab")) {
                        try {
                            Robot robot = new Robot();
                            robot.delay(500);
                            robot.keyPress(KeyEvent.VK_CONTROL);
                            robot.keyPress(KeyEvent.VK_SHIFT);
                            robot.keyPress(KeyEvent.VK_TAB);
                            robot.keyRelease(KeyEvent.VK_CONTROL);
                            robot.keyRelease(KeyEvent.VK_SHIFT);
                            robot.keyRelease(KeyEvent.VK_TAB);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                } else if (speech.contains("call cortana")) {
                    Say.input = "open cortana";
                    Thread thread = new Thread(target);
                    thread.start();
                    try {
                        Robot robot = new Robot();
                        robot.delay(1000);
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
                        System.out.println(ex);
                    }
                } else if (speech.contains("close")) {
                    if (speech.contains("browser")) {
                        try {//EDITABLE-->YOU CAN REPLACE "chrome"(GOOGLE CHROME) WITH THE NAME OF THE PROCESS OF THE BROWSER YOU USE
                            Process pr = Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
                        } catch (Exception ex) {

                        }
                    } else if (speech.contains("visual studio code")) {
                        try {
                            Process pr = Runtime.getRuntime().exec("taskkill /F /IM Code.exe");
                        } catch (Exception ex) {

                        }
                    } else if (speech.contains("window")) {
                        try {
                            Robot robot = new Robot();
                            robot.delay(500);
                            robot.keyPress(KeyEvent.VK_ALT);
                            robot.keyPress(KeyEvent.VK_F4);
                            robot.keyRelease(KeyEvent.VK_ALT);
                            robot.keyRelease(KeyEvent.VK_F4);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    } else {
                        StringBuffer sb = new StringBuffer(speech);
                        speech = String.valueOf(sb.delete(0, 5));
                        try {
                            Process pr = Runtime.getRuntime().exec("taskkill /F /IM " + speech + ".exe");
                        } catch (Exception ex) {

                        }
                    }
                } else if (speech.contains("time")) {
                    Say.input = "time";
                    Thread thread = new Thread(target);
                    thread.start();
                } else if (speech.contains("date")) {
                    Say.input = "date";
                    Thread thread = new Thread(target);
                    thread.start();
                } else if (speech.contains("down")) {
                    killSwitch.start();
                }

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public int[] ASCII(String text) {

        text = text.toUpperCase();

        int[] ascii = new int[text.length()];

        for (int i = 0; i < text.length(); i++) {
            ascii[i] = (int) text.charAt(i);
        }

        return ascii;
    }

}
