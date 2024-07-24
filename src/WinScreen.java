/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class allows the player to see who won
	the round after one player defeats the other, then return to menu.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class WinScreen extends JPanel implements ActionListener {
	// declare variables
	private JButton returnButton;
	private ImageIcon background;
	private static boolean roundsComplete;
	private static ImageIcon winner, loser;
	private ImageIcon player1winDisplay, player2winDisplay, player1RoundWin, player2RoundWin, player1winCount, player2winCount;

	public WinScreen() { // construct the return button and background image 
		player1winDisplay = new ImageIcon("../images/winscreen/play1win.png");
		player2winDisplay = new ImageIcon("../images/winscreen/play2win.png");
		player1winCount = new ImageIcon("../images/winscreen/play1.png");
		player2winCount = new ImageIcon("../images/winscreen/play2.png");
		player1RoundWin = new ImageIcon("../images/winscreen/play1winRound.png");
		player2RoundWin = new ImageIcon("../images/winscreen/play2winRound.png");
		returnButton = new JButton(new ImageIcon("../images/winscreen/nextButton.png"));
		
		// set the location of the return button and specify formatting
		returnButton.setBounds(1100, 20, 167, 61);
		returnButton.setOpaque(false);
		returnButton.setContentAreaFilled(false);
		returnButton.setBorderPainted(false);
		GamePanel.setTotalRounds(1);
 
		// add a default background and no layout manager
		this.setBackground(Color.red);
		this.setLayout(null);
		this.add(returnButton);
		
		returnButton.addActionListener(this);
		returnButton.setFocusable(false);

		background = new ImageIcon("../images/winscreen/returnBackground.jpg");
	}
	
	// get and set if rounds have been completed
	public boolean roundsAreComplete() {
		return roundsComplete;
	}
	
	public static void setRoundsComplete(boolean roundsComplete) {
		WinScreen.roundsComplete = roundsComplete;
	}
	
	// set winner and loser images for paintComponent
	public static void setWinnerAndLoser(ImageIcon theWinner, ImageIcon theLoser) { 
		winner = theWinner;
		loser = theLoser;
	}
	
	public void actionPerformed(ActionEvent e) {
		// return to the main menu when the return button is pressed if rounds are finished
		if (e.getSource() == returnButton) {  
			if (roundsComplete) {
				roundsComplete = false;
				GameFrame.layout.show(GameFrame.c, "Main");
				GamePanel.setPlayer1Wins(0);
				GamePanel.setPlayer2Wins(0);
				GamePanel.setTotalRounds(1);
				
				// otherwise continue to the next round
			} else {
				GameFrame.layout.show(GameFrame.c, "Game");
				MainMenu.setGameStarted(true);
				GameFrame.gamePanel.setFocusable(true);
				GameFrame.gamePanel.requestFocusInWindow();
			}
		}
	}

	// display the enlargened winner and a shrunken loser 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.blue);
		g.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(winner.getImage(), 200, 200, 240, 390, null);
		g.drawImage(loser.getImage(), 800, 400, 95, 150, null);
		
		g.setFont(new Font("SansSerif", Font.BOLD, 40));
		
		// if the game has been completed and final winner is determined
		if (roundsComplete) {
			if (GamePanel.getPlayer1Wins() > GamePanel.getPlayer2Wins()) { // if player 1 wins, display it
				g.drawImage(player1winDisplay.getImage(), 300, 0, 691, 255, null);
			} else { // else player 2 wins
				g.drawImage(player2winDisplay.getImage(), 300, 0, 691, 255, null);
			}
		}
		// if the game has not been completed and there are more rounds to play
		else { 
			// display the winner of the round
			if (GamePanel.player1Won()) { 
				g.drawImage(player1RoundWin.getImage(), 300, 50, 691, 255, null);

			} else {
				g.drawImage(player2RoundWin.getImage(), 300, 50, 691, 255, null);
			}			
		}
		
		// keep a win tracker to see which player is in the lead
		if (GamePanel.player1Won()) { // if player 1 wins, display p1 score on the right and p2 on the left
			g.drawImage(player1winCount.getImage(), 0, 125, 150, 60, null);
			g.drawString("" + GamePanel.getPlayer1Wins(), 150, 170);
			g.drawImage(player2winCount.getImage(), 1050, 125, 150, 60, null);
			g.drawString("" + GamePanel.getPlayer2Wins(), 1200, 170);
		}
		
		// otherwise, put p2 score on the right and p1 on the left
		else { 
			g.drawImage(player2winCount.getImage(), 0, 125, 150, 60, null);
			g.drawString("" + GamePanel.getPlayer2Wins(), 150, 170);
			g.drawImage(player1winCount.getImage(), 1050, 125, 150, 60, null);
			g.drawString("" + GamePanel.getPlayer1Wins(), 1200, 170);
		}		
		repaint();
	}
}
