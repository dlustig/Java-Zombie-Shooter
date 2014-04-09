import java.awt.*;
import java.util.*;

public class Shot implements Cloneable{

	//speed across different directions (once placed + directed
	private double xSpeed;
	private double ySpeed;
	generic speed of the shot
	private double speed;
	private double damage=10;
	private boolean exists;

	//shot location
	private double xCo;
	private double yCo;


	public Shot(double pX, double pY, double pSpeed, double pDamage) {
		damage = pDamage;
		xCo = pX;
		yCo = pY;
		exists = true;
		speed = pSpeed;
	}

	public Shot clone() {
		return new Shot(xCo,yCo,speed,damage);
	}

	//ups the damage of the shot
	public void levelUp(double lvlAmount) {
		damage += lvlAmount;
		//System.out.println(damage);
	}

	//sets the x and yspeed based on where the shot should go
	public void setDestination(int pX, int pY) {
		double distance = getDistanceTo(pX,pY);
		xSpeed = (pX - xCo) / distance * speed;
		ySpeed = (pY - yCo) / distance * speed;
	}

	//draws the shot at its location
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval((int)xCo-3,(int)yCo-3,7,7);
	}

	private double getDistanceTo(int xLoc, int yLoc) {
		return Math.sqrt((xCo-xLoc)*(xCo-xLoc) + (yCo-yLoc)*(yCo-yLoc));
	}

	public void setLocation(double x, double y) {
		xCo = x;
		yCo = y;
	}

	//moves the shot based on the time amount and its x/yspeed
	public void move(double dt) {
		xCo += xSpeed * dt;
		yCo += ySpeed * dt;

		if(xCo < -50 || yCo < -50 || xCo > 1000 || yCo > 1000) {
			exists = false;
		}

	}

	//Moves the shot and looks for collisions
	public void upkeep(double dt, ArrayList<Enemy> list, Graphics g) {
		move(dt);

		boolean draw = true;

		//check whether it has collided with each enemy
		for(Enemy e: list) {
			if(collide(e.getZombie()) && e.checkLife() == true) {
				//cause the enemy to take damage
				e.registerShots((int)damage);
				GameWorld.incHit();
				//System.out.println(damage + "<---");
				exists = false;
				break;
			}
		}
		if(exists) {
			draw(g);
		}

	}

	public boolean exists() {
		return exists;
	}

	//checks collisions
	public boolean collide(Shape shape) {
		if(shape.contains(xCo,yCo)) {
			return true;
		}
		return false;
	}

	public double getDamage() {
		return damage;
	}


}

