package abstractfactory;

import tank.Dir;
import tank.Group;
import tank.TankFrame;

public abstract class GameFactory {

	public abstract BaseTank creatTank(int x, int y, Dir dir, Group group, TankFrame tf);
	public abstract BaseExplode creatExplode(int x, int y, TankFrame tf);
	public abstract BaseBullet creatBullet(int x, int y, Dir dir, Group group, TankFrame tf);
	
}
