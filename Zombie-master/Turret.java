import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.geom.*;

public class Turret {


	//drawing vars/rotation point offset
	private BufferedImage animation;
	private int offsetX=0;
	private int offsetY=0;
	private int turretLength = 0;

	//number of shots fired by the turret
	private int spread = 1;

	//turret location
	private int xCo;
	private int yCo;

	//turret firerate variables
	private double recharge;
	private double cooldown;

	//The tyoe of shot this turret fires, and the bonus damage applied to the shot on lvlup
	private Shot typeShot;
	private double plusDamage;

	//The list of all active shots fired by this turret
	private LinkedList<Shot> magazine;

	//the cooldown bar
	private AnimatedBar reloadBar;

	//whether this turret will fire when requested to do so (such as when a player clicks the mouse)
	private boolean activated;

	public Turret(int pX, int pY, double pCooldown, int pSpread, TurretReader reader, Shot type) {

		spread = pSpread;

		animation = reader.getImage();



		//if the animation is null, then the turrets will be rectangles, and all this stuff will be moot
		if(animation != null) {
			offsetX = reader.getXOff();
			offsetY = reader.getYOff();
			turretLength = reader.getTurretLength();
		}
		xCo = pX;
		yCo = pY;
		typeShot = type;
		cooldown = pCooldown;
		magazine = new LinkedList<Shot>();
		recharge = cooldown;
		reloadBar = new AnimatedBar(cooldown, 10, Color.RED);
		activated = false;

		plusDamage = type.getDamage() / 10;
		//System.out.println(plusDamage);
	}

	//increases shot damage
	public void levelUp(double lvlAmt) {
		typeShot.levelUp(plusDamage);
		//recharge *= (2-lvlAmt);
	}

	//Activates/deactivates this turret
	public void setActive(boolean pActive) {
		activated = pActive;
	}

	public boolean isActivated() {
		return activated;
	}

	//Fires the turret at a specified location, if able
	public void fire(int locX, int locY) {
		if(recharge >= cooldown) {

			//how much space are between the multiple shots being fired on one fire command
			int shotOffset = offsetY * 2 / (spread+1);
			//angle (in radians) to target location
			double angle = getRadiansTo(locX-offsetX,locY-offsetY);

			//places each shot according to the angle of the turret and the destination
			for(int index = 1; index < spread + 1; index++) {

				int trueOffset = index*shotOffset - offsetY;

				Shot toBeAdded = typeShot.clone();


				double startX = xCo + offsetX + Math.cos(angle+Math.PI/2) * trueOffset;
				double startY = yCo + offsetY + Math.sin(angle+Math.PI/2) * trueOffset;


				startX += Math.cos(angle) * turretLength;
				startY += Math.sin(angle) * turretLength;

				//Sets the destination of the shot (where it is travelling towards)
				toBeAdded.setDestination((int)(locX + Math.cos(angle) * trueOffset),(int)(Math.sin(angle) * trueOffset + locY));
				//places the shot at the end of the turret barrel
				toBeAdded.setLocation(startX, startY);
				//actually fires the turret by adding an active shot
				magazine.add(toBeAdded);
				//Increments the GameWorld. fired for accuracy purposes
				GameWorld.incFired();

			}
			recharge = 0;
		}
	}

	//draws the turret
	public void draw(int x, int y,Graphics g) {
		//if the animation failed to load, draw a retangle
		if(animation == null) {
			g.setColor(activated?Color.GREEN:Color.BLUE);
			g.fillRect(xCo-15,yCo-15,30,30);
		}
		else {
			//Transform rotates the png file
			AffineTransform at = AffineTransform.getRotateInstance(getRadiansTo(x-offsetX,y-offsetY),xCo+offsetX,yCo+offsetY);
			Graphics2D g2d = (Graphics2D)g;
			g2d.setTransform(at);
			g.drawImage(animation,xCo,yCo,null);
			g2d.setTransform(new AffineTransform());


            /*		double startX = xCo+offsetX;
             double startY = yCo+offsetY;
             double angle = getRadiansTo(x-offsetX,y-offsetY);


             startX += Math.cos(angle) * 60;
             startY += Math.sin(angle) * 60;

             g.fillOval((int)startX-2,(int)startY-2,5,5);*/
		}
	}


	//oes everything the turret should do each gameframe (draw turret, draw shots, move shots, updates cooldowns)
	public void upkeep(double dt, int x, int y,ArrayList<Enemy> list,Graphics g) {

		//draws the turret
		draw(x,y,g);

		//updates the cooldown
		if(recharge < cooldown)
		{
			recharge+=dt;
		}

		//updates and draws the animation bar
		reloadBar.update(recharge);
		reloadBar.draw(xCo + offsetX, yCo + 2*offsetY + 10, g);
		reloadBar.draw(xCo + offsetX, yCo + 2*offsetY + 10, g);

		//upkeeps all the shots
		ListIterator<Shot> iter = magazine.listIterator();
		while(iter.hasNext()) {
			Shot nextShot = iter.next();
			nextShot.upkeep(dt,list,g);
			if(nextShot.exists() == false) {
				iter.remove();
			}
		}
	}

	//Returns the radian measure from this turrets x,y location to the specified point
	private double getRadiansTo(int xLoc, int yLoc) {
		double quadrantAdjustment = 0; //So we can use trig on a normal triangle, we will first "translate the point into the first quadrant
		double base = 0;//the base and height of the representative triangle
		double height = 0;

		if(xCo > xLoc && yCo <= yLoc) {	//2nd quadrant
			base = xLoc-xCo;
			height = yLoc-yCo;
			height *= -1;
			quadrantAdjustment = Math.PI;
		} else if (xCo > xLoc && yCo > yLoc) {	//3rd quadrant
			height = yCo-yLoc;
			base = xCo-xLoc;
			height *= -1;
			quadrantAdjustment = Math.PI;
		} else if (xCo < xLoc && yCo < yLoc) {	//4th quadrant


			base = yCo-yLoc;
			height = xCo-xLoc;
			quadrantAdjustment = 3*Math.PI/2;


		} else {
			base = xLoc - xCo;
			height = yCo - yLoc;
		}


		if(base == 0) {
			return (3*Math.PI/2) - ((yCo>yLoc)?0:Math.PI);
		}


		double dist = distanceTo(xLoc,yLoc);

		return -1 * (Math.atan((height/dist)/(base/dist)) + quadrantAdjustment);
	}

	//distance between this turret and the specified point
	public double distanceTo(int xLoc,int yLoc) {
		return Math.sqrt((xCo-xLoc)*(xCo-xLoc)+(yCo-yLoc)*(yCo-yLoc));
	}
}