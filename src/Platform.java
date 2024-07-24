/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class creates a simple invisible rectangular platform
	for players where platforms can be seen in the background.
*/

import java.awt.Rectangle;

public class Platform {
	// declare platform dimension variables
	private int x, y, width, height;
	
	 // construct and initialize all platform dimensions
	public Platform(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	// get all platform dimensions
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y - 30, width, height);
	}
}
