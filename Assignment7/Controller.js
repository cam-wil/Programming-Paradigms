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

	// these two methods handle keyboard events
	keyDown(event)
	{
		if(event.keyCode == 39) this.key_right = true;
		else if(event.keyCode == 37) this.key_left = true;
		else if(event.keyCode == 32 || event.keyCode == 38) this.key_space = true;
	}

	keyUp(event)
	{
		if(event.keyCode == 39) this.key_right = false;
		else if(event.keyCode == 37) this.key_left = false;
		else if(event.keyCode == 32 || event.keyCode == 38) this.key_space = false;
	}

	// this is called every frame to handle updates on key events
	update()
	{
		this.model.mario.savePrevCoords();
		if(this.key_left) {
			this.model.cameraPos -= 8;
			this.model.mario.image_index++;
		}
		if(this.key_right) {
			this.model.cameraPos += 8;
			this.model.mario.image_index++;
		}
		if(this.key_space) {
			if(this.model.mario.jumpFrame <= 6) this.model.mario.Jump();
		}
	}
}