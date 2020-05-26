package abstractfactory;

import tank.Bullet;
import tank.Dir;
import tank.Explode;
import tank.Group;
import tank.Tank;
import tank.TankFrame;

public class DefaultFactory extends GameFactory {

	@Override
	public BaseTank creatTank(int x, int y, Dir dir, Group group, TankFrame tf) {
		return new Tank(x, y, dir, group, tf);
	}

	@Override
	public BaseExplode creatExplode(int x, int y, TankFrame tf) {
		return new Explode(x, y, tf);
	}

	@Override
	public BaseBullet creatBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
		return new Bullet(x, y, dir, group, tf);		
	}

}
