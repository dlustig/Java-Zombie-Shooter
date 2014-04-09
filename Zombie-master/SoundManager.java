import java.util.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;

public class SoundManager {


	public  static void main(String[] args) {

		startBackgroundMusic();

		while(true) {

			if(r.nextInt(100000000) < 1)
			{
				playRandomSound();
			}

		}
	}

	//The number of sound files in the game
	final static int NUM_SOUNDS = 7;

	static Random r = new Random();

	private static Clip[] soundFiles = new Clip[NUM_SOUNDS];



	//reads in all the zombie sound files by looping through regularly named sound files
	static {
		for(int index = 1; index <= NUM_SOUNDS; index++){
   			try {
   				  soundFiles[index - 1] = AudioSystem.getClip( );
   				  AudioInputStream is = AudioSystem.getAudioInputStream(new File("zom" + index + ".wav"));
   				  soundFiles[index - 1].open(is);
   			} catch(Exception e) {
				System.out.println(index);
				soundFiles[index - 1] = null;
				System.out.println(e);
   			}
		}

	}

	//plays a random sound if the chosen sound is not currently playing
	public static void playRandomSound() {
		try{
			int soundNum = r.nextInt(NUM_SOUNDS);
			if(!soundFiles[soundNum].isActive()) {
				soundFiles[soundNum].stop();
				soundFiles[soundNum].setFramePosition(0);
				soundFiles[soundNum].start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	//has a chance of playing a random sound based on dt, the time passed since this method was last called
	public static void zombieBanter(double dt) {
		//note that probability of playing a sound is based on time passed since
		if(r.nextInt(1000) == dt * 1000)
			playRandomSound();
	}

	//initializes and starts playing the background music
	public static void startBackgroundMusic() {
   		 try {
   			   Clip clip = AudioSystem.getClip( );
   			   AudioInputStream is = AudioSystem.getAudioInputStream(new File("ZAT.mid"));
   			   clip.open(is);
   			   clip.loop(Clip.LOOP_CONTINUOUSLY);
   		 } catch(Exception e) {
			 System.out.println(e);
   		 }
	}




}