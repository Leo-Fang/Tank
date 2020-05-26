package tank;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {

	private static final int SPEED = 15;
	private  int x, y;
	private Dir dir;
	public static int WIDTH = ResourceMgr.bulletD.getWidth();
	public static int HEIGHT = ResourceMgr.bulletD.getHeight();
	
	private boolean living = true;//�����ӵ�����
	TankFrame tf = null;
	private Group group = Group.BAD;
	
	public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void paint(Graphics g) {
		if(!living)
			tf.bullets.remove(this);
		
		switch(dir){
		case LEFT:
			g.drawImage(ResourceMgr.bulletL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.bulletR, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.bulletU, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, x, y, null);
			break;
		}
		
		move();
	}

	private void move() {
		switch(dir) {
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
		default:
			break;
		}
		if(x<0 || y<0 || x>TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT)
			living = false;
	}

	public void collideWith(Tank tank) {
		if(this.group == tank.getGroup())
			return;
		
		//TODO:���ַ���ÿѭ��һ�ζ�Ҫnewһ��Rectangleռ�ڴ档������һ��rect����¼�ӵ���λ��
		Rectangle rectB = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
		Rectangle rectT = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
		if(rectB.intersects(rectT)){
			tank.die();
			this.die();
		}
	}

	private void die() {
		this.living = false;
	}
	
}
