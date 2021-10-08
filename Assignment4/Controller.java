// Cameron Wilson
// 010553727
// Homework 4
// 9/30/21

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	int tempx = 0, tempy = 0, tempw = 0, temph = 0;
	View view;
	Model model;
	private boolean keyLeft, keyRight, keyUp, keyDown;
	boolean editing = false;

	// default constructor
	Controller(Model m)
	{
		model = m;
	}

	// copy constructor
	Controller(Controller c)
	{
		this.tempx = c.tempx;
		this.tempy = c.tempy;
		this.tempw = c.tempw;
		this.temph = c.temph;

		this.view = c.view;
		this.model = c.model;

		this.keyLeft = c.keyLeft;
		this.keyRight = c.keyRight;
		this.keyUp = c.keyUp;
		this.keyDown = c.keyDown;
	}

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
			case KeyEvent.VK_SPACE:
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
			case KeyEvent.VK_SPACE:
				keyUp = false;
				break;
			case KeyEvent.VK_DOWN:
				keyDown = false;
				break;
			case KeyEvent.VK_C:
				if(editing) model.clearBricks();
				break;
			case KeyEvent.VK_S:
				if(editing) model.Marshal();
				break;
			case KeyEvent.VK_L:
				if(editing) model.UnMarshal();
				break;
			case KeyEvent.VK_E:
				editing = !editing;
				break;
		}
	}

	// method to satisfy KeyListener
	public void keyTyped(KeyEvent e) {}

	// detect that mouse is clicked, and send x y coords to the model
	public void mousePressed(MouseEvent e)
	{
		if(editing)
		{
			tempx = e.getX();
			tempy = e.getY();
		}
	}

	// methods to satisfy MouseListener
	public void mouseReleased(MouseEvent e)
	{
		if(editing)
		{
			tempw = e.getX();
			temph = e.getY();
			model.addBrick(tempx, tempy, tempw, temph);
		}
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}

	// runs to every tick to update model destination via KeyListeners
	void update()
	{
		// move our cameraPos left or right, simulates moving
		if(keyLeft)
		{
			model.cameraPos -= 8;
			model.mario.image_index++;
		}
		if(keyRight)
		{			
			model.cameraPos += 8;
			model.mario.image_index++;
		}
		// run jump method
		if(keyUp)
		{
			model.mario.Jump();
		}
		if(keyDown){}
	}

	void setView(View v)
	{
		view = v;
	}

	public void actionPerformed(ActionEvent e) {}
}
