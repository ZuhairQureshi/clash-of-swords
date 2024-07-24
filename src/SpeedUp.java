/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class represents the speed up power up
	which allows the player to move faster when 
	received . */
import javax.swing.*;

public class SpeedUp extends PowerUp {
	private ImageIcon speedCrate;
	
	// constructor to set speed up crate image
	public SpeedUp(Map field) {
		super(field);
		speedCrate = new ImageIcon("../images/powerups/speed.png");
		setPowerUpImg(speedCrate);
	}
	
	// allow player to move faster and make disappear once picked up
	public void received(Player player) {
		setVisible(false);
		player.setDX(25);
		player.setWithSpeedUp(true);
	}
}
