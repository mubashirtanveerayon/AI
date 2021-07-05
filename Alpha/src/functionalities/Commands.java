package functionalities;

import autotype.AutoType;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class Commands{
    
    private Say say=new Say();
    private Desktop d=Desktop.getDesktop();
    private JFileChooser fc=new JFileChooser("");
    
    public synchronized String operation(String type,String command){
        fc.setDialogTitle("Save file");
        switch (type){
            case "file":
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int response=fc.showSaveDialog(null);
                String path="";
                if (response == JFileChooser.APPROVE_OPTION) {
                    path = fc.getSelectedFile().getPath();
                    BufferedWriter bw = null;
                    try {
                        bw = new BufferedWriter(new FileWriter(path));
                        bw.write(command);
                        say.input = "file";
                        Thread th = new Thread(say);
                        th.start();
                        d.open(new File(path));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    } finally {
                        try {
                            bw.close();
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                }
                break;
            case "web":
                if(command.contains("youtube->")){      
                    say.input="search on youtube";
                    Thread th=new Thread(say);
                    th.start();
                    String temp=command;
                    StringBuffer sb = new StringBuffer(temp);
                    temp=String.valueOf(sb.delete(0, temp.indexOf(">") + 1)).replaceAll(" ", "+");
                    try{
                        d.browse(new URI("https://www.youtube.com/results?search_query="+temp));
                    }catch(Exception ex){
                        System.out.println(ex);
                    }
                }else if(command.contains("wikipedia->")){
                    say.input="search on wikipedia";
                    Thread th=new Thread(say);
                    th.start();
                    String temp=command;
                    StringBuffer sb = new StringBuffer(temp);
                    temp=String.valueOf(sb.delete(0, temp.indexOf(">") + 1)).replaceAll(" ", "_");
                    try{
                        d.browse(new URI("https://en.wikipedia.org/wiki/"+temp));
                    }catch(Exception ex){
                        System.out.println(ex);
                    }
                }else{
                    say.input="search on bing";
                    Thread th=new Thread(say);
                    th.start();
                    String temp=command.replaceAll(" ", "+");
                    try{
                        d.browse(new URI("https://www.bing.com/search?q="+temp));
                    }catch(Exception ex){
                        System.out.println(ex);
                    }
                }
                break;
            case "math":
                String temp = command.trim();
                StringBuffer sb1 = new StringBuffer(temp);
                StringBuffer sb2 = new StringBuffer(temp);
                double num1,num2;
                try{
                    double t1 = Double.parseDouble(String.valueOf(sb1.delete(temp.indexOf(" "), temp.length())));
                    double t2 = Double.parseDouble(String.valueOf(sb2.delete(0, temp.lastIndexOf(" ") + 1)));
                    num1=t1;
                    num2=t2;
                }catch(Exception ex){
                    return "Syntax error";
                }    
                StringBuffer sb3=new StringBuffer(temp);
                String temp1=String.valueOf(sb3.delete(temp.lastIndexOf(" "), temp.length()));
                StringBuffer sb4 = new StringBuffer(temp1);
                String op = String.valueOf(sb4.delete(0, temp1.indexOf(" ") + 1));
                switch(op){
                    case "+":
                        return command+" = "+String.valueOf(num1+num2);
                    case "-":
                        return command+" = "+String.valueOf(num1-num2);
                    case "*":
                        return command+" = "+String.valueOf(num1*num2);
                    case "/":
                        return command+" = "+String.valueOf(num1/num2);
                    case "^":
                        return command+" = "+String.valueOf(Math.pow(num1, num2));
                    default:
                        return "Syntax error!";                    
                }
            default:                
                command=command.toLowerCase();
                if(command.contains("exit")){
                    say.input="exit";
                    Thread th=new Thread(say);
                    th.start();
                }else if(command.contains("time")){
                    say.input="time";
                    Thread th=new Thread(say);
                    th.start();
                    Date dt = new Date();
                    SimpleDateFormat t = new SimpleDateFormat("hh:mm:ss a");
                    return "Time : " + t.format(dt);
                }else if(command.contains("date")){
                    say.input="date";
                    Thread th=new Thread(say);
                    th.start();
                    Date dt = new Date();
                    SimpleDateFormat d = new SimpleDateFormat("EEEE dd/MM/yyyy");
                    return "Time : " + d.format(dt);
                }else if (command.contains("open")||command.contains("start")){
                    say.input=command;
                    Thread th=new Thread(say);
                    th.start();
                    if(command.contains("youtube")){
                        try{
                            d.browse(new URI("https://www.youtube.com/"));
                        }catch(Exception ex){
                            System.out.println(ex);
                        }
                    }else{
                        StringBuffer sb=new StringBuffer(command);
                        try{
                            Robot r=new Robot();
                            r.keyPress(524);
                            r.keyRelease(524);
                            AutoType aut = command.contains("open")?new AutoType(String.valueOf(sb.delete(0, command.indexOf("n") + 1)) + "\n",100)
                                    :new AutoType(String.valueOf(sb.delete(0, 5)) + "\n",100);
                            aut.start();
                        }catch(Exception ex){
                            System.out.println(ex);
                        }
                    }
                }else if(command.contains("close")){
                    StringBuffer sb = new StringBuffer(command);
                    try {
                        Process pr = Runtime.getRuntime().exec("taskkill /F /IM " + String.valueOf(sb.delete(0, 5)) + ".exe");
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }else if(command.contains("screenshot")){
                    fc.setDialogTitle("Save screenshot");
                    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int response1 = fc.showSaveDialog(null);
                    String path1 = "";
                    if (response1 == JFileChooser.APPROVE_OPTION) {
                        path1 = fc.getSelectedFile().getPath();
                        try {
                            File file = new File(path1 + ".png");
                            BufferedImage bi = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                            ImageIO.write(bi, "png", file);
                            d.open(file);
                            say.input = "screenshot";
                            Thread th = new Thread(say);
                            th.start();
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                }else if(command.contains("window")){
                    if (command.contains("change")) {
                        try {
                            Robot robot = new Robot();
                            robot.delay(50);
                            robot.keyPress(KeyEvent.VK_ALT);
                            robot.keyPress(KeyEvent.VK_TAB);
                            robot.delay(50);
                            robot.keyRelease(KeyEvent.VK_ALT);
                            robot.keyRelease(KeyEvent.VK_TAB);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }else if(command.contains("close")){
                        try{
                            Robot r=new Robot();
                            r.keyPress(KeyEvent.VK_ALT);
                            r.keyPress(KeyEvent.VK_F4);
                            r.keyRelease(KeyEvent.VK_F4);
                            r.keyRelease(KeyEvent.VK_ALT);
                        }catch(Exception ex){
                            System.out.println(ex);
                        }
                    }
                }
                break;
        }
        
        return null;
    }

}
