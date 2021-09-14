// Cameron Wilson
// 9/5/2021
// Assignment 2

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.JButton;

class View extends JPanel
{
	JButton b1;
	BufferedImage turtle_image;
	Model model;

	// set background color and draw turtle image
	public void paintComponent (Graphics g)
	{
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		g.drawImage(this.turtle_image, model.turtle_x, model.turtle_y, null);
	}

	// remove the button
	void removeButton()
	{
		this.remove(b1);
		this.repaint();
	}

	// view constructor, add jbutton, attach a actionListener, load image into memory
	View(Controller c, Model m)
	{
		model = m;
		b1 = new JButton("This is a fancy button");
		b1.addActionListener(c);
		this.add(b1);
		c.setView(this);
		
		try
		{
			this.turtle_image = ImageIO.read(new File("turtle.png"));
		} catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}
}
