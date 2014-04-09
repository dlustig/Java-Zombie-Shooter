import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.*;

public class TestBlaster extends JFrame
{

	public TestBlaster()
	{
		super("Test");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		add(new TestWindow());
		setSize(600,600);
		setVisible(true);


	}

	public static void main(String[] args) {

		enemyClass a = new enemyClass();

		TestBlaster t = new TestBlaster();
   		 try {
   			   Clip clip = AudioSystem.getClip( );
   			   AudioInputStream is = AudioSystem.getAudioInputStream(new File("ZAT.mid"));
   			   clip.open(is);
   			   clip.loop(0);
   		 } catch(Exception e) {
			 System.out.println(e);
   		 }

		while(true) {
			t.repaint();
		}
	}

}

class TestWindow extends JPanel{
	Player x = new Player(new ArrayList<Enemy>());

	public TestWindow() {
		setFocusable(true);
		requestFocusInWindow();
		addMouseListener(x);
		addMouseMotionListener(x);
		addKeyListener(x);
	}

	public void paintComponent(Graphics g) {
		x.upkeep(.05,g);
	}


}