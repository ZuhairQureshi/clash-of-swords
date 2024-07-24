/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class is responsible for the Final Destination map in 
	the background and its cliffs at the edges. 
 */ 
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class FinalDestination extends Map {
	// declare variables, as well as final variables
	private Platform floor;
	private static final int floorLength = 1150;
	private static final int floorStartValue = 80;

	// construct and set background and platform floor
	public FinalDestination() {
		super();
		setBackground(new ImageIcon("../images/maps/finDes.jpg"));
		floor = new Platform(floorStartValue, 425, floorLength, 2);
	}

	public void executeMapFeature(Player player) {
		// if the player is not already falling off the platform
		if (player.isOnGround() && !player.isFalling()) {
			// if the player moves off the platform
			if (player.getX() < floor.getX() || player.getX() + player.getWidth() > floor.getX() + floor.getWidth()) {
				// fall down
				
				player.setOnGround(false); 
				if (player.getJumpSpeed() >= 6)
					player.setJump(false);
				
				player.setFalling(true);
			}
			// the player will remain on the map if it is above the platform floor
			if (player.getY() + player.getHeight() < floor.getY())
				player.setOnGround(true);
		}
	}

	//default return values (there are no platforms above ground) 
	public boolean yAbovePlatform(Player player) {
		return false;
	}

	public boolean relevantCollision(Rectangle hitbox) {
		return false;
	}

	// getter methods
	public int getFloorLength() {
		return floorLength;
	}

	public int getFloorStartValue() {
		return floorStartValue;
	}
}