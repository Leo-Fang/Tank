package tank;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

	Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);
	List<Bullet> bullets = new ArrayList<>();
	List<Tank> tanks = new ArrayList<>();
	List<Explode> explodes = new ArrayList<>();
	
	public GameModel(){
		int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
		
		//��ʼ���ط�̹��
		for (int i = 0; i < initTankCount; i++) {
			tanks.add(new Tank(10+i*80, 200, Dir.DOWN, Group.BAD, this));
		}
	}
	
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("�ӵ�������"+bullets.size(), 10, 60);
		g.drawString("���˵�����"+tanks.size(), 10, 80);
		g.drawString("��ը������"+explodes.size(), 10, 100);
		g.setColor(c);
		
		myTank.paint(g);
		//���ӵ�
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).paint(g);
		}
		//��̹��
		for (int i = 0; i < tanks.size(); i++) {
			tanks.get(i).paint(g);
		}
		//��ײ���
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < tanks.size(); j++) {
				bullets.get(i).collideWith(tanks.get(j));
			}
		}
		//����ը
		for (int i = 0; i < explodes.size(); i++) {
			explodes.get(i).paint(g);
		}
	}

	public Tank getMainTank() {
		return myTank;	
	}

}
