// Cameron Wilson
// 9/5/2021
// Assignment 2

class Model
{
    int turtle_x, turtle_y, dest_x, dest_y;

    Model() {}

    public void update()
    {
        // move the turtle x axis
        if(this.turtle_x < this.dest_x)
            this.turtle_x += Math.min(4, dest_x - turtle_x);
        else if(this.turtle_x > this.dest_x)
            this.turtle_x -= Math.min(4, turtle_x - dest_x);

        // move turtle y axis
        if(this.turtle_y < this.dest_y)
            this.turtle_y += Math.min(4, dest_y - turtle_y);
        else if(this.turtle_y > this.dest_y)
            this.turtle_y -= Math.min(4, turtle_y - dest_y);
    }

    public void setDestination(int x, int y)
    {
        this.dest_x = x;
        this.dest_y = y;
    }
}