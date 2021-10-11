// Cameron Wilson
// 010553727
// Homework 4
// 9/30/21

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Iterator;



class View extends JPanel
{
	Model model;

	static BufferedImage background;

	// default constructor
	View(Controller c, Model m)
	{
		model = m;
		c.setView(this);

		background = loadImage("background.png");
	}

	// static method to handle loading images and return them
	static BufferedImage loadImage(String fn)
	{
		BufferedImage image = null;
		try 
		{
			image = ImageIO.read( new File(fn));
			System.out.println(fn + " loaded...");
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return image;
	}

	public void paintComponent (Graphics g)
	{
		// back fill
		g.setColor(new Color(109,208,247));
		g.fillRect(0, 0, 800, 700);

		// make background move very slow and then update
		double bg = -(model.cameraPos * 0.07);
		g.drawImage(background, (int)bg, 0 , background.getWidth(), 600, null);

		// iterate over all bricks and draw
		Iterator<Brick> brickIter = model.bricks.iterator();
		while(brickIter.hasNext())
		{
			Brick b = brickIter.next();
			b.Draw(g, model, b);
		}
		
		// grass
		g.setColor(Color.green);
		g.fillRect(0, 600, 2000, 600);
		
		// draw the mario
		model.mario.Draw(g);
	}
}
