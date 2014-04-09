import javax.swing.*;
import javax.imageio.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.Timer;
import java.util.TimerTask;


class Enemy {

	private double x, y, dx, dy;
	private Image [] zombie;
	private Image tombstone;
	private int current;
	private boolean right = true;
	private boolean moving = true;
	private boolean firstPass = true;
	private boolean rebound = false;
	/* time between flips in the animation */
	private static final double FLIP_TIME = 0.050;
	/* time since last flip */
	private double timer = 0.0;
	private int zomType;
	private boolean alive = true;
	private int health = 100;
	private int startHealth = 100;
	private double starty = 500;

	public Enemy(int i) {
		zomType = i;
		//plays if the zomie is alive
		if (alive){
			Random r = new Random();
			x = r.nextFloat( ) * 450;
			y = r.nextFloat( ) * starty;

			//make sure zombies start at the top
			if (firstPass == true){
				x = r.nextFloat() * (450 - 0) + 0;
				y = starty;
				firstPass = false;
			}


			//calculates pixel per second
			dx = r.nextFloat( )*50 - 25;
			dy = r.nextFloat( )*50 + 100;

		}
		/* load all the images */
		try {

			zombie = new Image[5];
			if (zomType == 0){
				zombie[0]  = ImageIO.read(new File("z1.png"));
				zombie[1]  = ImageIO.read(new File("z2.png"));
				zombie[2]  = ImageIO.read(new File("z3.png"));
				zombie[3]  = ImageIO.read(new File("z4.png"));
			}
			if (zomType == 1){
				zombie[0]  = ImageIO.read(new File("a1.png"));
				zombie[1]  = ImageIO.read(new File("a2.png"));
				zombie[2]  = ImageIO.read(new File("a3.png"));
				zombie[3]  = ImageIO.read(new File("a4.png"));
			}
			if (zomType == 2){
				zombie[0]  = ImageIO.read(new File("b1.png"));
				zombie[1]  = ImageIO.read(new File("b2.png"));
				zombie[2]  = ImageIO.read(new File("b3.png"));
				zombie[3]  = ImageIO.read(new File("b4.png"));
			}
			if (zomType == 3){
				zombie[0]  = ImageIO.read(new File("c1.png"));
				zombie[1]  = ImageIO.read(new File("c2.png"));
				zombie[2]  = ImageIO.read(new File("c3.png"));
				zombie[3]  = ImageIO.read(new File("c4.png"));
			}
			if (zomType == 4){
				zombie[0]  = ImageIO.read(new File("d1.png"));
				zombie[1]  = ImageIO.read(new File("d2.png"));
				zombie[2]  = ImageIO.read(new File("d3.png"));
				zombie[3]  = ImageIO.read(new File("d4.png"));
			}
			if (zomType > 4){
				zombie[0]  = ImageIO.read(new File("d1.png"));
				zombie[1]  = ImageIO.read(new File("d2.png"));
				zombie[2]  = ImageIO.read(new File("d3.png"));
				zombie[3]  = ImageIO.read(new File("d4.png"));
			}
			//tombstone = ImageIO.read(new File("tombstone.png"));
		} catch(Exception e) {

			zombie = null;
		}
		current = 0;
	}


	public void draw(Graphics g) {
		/* add to the index if going left */
		int add = 0;
		if(!right) add = 2;

		/* draw zombie on the screen */
		if (alive == true){
			g.drawImage(zombie[current + add], (int)x, (int)y, null);
		}
		else{
			//g.drawImage(tombstone, (int)x, (int)y, null);
		}
	}



	public void update(double dt) {
		if (alive){
			//bounces off the side walls
			if (rebound == false){
				x += (dx * dt);
				y += (dy * dt);
			}
			else{
				x -= (dx * dt);
				y += (dy * dt);
			}

			if(y < 0) y = starty;
			if(y > starty) y = 0;
			if(x < 0){
				if (rebound == false){
					rebound = true;
				}
				else{
					rebound = false;
				}
				//x = 500;
			}
			if(x > 500){
				if (rebound == false){
					rebound = true;
				}
				else{
					rebound = false;
				}
			}



			//****** recognize zombie crossing/winning
			if(y > 499 && y < 500){
					GameWorld.incZombiesCrossed();
					y = 0;

			}


			/* update animation */
			if(moving) {
				timer += dt;
				if(timer > FLIP_TIME) {
					timer = 0;
					current = (current + 1) % 4;
				}
			}
		}
	}

	//pass the hit box for the collision detection
	public Rectangle2D.Double getZombie(){
		return new Rectangle2D.Double(x, y, 29, 32);
	}

	//input from the turrets when a specific zombie is hit
	public void registerShots(int damage){
		health = health - damage;
		if (health <= 0){
			alive = false;
			GameWorld.incKilledZombies();

		}
	}
	public boolean checkLife(){
		return alive;
	}



