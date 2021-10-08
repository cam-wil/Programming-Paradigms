// Cameron Wilson
// 010553727
// Homework 4
// 9/30/21

import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

class Mario {
    int x, y, w, h;
    int oldx;
    int oldy;
    int jumpFrame = 0;
    double vert_velocity = 0.0;
    Model model;
    int head, feet, left, right;

    static BufferedImage mario_image[] = null;
	int image_index = 0;

    Mario(Model m)
    {
        this.x = 200;
        this.h = 95;
        this.w = 60;
    
        this.jumpFrame = 0;
        this.model = m;
        vert_velocity = 0;

        this.LoadImage();


        this.head = this.y;
        this.feet = this.y + this.h;
        this.left = this.x;
        this.right = this.x + this.w;

        this.oldx = this.x + model.cameraPos;
        this.oldy = this.y;
    }

    // load array of images, called from constructer for lazy loading
    void LoadImage()
    {
        if(mario_image == null)
        {
            mario_image = new BufferedImage[5];
            for(int i = 0;i<5;i++)
            {
                String path = "mario"+(i+1)+".png";
                mario_image[i] = View.loadImage(path);
            }
        }
    }

    // the longer you hold jump, the more you jump until jumpFrame == 8
    public void Jump()
    {
        if(this.jumpFrame <= 6)
        {
            this.vert_velocity -= 8;
        }
    }

    // checks for collision and returns boolean
    boolean Collision(Brick b)
    {
        if(this.head > b.bottom)                        return false;
        if(this.feet < b.top)                           return false;
        if(this.left > (b.right- model.cameraPos))      return false;
        if(this.right < (b.left - model.cameraPos))     return false;
        return true;
    }

    // after a collision this gets mario back in the correct location
    void GetOut(Brick temp)
    {               
        // check for right collision
        if(this.right + model.cameraPos > temp.x && this.oldx + this.w <= temp.left)
            model.cameraPos -= 8;
        // check for left collision
        else if(this.left + model.cameraPos < temp.right && this.oldx >= temp.right)
            model.cameraPos += 8;
        // check for head collision
        else if(this.head < temp.bottom && this.oldy > temp.top)
        {
            this.y = temp.bottom;
            this.vert_velocity = 0;
        }
        // check for foot collision
        else if (this.feet > temp.y && this.oldy + this.h < temp.bottom)
        {
            this.y = temp.y  - this.h;
            this.jumpFrame = 0;
            this.vert_velocity = 0;
        }
    }

    //  reset image index if out of bounds and draw mario
    public void Draw(Graphics g)
    {
        if(image_index > 4) image_index = 0;
        g.drawImage(mario_image[image_index], this.x, this.y , 60, 95, null);
    }

    // handles all of marios events
    public void Update(ArrayList<Brick> b)
    { 
        vert_velocity += 2.1;
        this.y += vert_velocity;

        this.head = this.y;
        this.feet = this.y + this.h;
        this.left = this.x;
        this.right = this.x + this.w;
        
        // hit the ground and relocate above ground
        if(this.feet > 600)
        {
            this.y =  600 - this.h;
            this.vert_velocity = 0;
            this.jumpFrame = 0;
        }  

        // iterate through all bricks to check for collision
        Iterator<Brick> it = b.iterator();
        while(it.hasNext())
        {
            Brick temp = it.next();
            if(Collision(temp))
            {
                GetOut(temp);
            }
        }
        this.oldx = this.x + model.cameraPos;
        this.oldy = this.y;
        this.jumpFrame++;
    }

    @Override
    public String toString()
    {
        return "Mario: [X:"+this.x+" Y:"+this.y+"] [R:"+(this.x+this.w)+" B:"+(this.y+this.h)+"] cpos:" + model.cameraPos;
    }
}