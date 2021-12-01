 class Mario extends Sprite {
    constructor(x,y,model) {
        super(x, y, 60, 95, model)
        this.images = [];
        this.image_index = 0;
        this.jumpFrame = 0;
        this.cameraPos = model.cameraPos;
        this.oldX = x + this.cameraPos;
        this.oldY = y;
        this.vert_velocity = 0;
        this.isMario = true;
        
        // put images in array
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

        // update camerapos
        this.cameraPos = this.model.cameraPos;
       
        // hit the ground and relocate above ground
        if(this.y + this.h > 400) {
            this.y =  400 - (this.h);
            this.vert_velocity = 0;
            this.jumpFrame = 0;
        } 
        
        
        for(let i = 0; i < this.model.sprites.length; i++) {
            if(this.model.sprites[i].isBrick && this.Collision(this.model.sprites[i])) {
                this.GetOut(this.model.sprites[i])
            }
        }
        this.savePrevCoords();
        this.jumpFrame++;
    }

    GetOut(temp) {
        // mario right detection
        if(((this.x + this.w) + game.model.cameraPos) >= temp.x && (this.oldX + this.w) < temp.x) {
            game.model.cameraPos -= 8;
        }
        // mario left detection
        else if((this.x + game.model.cameraPos) <= (temp.x + temp.w) && this.oldX > (temp.x + temp.w)) {
            game.model.cameraPos += 8;
        }
        // mario head detection
        else if(this.y <= (temp.y + temp.h) && this.oldY > temp.y) {
            this.y = temp.y + temp.h + 2;
            this.vert_velocity = 10;
            if(temp.isCoinBlock) {
                temp.coinCounter()
            }
        }
        // mario foot detection
        else if((this.y + this.h) >= temp.y && (this.oldY + this.h) < (temp.y + temp.h)) {
            this.y = (temp.y - this.h) - 2;
            this.jumpFrame = 0;
            this.vert_velocity = 0;
        }
    }

    // update oldX and oldY for collision detection
    savePrevCoords() {
        this.oldX = this.x + game.model.cameraPos;
        this.oldY = this.y;
    }

    // keep track of jumping length
    Jump() {
        if(this.jumpFrame <= 6)  this.vert_velocity -= 7;
    }

    // detect collison, return boolean
    Collision(b)
    {
        if(this.y > (b.y + b.h))    return false;
        else if((this.y + this.h) < b.y) return false;
        else if(this.x > ((b.x + b.w) - game.model.cameraPos))  return false;
        else if((this.x + this.w) < (b.x - game.model.cameraPos)) return false;
        return true;
    }

    // draw mario
    draw(ctx) {
        ctx.drawImage(this.image, this.x, this.y, this.w, this.h);
	}
}