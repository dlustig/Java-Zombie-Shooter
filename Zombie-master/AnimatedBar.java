import java.awt.*;

public class AnimatedBar {


	private double max;

	private double current;
	//length of the bar
	private int size;
	//fill color of the bar
	private Color barColor;

	public AnimatedBar(double pMax, int pSize, Color fillColor) {
		max = pMax;
		current = pMax;
		size = pSize;
		barColor = fillColor;
	}

	//draws he bar centered at the specified location
	public void draw(int xLoc, int yLoc, Graphics g) {
		int height = size / 6 + 1;
		g.setColor(Color.BLACK);
		g.drawRect(xLoc - size - 1, yLoc - height/2 - 1, size * 2 + 3, height + 2);
		g.setColor(barColor);
		double percentage = current/max;

		//makes sure that we don;t draw outside the bounds of the animatedbar
		if(percentage > 1) {
			percentage = 1;
		}
		if(percentage < 0) {
			percentage = 0;
		}
		g.fillRect(xLoc - size, yLoc - height/2, (int)((size * 2 + 1) * percentage)+1, height+1);
	}

	//changes the amount that the bar should be filled
	public void update(double pCurrent) {
		current = pCurrent;
	}

}

