/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class creates the game frame, where
	all panels are added.
 */
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	// create all panels, layout and container
	private MainMenu main;
	private WinScreen winScreen;
	private ChoosingScreen choiceScreen;
	private InstructionsScreen instructions;
	static GamePanel gamePanel;    
	static CardLayout layout;
	static Container c;

	// construct Frame
	public GameFrame(String title) {
		super(title);
		
		// create the container
		c = this.getContentPane();
		layout = new CardLayout();
		c.setLayout(layout);
		
		// instantiate all panels
		gamePanel = new GamePanel();
		main = new MainMenu();
		winScreen = new WinScreen();
		choiceScreen = new ChoosingScreen();
		instructions = new InstructionsScreen();
		
		// add all panels to container
		c.add("Main", main);
		c.add("Instructions", instructions);
		c.add("Game", gamePanel);
		c.add("WinScreen", winScreen);
		c.add("ChoiceScreen", choiceScreen);
		
		// set screen size and visibility
		this.setVisible(true);
		this.setSize(1300,700);
	}
}
