/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class is the instructions screen
	of the game that instructs players on how to play the 
	game and control the players */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class InstructionsScreen extends JPanel implements ActionListener {
	// declare return button and image
	private JButton returnButton;
	private ImageIcon instructionsImage;

	public InstructionsScreen() { 
		// initialize button and style
		returnButton = new JButton(new ImageIcon("../images/Choosing Screen/backToMenu.png"));
		returnButton.addActionListener(this);
		returnButton.setFocusable(false);
		returnButton.setOpaque(false);
		returnButton.setContentAreaFilled(false);
		returnButton.setBorderPainted(false);
		
		// initialize instructions image
		instructionsImage = new ImageIcon("../images/instructions/instructionsImage.png");
		this.add(returnButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// return to main if return is pressed
		if (e.getSource() == returnButton) {
			GameFrame.layout.show(GameFrame.c, "Main");
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paint instructions image over the background
		g.drawImage(instructionsImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
		returnButton.setBounds(0,0, 200, 65);
		repaint();
	}
}
