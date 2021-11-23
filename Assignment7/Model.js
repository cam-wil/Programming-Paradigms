class Model
{
	constructor()
	{
		this.map = {"bricks":[{"x":346,"y":505,"w":59,"h":95},{"x":507,"y":288,"w":59,"h":73},{"x":643,"y":292,"w":46,"h":70},{"x":826,"y":274,"w":85,"h":86},{"x":1060,"y":280,"w":55,"h":73},{"x":1203,"y":272,"w":69,"h":79},{"x":934,"y":542,"w":72,"h":57},{"x":1292,"y":273,"w":68,"h":68},{"x":1437,"y":282,"w":77,"h":82},{"x":1637,"y":372,"w":96,"h":43},{"x":-23,"y":263,"w":66,"h":71},{"x":-199,"y":267,"w":78,"h":67},{"x":-472,"y":263,"w":96,"h":77},{"x":-740,"y":266,"w":89,"h":60},{"x":-963,"y":138,"w":73,"h":58},{"x":-1088,"y":132,"w":92,"h":64},{"x":-1303,"y":262,"w":91,"h":61},{"x":-1496,"y":242,"w":79,"h":78},{"x":-1640,"y":241,"w":72,"h":82},{"x":1784,"y":291,"w":76,"h":75},{"x":1929,"y":291,"w":53,"h":72}]}
		this.sprites = [];
		this.cameraPos = 0;
		this.mario = new Mario(200, 200, this);
		this.sprites.push(this.mario)
		for(let i = 0; i < this.map.bricks.length; i++) {
			let tempBrick = this.map.bricks[i]
			this.sprites.push(new Brick(tempBrick.x,tempBrick.y, this))
		}
	// 	this.sprites.push(new Sprite(200, 100, "brick.png", Sprite.prototype.sit_still, Sprite.prototype.ignore_click));
	// 	this.turtle = new Sprite(50, 50, "coin.png", Sprite.prototype.go_toward_destination, Sprite.prototype.set_destination);
	// 	this.sprites.push(this.turtle);
	//  this.sprites.push(new Sprite(0,0,100,100,this))

	// }
	}

	update()
	{
		for(let i = 0; i < this.sprites.length; i++)
		{
			if(this.sprites[i] === undefined) return;
			this.sprites[i].update();
		}
	}
}