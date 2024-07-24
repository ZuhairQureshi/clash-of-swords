/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class extends the Player class
	to initialize all values for the Adventurer subclass. 
*/

import javax.swing.ImageIcon;

public class Adventurer extends Player{
	// final variables for original dimensions
	private static final int ORIGINAL_WIDTH = 95;
	private static final int ORIGINAL_HEIGHT = 150;
	
	public Adventurer(boolean isPlayer1) { // constructor of Adventurer
		// initialize all values and image array 
		super(isPlayer1);
		rightMovements = new ImageIcon[6];
		leftMovements = new ImageIcon[6];
		gunWalkRight = new ImageIcon[6];
		gunWalkLeft = new ImageIcon[6];
		rightAttacks = new ImageIcon[5];
		leftAttacks = new ImageIcon[5];
		
		// initialize integer values specific to Adventurer class
		setWidth(95);
		setAttackWidth(195);
		setWidthWithGun(95);
		setHeight(150);
		setPlatBase(10);
		setPlatLimit(34);
		setMaxDepth(415);
		setWalkSequenceLength(6);
		setAttackSequenceLength(5);
		setPlayerIcon(ChoosingScreen.getAdventurerIcon());
		flippedIcon = new ImageIcon(GameLaunch.getFlippedImage(playerIcon));

		// populate the image arrays
		for (int i = 0; i < 6; i++) {
			rightMovements[i] = new ImageIcon("../images/adventurer/Individual Sprites/adventurer-run-0" + i + ".png");
			leftMovements[i] = new ImageIcon(GameLaunch.getFlippedImage(rightMovements[i]));
			gunWalkRight[i] = new ImageIcon("../images/adventurer/Individual Sprites/adventurerGunRight" + (i+1) + ".png");
			gunWalkLeft[i] = new ImageIcon(GameLaunch.getFlippedImage(gunWalkRight[i]));
			
			if (i < 5) {
				rightAttacks[i] = new ImageIcon("../images/adventurer/Individual Sprites/adventurer-attack1-0" + i + ".png");
				leftAttacks[i] = new ImageIcon(GameLaunch.getFlippedImage(rightAttacks[i]));
			}
		}
		
		// adventurer is the default Player 2 - initialize all images to face left
		move = leftMovements;
		attacks = leftAttacks;

		// initialize all the starting image sequences  
		idleRight = new ImageIcon("../images/adventurer/Individual Sprites/adventurer-idle-2-00.png");
		idleLeft = new ImageIcon(GameLaunch.getFlippedImage(idleRight));
		idleGunLeft = new ImageIcon("../images/adventurer/Individual Sprites/adventurerGunLeft3.png");
		idleGunRight = new ImageIcon(GameLaunch.getFlippedImage(idleGunLeft));
		idle = idleLeft;
		
		hurtRight = new ImageIcon("../images/adventurer/Individual Sprites/adventurer-hurtright-00.png");
		hurtLeft = new ImageIcon(GameLaunch.getFlippedImage(hurtRight));
		hurt = hurtLeft;
		
		// set x and y values based on if player 1 or player 2
		if (isPlayer1)
			setPlayer1Positions();
		else
			setPlayer2Positions();
		
	}
	// getters to return width and height of the original image when reverting back to idle image / movement sequence
	public int getOriginalWidth() { 
		return ORIGINAL_WIDTH;
	}
	
	public int getOriginalHeight() {
		return ORIGINAL_HEIGHT;
	}
}