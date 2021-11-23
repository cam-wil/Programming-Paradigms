class Controller
{
	constructor(model, view)
	{
		this.model = model;
		this.view = view;
		this.key_right = false;
		this.key_left = false;
		this.key_space = false;
		let self = this;
		document.addEventListener('keydown', function(event) { self.keyDown(event); }, false);
		document.addEventListener('keyup', function(event) { self.keyUp(event); }, false);
	}



	keyDown(event)
	{
		if(event.keyCode == 39) this.key_right = true;
		else if(event.keyCode == 37) this.key_left = true;
		else if(event.keyCode == 32) this.key_space = true;
	}

	keyUp(event)
	{
		if(event.keyCode == 39) this.key_right = false;
		else if(event.keyCode == 37) this.key_left = false;
		else if(event.keyCode == 32) this.key_space = false;
	}

	update()
	{
		if(this.key_right) {
			this.model.cameraPos -= 8;
			this.model.mario.image_index += 1;
			this.flipped = false;
		}
		if(this.key_left) {
			this.model.cameraPos += 8;
			this.model.mario.image_index += 1;
			this.flipped = true;
		}
		if(this.key_space) {
			if(this.model.mario.jumpFrame <= 6) this.model.mario.Jump();
		}

	}
}