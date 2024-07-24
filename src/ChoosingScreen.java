/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class is the choosing screen
	from where users can choose their character, map
	and the number of rounds that they want to play. */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ChoosingScreen extends JPanel implements ActionListener {
	// declare all variables - images and buttons
	private static ImageIcon skeletonIcon, adventurerIcon, banditIcon, knightIcon, background, player1choose, player2choose, mapsLabel, rounds;
	private ImageIcon[] roundTracker;
	private Image image;
	private JButton p1Skeleton, p1Adventurer, p1Bandit, p1Knight;
	private JButton p2Skeleton, p2Adventurer, p2Bandit, p2Knight;
	private JButton fieldButton, finalDestinationButton, darkStageButton;
	private int roundTrack;
	private JButton moreRounds, lessRounds;
	private boolean choseP1Skeleton, choseP1Adventurer, choseP1Bandit, choseP1Knight;
	private boolean choseP2Skeleton, choseP2Adventurer, choseP2Bandit, choseP2Knight;
	private boolean choseField, choseFinalDestination, choseDarkStage;
	private JButton returnToMenu, beginGame;
	private Player skeleton1, adventurer1, bandit1, knight1, skeleton2, adventurer2, bandit2, knight2;
	private Map field, finalDestination, darkStage;
	
	public ChoosingScreen() { // begin constructor
		
		// construct all images first
		skeletonIcon = new ImageIcon("../images/skeleton/skeletonIcon.png");
		roundTracker = new ImageIcon[5];
		for (int i = 0; i <= 4; i++) // construct array of number images for round chooser
			roundTracker[i] = new ImageIcon("../images/Numbers/"+(i*2 + 1)+".png");
		
		adventurerIcon = new ImageIcon("../images/Adventurer/adventurerIcon.png");
		image = adventurerIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		adventurerIcon = new ImageIcon(image);
		
		banditIcon = new ImageIcon("../images/Heavy Bandit/BanditIcon.png");
		image = banditIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		banditIcon = new ImageIcon(image);
		
		background = new ImageIcon("../images/Choosing Screen/background.jpg");
		player1choose = new ImageIcon("../images/Choosing Screen/choosePlayer1.png");
		player2choose = new ImageIcon("../images/Choosing Screen/choosePlayer2.png");
		mapsLabel = new ImageIcon("../images/Choosing Screen/mapsLabel.png");
		rounds = new ImageIcon("../images/Choosing Screen/round.png");
		
		knightIcon = new ImageIcon("../images/RogueKnight/knightIcon.png");
		image = knightIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		knightIcon = new ImageIcon(image);
		
		// construct all buttons that allow players to choose their map, character and number of rounds
		fieldButton = new JButton(scaleImage("../images/maps/battlefield1.png", 200, 100));
		addAction(fieldButton);
		
		finalDestinationButton = new JButton(scaleImage("../images/maps/finDes.jpg", 200, 100));
		addAction(finalDestinationButton);
		
		darkStageButton = new JButton(scaleImage("../images/maps/darkStage.png", 200, 100));
		addAction(darkStageButton);
		
		p1Skeleton = new JButton(skeletonIcon);
		addAction(p1Skeleton);
		
		p1Adventurer = new JButton(adventurerIcon);
		addAction(p1Adventurer);
		
		p1Bandit = new JButton(banditIcon);
		addAction(p1Bandit);
		
		p1Knight = new JButton(knightIcon);
		addAction(p1Knight);
		
		p2Skeleton = new JButton(skeletonIcon);
		addAction(p2Skeleton);
		
		p2Adventurer = new JButton(adventurerIcon);
		addAction(p2Adventurer);
		
		p2Bandit = new JButton(banditIcon);
		addAction(p2Bandit);
		
		p2Knight = new JButton(knightIcon);
		addAction(p2Knight);
		
		// return to menu and start game buttons
		returnToMenu = new JButton(new ImageIcon("../images/Choosing Screen/backToMenu.png"));
		addAction(returnToMenu);
		
		beginGame = new JButton(new ImageIcon("../images/Choosing Screen/startFromChoiceScreen.png"));
		addAction(beginGame);
		
		moreRounds = new JButton(scaleImage("../images/Numbers/upArrow.png", 75, 75));
		addAction(moreRounds);
		
		lessRounds = new JButton(scaleImage("../images/Numbers/downArrow.png", 75, 75));
		addAction(lessRounds);
		
		skeleton1 = new Skeleton(true);
		adventurer1 = new Adventurer(true);
		bandit1 = new Bandit(true);
		knight1 = new Knight(true);
		
		skeleton2 = new Skeleton(false);
		adventurer2 = new Adventurer(false);
		bandit2 = new Bandit(false);
		knight2 = new Knight(false);
		
		field = new Field();
		finalDestination = new FinalDestination();
		darkStage = new DarkStage();
		
		this.setBackground(Color.red);
		this.setLayout(null);
		
		// add all buttons to panel
		this.add(p1Skeleton);
		this.add(p1Adventurer);
		this.add(p1Bandit);
		this.add(p1Knight);

		this.add(p2Skeleton);
		this.add(p2Adventurer);
		this.add(p2Bandit);
		this.add(p2Knight);
		
		this.add(fieldButton);
		this.add(finalDestinationButton);
		this.add(darkStageButton);
		
		this.add(moreRounds);
		this.add(lessRounds);
		
		this.add(returnToMenu);
		this.add(beginGame);
	}
	
	// method for scaling images
	public static ImageIcon scaleImage(String fileName, int width, int height) {
		ImageIcon icon = new ImageIcon(fileName);
		Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		return icon;
	}
	
	// method to simplify adding action and removing focus
	public void addAction(JButton button) {
		button.addActionListener(this);
		button.setFocusable(true);
	}
	
	// method that assigns Player 1 and tracks which player was chosen
	public void chosenPlayer1(Player player, boolean isSkeleton, boolean isAdventurer, boolean isBandit, boolean isKnight) {
		GamePanel.setPlayer1(player);
		choseP1Skeleton = isSkeleton;
		choseP1Adventurer = isAdventurer;
		choseP1Bandit = isBandit;
		choseP1Knight = isKnight;
	}

	// method that sets Player 2 and tracks the chosen character
	public void chosenPlayer2(Player player, boolean isSkeleton, boolean isAdventurer, boolean isBandit, boolean isKnight) {
		GamePanel.setPlayer2(player);
		choseP2Skeleton = isSkeleton;
		choseP2Adventurer = isAdventurer;
		choseP2Bandit = isBandit;
		choseP2Knight = isKnight;
	}
	
	public void chosenMap(Map map, boolean isMap1, boolean isMap2, boolean isMap3) {
		GamePanel.setMap(map);
		choseField = isMap1;
		choseFinalDestination = isMap2;
		choseDarkStage = isMap3;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == returnToMenu) 
			GameFrame.layout.show(GameFrame.c, "Main");
		
		// create object of chosen player in GamePanel
		if (e.getSource() == p1Skeleton) 
			chosenPlayer1(skeleton1, true, false, false, false);
		
		if (e.getSource() == p1Adventurer) 
			chosenPlayer1(adventurer1, false, true, false, false);
		
		if (e.getSource() == p1Bandit) 
			chosenPlayer1(bandit1, false, false, true, false);
		
		if (e.getSource() == p1Knight) 
			chosenPlayer1(knight1, false, false, false, true);
		
		if (e.getSource() == p2Skeleton) 
			chosenPlayer2(skeleton2, true, false, false, false);
		
		if (e.getSource() == p2Adventurer) 
			chosenPlayer2(adventurer2, false, true, false, false);
		
		if (e.getSource() == p2Bandit) 
			chosenPlayer2(bandit2, false, false, true, false);
		
		if (e.getSource() == p2Knight) 
			chosenPlayer2(knight2, false, false, false, true);
		
		if (e.getSource() == beginGame) { // if game button is pressed
			roundTrack = 0;
			// start game in GamePanel class
			MainMenu.setGameStarted(true);
			// add players to player array list and clear any existing values inside
			GamePanel.clearPlayerList();
			GamePanel.addToPlayerList(GamePanel.getPlayer1());
			GamePanel.addToPlayerList(GamePanel.getPlayer2());
			GameFrame.layout.show(GameFrame.c, "Game");
			GameFrame.gamePanel.setFocusable(true);
			GameFrame.gamePanel.requestFocusInWindow();
		}
		
		// set object of chosen map
		if (e.getSource() == fieldButton) 
			chosenMap(field, true, false, false);
		
		if (e.getSource() == finalDestinationButton) 
			chosenMap(finalDestination, false, true, false);
		
		if (e.getSource() == darkStageButton) 
			chosenMap(darkStage, false, false, true);
		
		// to increase the number of rounds
		if (e.getSource() == moreRounds){
			if (roundTrack < 4){ // rounds cannot be greater than 9 (or 4*2 + 1)
				roundTrack++;
				GamePanel.setTotalRounds(roundTrack*2 + 1);
			}
		 }
		 
		// to decrease the number of rounds to play
		 if (e.getSource() == lessRounds){
		 	if (roundTrack > 0){ // rounds cannot go below 1
		 		roundTrack--;
		 		GamePanel.setTotalRounds(roundTrack*2 + 1); 
		 	}
		 }
	}
	
	// draw to the screen
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.blue);
		
		// draw all background images
		g.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(player1choose.getImage(), 0, 100, 623, 121, null);
		g.drawImage(player2choose.getImage(), 0, 350, 623, 121, null);
		g.drawImage(roundTracker[roundTrack].getImage(), 1100, 400, 75, 75, null);
		g.drawImage(mapsLabel.getImage(), 730, 120, 349, 102, null);
		g.drawImage(rounds.getImage(), 1030, 250, 225, 63, null);
		
		// draw rectangles behind character icon to indicate which character / map is chosen
		if (choseP1Skeleton) 
			g.fillRect(5, 220, 110, 110);
		
		if (choseP1Adventurer) 
			g.fillRect(175, 220, 110, 110);
	
		if (choseP1Bandit) 
			g.fillRect(345, 220, 110, 110);
		
		if (choseP1Knight) 
			g.fillRect(515, 220, 110, 110);
		
		if (choseP2Skeleton) 
			g.fillRect(5, 495, 110, 110);
		
		if (choseP2Adventurer) 
			g.fillRect(175, 495, 110, 110);

		if (choseP2Bandit) 
			g.fillRect(345, 495, 110, 110);
		
		if (choseP2Knight) 
			g.fillRect(515, 495, 110, 110);
		
		if (choseField) 
			g.fillRect(795, 495, 210, 110);

		if (choseFinalDestination) 
			g.fillRect(795, 345, 210, 110);
		
		if (choseDarkStage) 
			g.fillRect(795, 195, 210, 110);
		
		// set button locations
		p1Skeleton.setBounds(10, 225, 100, 100);
		p1Adventurer.setBounds(180, 225, 100, 100);
		p1Bandit.setBounds(350, 225, 100, 100);
		p1Knight.setBounds(520, 225, 100, 100);
		p2Skeleton.setBounds(10, 500, 100, 100);
		p2Adventurer.setBounds(180, 500, 100, 100);
		p2Bandit.setBounds(350, 500, 100, 100);
		p2Knight.setBounds(520, 500, 100, 100);
		returnToMenu.setBounds(20, 10, 200, 65);
		beginGame.setBounds(1000, 10, 200, 70);	
		fieldButton.setBounds(800, 500, 200, 100);
		finalDestinationButton.setBounds(800, 350, 200, 100);
		darkStageButton.setBounds(800, 200, 200, 100);
		
		moreRounds.setBounds(1100,300, 75, 75);
		lessRounds.setBounds(1100, 500, 75, 75);
		
		repaint();
	}
	// getters for player icons
	public static ImageIcon getSkeletonIcon() {
		return scaleImage("../images/skeleton/skeletonIcon.png", 75, 75);
	}
	
	public static ImageIcon getAdventurerIcon() {
		return scaleImage("../images/Adventurer/adventurerIcon.png", 75, 75);
	}
	
	public static ImageIcon getBanditIcon() {
		return scaleImage("../images/Heavy Bandit/BanditIcon.png", 75, 75);
	}
	
	public static ImageIcon getKnightIcon() {
		return scaleImage("../images/RogueKnight/knightIcon.png", 75, 75);
	}
}
