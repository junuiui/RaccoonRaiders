package com.group21;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * The Sound class provides methods for Sound control.
 */
public class Sound {
    /**
     * The clip variable to control over the playback of in-memory sound.
     */
    protected Clip clip;
    /**
     * To get the URL for sound files.
     */
    protected URL[] soundURL;

    /**
     * Constructor.
     */
    public Sound()
    {
        initializeSoundURL();
    }
    /**
     * Initialize the sound URL array.
     */
    public void initializeSoundURL() {
        String[] fileNames = {"MainBG.wav", "Title.wav"};
        soundURL = new URL[fileNames.length];

        for (int i = 0; i < fileNames.length; i++) {
            soundURL[i] = getClass().getResource("/sound/" + fileNames[i]);
        }
    }
    /**
     * Select the music file input stream.
     *
     * @param i index of different music paths
     */
    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Play the music.
     */
    public void play()
    {
        if(clip != null) {
            clip.start();
        }
    }

    /**
     * Loop the music
     */
    public void loop()
    {
        if(clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    
    /**
     * Stop the music
     */
    public void stop()
    {
        if(clip != null){
            clip.stop();
            clip.close();
        }
    }
}
