// Cameron Wilson
// 010553727
// Homework 3
// 9/20/21

public class Brick {
    int x,y,w,h;

    Brick(){}

    // copy constructor
    Brick(Brick b)
    {
        this.x = b.x;
        this.y = b.y;
        this.w = b.w;
        this.h = b.h;
    }

    // constructor with xy coordinates
    Brick(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    // constructor that uses Json
    Brick(Json j)
    {
        this.x = (int)j.getLong("x");
        this.y = (int)j.getLong("y");
        this.w = (int)j.getLong("w");
        this.h = (int)j.getLong("h");
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
}
