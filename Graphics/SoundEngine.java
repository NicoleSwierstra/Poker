package Graphics;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.*;

public class SoundEngine{

    static Map<String, AudioInputStream> clips = new HashMap<String, AudioInputStream>();

    public static synchronized void playSound(final String location) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream; 
                    if(clips.containsKey(location)){
                        inputStream = clips.get(location);
                        inputStream.reset();
                    }
                    else {
                        inputStream = AudioSystem.getAudioInputStream(
                            new BufferedInputStream(
                                new FileInputStream(
                                    new File(location)
                                )
                            )
                        );
                        clips.put(location, inputStream);
                        inputStream.mark(10000000);   
                    }
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
};