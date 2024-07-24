/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class is for healing players, a type of power
	up that extends from the PowerUp class and allows the player to
	regenerate up to 250 hitpoints of health.
*/

import javax.swing.ImageIcon;

public class HealUp extends PowerUp {
	// constructor to set heal up image
	public HealUp(Map field) { 
		super(field);
		setPowerUpImg(new ImageIcon("../images/powerups/healCrate.png"));	
	}

	// when player picks up a heal item box
	public void received(Player player) {
		setVisible(false);
		// restore player health by 250 points - max of 500 hp
		if (player.getHealth() >= 250) {
			player.setHealth(500);
		} else {
			player.setHealth(player.getHealth() + 250);
		}
	}
}
