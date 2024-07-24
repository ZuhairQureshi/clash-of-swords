/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class creates and displays the main
	menu screen.
*/

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

@SuppressWarnings("serial")
public class MainMenu extends JPanel implements ActionListener {
	// declare buttons and images for main screen
	private JButton startGame, instructions;
	private ImageIcon background, swords, warrior, title;
	private static BufferedImage defaultImage;
	private static boolean gameStarted;
	
	// construct buttons and images and add actionlisteners
	public MainMenu() { 
		title = new ImageIcon("../images/Choosing Screen/title.png");
		
		startGame = new JButton(new ImageIcon("../images/Choosing Screen/startButton.jpg"));
		startGame.setBounds(450, 200, 400, 200);
		startGame.setOpaque(false);
		startGame.setContentAreaFilled(false);
		startGame.setBorderPainted(false);
		
		instructions = new JButton(new ImageIcon("../images/Choosing Screen/instructionsButton.png"));
		instructions.setBounds(450, 400, 400, 200);
		instructions.setOpaque(false);
		instructions.setContentAreaFilled(false);
		instructions.setBorderPainted(false);
		
		background = new ImageIcon("../images/Choosing Screen/background.jpg");
		swords = new ImageIcon("../images/Choosing Screen/swords.png");
		warrior = new ImageIcon("../images/Choosing Screen/knightLogo.png");
		this.setLayout(null);
		this.add(startGame);
		this.add(instructions);
		this.setBackground(Color.RED);
		
		try {
			defaultImage = ImageIO.read(new File("blackRect.jfif"));
		} catch (IOException e) {
			System.out.println("Error when loading image: " + e);
		}
		
		// add actionlisteners to buttons
		startGame.addActionListener(this);
		startGame.setFocusable(false);
		
		instructions.addActionListener(this);
		instructions.setFocusable(false);
	}
	
	// use buttons to show either choosing screen or instructions screen
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startGame) { 
			GameFrame.layout.show(GameFrame.c, "ChoiceScreen");
		}
		
		if (e.getSource() == instructions) { 
			GameFrame.layout.show(GameFrame.c, "Instructions");
		}
	}
	
	// draw all the images to the screen
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		g.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(title.getImage(), 100, 0, 1052, 176, null);
		g.drawImage(swords.getImage(), 100, 250, 300, 300, null);
		g.drawImage(warrior.getImage(), 800, 150, 400, 470, null);
	}
	
	// getters and setters for access to gameStart variable from other classes
	public static boolean getGameStarted() {
		return gameStarted;
	}
	public static void setGameStarted(boolean hasGameStarted) {
		gameStarted = hasGameStarted;
	}
	
	// if a flipped image does not load, use the default image instead (in GameLaunch)
	public static BufferedImage getDefaultImage() {
		return defaultImage;
	}
}
