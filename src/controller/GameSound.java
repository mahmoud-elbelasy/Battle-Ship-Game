package controller;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GameSound {
	
	// The MediaPlayer that will play the main game sound
	MediaPlayer mainSound;
	
	// Constructor for the GameSound class
	public GameSound() {
		// Import the main game sound file
		File Sound1File = new File("C:\\Users\\Mohamed Belasy\\Downloads\\38833FF26BA1D.UnigramPreview_g9c9v27vpyspw!App\\battleShip\\src\\Sound\\sound_effect.mp3");
		
		// Create a new MediaPlayer for the main game sound using the imported file
		mainSound = new MediaPlayer(new Media(Sound1File.toURI().toString()));
		
		// Set the main game sound to loop indefinitely
		mainSound.setCycleCount(MediaPlayer.INDEFINITE);
	}
}