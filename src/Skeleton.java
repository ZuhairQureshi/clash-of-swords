/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class extends the Player class
	to initialize all values for the Skeleton subclass. 
*/
import java.awt.Rectangle;
import javax.swing.*;

public class Skeleton extends Player {
	
	private static final int ORIGINAL_WIDTH = 80;
	private static final int ORIGINAL_HEIGHT = 130;

	// constructor to initialize image and Skeleton-specific integers
	public Skeleton(boolean isPlayer1) { 
		super(isPlayer1);
		playerIcon = ChoosingScreen.scaleImage("../images/skeleton/skeletonIcon.png", 100, 100);
		rightMovements = new ImageIcon[8];
		leftMovements = new ImageIcon[8];
		
		leftAttacks = new ImageIcon[8];
		attacks = new ImageIcon[8];
		rightAttacks = new ImageIcon[8];
		
		gunWalkRight = new ImageIcon[8];
		gunWalkLeft = new ImageIcon[8];
		
		// initialize all integer values for images specific to the Skeleton subclass
		setWidth(80);
		setHeight(130);
		setPlatBase(5);
		setPlatLimit(44);
		setMaxDepth(420);
		setWidthWithGun(105);
		setAttackWidth(130);
		setHurtWidth(100);
		setAttackHeight(8);
		setWalkSequenceLength(8);
		setAttackSequenceLength(8);
		setHurtPositioning(-50);
		setPlayerIcon(ChoosingScreen.getSkeletonIcon());
		flippedIcon = new ImageIcon(GameLaunch.getFlippedImage(playerIcon));

		// load all image arrays with the appropriate image sequence
		for (int i = 0; i < 8; i++) {
			rightMovements[i] = new ImageIcon("../images/skeleton/right_"+(i + 1) + ".png");
			leftMovements[i] = new ImageIcon(GameLaunch.getFlippedImage(rightMovements[i]));
			gunWalkRight[i] = new ImageIcon("../images/skeleton/skeletonGunRight" + (i+1) + ".png");
			gunWalkLeft[i] = new ImageIcon(GameLaunch.getFlippedImage(gunWalkRight[i]));
			leftAttacks[i] = new ImageIcon("../images/skeleton/hit_" + (i + 1) + ".png");
			rightAttacks[i] = new ImageIcon(GameLaunch.getFlippedImage(leftAttacks[i]));
		}
		
		// skeleton is the default player 1 - set all sequences (hurt, idle, walking) by default to facing right
		move = rightMovements;
		attacks = rightAttacks;

		// hurt character images
		hurtRight = new ImageIcon("../images/skeleton/hurt_right_2.png");
		hurtLeft = new ImageIcon(GameLaunch.getFlippedImage(hurtRight));
		hurt = hurtRight;

		// idle character images
		idleRight = new ImageIcon("../images/skeleton/idle_right.png");
		idleLeft = new ImageIcon(GameLaunch.getFlippedImage(idleRight));
		idle = idleRight;
		idleGunRight = new ImageIcon("../images/skeleton/skeletonGunRight8.png");
		idleGunLeft = new ImageIcon(GameLaunch.getFlippedImage(idleGunRight));		
		setRight(true);
		
		// check if skeleton has been chosen as Player 1 or Player 2
		if (isPlayer1)
			setPlayer1Positions();
		else
			setPlayer2Positions();
	}
	
	// skeleton y value must be slightly adjusted for platform collision detection
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY() + 20, getWidth(), getHeight());
	}
	
	// getters for original width and height final variables
	public int getOriginalWidth() {
		return ORIGINAL_WIDTH;
	}
	
	public int getOriginalHeight() {
		return ORIGINAL_HEIGHT;
	}
}