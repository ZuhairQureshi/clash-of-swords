/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class extends the Player class
	to initialize all values for the Bandit subclass. 
*/
import javax.swing.ImageIcon;

public class Bandit extends Player {
	private static final int ORIGINAL_WIDTH = 95;
	private static final int ORIGINAL_HEIGHT = 150;
	
	public Bandit(boolean isPlayer1) { // constructor to initialize images / integer values
		super(isPlayer1);
		rightMovements = new ImageIcon[8];
		leftMovements = new ImageIcon[8];
		gunWalkRight = new ImageIcon[8];
		gunWalkLeft = new ImageIcon[8];
		rightAttacks = new ImageIcon[8];
		leftAttacks = new ImageIcon[8];
		
		// initialize integer values specific to Bandit class
		setWidth(95);
		setAttackWidth(195);
		setWidthWithGun(95);
		setHeight(150);
		setPlatBase(10);
		setPlatLimit(34);
		setMaxDepth(415);
		setWalkSequenceLength(8);
		setAttackSequenceLength(8);
		setHurtWidth(100);
		setAttackHeight(0);
		setPlayerIcon(ChoosingScreen.getBanditIcon());
		flippedIcon = new ImageIcon(GameLaunch.getFlippedImage(playerIcon));

		//populate the image arrays
		for (int i = 0; i < 8; i++) {
			leftMovements[i] = new ImageIcon("../images/Heavy Bandit/Run/HeavyBandit_Run_" + i + ".png");
			rightMovements[i] = new ImageIcon(GameLaunch.getFlippedImage(leftMovements[i]));
			leftAttacks[i] = new ImageIcon("../images/Heavy Bandit/Attack/HeavyBandit_Attack_" + i + ".png");
			rightAttacks[i] = new ImageIcon(GameLaunch.getFlippedImage(leftAttacks[i]));
			gunWalkLeft[i]= new ImageIcon("../images/Heavy Bandit/Run/gunWalk" + i + ".png");
			gunWalkRight[i] = new ImageIcon(GameLaunch.getFlippedImage(gunWalkLeft[i]));
		}

		move = leftMovements;
		attacks = leftAttacks;
		
		// initialize all idle and hurt images
		idleLeft = new ImageIcon("../images/Heavy Bandit/Idle/HeavyBandit_Idle_0.png");
		idleRight = new ImageIcon(GameLaunch.getFlippedImage(idleLeft));
		idleGunLeft = new ImageIcon("../images/Heavy Bandit/Run/gunIdle.png");
		idleGunRight = new ImageIcon(GameLaunch.getFlippedImage(idleGunLeft));
		idle = idleLeft;

		hurtLeft = new ImageIcon("../images/Heavy Bandit/Hurt/HeavyBandit_Hurt_1.png");
		hurtRight = new ImageIcon(GameLaunch.getFlippedImage(hurtLeft));
		hurt = hurtLeft;

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
