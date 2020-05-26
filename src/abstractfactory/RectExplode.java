package abstractfactory;

import java.awt.Color;
import java.awt.Graphics;

import tank.ResourceMgr;
import tank.TankFrame;

public class RectExplode extends BaseExplode {

	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
	
	private int x,y;

	TankFrame tf = null;
	
	private int step = 0;
	
	public RectExplode(int x, int y, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.tf = tf;
	}
	

	public void paint(Graphics g) { 		
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillRect(x, y, 10*step, 10*step);
		step++;
		
		if(step >= 10)
			tf.explodes.remove(this);
		
		g.setColor(c);
	}

}
