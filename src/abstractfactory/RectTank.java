package abstractfactory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import tank.Bullet;
import tank.Dir;
import tank.FireStrategy;
import tank.Group;
import tank.PropertyMgr;
import tank.ResourceMgr;
import tank.Tank;
import tank.TankFrame;

public class RectTank extends BaseTank{
	
	private static final int SPEED = Integer.parseInt((String)PropertyMgr.get("tankSpeed"));

	public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
	public static int WIDTH = ResourceMgr.goodTankU.getWidth();

	Dir dir = Dir.DOWN;

	private boolean moving = true;//开始时坦克的状态
	private Random random = new Random();
	Group group = Group.BAD;
	
	public Rectangle rect = new Rectangle();

	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}

	TankFrame tf = null;
	int x,y;
	
	private boolean living = true;
	
	FireStrategy fs;
	
	public RectTank(int x, int y, Dir dir, Group group, TankFrame tf) {
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
		
		if(group == Group.GOOD) {
			String goodFSName = (String)PropertyMgr.get("goodFS");			
			try {
				fs = (FireStrategy)Class.forName(goodFSName).getDeclaredConstructor().newInstance();
			} catch (Exception e) {				
				e.printStackTrace();
			}			
		} else {
			String badFSName = (String)PropertyMgr.get("badFS");			
			try {
				fs = (FireStrategy)Class.forName(badFSName).getDeclaredConstructor().newInstance();
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}		
	}
	
	public void die() {
		this.living = false;
	}
	
	public void fire() {
//		 fs.fire(this);
		int bX = this.x + Tank.WIDTH/2 -Bullet.WIDTH/2;
		int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		
		Dir[] dirs = Dir.values();
		for (Dir dir : dirs) {
			tf.gf.creatBullet(bX, bY, dir, group, tf);			
		}
	}
	
	public Dir getDir() {
		return dir;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isMoving() {
		return moving;
	}

	public void move() {
		if(!moving)
			return;
		
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
		
		//给敌人坦克设置发射炮弹
		if(this.group == Group.BAD && random.nextInt(100) > 95)
			this.fire();
		
		//给敌人坦克设置随机方向
		if(this.group == Group.BAD && random.nextInt(10) > 8)
			randomDir();
		
		//边界设置
		boundsCheck();
		
	}	 

	private void boundsCheck() {
		if(this.x < 2)
			this.x = 2;
		if(this.x > TankFrame.GAME_WIDTH - RectTank.WIDTH - 2)
			this.x = TankFrame.GAME_WIDTH - RectTank.WIDTH - 2;
		if(this.y < 32)
			this.y = 32;
		if(this.y > TankFrame.GAME_HEIGHT - RectTank.HEIGHT - 2)
			this.y = TankFrame.GAME_HEIGHT - RectTank.HEIGHT - 2;
	}
	private void randomDir() {
		this.dir = Dir.values()[random.nextInt(4)];
	}
	public void paint(Graphics g) {
		if(!living)
			tf.tanks.remove(this);

		Color c = g.getColor();
		g.setColor(group==Group.GOOD? Color.RED: Color.BLUE);
		g.fillRect(x, y, 40, 40);
		g.setColor(c);
				
		move();
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
