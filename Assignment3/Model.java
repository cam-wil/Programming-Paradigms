// Cameron Wilson
// 010553727
// Homework 3
// 9/20/21

import java.util.ArrayList;

class Model
{
    ArrayList<Brick> bricks;
    int cameraPos = 0;

    // default constructor
    Model()
    {
        bricks = new ArrayList<Brick>();
    }

    // copy constructor
    Model(Model m)
    {
        this.bricks = m.bricks;
        this.cameraPos = m.cameraPos;
    }

    public void update() {}

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
        for(int i = 0; i < bricks.size(); i++)
        {
            lst.add(bricks.get(i).Marshal());
        }
        ob.save("map.json");
        System.out.println("File saved...");
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
            System.out.println("File loaded...");
        } catch (Exception e)
        {
            System.out.println("Failed to open file...");
        }
        
    }
}
