package tank;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		TankFrame tf = new TankFrame();
		
		//ÿ��50msˢ��һ��Frame
		while(true) {
			Thread.sleep(50);
			tf.repaint();
		}
		
	}
	
}
