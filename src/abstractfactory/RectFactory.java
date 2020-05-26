package abstractfactory;

import tank.Dir;
import tank.Group;
import tank.TankFrame;

public class RectFactory extends GameFactory {

	@Override
	public BaseTank creatTank(int x, int y, Dir dir, Group group, TankFrame tf) {

		return new RectTank(x, y, dir, group, tf);

	}

	@Override
	public BaseExplode creatExplode(int x, int y, TankFrame tf) {

		return new RectExplode(x, y, tf);

	}

	@Override
	public BaseBullet creatBullet(int x, int y, Dir dir, Group group, TankFrame tf) {

		return new RectBullet(x, y, dir, group, tf);

	}

}
