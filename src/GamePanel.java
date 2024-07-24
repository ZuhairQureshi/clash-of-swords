/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class creates the game panel on which 
	all characters and game action are drawn.
 */
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener, ActionListener {

	// initialize all variables
	private int timeHoldingGun, powerUpStayTime, speedUpStayTime, accessPowerUp, accessSpeedUp, ticksElapsed, timeWithSpeed, timeHurt;
	private static Rectangle screenBounds;
	private Timer timer;
	private static Player player;
	private static Player player2;
	private static ArrayList<Player> players;
	private static int totalRounds;
	private JButton exit;
	private static int roundsCompleted, p1Wins, p2Wins;
	private static boolean player1Won;
	private static Map map;
	private static ArrayList<PowerUp> powerUps;
	private static ArrayList<PowerUp> speedUps;
	private ArrayList<Bullet> playerBullets;
	private ArrayList<Bullet> player2Bullets; 

	public GamePanel() { // Game panel constructor
		screenBounds = new Rectangle(0, 0, 1300, 700);
		
		exit = new JButton(new ImageIcon("../images/exitButton.png"));
		this.add(exit);
		exit.addActionListener(this);
		exit.setFocusable(false);
		
		// fill player list from ChoosingScreen
		players = new ArrayList<Player>(); 
		
		powerUps = new ArrayList<PowerUp>();
		speedUps = new ArrayList<PowerUp>();
		playerBullets = new ArrayList<Bullet>();
		player2Bullets = new ArrayList<Bullet>();
		
		accessPowerUp = 0;
		accessSpeedUp = 0;
		ticksElapsed = 0;
		timeHurt = 0;
		
		// default values for player and map if none are chosen from ChoosingScreen
		map = new Field();
		player = new Skeleton(true);
		player2 = new Adventurer(false);

		timer = new Timer(60, this);

		// fill ArrayList of power ups to drop on default field 
		reloadPowerUps();

		// add focus to panel
		this.addKeyListener(this);
		setFocusable(true);

		timer.start();
	}

	// when a custom map is selected
	public static void setMap(Map map) {
		// clear out the default map parameter and reset with new map parameter
		GamePanel.map = map;
		reloadPowerUps();
	}

	// reset all variables for new game match
	public void restartGame() {
		resetPositions(player, 100);
		player.setImageArrRight();
		player.setPlayer1Positions();

		resetPositions(player2, 1000);
		player2.setImageArrLeft();
		player2.setPlayer2Positions();
		
		reloadPowerUps();
		playerBullets.clear();
		player2Bullets.clear();
		
		// reset all Timer tick counters
		ticksElapsed = 0;
		timeHoldingGun = 0;
		powerUpStayTime = 0;
		accessPowerUp = 0;
		accessSpeedUp = 0;
		timeWithSpeed = 0;
		speedUpStayTime = 0;
	}
	// reset all player values back to initial values
	public void resetPositions(Player player, int x) {
		player.setX(x);
		player.setWithGun(false);
		player.setWithSword(true);
		player.setOnGround(true);
		player.setJump(false);
		player.setFalling(false);
		player.setJumpSpeed(0);
		player.setGravity(0);
		player.setY(player.getMaxDepth());
		player.setHealth(500);
		player.setAttack(false);
		player.moving(false);
		player.hit(false);
		player.setLeftMotion(false);
		player.setRightMotion(false);
		player.setWidth(player.getOriginalWidth());
		player.setHeight(player.getOriginalHeight());
		player.makeShooting(false);
		player.setLeft(false);
		player.setRight(false);
		player.setXOnPlatform(false);
		player.setYOnPlatform(false);
		player.setOnPlatform(false);
		player.setDX(15);
	}
	
	// reload arraylist of items
	public static void reloadPowerUps() {
		powerUps.clear();
		speedUps.clear();
		
		for (int i = 0; i < 10; i++) {  
			powerUps.add(new Gun(map));
			powerUps.add(new HealUp(map));
			speedUps.add(new SpeedUp(map));
		}
	}

	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {
		// player 1 movements
	
		if (e.getKeyCode() == KeyEvent.VK_F) 
			// set player facing right / moving right to true, and facing left / moving left to false
			player.setDirectionsOfMotion(true, false, true, false);

		// set player values the opposite way when moving left compared to moving right
		if (e.getKeyCode() == KeyEvent.VK_S)  
			player.setDirectionsOfMotion(false, true, false, true);
		
		// change attack booleans to true and change images, then implement in actionperformed
		if (e.getKeyCode() == KeyEvent.VK_Q) 
			player.instantiateAttack(); 

		// get player values ready for jump
		if (e.getKeyCode() == KeyEvent.VK_E) 
			player.instantiateJump(); 

		// allow player to drop gun
		if (e.getKeyCode() == KeyEvent.VK_R) {  
			player.dropGun();
			player.setCorrespondingWidth();
		}

		player.hit(false);

		// player 2 movements - prepare the same values as Player 1 movements
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
			player2.setDirectionsOfMotion(true, false, true, false);

		if (e.getKeyCode() == KeyEvent.VK_LEFT ) 
			player2.setDirectionsOfMotion(false, true, false, true);

		if (e.getKeyCode() == KeyEvent.VK_P) 
			player2.instantiateAttack();

		if (e.getKeyCode() == KeyEvent.VK_UP)  
			player2.instantiateJump();

		if (e.getKeyCode() == KeyEvent.VK_O) {
			player2.dropGun();
			player2.setCorrespondingWidth();	
		}

		player2.hit(false);	
	}	
	
	public void keyReleased(KeyEvent e) {
		
		// Undo and reverse all the boolean values in keyPressed after key is released
		
		if (e.getKeyCode() == KeyEvent.VK_F) {
			player.setRightMotion(false);
			player.stopMovement();
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			player.setLeftMotion(false);
			player.stopMovement();
		}

		if (e.getKeyCode() == KeyEvent.VK_Q) {
			player.stopMovement();
			if (player.isShooting()) {
				player.makeShooting(false);
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player2.setRightMotion(false);
			player2.stopMovement();
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player2.setLeftMotion(false);
			player2.stopMovement();
		}

		if (e.getKeyCode() == KeyEvent.VK_P) {
			player2.stopMovement();
			if (player2.isShooting())
				player2.makeShooting(false);
		}
	}

	// when player picks up a gun item
	public void holdingGun(Player player) {
		timeHoldingGun++;
		// allow player to hold a gun for 200 timer ticks, then reset to sword
		if (timeHoldingGun == 200) {
			player.setWithGun(false);
			player.makeShooting(false);
			player.setWithSword(true);
			
			// reset to the corresponding width - either the attack image width or the original width
			player.setCorrespondingWidth();

			player.resetIdle();
			timeHoldingGun = 0; // reset time held value
		}		
	}

	public void gotSpeedUp(Player player) {
		timeWithSpeed++;
		// allow player to be sped up for 100 timer ticks, then reset to original
		if (timeWithSpeed == 100) {
			player.setDX(15);
			player.setWithSpeedUp(false);
			timeWithSpeed = 0;
		}
	}
	
	// paint all players, maps and bullets to the screen
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		exit.setBounds(575, 0, 150, 30);
		map.render(g);
		player.render(g);
		player2.render(g);
		powerUps.get(accessPowerUp).render(g);
		speedUps.get(accessSpeedUp).render(g);
		
		// draw bullets, if any, coming from player
		for (int i = 0; i < playerBullets.size(); i++)  
			playerBullets.get(i).render(g);

		// draw bullets, if any, coming from player2
		for (int i = 0; i < player2Bullets.size(); i++) 
			player2Bullets.get(i).render(g);
		
		repaint();
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == timer && MainMenu.getGameStarted()) {	
			PowerUp currentPowerUp = powerUps.get(accessPowerUp);
			PowerUp currentSpeedUp = speedUps.get(accessSpeedUp);

			// set walking boundaries for players and boundaries for the power ups  
			Rectangle itemBox = currentPowerUp.getBounds();
			Rectangle speedBox = currentSpeedUp.getBounds();
			Rectangle skelWalkingBounds = new Rectangle(player.getX() - 15, player.getY(), player.getWidth() - 15, player.getHeight());
			Rectangle advWalkingBounds = new Rectangle(player2.getX() - 15, player2.getY(), player2.getWidth() - 15, player2.getHeight());
			
			
			// handles all animations relating to the two players 
			for (int i = 0; i < players.size(); i++) {
				
				Player thisPlayer = players.get(i);
				// get the opposing player
				Player other = players.get((i + 1) % 2); 
				
				Rectangle hitBox = thisPlayer.getBounds();
				
				// constantly update if the player is ahead of the other player on the screen	
				thisPlayer.setPositionAhead(thisPlayer.getX() > other.getX()); 
				ArrayList<Bullet> bullets;
				
				 // check for collision with map platforms, if player is falling off the edge, etc.
				map.executeMapFeature(thisPlayer);
				
				// player cannot sink below a certain point while on the ground - reset to original y value 
				if (thisPlayer.getY() > thisPlayer.getMaxDepth() && thisPlayer.isOnGround()) { 
					thisPlayer.setY(thisPlayer.getMaxDepth());
				}
				
				// if player falls off the map (as in Final Destination map), they are dead
				if (thisPlayer.getY() > 530) {
					thisPlayer.setHealth(-1);
				}			
				
				// keep player within boundaries
				if (thisPlayer.getX() + thisPlayer.getWidth() > 1300)
					thisPlayer.setX(thisPlayer.getX() - 25);
				
				else if (thisPlayer.getX() <= 0) 
					thisPlayer.setX(thisPlayer.getX() + 25);

				// move player by dx values of 10 (right) or -10 (left)
				if (thisPlayer.canMoveRight() &&!thisPlayer.ableAttack()) {
					thisPlayer.motionPerform(thisPlayer.getDX());
					thisPlayer.setImageArrRight();
				}

				if (thisPlayer.canMoveLeft() && !thisPlayer.ableAttack()) {
					thisPlayer.motionPerform(-thisPlayer.getDX());
					thisPlayer.setImageArrLeft();
				}
				
				
				// if the player is not on the platform but is above the height of the platform, fall
				if (!thisPlayer.getOnPlatform() && map.yAbovePlatform(thisPlayer)) {
					thisPlayer.setFalling(true);
				}
				
				if (thisPlayer.canAttack()) {
					thisPlayer.setAttackCount(thisPlayer.getAttackCount() + 1); // run through the attack sequence
				}
				
				if (thisPlayer.gotHit()) {
					thisPlayer.setTimeHit(thisPlayer.getTimeHit()+1);
					if (thisPlayer.getTimeHit() == 25) 
						thisPlayer.setTimeHit(0);
				}

				else 
					thisPlayer.setTimeHit(0);
				
				if (thisPlayer.getTimeHit() == 0)
					thisPlayer.hit(false);
				
				// call fall when jumpSpeed reaches 0 - reverse the velocity
				if (thisPlayer.isFalling()) { 
					
					thisPlayer.setY((int)(thisPlayer.getY() + Math.pow(2, -thisPlayer.getGravity())));
					thisPlayer.setGravity(thisPlayer.getGravity() - 1);
				
					// when player has reached as far down in distance as the jump height, stop falling
					if (thisPlayer.getGravity() < -7) {
						thisPlayer.setFalling(false);
						thisPlayer.setY(thisPlayer.getY() - 1);
					}
					thisPlayer.moving(false); // player cannot walk while in the air
				}

				else 
					thisPlayer.setGravity(0);
				
				// start the player's time with the power up and remove the power up after time is up
				if (thisPlayer.hasGun())
					holdingGun(thisPlayer);

				if (thisPlayer.hasSpeedUp()) {
					gotSpeedUp(thisPlayer);
				}
				
				// Jump up and gradually decrement the exponential jump value to a negative number, then call fall
				if (thisPlayer.isJumping()) {		
					if (thisPlayer.getJumpSpeed() > 0) 
						thisPlayer.setY((int)(thisPlayer.getY() - Math.pow(2, thisPlayer.getJumpSpeed())));				
					
					if (thisPlayer.getJumpSpeed() <= 0 && !thisPlayer.getXOnPlatform()) { // when player moves off platform
						thisPlayer.setFalling(true);
						thisPlayer.setJump(false);
					}

					thisPlayer.setJumpSpeed(thisPlayer.getJumpSpeed() -1);  

					if (thisPlayer.getJumpSpeed() < -1) {
						thisPlayer.setJump(false);
					}

					thisPlayer.moving(false);
				}
				
				// if a player picks up a gun or a heal up 
				if (hitBox.intersects(itemBox) && currentPowerUp.isVisible()) { 
					currentPowerUp.received(thisPlayer);
				}
				
				// if a player picks up a speed up
				if (hitBox.intersects(speedBox) && currentSpeedUp.isVisible()) {
					timeWithSpeed = 0; // reset their power up duration
					currentSpeedUp.received(thisPlayer);
				}
				
				// if one player hits the other player with a sword
				if (hitBox.intersects(other.getBounds()) && thisPlayer.hasSword() && thisPlayer.ableAttack()) {
					other.wasAttacked();
					other.setHurtImage(other.isPositionedAhead()); // set the player facing the direction from where they were hit
				}
				
				if (i == 0)
					bullets = playerBullets;
				else
					bullets = player2Bullets;
				
				// checks if player is shooting and loads bullet array list if true
				if (thisPlayer.isShooting() && bullets.size() < 5) { // only allow 5 bullets on screen at once
					if (thisPlayer.isFacingRight()) // shoot the bullet in the correct direction, from the correct place
						bullets.add(new Bullet(thisPlayer.getX() + thisPlayer.getWidth(), thisPlayer.getY() + thisPlayer.getHeight() / 2, 50));
					else 
						bullets.add(new Bullet(thisPlayer.getX(), thisPlayer.getY() + thisPlayer.getHeight() / 2, -50));
				}
				
				// track each of the bullets and their collisions
				for (int j = 0; j < bullets.size(); j++) {
					Bullet bullet = bullets.get(j);
					Rectangle bulletBounds = bullet.getBounds();
					bullet.travel(); // move the bullet

					if (bulletBounds.intersects(other.getBounds())) { // collide with other player and do damage
						bullets.remove(j);
						other.setHealth(other.getHealth() - 5);
						other.wasAttacked();
						other.setHurtImage(other.isPositionedAhead()); // set player facing direction of collision
					}

					if (!bulletBounds.intersects(screenBounds)) // delete when bullet leaves screen boundaries
						bullets.remove(j);
				}
				
			}
			ticksElapsed++;

			// send a new speed up to the screen every 200 ticks
			if (ticksElapsed % 200 == 0) {
				accessSpeedUp++;
				speedUps.add(new SpeedUp(map));
				
				// must reset the boundaries and visibility of power up right away once the next one appears (will stay in the same spot for the next one tick otherwise)
				currentSpeedUp = speedUps.get(accessSpeedUp);
				speedBox = currentSpeedUp.getBounds();
				currentSpeedUp.setVisible(true);
				speedUpStayTime = 0;
			}

			// get power ups to fall to the screen
			currentPowerUp.fall();
			currentSpeedUp.fall();
			
			// once the power up is visible, start its visible clock and make power up invisible after it is done
			if (currentPowerUp.isVisible()) 
				powerUpStayTime = currentPowerUp.powerUpVisible(powerUpStayTime);

			if (currentSpeedUp.isVisible()) 
				speedUpStayTime = currentSpeedUp.powerUpVisible(speedUpStayTime);
			
			// send a new power up to the screen every 300 ticks
			if (ticksElapsed % 300 == 0) {
				accessPowerUp++;
				if (accessPowerUp%2 != 1)
					powerUps.add(new Gun(map));
				else
					powerUps.add(new HealUp(map));

				// reset the bounds and power up with the new current power up values right away (otherwise old power up becomes visible for one tick)
				currentPowerUp = powerUps.get(accessPowerUp);
				itemBox = currentPowerUp.getBounds();
				currentPowerUp.setVisible(true);
				powerUpStayTime = 0;
			}
			// make players tangible to one another
			if (skelWalkingBounds.intersects(advWalkingBounds)) { 
				if (player.getX() <= player2.getX()) { // move players in opposing directions when colliding with each other
					player.setX(player.getX() - 7);
					player2.setX(player2.getX() + 7);
				}
				
				// value of 7 ensures players can push each other but also run past one another
				if (player.getX() > player2.getX()) {
					player.setX(player.getX() + 7);
					player2.setX(player2.getX() - 7);
				}	
			}
			
			// if either one of the players dies
			if (player.getHealth() <= 0 || player2.getHealth() <= 0) {
				roundsCompleted++; // a round has been completed

				// if player 1 died
				if (player.getHealth() <= 0) { 
					p2Wins++;
					player1Won = false;
					WinScreen.setWinnerAndLoser(player2.getIdleLeft(), player.getIdleLeft()); // set player 2 as winner image
				} else if (player2.getHealth() <= 0) { // if player 2 died
					p1Wins++;
					player1Won = true;
					WinScreen.setWinnerAndLoser(player.getIdleLeft(), player2.getIdleLeft()); // set player 1 as the winner image
				}
				// implement a "best of" feature that automatically ends the rounds when one player wins the majority of them
				if (p1Wins > totalRounds/2 || p2Wins > totalRounds/2 ) { 
					WinScreen.setRoundsComplete(true);
					roundsCompleted = 0; // reset the rounds
					
					// if player 1 won most of the rounds, they win the series
					if (p1Wins > p2Wins) 
						WinScreen.setWinnerAndLoser(player.getIdleLeft(), player2.getIdleLeft()); 
					
					// else, player 2 is final winner
					else 
						WinScreen.setWinnerAndLoser(player2.getIdleLeft(), player.getIdleLeft());
				}

				restartGame();
				MainMenu.setGameStarted(false);
				GameFrame.layout.show(GameFrame.c, "WinScreen");				
			}
		}
		
		// if the exit button is clicked
		if (e.getSource() == exit) {
			// reset all the rounds and wins and go to menu
			roundsCompleted = 0; 
			totalRounds = 1;
			p1Wins = 0;
			p2Wins = 0;
			MainMenu.setGameStarted(false);
			GameFrame.layout.show(GameFrame.c, "Main");
			 // reset all player values
			restartGame();
		}
	}
	
	// getter and setter methods for players, rounds, map and individual number of wins
	public static int getPlayer1Wins() {
		return p1Wins;
	}

	public static void setPlayer1Wins(int p1Wins) {
		GamePanel.p1Wins = p1Wins;
	}

	public static int getPlayer2Wins() {
		return p2Wins;
	}
	
	public static Map getMap() {
		return map;
	}

	public static void setPlayer2Wins(int p2Wins) {
		GamePanel.p2Wins = p2Wins;
	}

	public static boolean player1Won() {
		return player1Won;
	}
	
	public static Player getPlayer1() {
		return player;
	}
	
	public static void setPlayer1(Player player) {
		GamePanel.player = player;
	}
	
	public static Player getPlayer2() {
		return player2;
	}
	
	public static void setPlayer2(Player player2) {
		GamePanel.player2 = player2;
	}
	
	public static void setTotalRounds(int totalRounds) {
		GamePanel.totalRounds = totalRounds;
	}
	
	public static void addToPlayerList(Player player) {
		players.add(player);
	}
	
	public static void clearPlayerList() {
		players.clear();
	}
}