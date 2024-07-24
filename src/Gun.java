/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class is the gun class, a type of power
	up that extends from the PowerUp class and allows the player to
	shoot bullets.
*/

import javax.swing.ImageIcon;

public class Gun extends PowerUp {
	// constructor to set gun crate image
	public Gun(Map field) { 
		super(field);
		setPowerUpImg(new ImageIcon("../images/powerups/gunCrate.png"));	
	}

	// when a player picks up a gun: remove the box, resize and replace the player's sword with a gun
	public void received(Player player) {
		setVisible(false);
		player.setWithSword(false);
		player.setWithGun(true);
		player.resetIdle();
		player.setWidth(player.getWidthWithGun());
	}
}
