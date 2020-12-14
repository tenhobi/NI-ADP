package cz.cvut.fit.miadp.mvcgame.controller;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundManager {
    String missile1 = "/sounds/missile-1.wav";
    String missile2 = "/sounds/missile-2.wav";
    String missile3 = "/sounds/missile-3.wav";
    String explosion = "/sounds/explosion.wav";

    private static class AudioPlayer {
        public AudioPlayer(String path) {
            Clip clip;
            try {
                URL url = SoundManager.class.getResource(path);
                AudioInputStream input = AudioSystem.getAudioInputStream(url);
                clip = AudioSystem.getClip();
                clip.open(input);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    public SoundManager() {

    }

    public void playMissile1() {
        new AudioPlayer(this.missile1);
    }

    public void playMissile2() {
        new AudioPlayer(this.missile2);
    }

    public void playMissile3() {
        new AudioPlayer(this.missile3);
    }

    public void playExplosion() {
        new AudioPlayer(this.explosion);
    }
}
