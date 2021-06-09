package functionalities;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Say implements Runnable{
    
    private Voice voice;
    private VoiceManager vm = VoiceManager.getInstance();
    private Random random = new Random();
    public String input = "";
        
    private void voiceProperty() {
        System.setProperty("mbrola.base", "lib/mbrola");
        voice = vm.getVoice("mbrola_us1");
        voice.allocate();
    }
    
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
        }else if(input.contains("exit")){
            exit();
        }
    }
    
    
    private void open() {

        StringBuffer sb = new StringBuffer(input);

        if (input.contains("open")) {
            input = String.valueOf(sb.delete(0, input.indexOf("n") + 1));
        } else {
            input = String.valueOf(sb.delete(0, 5));
        }

        if (input.contains("youtube")) {
            int p = random.nextInt(3);
            switch (p) {
                case 0:
                    voice.speak("Openning Youtube!");
                    break;
                case 1:
                    voice.speak("Going To Youtube Dot Com!");
                    break;
                case 2:
                    voice.speak("Starting Youtube!");
                    break;
                default:
                    break;
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
        }else if (input.contains("cortana")) {
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

    private void ss() {
        voice.speak("Screenshot Captured!");
        voice.speak("Here Is The Captured Screenshot!");
    }

    private void file() {
        voice.speak("The File Has Been Created!");
    }

    private void date() {
        Date dt = new Date();
        SimpleDateFormat df = new SimpleDateFormat("EEEEEEEEE dd MMMMMMMMM yyyy");
        voice.speak("Today is " + df.format(dt));
    }

    private void time() {
        Date dt = new Date();
        SimpleDateFormat df = new SimpleDateFormat("hh mm a");
        voice.speak("The time is now " + df.format(dt));
    }

    private void search() {
        if (input.contains("bing")) {
            voice.speak("Searching");
        } else if (input.contains("youtube")) {
            voice.speak("Searching On Youtube");
        } else if (input.contains("wikipedia")) {
            voice.speak("Searching On Wikipedia");
        }
    }
    
    private void exit(){
        int opt=random.nextInt(2);
        if(opt==0){
            voice.speak("Turning off system");
        }else{
            voice.speak("Goodbye master! Have a nice day.");
        }
        System.exit(0);
    }
    
}