	//level up the zombies
	public void levelUpHealth() {
		startHealth += 30;
		health = startHealth;
		alive = true;
		y = starty;
		firstPass = true;
	}
	//reset the zombie
	public void reAnimate(){
		alive = true;
		y = starty;
		health = startHealth;
		firstPass = true;

	}




}


class GameWorld extends JComponent  {
	private ArrayList<Enemy> EnemyFactory;
	private long elapsed;
	private boolean levelUp = false;
	private int zombiesDead = 0;
	private int numZombies = 5;
	private int ZombiesInLevel = 17;
	private int zombiesAlive;
	private double zombieSpeed = 10000f;
	private int zombiesAdded;
	private static int zombiesKilled = 0;
	private static int zombiesCrossed = 0;
	private static double shotsFired = 0;//used for calculating accuracy
	private static double shotsHit = 0;

	private static Player player;


	public GameWorld( ) {


        setSize(650,700);

        setSize(700,700);

        setSize(700,700);
		elapsed = new Date( ).getTime( );
		EnemyFactory = new ArrayList<Enemy>( );

		player = new Player(EnemyFactory);
		setFocusable(true);
		requestFocus();
		addMouseListener(player);
		addMouseMotionListener(player);
		addKeyListener(player);


		//update the i < # for different number of enemies
		for(int i = 0; i < numZombies; i++) {
			EnemyFactory.add(new Enemy(i));
		}



	}
	//increment when zombie is hit
	public static void incHit() {
		shotsHit++;
	}
	//increment amount of shots
	public static void incFired() {
		shotsFired++;
	}
	//calculate the accuracy
	public static double getAcc() {
		if(shotsFired == 0) {
			return 0;
		}
		else return shotsHit/shotsFired;
	}



	public static int getScore(){
		return zombiesKilled;
	}

	public static void incZombiesCrossed(){
		zombiesCrossed +=1;

		ZombieAttack.setHealth((10-zombiesCrossed >= 0)?10 - zombiesCrossed:0);

		//System.out.println("zombie Crossed!");
		
		//zombies have won, disable the turrets
		if (zombiesCrossed == 10){
			player.disable();

			//bring up the highscore
			Highs highScorePanel = new Highs();
			highScorePanel.Gui();

		}
	}
	//increment zombie kills and score
	public static void incKilledZombies(){
		zombiesKilled +=1;

		ZombieAttack.setScore(zombiesKilled);

		//System.out.println("killed : " + zombiesKilled);
	}

	public void runningGame(){

		//reset to check each time
		zombiesDead = 0;
		zombiesAlive = 0;
		//check status on all zombies to see if any are still alive
		for(Enemy f : EnemyFactory) {
			//if it returns true, it means at least one is still alive.
			if (f.checkLife() == false){
				zombiesDead += 1;
			}
			else{
				zombiesAlive += 1;
			}
		}
		//check if game needs to level up
		if (zombiesKilled >= ZombiesInLevel){
			levelUp = true;
			levelUP();
			//update whole game

		}
		else{
			//find out how many zombies need to be reset

			int ZombiesNeeded = ZombiesInLevel - zombiesDead - zombiesAlive - zombiesAdded;

			//System.out.println("Zombies needed : " + ZombiesNeeded + "  zombies added : " + zombiesAdded);
			for(Enemy f : EnemyFactory) {

				if (f.checkLife() == false){
					if (ZombiesNeeded > 0){
						ZombiesNeeded -= 1;
						zombiesAdded += 1;
						f.reAnimate();
					}
				}
			}
		}
	}




	//level up whole game
	public void levelUP(){
		if (levelUp == true){


			for(Enemy f : EnemyFactory) {
				f.levelUpHealth();
			}
			zombieSpeed = zombieSpeed - 2000; //increase speed
			if (zombieSpeed < 1000){
				zombieSpeed = 1000;
			}
			ZombiesInLevel += 15; //increase total number of zombies
			numZombies += 1; //increase number of zombies on screen
			

			ZombieAttack.setLvl(numZombies - 4);

			player.levelUp();//level up the player attributes
			//stop the levelUP
			levelUp = false;
		}
	}



	public void paintComponent(Graphics g) {

		ZombieAttack.setAcc(getAcc());

		//check status on zombies alive/vs dead
		for(Enemy f : EnemyFactory) {
			f.draw(g);
		}


		/* now update */
		long time_now = new Date( ).getTime( );
		//update the divided number higher to go slower
		double seconds = (time_now - elapsed) / zombieSpeed;
		for(Enemy f : EnemyFactory) {
			f.update(seconds);
			elapsed = time_now;
		}

		runningGame();
		SoundManager.zombieBanter(seconds);
		player.upkeep(seconds,g);


		/* force an update */
		revalidate( );
		repaint( );
		/* sleep for 1/20th of a second */

		/*try {
			//Thread.sleep(50);
		} catch(InterruptedException e) {
			Thread.currentThread( ).interrupt( );
		}*/
	}



}

public class enemyClass {
	public enemyClass(JComponent comp){
		GameWorld g = new GameWorld();
        comp.add(g);
	}
}
