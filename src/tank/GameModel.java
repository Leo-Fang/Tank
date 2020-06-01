package tank;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import tank.cor.ColliderChain;

public class GameModel {

	private static final GameModel INSTANCE = new GameModel();
	static {
		INSTANCE.init();
	}

	Tank myTank;
//	List<Bullet> bullets = new ArrayList<>();
//	List<Tank> tanks = new ArrayList<>();
//	List<Explode> explodes = new ArrayList<>();
	
	private List<GameObject> objects = new ArrayList<>();
	
	ColliderChain chain = new ColliderChain();
	
	private GameModel() {}
	private void init(){
		//��ʼ����վ̹��
		myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD);
		
		int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
		
		//��ʼ���ط�̹��
		for (int i = 0; i < initTankCount; i++) {
			add(new Tank(10+i*80, 200, Dir.DOWN, Group.BAD));
		}
		
		//��ʼ��ǽ
		add(new Wall(150, 150, 200, 50));
		add(new Wall(550, 150, 200, 50));
		add(new Wall(300, 300, 50, 200));
		add(new Wall(550, 300, 50, 200));
	}
	
	public void add(GameObject go) {
		this.objects.add(go);
	}
	
	public void remove(GameObject go) {
		this.objects.remove(go);
	}
	
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
//		g.drawString("�ӵ�������"+bullets.size(), 10, 60);
//		g.drawString("���˵�����"+tanks.size(), 10, 80);
//		g.drawString("��ը������"+explodes.size(), 10, 100);
		g.setColor(c);
		
		myTank.paint(g);

		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).paint(g);
		}
		
		//��ײ���
//		for (int i = 0; i < bullets.size(); i++) {
//			for (int j = 0; j < tanks.size(); j++) {
//				bullets.get(i).collideWith(tanks.get(j));
//			}
//		}
		
		for (int i = 0; i < objects.size()-1; i++) {
			for (int j = i+1; j < objects.size(); j++) {
				GameObject o1 = objects.get(i);
				GameObject o2 = objects.get(j);
				chain.collide(o1, o2);
			}
		}
		
	}

	public Tank getMainTank() {
		return myTank;	
	}

	public static GameModel getInstance() {
		return INSTANCE;		
	}

}
