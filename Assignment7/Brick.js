class Brick extends Sprite {
    constructor(x,y,model) {
        super (x,y,40,40,model)

        // counter for coinblocks
        this.coinCount = 0;

        // determine if coinblock  and  setup image
        let tempImage = new Image();
        if(Math.floor(Math.random() * 10) % 2 == 0) {
            tempImage.src = "coinblock.png";
            this.coinBlock = true;
        } else {
            tempImage.src = "brick.png";
        }
        super.image = tempImage;
    }

    update() {
        this.x = this.x + this.model.cameraPos;
    }
}