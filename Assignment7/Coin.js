class Coin extends Sprite {
    constructor(x,y,w,h,model) {
        super (x,y,w,h,model)
        // create random horizontal value for coin 
        this.dy = Math.floor(Math.random() * 10) + 1;

        // setup image
        let tempImage = new Image();
        tempImage.src = "coin.png";
        super.image = tempImage;
    }

    // remove coin if goes below map, add random horizontal and constant vertical values each update
    update() {
        if(this.y > 550) {
            game.model.sprites.splice(game.model.sprites.indexOf(this), 1);
        } else {
            this.x += this.dy;
            this.y += 5;
        }
    }

    // draw the coin
    draw(ctx) {
        ctx.drawImage(this.image, this.x - game.model.cameraPos, this.y, 50, 50);
    }
}