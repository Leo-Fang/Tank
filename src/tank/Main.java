package tank;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		TankFrame tf = new TankFrame();
		
		//��ʼ���ط�̹��
		for (int i = 0; i < 5; i++) {
			tf.tanks.add(new Tank(50+i*150, 200, Dir.DOWN, tf));
		}
		
		//ÿ��50msˢ��һ��Frame
		while(true) {
			Thread.sleep(50);
			tf.repaint();
		}
		
	}
	
}
