/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class is responsible for the Field map in 
	the background and its corresponding platforms. 
*/

import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Field extends Map {
	// create platforms and the floor boundaries
	private static Platform platform1;
	private static Platform platform2;
	private static final int floorLength = 1200;
	private static final int floorStartValue = 0;
	
	// constructor for creating platforms and background image
	public Field() {
		super();
		setBackground(new ImageIcon("../images/maps/battlefield1.png"));
		platform1 = new Platform(80, 340, 300, 5);
		platform2 = new Platform(900, 340, 270, 5); 
	} 
	
	// use platform features to check when player lands on one 
	public void executeMapFeature(Player player) {
		// check if the players X-value is within the X-values of either of the two platforms, and if so set X on platform to true
		player.setXOnPlatform((player.getX() >= platform1.getX() && player.getX() <= platform1.getX() + platform1.getWidth()) || (player.getX() >= platform2.getX() && player.getX() <= platform2.getX() + platform2.getWidth()));
		// check if the player's bottom most pixel, plus the player's specific "platform limit value" are on or above both platform's y-value (which is the same) plus the specific platform base value for the player, and if so set Y on platform to true
		player.setYOnPlatform((player.getY() + player.getHeight() + player.getPlatLimit()) <= platform1.getY() + player.getPlatBase() && (player.getY() + player.getHeight() + player.getPlatLimit()) >= platform1.getY()-10);
		player.setOnPlatform(player.getXOnPlatform() && player.getYOnPlatform()); 
	}
	
	// returns whether or not the player is above the platform
	public boolean yAbovePlatform(Player player) {
		return player.getYOnPlatform() || (player.getY() + player.getHeight() + player.getPlatLimit() <= platform1.getY()- player.getPlatBase() && player.getXOnPlatform());
		//		checks if player y-value is on platform or if the player's feet plus its platform limit value are above the platform - and if the player's x-value also corresponds to the platform
	}
	
	// checks if any power up boxes are touching platforms
	public boolean relevantCollision(Rectangle hitbox) {
		return (hitbox.intersects(platform1.getBounds()) || hitbox.intersects(platform2.getBounds())); 
	}
	
	// getter methods
	public int getFloorLength() {
		return floorLength;
	}
	
	public int getFloorStartValue() {
		return floorStartValue;
	}
	
}