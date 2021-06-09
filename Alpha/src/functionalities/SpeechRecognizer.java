package functionalities;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import gui.VoiceUI;
import java.awt.Color;
import java.awt.Cursor;


public class SpeechRecognizer implements Runnable{

    private String speech;
    private Configuration config = new Configuration();
    private SpeechResult speechResult;
    
    public boolean paused=false;
    private final Commands com=new Commands();
    
    @Override
    public void run() {
        SpeechRecognizerProperties();
        try {
            recognizeSpeech();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void SpeechRecognizerProperties() {
        config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        config.setDictionaryPath("lib/7896.dic");
        config.setLanguageModelPath("lib/7896.lm");
    }
    
    public void recognizeSpeech() throws Exception{
        
        LiveSpeechRecognizer lsr=new LiveSpeechRecognizer(config);
        lsr.startRecognition(true);
        
        while((speechResult=lsr.getResult())!=null){
            speech=speechResult.getHypothesis().toLowerCase();
            System.out.println(speech);
            if(speech.contains("sleep")){
                paused=true;
                VoiceUI.voiceLabel.setIcon(VoiceUI.mute);
                VoiceUI.listenLabel.setForeground(Color.black);
                VoiceUI.voiceLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                while(paused){
                    try{
                        Thread.sleep(100);
                    }catch(Exception ex){
                        System.out.println(ex);
                    }
                }
            }else{
                com.operation("bot", speech);
            }
        }
    }
    
}
