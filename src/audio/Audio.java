package src.audio;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {

    private Clip song, song2;
    private float volume = 1f;

    public Audio() {
        song = ImportPlayingSound();
        song2 = ImportMenuSound();
    }

    private Clip ImportPlayingSound() {
        URL url = getClass().getResource("/res/audio/level.wav");
        AudioInputStream audio;
        try {
            audio = AudioSystem.getAudioInputStream(url);
            Clip c = AudioSystem.getClip();
            c.open(audio);
            return c;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Clip ImportMenuSound() {
        URL url2 = getClass().getResource("/res/audio/menu.wav");
        AudioInputStream audio2;
        try {
            audio2 = AudioSystem.getAudioInputStream(url2);
            Clip c = AudioSystem.getClip();
            c.open(audio2);
            return c;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void playMenuSound() {
        stopSound();

        song2.setMicrosecondPosition(0);
        song2.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playPlayingSound() {
        stopSound();

        updateSongVolume();
        song.setMicrosecondPosition(0);
        song.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public Clip getPlayingSound() {
        return song;
    }

    public void stopSound() {
        if (song.isActive())
            song.stop();
        if (song2.isActive())
            song2.stop();
    }

    private void updateSongVolume() {

        FloatControl gainControl = (FloatControl) song.getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * volume) + gainControl.getMinimum();
        gainControl.setValue(gain);

    }

    public void setVolume(float volume) {
        this.volume = volume;
        updateSongVolume();
    }
}
