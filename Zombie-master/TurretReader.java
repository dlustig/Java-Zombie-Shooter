import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class TurretReader {

	//the read image
	private BufferedImage animation = null;

	//these variables hold the locations of the turret rotation point and the length of the turret from the rotation point
	//for drawing purposes
	private int xOff;
	private int yOff;
	private int turretLength;

	public TurretReader(String fileString) {
		try {
				animation = ImageIO.read(new File(fileString));
		} catch(Exception e) {
			System.out.println("ERROR");
			System.out.println(e);
			e.printStackTrace();
		}
		yOff = animation.getHeight() / 2;
		xOff = animation.getWidth() / 3;
		turretLength = 2 * xOff;
	}

	//Get the different attributes
	public int getTurretLength () {
		return turretLength;
	}

	public int getXOff() {
		return xOff;
	}

	public int getYOff() {
		return yOff;
	}

	public BufferedImage getImage() {
		return animation;
	}
}