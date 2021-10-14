package com.dictionary.tts;

import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;

public class TTSController {
    private static Synthesizer synthesizer;
    public static void phatam(String word){
        try {
            //setting properties as Kevin Dictionary
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");

            //registering speech engine
            Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");

            //create a Synthesizer that generates voice
            synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));


            //allocates a synthesizer
            synthesizer.allocate();

            //resume a Synthesizer
            synthesizer.resume();

            //speak the specified text until the QUEUE become empty
            synthesizer.speakPlainText(word, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

            //deallocating the Synthesizer
//            synthesizer.deallocate();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void off(){
        try {
            //deallocating the Synthesizer
            synthesizer.deallocate();
        } catch (EngineException e) {
            e.printStackTrace();
        }
    }
}
