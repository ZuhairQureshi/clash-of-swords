/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class creates the template for 
	the players in this game and all their necessary
	states and behaviours.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public abstract class Player {
	// declarations of all variables in the Player class,
	// including coordinates and dimensions, images, and movement / power up booleans
	private boolean isPlayer1;
	private int jumpSpeed, gravity;
	private boolean ableAttack, canJump, fall, isMoving, gotHit;
	private int imageCount, x, y, dx;
	private int health, healthBarLocation, iconLocation;
	private boolean moveRight, moveLeft;
	private boolean facingRight, facingLeft, positionedAhead;
	private boolean hasGun, hasSword, hasSpeedUp; 
	private boolean shootingBullets;
	protected ImageIcon[] rightMovements, leftMovements, move;
	protected ImageIcon[] attacks, rightAttacks, leftAttacks;
	protected ImageIcon [] gunWalkRight, gunWalkLeft;
	protected ImageIcon idle, idleRight, idleLeft, idleGunRight, idleGunLeft; 
	protected ImageIcon hurt, hurtRight, hurtLeft, playerIcon, flippedIcon;
	private int walkSequenceLength, attackSequenceLength;
	private int width, widthWithGun, attackWidth, attackHeight, hurtWidth, hurtPositioning;
	private int height, maxDepth;
	private int platBase, platLimit; // these variables are specific values that permit the player to stand on and intersect the platform
	private boolean xOnPlatform, yOnPlatform, onPlatform, onGround;
	private int attackCount, timeHit;

	public Player(boolean isPlayer1) { // constructor of Player class
		jumpSpeed = 0;
		gravity = 0;
		ableAttack = false;
		canJump = false;
		fall = false;
		isMoving = false;
		gotHit = false;
		onGround = true;

		imageCount = 0;
		timeHit = 0;

		dx = 15;
		health = 500;
		
		hurtWidth = 0;
		attackHeight = 0;
		
		moveRight = false;
		moveLeft = false;
		hasSword = true;
		hasGun = false;

		attackCount = 0;
	}
	
	// get and set if the player is the first player
	public boolean isPlayer1() {
		return isPlayer1;
	}

	public void setPlayer1(boolean isPlayer1) {
		this.isPlayer1 = isPlayer1;
	}
	
	// creates starting positions and images for player 1 and player 2 
	public void setPlayer1Positions() {
		x = 100;
		y = maxDepth;
		move = rightMovements;
		attacks = rightAttacks;
		hurt = hurtRight;
		idle = idleRight;
		healthBarLocation = 0;
		playerIcon = flippedIcon;
		iconLocation = 500;
		facingRight = true;
	}

	public void setPlayer2Positions() {
		x = 1000;
		y = maxDepth;
		move = leftMovements;
		attacks = leftAttacks;
		hurt = hurtLeft;
		idle = idleLeft;
		healthBarLocation = 800;
		iconLocation = 725;
		facingLeft = true;
	}

	// getter and setter methods for all pertinent variables in the Player class
	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public int getDX() {
		return dx;
	}

	public void setDX(int dx) {
		this.dx = dx;
	}

	public void setAttack(boolean attack) {
		this.ableAttack = attack;
	}

	public boolean ableAttack() {
		return ableAttack;
	}

	public boolean canMoveLeft() {
		return moveLeft;
	}

	public void setLeftMotion(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}

	public boolean canMoveRight() {
		return moveRight;
	}

	public void setRightMotion(boolean moveRight) {
		this.moveRight = moveRight;
	}

	public boolean isFacingRight() {
		return facingRight;
	}

	public void setRight(boolean facingRight) {
		this.facingRight = facingRight;
	}

	public void setLeft(boolean facingLeft) {
		this.facingLeft = facingLeft;
	}

	// when a player is hit
	public void wasHit() { 
		// move the player back or forward after being hit
		if (positionedAhead)
			x+=2;
		else
			x -= 2;
		gotHit = true;
	}

	public void moving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public void hit(boolean gotHit) {
		this.gotHit = gotHit;
	}

	public boolean gotHit() {
		return gotHit;
	}

	public boolean canAttack() {
		return ableAttack;
	}

	// returns the remaining health of the character
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealthBarLocation() {
		return healthBarLocation;
	}

	// methods to get and set whether the player is jumping or falling and related variables
	public void setFalling(boolean fall) {
		this.fall = fall;
	}

	public boolean isFalling() {
		return fall;
	}

	public boolean isJumping() {
		return canJump;
	}
	public void setJump(boolean canJump) {
		this.canJump = canJump;
	}

	public int getJumpSpeed() {
		return jumpSpeed;
	}

	public void setJumpSpeed(int jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
	}

	public int getGravity() {
		return gravity;
	}

	public void setGravity (int gravity) {
		this.gravity = gravity;
	}
	// get and set player with sword or gun
	public boolean hasSword() {
		return hasSword;
	}

	public void setWithSword(boolean hasSword) {
		this.hasSword = hasSword;
	}

	public boolean hasGun() {
		return hasGun;
	}

	public void setWithGun(boolean hasGun) {
		this.hasGun = hasGun;
	}
	
	// returns if player is further right on the screen than the other player
	public boolean isPositionedAhead() {
		return positionedAhead;
	}

	public void setPositionAhead(boolean positionedAhead) {
		this.positionedAhead = positionedAhead;
	}
	
	public boolean hasSpeedUp() {
		return hasSpeedUp;
	}

	public void setWithSpeedUp(boolean hasSpeedUp) {
		this.hasSpeedUp = hasSpeedUp;
	}

	public boolean isShooting() {
		return shootingBullets;
	}

	public void makeShooting(boolean shootingBullets) {
		this.shootingBullets = shootingBullets;
	}

	// getters and setters for player dimensions
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidthWithGun() {
		return widthWithGun;
	}

	public void setWidthWithGun(int widthWithGun) {
		this.widthWithGun = widthWithGun;
	}

	public int getAttackWidth() {
		return attackWidth;
	}

	public void setAttackWidth(int attackWidth) {
		this.attackWidth = attackWidth;
	}
	
	public void setAttackHeight(int attackHeight) {
		this.attackHeight = attackHeight;
	}

	public void setHurtWidth(int hurtWidth) {
		this.hurtWidth = hurtWidth;
	}
	
	public void setHurtPositioning(int hurtPositioning) {
		this.hurtPositioning = hurtPositioning;
	}
	
	// specific integer value necessary for a player to contact platform
	public int getPlatBase() {
		return platBase;
	}

	public void setPlatBase(int platBase) {
		this.platBase = platBase;
	}
	
	// a second necessary specific integer value for player to contact platform
	public int getPlatLimit() {
		return platLimit;
	}

	public void setPlatLimit(int platLimit) {
		this.platLimit = platLimit;
	}

	public int getMaxDepth() {
		return maxDepth;
	}

	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	public ImageIcon getIdleLeft() {
		return idleLeft;
	}
	// number of images in the walk sequence
	public void setWalkSequenceLength(int walkSequenceLength) {
		this.walkSequenceLength = walkSequenceLength;
	}

	public void setAttackSequenceLength(int attackSequenceLength) {
		this.attackSequenceLength = attackSequenceLength;
	}

	// set player images facing left
	public void setImageArrLeft() {
		if (hasSword) {
			setImages(leftMovements, leftAttacks, idleLeft);
		} else if (hasGun) {
			setImages(gunWalkLeft, leftAttacks, idleGunLeft);
		}
	}

	// set player image facing right
	public void setImageArrRight() {
		if (hasSword) {
			setImages(rightMovements, rightAttacks, idleRight);
		} else if (hasGun) {
			setImages(gunWalkRight, rightAttacks, idleGunRight);
		}
	}
	
	// method to simplify resetting of movement images
	public void setImages(ImageIcon[] movements, ImageIcon[] attackSequence, ImageIcon idleImage) {
		move = movements;
		attacks = attackSequence;
		idle = idleImage;
	}

	// create a hitbox for the player
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public boolean getXOnPlatform() {
		return xOnPlatform;
	}

	public void setXOnPlatform(boolean xOnPlatform) {
		this.xOnPlatform = xOnPlatform;
	}

	public boolean getYOnPlatform() {
		return yOnPlatform;
	}

	public void setYOnPlatform(boolean yOnPlatform) {
		this.yOnPlatform = yOnPlatform;
	}

	public boolean getOnPlatform() {
		return onPlatform;
	}

	public void setOnPlatform(boolean onPlatform) {
		this.onPlatform = onPlatform;
	}

	public boolean isOnGround() {
		return onGround;
	}

	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}

	// iterator  displaying attack sequence images one after the other
	public int getAttackCount() {
		return attackCount;
	}
	
	public int getTimeHit() {
		return timeHit;
	}
	
	public void setTimeHit(int timeHit) {
		this.timeHit = timeHit;
	}

	public void setAttackCount(int attackCount) {
		this.attackCount = attackCount;
	}
	
	public void setPlayerIcon(ImageIcon playerIcon) {
		this.playerIcon = playerIcon;
	}
	
	// checks the direction from which the character was attacked
	public void setHurtImage(boolean facingForward) { 
		if (facingForward)  // moves the player back in the correct direction and displays the correct image
			this.hurt = hurtLeft;
		 else 
			this.hurt = hurtRight;
	}
	
	// sets the corresponding width for the player based on if they are attacking or not
	public void setCorrespondingWidth() {
		if (ableAttack)
			width = attackWidth;
		else
			width = this.getOriginalWidth();
	}
	
	// move player and set direction and images of motion 
	public void motionPerform(int dx) {
		timeHit = 0;
		imageCount++;
		x += dx;	
		isMoving = true;
	}
	
	// when a player has stopped moving
	public void stopMovement() {
		// set player to stop attacking and stop moving - reset to original width too
		ableAttack = false; 
		isMoving = false;
		if (!hasGun)
			width = this.getOriginalWidth();
	}
	
	// set the players values accordingly for it to attack when called to attack in the Game Panel
	public void instantiateAttack() {
		if (!ableAttack) {
			resetIdle();
			ableAttack = true;
			isMoving = false; // cannot move or slide while attacking

			if (hasSword) 
				width = attackWidth; // reset to width of attack image
			else if (hasGun)  
				shootingBullets = true; 
		}
	}
	
	// set the players values accordingly for it to jump when called to jump in the Game Panel
	public void instantiateJump() {
		// only allow player to jump when on ground or platform 
		if (!canJump && !fall && (y >= maxDepth || GamePanel.getMap().relevantCollision(getBounds()))) { 
			jumpSpeed = 7;
			gravity = 0;
			canJump = true;
		}
	}

	// decrease player's health after being attacked and set hurt image
	public void wasAttacked() {
		health -= 10;
		wasHit(); // apply hit back value and hurt image
	}

	// set the players directions of motion to either left or right, and make it face either left or right
	public void setDirectionsOfMotion(boolean moveRight, boolean moveLeft, boolean facingRight, boolean facingLeft) {
		if (!ableAttack) {
			this.moveRight = moveRight;
			this.moveLeft = moveLeft;
			this.facingRight = facingRight;
			this.facingLeft = facingLeft;
		}
	}

	// reset the character back to its corresponding idle position
	public void resetIdle() {
		if (hasSword) {
			if (facingRight) {
				idle = idleRight;
				attacks = rightAttacks;
			} else if (facingLeft){
				idle = idleLeft;
				attacks = leftAttacks;
			}
		} else if (hasGun) {
			if (facingRight) 
				idle = idleGunRight;
			 else if (facingLeft) 
				idle = idleGunLeft;
		}
	}
	
	// if the player chooses to drop their gun
	public void dropGun() {
		if (hasGun) {
			hasGun = false;
			shootingBullets = false;
			ableAttack = false;
			hasSword = true;
			resetIdle();	
		}
	}

	public void render(Graphics g) {
		// display images, one by one, in movement sequence
		if (isMoving) {
			if (!ableAttack)
				g.drawImage(move[imageCount % walkSequenceLength].getImage(), x, y, width, height, null);
		}

		// display images in the sword attack sequence one after the other
		else if (ableAttack && hasSword) 
			g.drawImage(attacks[attackCount % attackSequenceLength].getImage(), x, y + attackHeight, width, height, null);	 

		// display the hurt image when hit
		else if (gotHit) 
			g.drawImage(hurt.getImage(), x + hurtPositioning, y, width + hurtWidth, height, null);

		// draw the idle image otherwise
		else 
			g.drawImage(idle.getImage(), x, y, width, height, null);

		// draw health bar 
		g.setColor(Color.red);
		g.fillRect(healthBarLocation, 0, 500, 20);
		g.setColor(Color.green);
		g.fillRect(healthBarLocation, 0, health, 20);
		g.drawImage(playerIcon.getImage(), iconLocation, 0, 75, 75, null);
	}

	public abstract int getOriginalWidth();
	public abstract int getOriginalHeight();
}