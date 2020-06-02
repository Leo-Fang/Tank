package tank.strategy;

import java.io.Serializable;

import tank.Tank;

public interface FireStrategy extends Serializable {

	void fire(Tank t);
}
