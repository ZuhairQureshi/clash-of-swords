/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class extends the Map class
	and is responsible for the Dark Stage in the game. 
*/
import javax.swing.*;
import java.awt.*;

public class DarkStage extends Map {
	// declare final variables
	private static final int floorLength = 1250;
	private static final int floorStartValue = 0;
	
	public DarkStage() { // construct background
		super();
		setBackground(new ImageIcon("../images/maps/darkStage.png"));
	}
	
	public void executeMapFeature(Player player) {}

	// getter methods
	public int getFloorLength() {
		return floorLength;
	}

	public int getFloorStartValue() {
		return floorStartValue;
	}

	// default return values - this class has no platforms
	public boolean yAbovePlatform(Player player) {
		return false;
	}

	public boolean relevantCollision(Rectangle hitbox) {
		return false;
	}
}
