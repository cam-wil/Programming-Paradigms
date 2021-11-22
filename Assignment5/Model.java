// Cameron Wilson
// 010553727
// Homework 5
// 10/14/21

import java.util.ArrayList;
import java.util.Iterator;

class Model
{
    ArrayList<Sprite> sprites;
    int cameraPos = 0;
    Mario mario;

    // default constructor
    Model()
    {
        sprites = new ArrayList<Sprite>();
        mario = new Mario(this);
        sprites.add(mario);
    }

    // update all sprites and add new coins to sprites array list
    public void update() 
    {
        Iterator<Sprite> it = sprites.iterator();
        while(it.hasNext())
        {
            it.next().update();
        }
        Iterator<Sprite> coinit = mario.coinList.iterator();
        if(coinit.hasNext())
        {
            sprites.add(coinit.next());
        }
        removeCoins();
    }

    // removes coins that are off screen
    public void removeCoins()
    {
        ArrayList<Sprite> copy = new ArrayList<>(sprites);
        for(Sprite sp : copy)
        {
            if(sp.isCoin())
            {
                if(sp.x - this.cameraPos < -5 || sp.x - this.cameraPos > 805 || sp.y < -5 || sp.y > 705)
                {
                    sprites.remove(sp);
                }
            }
        }
    }

    // adds brick
    public void addBrick(int x, int y, int w, int h)
    {
        // check if starting values are larger than ending values, if so, swap them. this allows drawing in either direction
        if (x > w)
        {
            int xx = x;
            x = w;
            w = xx;
        }

        if (y > h)
        {
            int yy = y;
            y = h;
            h = yy;
        }
        // add new brick with cameraPos offset
        sprites.add(new Brick(x + this.cameraPos, y, w - x, h - y));
    }

    // clear all bricks
    public void clearBricks()
    {
        Iterator<Sprite> it = sprites.iterator();
        while(it.hasNext())
        {
            Sprite temp = it.next();
            if(temp.isBrick())
            {
                it.remove();
            }
        }
        System.out.println("Bricks cleared...");
    }

    // create a json "object" from our bricks arraylist
    public void Marshal()
    {
        Json ob = Json.newObject();
        Json lst = Json.newList();

        ob.add("bricks", lst);
        Iterator<Sprite> sr = sprites.iterator();
        while(sr.hasNext())
        {
            Sprite temp = sr.next();
            if(temp.isBrick())
                lst.add(temp.Marshal());
        }
        ob.save("map.json");
        System.out.println("Map saved...");
    }

    // populate our bricks arraylist from a json file
    public void UnMarshal()
    {
        try
        {
            Json ob = Json.load("map.json");
            Json lst = ob.get("bricks");

            sprites = new ArrayList<Sprite>();
            sprites.add(mario);
            
            for(int i = 0; i < lst.size(); i++)
            {
                sprites.add(new Brick(lst.get(i)));
            }
            System.out.println("Map loaded...");
            System.out.println("Pro tip: go into edit mode and press 'L' to reload the map to get different random coin blocks");
        } catch (Exception e)
        {
            System.out.println("Failed to open map...");
        }
    }
}
