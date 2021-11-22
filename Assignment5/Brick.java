// Cameron Wilson
// 010553727
// Homework 5
// 10/14/21

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Brick extends Sprite{
    static BufferedImage brickImage = null;
    static BufferedImage coinBlockImage = null;
    static Model m;

    int coinCount = 0;

    Brick(Model model)
    {
        m = model;
    }

    // constructor with xy coordinates
    Brick(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.isCoinBlock = makeCoinBlock();

        this.LoadImage();
    }

    // constructor that uses Json
    Brick(Json j)
    {
        this.x = (int)j.getLong("x");
        this.y = (int)j.getLong("y");
        this.w = (int)j.getLong("w");
        this.h = (int)j.getLong("h");

        this.isCoinBlock = makeCoinBlock();

        this.LoadImage();
    }

    // loads image, called from constructer, lazy loading
    void LoadImage()
    {
        if(!isCoinBlock && brickImage == null)
            brickImage = View.loadImage("brick.png");
        if(isCoinBlock && coinBlockImage == null)
            coinBlockImage = View.loadImage("coinblock.png");            
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

    // makes new block or coinblock based on random
    boolean makeCoinBlock()
    {
        if(Math.floor(Math.random() * 6) == 2)
        {
            return true;
        } else 
        {
            return false;
        }
    }

    public void update() {}

    // draw brick
    public void draw(Graphics g) 
    {
        if(this.isCoinBlock)
            g.drawImage(coinBlockImage, this.x - Brick.m.cameraPos, this.y, this.w, this.h, null);
        else
            g.drawImage(brickImage, this.x - Brick.m.cameraPos, this.y, this.w, this.h, null);
    }

    @Override
    void coinCounter()
    {
        coinCount++;
        if(coinCount >= 5) this.isCoinBlock = false;
    }
    
    @Override
    public boolean isBrick()
    {
        return true;
    }
    
    @Override
    public String toString()
    {
        return "Brick [X:" + this.x + " Y:" + this.y + "] [R:"+(this.x+this.w) + " B:" + (this.y+this.h) + "]";
    }
}
