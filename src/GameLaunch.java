/*	Author: Zuhair Qureshi
	Last Modified: 4-10-2021
	Course: ICS3U7 Strelkovska
	Description: This class is the main class that launches 
	the game and initializes the frame name. 
*/

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.swing.*;

public class GameLaunch {
    public static void main(String[] args) {
    	GameFrame frame= new GameFrame("Final"); // create frame
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	System.out.println("Author: Zuhair Qureshi \nGame: Clash of Swords \nICS3U7 Strelkovska 2021");
    }
    
    // method to call when image must be flipped
	public static BufferedImage getFlippedImage(ImageIcon icon) {
		// temporary value if image cannot be loaded
		BufferedImage image = MainMenu.getDefaultImage();
		
		// catch any loading error when using BufferedImage 
		try {
			// create BufferedImage from the ImageIcon
			image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics g = image.createGraphics();
			icon.paintIcon(null, g, 0,0);
			g.dispose(); 
		} catch (Exception e) {
			System.out.println("This file could not be found.");
		}
		// flip the image horizontally across the x-axis
		AffineTransform flip = AffineTransform.getScaleInstance(-1, 1);
		flip.translate(-image.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(flip, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		image = op.filter(image, null);
		return image;
	}

}