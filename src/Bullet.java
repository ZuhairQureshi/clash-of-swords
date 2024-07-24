/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class is responsible for handling the appearance
	of power ups and their acquirement by players in the game. 
*/

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class is responsible for 
	creating bullets that are drawn once a player shoots. 
*/
public class Bullet {
	// declare all dimensions and properties of the bullet
	private int x, y, width, height;
	private int dx;
	private ImageIcon bullet;
	
	public Bullet(int x, int y, int dx) { // construct Bullet
		this.x = x;
		this.y = y;
		width = 5;
		height = 5;
		this.dx = dx;
		bullet = new ImageIcon("../images/leftBullet.png");
	}
	
	// move bullet along x-axis
	public void travel() {  
		x += dx;
	}
	
	// create a bullet hitbox
	public Rectangle getBounds() {  
		return new Rectangle(x, y, width, height);
	}
	
	// draw Bullet
	public void render(Graphics g) {
		g.drawImage(bullet.getImage(), x, y, width, height, null);
	}
}	
