class Brick extends Sprite {
    constructor(x,y,w,h,model) {
        super (x,y,w,h,model)
        this.posX = x;

        // counter for coinblocks
        this.coinCount = 0;

        // determine if coinblock  and  setup image
        let tempImage = new Image();
        if(Math.floor(Math.random() * 10) % 2 == 0) {
            tempImage.src = "coinblock.png";
            this.isCoinBlock = true;
            this.isBrick = true;
        } else {
            tempImage.src = "brick.png";
            this.isBrick = true;
        }
        super.image = tempImage;
    }

    update() {}

    // if coin count exceeds 5, turn to normal block
    coinCounter() {
        this.coinCount++;
        if(this.coinCount >= 5) this.isCoinBlock = false;
        game.model.sprites.push(new Coin(this.x,this.y,50,50,this.model))
    }

    // draw appropriate image
    draw(ctx) {
        let tempImage = new Image();
        if(this.isCoinBlock) {
            tempImage.src = "coinblock.png";
        } else {
            tempImage.src = "brick.png";
        }
        super.image = tempImage;
		ctx.drawImage(this.image, this.x - this.model.cameraPos, this.y, this.w, this.h);
	}
}