// Cameron Wilson
// 9/5/2021
// Assignment 2

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	private boolean keyLeft, keyRight, keyUp, keyDown;

	// detect up down left and right keyboard presses and update the respective boolean
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT:
				keyRight = true;
				break;
			case KeyEvent.VK_LEFT:
				keyLeft = true;
				break;
			case KeyEvent.VK_UP:
				keyUp = true;
				break;
			case KeyEvent.VK_DOWN:
				keyDown = true;
				break;
		}
	}

	// detect up down left and right keyboard release and update the respective boolean
	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT:
				keyRight = false;
				break;
			case KeyEvent.VK_LEFT:
				keyLeft = false;
				break;
			case KeyEvent.VK_UP:
				keyUp = false;
				break;
			case KeyEvent.VK_DOWN:
				keyDown = false;
				break;
		}
	}

	// method to satisfy KeyListener
	public void keyTyped(KeyEvent e) {}

	// detect that mouse is clicked, and send x y coords to the model
	public void mousePressed(MouseEvent e)
	{
		model.setDestination(e.getX(), e.getY());
	}

	// methods to satisfy MouseListener
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}

	// runs to every tick to update model destination via KeyListeners
	void update()
	{
		if(keyRight) model.dest_x+=4;
		if(keyLeft) model.dest_x-=4;
		if(keyDown) model.dest_y+=4;
		if(keyUp) model.dest_y-=4;
	}

	Controller(Model m)
	{
		model = m;
	}

	void setView(View v)
	{
		view = v;
	}

	// ActionEvent of clicking the button removes the button
	public void actionPerformed(ActionEvent e)
	{
		view.removeButton();
	}
}
