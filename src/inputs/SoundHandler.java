/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputs;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author LENOVO
 */
public class SoundHandler {
    
    Clip clip;
    
    public SoundHandler() {
        try {
            this.clip = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(SoundHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void RunMusic(String path) throws LineUnavailableException{
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            //Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(0);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void StopMusic() {
        clip.stop();
    }

   
    
}
