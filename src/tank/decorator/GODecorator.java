package tank.decorator;

import java.awt.Graphics;

import tank.GameObject;

public abstract class GODecorator extends GameObject {

	GameObject go;
	
	public GODecorator(GameObject go) {
		this.go = go;
	}
	
	@Override
	public abstract void paint(Graphics g);

}
