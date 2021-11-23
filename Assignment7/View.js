class View
{
	constructor(model)
	{
		this.model = model;
		this.canvas = document.getElementById("myCanvas");
		this.background = new Image();
		this.background.src = "background.png";
	}

	update()
	{
		let ctx = this.canvas.getContext("2d");
		ctx.clearRect(0, 0, 1000, 500);

		// draw background with paralax and grass
		ctx.drawImage(this.background, this.model.cameraPos * 0.2 ,0, this.background.width, this.canvas.height-100)
		ctx.fillStyle = "green";
		ctx.fillRect(-1,400,1003,1001);

		for(let i = 0; i < this.model.sprites.length; i++)
		{
			if(this.model.sprites[i] === undefined) return;
			let sprite = this.model.sprites[i];
			ctx.drawImage(sprite.image, sprite.x, sprite.y, sprite.w, sprite.h);
		}
	}
}