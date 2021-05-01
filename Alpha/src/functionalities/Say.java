package functionalities;

import com.sun.speech.freetts.*;
import java.util.*;
import java.text.*;

public class Say implements Runnable {

    public Voice voice;
    public VoiceManager vm = VoiceManager.getInstance();
    public Random random = new Random();
    public static String input = "";

    @Override
    public void run() {
        voiceProperty();
        if (input.contains("search")) {
            search();
        } else if (input.contains("time")) {
            time();
        } else if (input.contains("open")||input.contains("start")) {
            open();
        } else if (input.contains("file")) {
            file();
        } else if (input.contains("screenshot")) {
            ss();
        } else if (input.contains("date")) {
            date();
        }
    }

    public void voiceProperty() {
        System.setProperty("mbrola.base", "src\\Library\\Speak\\mbrola");
        voice = vm.getVoice("mbrola_us1");
        voice.allocate();
    }

    public void open() {

        StringBuffer sb = new StringBuffer(input);

        if (input.contains("open")) {
            input = String.valueOf(sb.delete(0, input.indexOf("n") + 1));
        } else {
            input = String.valueOf(sb.delete(0, 5));
        }

        if (input.contains("youtube")) {
            int p = random.nextInt(3);
            if (p == 0) {
                voice.speak("Openning Youtube!");
            } else if (p == 1) {
                voice.speak("Going To Youtube Dot Com!");
            } else if (p == 2) {
                voice.speak("Starting Youtube!");
            }
        } else if (input.contains("inbox")) {
            voice.speak("Openning Mail Inbox");
        } else if (input.contains("powerpoint")) {
            int p = random.nextInt(2);
            if (p == 0) {
                voice.speak("Openning Microsoft Power Point!");
            } else if (p == 1) {
                voice.speak("Starting Microsoft Power Point!");
            }
        }else if (input.contains("code")||input.contains("visual")) {
            int p = random.nextInt(2);
            if (p == 0) {
                voice.speak("Openning Visual Studio Code!");
            } else if (p == 1) {
                voice.speak("Starting Visual Studio Code!");
            }
        } else if (input.contains("file")) {
            int p = random.nextInt(2);
            if (p == 0) {
                voice.speak("Openning File Explorer!");
            } else if (p == 1) {
                voice.speak("Starting File Explorer!");
            }
        } else if (input.contains("cortana")) {
            voice.speak("Calling Cortana!");
        }  else {
            int p = random.nextInt(2);
            if (p == 0) {
                voice.speak("Openning " + input + "!");
            } else if (p == 1) {
                voice.speak("Starting" + input + "!");
            }
        }
    }

    public void ss() {
        voice.speak("Screenshot Captured!");
        voice.speak("Here Is The Captured Screenshot!");
    }

    public void file() {
        voice.speak("The File Has Been Created!");
    }

    public void date() {
        Date dt = new Date();
        SimpleDateFormat df = new SimpleDateFormat("EEEEEEEEE dd MMMMMMMMM yyyy");
        voice.speak("Today is " + df.format(dt));
    }

    public void time() {
        Date dt = new Date();
        SimpleDateFormat df = new SimpleDateFormat("hh mm a");
        voice.speak("The time is now " + df.format(dt));
    }

    public void search() {
        if (input.contains("bing")) {
            voice.speak("Searching");
        } else if (input.contains("youtube")) {
            voice.speak("Searching On Youtube");
        } else if (input.contains("wikipedia")) {
            voice.speak("Searching On Wikipedia");
        }
    }

}
