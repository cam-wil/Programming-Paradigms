// Cameron Wilson
// 010553727
// Homework 4
// 9/30/21

import java.util.ArrayList;
import java.util.Iterator;

class Model
{
    ArrayList<Brick> bricks;
    int cameraPos = 0;
    Mario mario;

    // default constructor
    Model()
    {
        bricks = new ArrayList<Brick>();
        mario = new Mario(this);
    }

    // copy constructor
    Model(Model m)
    {
        this.bricks = m.bricks;
        this.cameraPos = m.cameraPos;
        this.mario = m.mario;
    }

    public void update() 
    {
        mario.Update(bricks);
    }

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
        bricks.add(new Brick(x + cameraPos, y, w - x, h - y));
    }

    // clear all bricks
    public void clearBricks()
    {
        bricks = new ArrayList<Brick>();
        System.out.println("Bricks cleared...");
    }

    // create a json "object" from our bricks arraylist
    public void Marshal()
    {
        Json ob = Json.newObject();
        Json lst = Json.newList();

        ob.add("bricks", lst);
        Iterator<Brick> br = bricks.iterator();
        while(br.hasNext())
        {
            lst.add(br.next().Marshal());
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

            bricks = new ArrayList<Brick>();
            
            for(int i = 0; i < lst.size(); i++)
            {
                bricks.add(new Brick(lst.get(i)));
            }
            System.out.println("Map loaded...");
        } catch (Exception e)
        {
            System.out.println("Failed to open map...");
        }
    }
}
