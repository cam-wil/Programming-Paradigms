// Cameron Wilson
// 010553727
// Homework 5
// 10/14/21

import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	Model model;
	Controller controller;
	View view;

	// initializes the game window, the input listeners, and instantiates the model, view, controller objects
	public Game()
	{
		model = new Model();
		new Brick(model);
		controller = new Controller(model);
		view = new View(controller, model);

		view.addMouseListener(controller);

		this.addKeyListener(controller);
		this.setTitle("Mario HW5");
		this.setSize(800,700);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	// entry point of program, makes a new game object, then runs it
	public static void main(String[] args)
	{
		Game g = new Game();
		g.model.UnMarshal();
		g.run();
	}

	// game loop
	public void run()
	{
		while(true)
		{
			// update our objects
			controller.update();
			model.update();
			view.repaint();

			Toolkit.getDefaultToolkit().sync();

			// try to sleep for 40ms, if fail, throw exception and close program
			try
			{
				Thread.sleep(40);
			} catch (Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}