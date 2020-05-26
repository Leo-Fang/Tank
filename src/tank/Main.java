package tank;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		TankFrame tf = new TankFrame();
		
		int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
		
		//��ʼ���ط�̹��
		for (int i = 0; i < initTankCount; i++) {
			tf.tanks.add(new Tank(10+i*80, 200, Dir.DOWN, Group.BAD, tf));
		}
//		new Thread(()->new Audio("audio/war1.wav").loop()).start();
		
		//ÿ��50msˢ��һ��Frame
		while(true) {
			Thread.sleep(50);
			tf.repaint();
		}
		
	}
	
}
