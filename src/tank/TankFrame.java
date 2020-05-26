package tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	
	Tank myTank = new Tank(200, 200, Dir.DOWN, this);
	List<Bullet> bullets = new ArrayList<>();
	
	public TankFrame() {
		setSize(GAME_WIDTH, GAME_HEIGHT);
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

	//�����Ļ��˸����
	Image offScreenImage = null;//���ڴ��ж���һ��ͼƬ

	@Override
	public void update(Graphics g) {//�����g����Ļ�Ļ���
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);//���ڴ��е�ͼƬһ���Ի�����Ļ��
	}
	
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("�ӵ�������"+bullets.size(), 10, 60);
		g.setColor(c);
		
		myTank.paint(g);
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
	}
	
	class MyKeyListener extends KeyAdapter {
		
		//����booleanֵ���ж��ƶ�����
		boolean bL = false;
		boolean bR = false;
		boolean bU = false;
		boolean bD = false;
		
		//��дkeyPressed��keyReleased����
		@Override
		public void keyPressed(KeyEvent e) {	
			int key = e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_LEFT:
				bL = true;
				break;
			case KeyEvent.VK_RIGHT:
				bR = true;
				break;
			case KeyEvent.VK_UP:
				bU = true;
				break;
			case KeyEvent.VK_DOWN:
				bD = true;
				break;
			default:
				break;
			}			
			setMainTankDir();
		}


		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_LEFT:
				bL = false;
				break;
			case KeyEvent.VK_RIGHT:
				bR = false;
				break;
			case KeyEvent.VK_UP:
				bU = false;
				break;
			case KeyEvent.VK_DOWN:
				bD = false;
				break;
				
			case KeyEvent.VK_CONTROL:
				myTank.fire();
				break;
				
			default:
				break;
			}
			setMainTankDir();
		}
	
		//���ݰ��������ƶ�����
		public void setMainTankDir() {
			if(!bL && !bR && !bU && !bD)
				myTank.setMoving(false);
			else
				myTank.setMoving(true);
			
			if(bL)
				myTank.setDir(Dir.LEFT);
			if(bR)
				myTank.setDir(Dir.RIGHT);
			if(bU)
				myTank.setDir(Dir.UP);
			if(bD)
				myTank.setDir(Dir.DOWN);
			
		}
	}
	
}
