package tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

	int x = 200, y = 200;
	public TankFrame() {
		setSize(800, 600);
		setTitle("Tank War");
		setResizable(false);
		setVisible(true);
		
		//��Ӽ��̼����¼�
		this.addKeyListener(new MyKeyListener());
		
		//��Ӵ��ڼ����¼������Frame���Ͻǵġ��������Թرմ���
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		g.fillRect(x, y, 50, 50);
	}
	
	class MyKeyListener extends KeyAdapter {
		//��дkeyPressed��keyReleased����
		@Override
		public void keyPressed(KeyEvent e) {	
			x += 100;
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("key released");
		}
		
	}
	
}
