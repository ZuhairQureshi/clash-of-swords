/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class extends the Player class
	to initialize all values for the Knight subclass. 
*/
import javax.swing.*;
public class Knight extends Player {
	// final variables for original dimensions
	private static final int ORIGINAL_WIDTH = 95;
	private static final int ORIGINAL_HEIGHT = 150;
	
	// constructor to initialize images and specific integer values
	public Knight(boolean isPlayer1) { 
		super(isPlayer1);
		rightMovements = new ImageIcon[6];
		leftMovements = new ImageIcon[6];
		gunWalkRight = new ImageIcon[6];
		gunWalkLeft = new ImageIcon[6];
		rightAttacks = new ImageIcon[8];
		leftAttacks = new ImageIcon[8];
		
		// initialize width, height and other integer values
		setWidth(95);
		setAttackWidth(195);
		setWidthWithGun(95);
		setHeight(150);
		setPlatBase(10);
		setPlatLimit(34);
		setMaxDepth(415);
		setWalkSequenceLength(6);
		setAttackSequenceLength(8);
		setPlayerIcon(ChoosingScreen.getKnightIcon());
		flippedIcon = new ImageIcon(GameLaunch.getFlippedImage(playerIcon));
		
		// populate image array
		for (int i = 0; i < 8; i++) {
			if (i < 6) {
				rightMovements[i] = new ImageIcon("../images/RogueKnight/run" + i + ".png");
				leftMovements[i] = new ImageIcon(GameLaunch.getFlippedImage(rightMovements[i]));
				gunWalkRight[i] = new ImageIcon("../images/RogueKnight/gunRun" + i + ".png");
				gunWalkLeft[i] = new ImageIcon(GameLaunch.getFlippedImage(gunWalkRight[i]));
			}
			
			rightAttacks[i] = new ImageIcon("../images/RogueKnight/attack" + (i+1) + ".png");
			leftAttacks[i] = new ImageIcon(GameLaunch.getFlippedImage(rightAttacks[i]));

		}
		
		// initialize all idle and hurt images
		idleRight = new ImageIcon("../images/RogueKnight/idle.png");
		idleLeft = new ImageIcon(GameLaunch.getFlippedImage(idleRight));
		idleGunRight = new ImageIcon("../images/RogueKnight/idleGun.png");
		idleGunLeft = new ImageIcon(GameLaunch.getFlippedImage(idleGunRight));

		hurtRight = new ImageIcon("../images/RogueKnight/hurt.png");
		hurtLeft = new ImageIcon(GameLaunch.getFlippedImage(hurtRight));
		
		// set x and y values based on if player 1 or player 2
		if (isPlayer1)
			setPlayer1Positions();
		else
			setPlayer2Positions();
	}

	// getters for original width and height 
	public int getOriginalWidth() {
		return ORIGINAL_WIDTH;
	}

	public int getOriginalHeight() {
		return ORIGINAL_HEIGHT;
	}

}
