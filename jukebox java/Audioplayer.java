package org.example;
import java.util.*;
import org.example.Connectivity;
import java.io.IOException;
import javax.sound.sampled.*;
import java.sql.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import org.example.Connectivity;

public class Audioplayer {

    Long currentFrame;
    Clip clip;
    String status;
    AudioInputStream audioInputStream;
    String filePath;

    public Audioplayer() {
        Connectivity.connectivity();
    }

    public List<String> getfilepath(int songid) {
        List<String> filepaths = new ArrayList<>();

        try {
            PreparedStatement ps = Connectivity.connect.prepareStatement("SELECT filepath FROM songs WHERE songid=?");
            ps.setInt(1, songid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                filepaths.add(rs.getString("filepath"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving file path: " + e.getMessage());
        }
        return filepaths;
    }

    public void Audiofun(int songid) {
        try {
            List<String> filepaths = getfilepath(songid);
            if (filepaths.isEmpty()) {
                System.out.println("File path not found for song ID: " + songid);
                return;
            }
            for (String filePath : filepaths) {
                this.filePath = filePath;
                System.out.println("Playing file: " + this.filePath);
                audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error during audio file loading: " + filePath);
            e.printStackTrace();
        }
    }


    public void play() {
        //start the clip
        clip.start();

        status = "play";
    }

    public void stop() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    // Method to pause the audio
    public void pause() {
        if (status.equals("paused")) {
            System.out.println(" alreaudio isady paused");
            return;
        }
        this.currentFrame =
                this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    // Method to resume the audio
    public void resumeAudio() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        if (status.equals("play")) {
            System.out.println("Audio is already " +
                    "being played");
            return;
        }
        clip.close();
        resetAudioStream();

        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException {
        clip.stop();
        clip.close();
        resetAudioStream();

        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}