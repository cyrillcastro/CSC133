package com.mycompany.a3;

import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

/**
 * This class creates a Media object which loops while playing the sound 
 */
public class BGSound implements Runnable {

	private Media m;
	
	/**	
	 * Constructor for the background sound 
	 * @param fileName
	 */
	public BGSound(String fileName) {
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
			m = MediaManager.createMedia(is, "audio/wav", this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method for pausing the sound 
	 */
	public void pause() {
		m.pause();
	}
	
	/**
	 * Method for continuing to play the sound where we have left off
	 */
	public void play() {
		m.play();
	}
	
	/**
	 * Entered when media has finished playing. Start playing from time zero (beginning
	 * of the sound file) 
	 */
	@Override
	public void run() {
		m.setTime(0);
		m.play();
	}

}
