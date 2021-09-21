// Cameron Wilson
// 010553727
// Homework 3
// 9/20/21

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

// old imports, saving for future
// import java.awt.Graphics2D;
// import java.awt.image.BufferedImage;
// import javax.imageio.ImageIO;
// import java.io.File;
// import javax.swing.JButton;

class View extends JPanel
{
	Model model;

	// default constructor
	View(Controller c, Model m)
	{
		model = m;
		c.setView(this);
	}

	// copy constructor
	View(View v)
	{
		this.model = v.model;
	}

	// set background color
	public void paintComponent (Graphics g)
	{
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, 500, 500);

		// populate screen with purple bricks
		g.setColor(new Color(155, 77, 255));
		for(int i = 0; i < model.bricks.size(); i++)
		{
			Brick b = model.bricks.get(i);
			g.fillRect(b.x - model.cameraPos, b.y, b.w, b.h);
		}
	}
}
