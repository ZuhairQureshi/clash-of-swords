/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class is responsible for handling the appearance
	of power ups and their acquirement by players in the game. 
*/

import java.awt.*;
import javax.swing.ImageIcon;

public abstract class PowerUp {
	// declare all variables relating to the power up box dimensions
	private int x;
	private int y;
	private int width;
	private int height;
	private int fallSpeed;
	private boolean visible;
	private ImageIcon powerUpImg;
	private Map field;
	
	public PowerUp(Map field) { // constructor for initializing the power up box dimensions
		this.field = field;
		width = 65;
		height = 65;
		x = (int)(Math.random() * (field.getFloorLength() - width)) + field.getFloorStartValue();
		y = 0;
		fallSpeed = 5;
		visible = true;
	}
	
	// create a boundaries box 
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	// getters and setters for visibility - important when drawing images
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	// power up falls to the floor or onto a platform 
	public void fall(){
		if (y < Map.getPlatFloor() &&!field.relevantCollision(getBounds())) {
			y += fallSpeed;
		}
	}
	
	// for subclasses to set the respective power up images
	public void setPowerUpImg(ImageIcon powerUpImg) {
		this.powerUpImg = powerUpImg;
	}
	
	// when a power up appears to the screen
	public int powerUpVisible(int duration) {
		duration++;
		
		// allow power up to stay for 200 timer ticks, then disappear
		if (duration >= 200) {
			setVisible(false);
			duration = 0;
		}
		
		return duration;
	}
	
	public void render(Graphics g) {
		if (isVisible())
			g.drawImage(powerUpImg.getImage(), x, y, width, height, null);
	}
	
	// each power up performs different functions when received 
	public abstract void received(Player player);
	
}