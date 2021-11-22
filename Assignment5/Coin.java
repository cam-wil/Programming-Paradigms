import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Coin extends Sprite 
{
    double xVelocity, yVelocity;
    static BufferedImage coinImage = null;
    Coin(Sprite s)
    {
        this.x = s.x + (this.w/2);
        this.y = s.y - s.h;
        this.w = 100;
        this.h = 100;
        this.isCoinBlock = false;
        this.xVelocity = Math.random() * 10;
        this.yVelocity = -(Math.random() * 10);

        this.LoadImage();
    }

    void LoadImage()
    {
        if(coinImage == null)
            coinImage = View.loadImage("coin.png");
    }

    void update() 
    {
        yVelocity += 0.5;
        y += yVelocity;
        x += xVelocity;
    }

    void draw(Graphics g) 
    {
        g.drawImage(coinImage, this.x - Brick.m.cameraPos, this.y, this.w, this.h, null);
    }

    Json Marshal() 
    {
        return Json.newObject();
    }

    @Override
    public boolean isCoin()
    {
        return true;
    }
}
