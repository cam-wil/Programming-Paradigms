// Cameron Wilson
// 010553727
// Homework 5
// 10/14/21

import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

class Mario extends Sprite{
    int oldx;
    int oldy;
    int cameraPos;
    int jumpFrame = 0;
    double vert_velocity = 0.0;
    Model model;
    boolean backwards = false;
    ArrayList<Sprite> coinList;

    static BufferedImage mario_image[] = null;
	int image_index = 0;

    // constructor
    Mario(Model m)
    {
        this.x = 200;
        this.y = 200;
        this.h = 95;
        this.w = 60;

        coinList = new ArrayList<>();

        this.isCoinBlock = false;
    
        this.model = m;
        this.cameraPos = m.cameraPos;
        this.jumpFrame = 0;
        
        vert_velocity = 0;

        this.LoadImage();

        this.oldx = this.x + this.cameraPos;
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

    // the longer you hold jump, the more you jump until jumpFrame == 6
    public void Jump()
    {
        if(this.jumpFrame <= 6)
        {
            this.vert_velocity -= 8;
        }
    }

    // checks for collision and returns boolean
    boolean Collision(Sprite b)
    {
        if(this.y > (b.y + b.h))                            return false;
        if((this.y + this.h) < b.y)                         return false;
        if(this.x > ((b.x + b.w) - this.cameraPos))         return false;
        if((this.x + this.w) < (b.x - this.cameraPos))      return false;
        return true;
    }

    // after a collision this gets mario back in the correct location
    void GetOut(Sprite temp)
    {               
        coinList = new ArrayList<>();
        // check for right collision
        if(((this.x + this.w) + model.cameraPos) >= temp.x && (this.oldx + this.w) < temp.x)
            model.cameraPos -= 8;
        // check for left collision
        else if((this.x + model.cameraPos) <= (temp.x + temp.w) && this.oldx > (temp.x + temp.w))
            model.cameraPos += 8;
        // check for head collision
        else if(this.y <= (temp.y + temp.h) && this.oldy > temp.y)
        {
            if(temp.isCoinBlock)
            {
                temp.coinCounter();
                coinList.add(new Coin(temp));
            }
            this.y = temp.y + temp.h;
            this.vert_velocity = 5;
        }
        // check for foot collision
        else if ((this.y + this.h) >= temp.y && (this.oldy + this.h) < (temp.y + temp.h))
        {
            this.y = temp.y - this.h;
            this.jumpFrame = 0;
            this.vert_velocity = 0;
        }        
    }

    //  reset image index if out of bounds and draw mario
    public void draw(Graphics g)
    {
        if(image_index > 4) image_index = 0;

        if(this.backwards)
        {
            g.drawImage(mario_image[image_index], this.x + this.w , this.y ,  -this.w , this.h, null);
        }    
        else  
        {
            g.drawImage(mario_image[image_index], this.x, this.y , this.w, this.h, null);
        }  
    }

    // handles all of marios events
    public void update()
    { 
        vert_velocity += 2.1;
        this.y += vert_velocity;
        this.cameraPos = model.cameraPos;
        
        // hit the ground and relocate above ground
        if(this.y + this.h > 600)
        {
            this.y =  600 - this.h;
            this.vert_velocity = 0;
            this.jumpFrame = 0;
        }  

        // iterate through all sprites to check for collision
        Iterator<Sprite> it = model.sprites.iterator();

        while(it.hasNext())
        {
            Sprite temp = it.next();
            if(temp.isBrick() && Collision(temp))
            {
                GetOut(temp);
            }
        }

        this.oldx = this.x + model.cameraPos;
        this.oldy = this.y;
        this.jumpFrame++;
    }

    Json Marshal()
    {
        Json ob = Json.newObject();
        return ob;
    }

    void savePrevCoords()
    {
        this.oldx = this.x + model.cameraPos;
        this.oldy = this.y;
    }

    @Override
    boolean isMario()
    {
        return true;
    }

    @Override
    public String toString()
    {
        return "Mario: [X:"+this.x+" Y:"+this.y+"] [R:"+(this.x+this.w)+" B:"+(this.y+this.h)+"] cpos:" + model.cameraPos;
    }
}