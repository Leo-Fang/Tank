package tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import tank.strategy.FireStrategy;

public class Tank extends GameObject {

	public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();
	private static final int SPEED = Integer.parseInt((String)PropertyMgr.get("tankSpeed"));
	public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
	
	public Dir dir = Dir.DOWN;
	FireStrategy fs;
	
	public GameModel gm;
	public Group group = Group.BAD;
	
	private boolean living = true;//Tanks寿命的判断标志
	
	private boolean moving = true;//Tank移动或停止的判断标志
	
	private Random random = new Random();

	Rectangle rect = new Rectangle();
	
	public int x, y;
	public Tank(int x, int y, Dir dir, Group group, GameModel gm) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.gm = gm;
		
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
	
	private void boundsCheck() {
		if(this.x < 2)
			this.x = 2;
		if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2)
			this.x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
		if(this.y < 32)
			this.y = 32;
		if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2)
			this.y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
	}

	public void die() {
		this.living = false;
	}

	public void fire() {
		fs.fire(this);
	}

	public Dir getDir() {
		return dir;
	}

	public Group getGroup() {
		return group;
	}

	public Rectangle getRect() {
		return rect;
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

	private void move() {
		if(!moving)
			return;
		
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
		
		if(this.group == Group.BAD && random.nextInt(100) > 95)
			this.fire();
		if(this.group == Group.BAD && random.nextInt(100) > 95)
			randomDir();
		
		boundsCheck();
		
		rect.x = this.x;
		rect.y = this.y;
	}
	
	//把换坦克的方法定义到Tank类中
	public void paint(Graphics g) {
		if(!living)
//			return;
			gm.remove(this);//Tank死后需要从List中移除
		
		switch(dir){
		case LEFT:
			g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankL:ResourceMgr.badTankL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankR:ResourceMgr.badTankR, x, y, null);
			break;
		case UP:
			g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankU:ResourceMgr.badTankU, x, y, null);
			break;
		case DOWN:
			g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankD:ResourceMgr.badTankD, x, y, null);
			break;
		}
		
		move();
	}

	private void randomDir() {
		this.dir = Dir.values()[random.nextInt(4)];
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void setGroup(Group group) {
		this.group = group;
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

	public void stop() {
		moving = false;
	}
	
}
