// Cameron Wilson
// 010553727
// Homework 4
// 9/30/21

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Brick {
    int x,y,w,h;
    int top, bottom, left, right;
    static BufferedImage brickImage = null;
    Brick(){}

    // copy constructor
    Brick(Brick b)
    {
        this.x = b.x;
        this.y = b.y;
        this.w = b.w;
        this.h = b.h;

        this.top    = b.y;
        this.bottom = b.y + b.h;
        this.left   = b.x;
        this.right  = b.x + b.w;
        this.LoadImage();
    }

    // constructor with xy coordinates
    Brick(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.top    = y;
        this.bottom = y + h;
        this.left   = x;
        this.right  = x + w;
        this.LoadImage();
    }

    // constructor that uses Json
    Brick(Json j)
    {
        this.x = (int)j.getLong("x");
        this.y = (int)j.getLong("y");
        this.w = (int)j.getLong("w");
        this.h = (int)j.getLong("h");

        this.top    = this.y;
        this.bottom = this.y + this.h;
        this.left   = this.x;
        this.right  = this.x + this.w;
        this.LoadImage();
    }
    // loads image, called from constructer, lazy loading
    void LoadImage()
    {
        if(brickImage == null)
        {
            brickImage = View.loadImage("brick.png");
        }
    }

    // returns this object as a Json object
    public Json Marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", this.x);
        ob.add("y", this.y);
        ob.add("w", this.w);
        ob.add("h", this.h);

        return ob;
    }

    // draw brick
    public void Draw(Graphics g, Model m, Brick b)
    {
        g.drawImage(brickImage, b.x - m.cameraPos, b.y, b.w, b.h, null);
    }
    
    @Override
    public String toString()
    {
        return "Brick [X:" + this.x + " Y:" + this.y + "] [R:"+(this.x+this.w) + " B:" + (this.y+this.h) + "]";
    }
}
