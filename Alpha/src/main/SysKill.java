package main;

import com.sun.speech.freetts.*;
import gui.*;

public class SysKill extends Thread {

    private static Voice voice;
    private static VoiceManager vm = VoiceManager.getInstance();

    @Override
    public void run() {
        try{
            CommandsWindowUI.frame.setVisible(false);
        }catch(Exception ex){
            new CommandsWindowUI();
            CommandsWindowUI.frame.setVisible(false);
        }
        try{
            VoiceUI.frame.setVisible(false);
        }catch(Exception ex){
            new VoiceUI();
            VoiceUI.frame.setVisible(false);
        }
        try{
            Information.f.setVisible(false);
        }catch(Exception ex){
            new Information();
            Information.f.setVisible(false);
        }
        Main.frame.setVisible(false);
        System.setProperty("mbrola.base", "Library\\Speak\\mbrola");
        voice = vm.getVoice("mbrola_us1");
        voice.allocate();
        voice.speak("Turning Off System.");
        System.exit(0);
    }
}
