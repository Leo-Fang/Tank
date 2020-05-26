package abstractfactory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import tank.Dir;
import tank.Explode;
import tank.Group;
import tank.PropertyMgr;
import tank.ResourceMgr;
import tank.Tank;
import tank.TankFrame;

public class RectBullet extends BaseBullet {

	private static final int SPEED = Integer.parseInt((String)PropertyMgr.get("bulletSpeed"));
	public static int WIDTH = ResourceMgr.bulletD.getWidth();
	public static int HEIGHT = ResourceMgr.bulletD.getHeight();
	
	private int x,y;
	private Dir dir;
	private Group group = Group.BAD;
	
	Rectangle rect = new Rectangle();
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	private boolean living = true;//定义子弹的寿命
	TankFrame tf = null;
	
	public RectBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
		
		rect.x = this.x;
		rect.y = this.y;
		rect.width = WIDTH;
		rect.height = HEIGHT;
		
		tf.bullets.add(this);
		
	}
	
	public void paint(Graphics g) { 
		if(!living) {
			tf.bullets.remove(this);
		}
		
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 20, 20);
		g.setColor(c);
		move();
	}

	private void move() {
		switch(dir){
		case LEFT:
			x -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		}
		
		rect.x = this.x;
		rect.y = this.y;
		
		if(x<0 || y<0 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT)
			living = false;
	}

	public void collideWith(BaseTank tank) {
		if(this.group == tank.getGroup())
			return;
		
		if(rect.intersects(tank.rect)) {
			tank.die();
			this.die();
			int eX = tank.getX() + Tank.WIDTH/2 -Explode.WIDTH/2;
			int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
			tf.explodes.add(tf.gf.creatExplode(eX, eY, tf));
		}
		
	}

	public void die() {
		this.living = false;
	}
}
