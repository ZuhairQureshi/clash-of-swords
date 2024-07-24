/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This abstract class is the parent class
	for the three maps available in this game 
*/ 

import javax.swing.*;
import java.awt.*;

public abstract class Map {
	// declare image background and final floor lower bound limit
	private ImageIcon background;
	private static final int platFloor = 487;
	
	public void setBackground(ImageIcon background) {
		this.background = background;
	}
	
	// draw the background
	public void render(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, 1375, 750, null);
	}
	
	// getter for map lower bounds
	public static int getPlatFloor() {
		return platFloor;
	}
	
	// abstract methods to override in map subclasses
	public abstract void executeMapFeature(Player player);
	public abstract int getFloorLength();
	public abstract int getFloorStartValue();
	public abstract boolean yAbovePlatform(Player player);
	public abstract boolean relevantCollision(Rectangle hitbox);
}
