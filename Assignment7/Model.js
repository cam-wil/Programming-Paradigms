class Model
{
	constructor() {
		// map object
		this.map = {"bricks": [{"x": -80,"y":320,"w":80,"h":80},{"x": 0,"y":320,"w":80,"h":80},{"x": 81,"y":350,"w":50,"h":50},{"x": 300,"y":320,"w":80,"h":80},{"x": 500,"y":220,"w":50,"h":50},{"x": 575,"y":100,"w":125,"h":125},{"x": 800,"y":330,"w":70,"h":70},{"x": 974,"y":200,"w":70,"h":70},{"x": 1050,"y":200,"w":70,"h":70},{"x": 1150,"y":220,"w":70,"h":70},{"x": 1250,"y":200,"w":70,"h":70},{"x": 1350,"y":220,"w":70,"h":70}]}
		this.sprites = [];
		this.cameraPos = 0;
		this.mario = new Mario(200, 200, this);
		this.sprites.push(this.mario)
		for(let i = 0; i < this.map.bricks.length; i++) {
			let tempBrick = this.map.bricks[i];
			this.sprites.push(new Brick(tempBrick.x,tempBrick.y, tempBrick.w, tempBrick.h, this));
		}
	}

	update() {
		for(let i = 0; i < this.sprites.length; i++)
		{
			if(this.sprites[i] === undefined) return;
			else this.sprites[i].update();
		}
	}
}