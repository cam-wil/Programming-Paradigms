class Sprite {
	constructor(x, y, w, h, model) {
		// basic structure of sprites including a model reference
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.model = model;
		this.image = new Image();


		// setup types
		this.isMario = false;
		this.isCoin = false;
		this.isBrick = false;
		this.isCoinBlock = false;
	}

	update() {}

	draw() {}
}