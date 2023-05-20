package GameSound;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SoundPlayer {
    private List<String> audioFileNames;
    private Clip clip;
    private int currentIndex;
    private boolean isLooping;

    public SoundPlayer() {
        audioFileNames = new ArrayList<>();
        currentIndex = 0;
        isLooping = false;
    }

    public void addSound(String fileName) {
        audioFileNames.add(fileName);
    }

    public void playNextSound() {
        if (audioFileNames.isEmpty()) {
            System.out.println("No audio files added.");
            return;
        }

        String fileName = audioFileNames.get(currentIndex);
        try {
            File audioFile = new File(getAudioFilePath(fileName));
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            // Register a listener to play the next sound when the current one finishes
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                    if (isLooping) {
                        currentIndex++;
                        if (currentIndex >= audioFileNames.size()) {
                            currentIndex = 0; // Restart from the beginning if all sounds played
                        }
                        playNextSound();
                    }
                }
            });
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void setLooping(boolean looping) {
        this.isLooping = looping;
    }

    private String getAudioFilePath(String fileName) {
        // Get the current working directory
        String workingDirectory = System.getProperty("user.dir");
        // Construct the file path by concatenating the working directory and the file name
        return workingDirectory + File.separator + fileName;
    }
}

