package tank;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		TankFrame tf = new TankFrame();
		
//		new Thread(()->new Audio("audio/war1.wav").loop()).start();
		
		//ÿ��50msˢ��һ��Frame
		while(true) {
			Thread.sleep(25);
			tf.repaint();
		}
		
	}
	
}
