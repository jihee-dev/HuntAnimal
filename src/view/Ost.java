package view;

import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
//import javax.sound.sampled.LineUnavailableException;

public class Ost {
    private AudioInputStream ais;
    private Clip clip;
    private boolean empty = true;

    public void setEmpty(boolean b) {
        this.empty = b;
    }

    public boolean getEmpty() {
        return this.empty;
    }

    public void playmusic(String fileName) {

        if (this.empty) {
            try {
                this.ais = AudioSystem.getAudioInputStream(new File(fileName));
                this.clip = AudioSystem.getClip();
                this.clip.stop();
                this.clip.open(ais);
                this.clip.start();
            } catch (Exception ex) {
                this.clip.stop();
            }
        }

    }

}