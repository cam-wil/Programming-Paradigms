import java.awt.Graphics;

abstract class Sprite
{
    int x, y, w, h;
    boolean isCoinBlock;

    abstract void update();
    abstract void draw(Graphics g);
    abstract Json Marshal();

    void coinCounter() {};

    boolean isCoin()
    {
        return false;
    }

    boolean isBrick()
    {
        return false;
    }

    boolean isMario()
    {
        return false;
    }
}