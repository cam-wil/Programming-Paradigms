class Mario extends Sprite {
    constructor(x,y,model) {
        super(x, y, 60, 95, model)
        this.images = [];
        this.image_index = 0;
        this.jumpFrame = 0;

        this.vert_velocity = 0;
        this.isMario = true;
        
        for(let i = 1; i < 6; i++) {
            let temp = new Image();
            temp.src = ("mario" + i + ".png")
            this.images.push(temp)
        }
    }

    update() {
        // image reset
        if(this.image_index >= 5) this.image_index = 0;
        super.image = this.images[this.image_index];

        // gravity
        this.vert_velocity += 2.1;

        // apply gravity
        this.y += this.vert_velocity;

        // hit the ground and relocate above ground
        if(this.y + this.h > 400)
        {
            this.y =  400 - (this.h);
            this.vert_velocity = 0;
            this.jumpFrame = 0;
        } 
        this.jumpFrame++;
    }

    Jump() {
        if(this.jumpFrame <= 6)  this.vert_velocity -= 7;
    }

    Collision(b)
    {
        if(this.y > (b.y + b.h))                                  return false;
        if((this.y + this.h) < b.y)                               return false;
        if(this.x > ((b.x + b.w) - this.model.cameraPos))         return false;
        if((this.x + this.w) < (b.x - this.model.cameraPos))      return false;
        return true;
    }
}