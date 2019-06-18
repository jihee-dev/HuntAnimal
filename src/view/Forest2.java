package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ViewController;
import model.active.Animal;
import model.active.Hunter;
import model.active.HunterDog;
import model.active.Mushroom;
import model.active.Prey;
import model.item.Gun;
import model.item.Item;
import model.item.Net;
import model.item.Trap;
import model.map.Forest;

public class Forest2 extends JPanel implements KeyListener, Runnable, ActionListener{
	private ViewController F;
	private Forest fModel;
	private Image background = new ImageIcon("./resourceFolder/image/forest2_background.png").getImage();
	
	// Ű���� �Է� ó���� ���� ������
	private boolean KeyUp = false; 
	private boolean KeyDown = false;
	private boolean KeyLeft = false;
	private boolean KeyRight = false;

	// Ÿ�̸� ���� �ʵ�
	private JLabel timerLabel;
	Thread timerTh; 	
//	Timer m_timer;
//	TimerTask m_task;
	
	Thread th; // ��ɲ� ������ ������
	
	// ��� ������
	CrashTh cTh; // hunter
	Thread crashTh;
	CrashTh cTh2; // trap
	Thread crashTh2;
	CrashTh cTh3; // hunterdog
	Thread crashTh3; 
	
	private JButton hb; // ��ɲ�
	private ArrayList<AnimalButton> animals; // ���� ����Ʈ (���� 4������ �����ϱ�)
	private ArrayList<Animal> prey; // forest model���� �޾ƿ� ���� ����Ʈ
	private JButton mushroom; // ����
	private JButton hd; // ��ɰ�
	
	// ������ ���� �г� ����
	private JPanel itemPanel;
	private JLabel itemLabel;
	private JButton trapBtn, netBtn, gunBtn; // ������ ���� �гο��� Ŭ���� ��ư��
	private JButton trap; // ���� �������� Ʈ�� �̹��� (��ư)
	
	// hunter�� ��ɻ��¸� �˷��� ������: ��뿩�� ����
	private boolean isUsingTrap = false;
	private boolean isUsingNet = false;
	private boolean isUsingGun = false;
	
	// CONSTRUCTOR ///////////////////////////////////////////////////////////////////
	public Forest2(ViewController f) {
		
		this.setLayout(null);
		this.setSize(1280,720);
		this.F = f;
		
		// Ÿ�̸� ������ ���� �ʱ�ȭ
		timerLabel = new JLabel();
		timerLabel.setForeground(Color.RED);
		timerLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.BOLD, 30));
		timerLabel.setBounds(10, 10, 80, 40);
		this.add(timerLabel);
		timerTh = new Thread() {
			@Override
			public void run() {
				int n = 30;
				while(n >= 0) {
					try {
						timerLabel.setText(Integer.toString(n));
						Thread.sleep(1000);
						n--;
						if(n < 0) {
							System.out.println("Ÿ�̸� ������ ����");
							F.getCardLayout().show(F.getContentPane(), "Map");
							this.interrupt();
						}
					} catch(InterruptedException e) {}
				}
			}
		};
		timerTh.start();
		// ��ɲ� ����
		hb = new HunterButton(Hunter.getInstance());
		hb.setBounds(100, 100, 180, 280);
		hb.addKeyListener(this); // Ű���� �̺�Ʈ ����
		this.add(hb);
		th = new Thread(this); // ������ ����
		th.start();
		
		// ���� ����
	    animals = new ArrayList<AnimalButton>(); // ���� ��ư ������ arraylist (forest1������)
	    prey = new ArrayList<Animal>(); // forest model���� �޾ƿ� ��������Ʈ
	    fModel = new Forest("Forest2");
	    fModel.setPrey(prey);
	    fModel.enter(2);
	    for(Animal ani: prey) {
	    	AnimalButton aniB = new AnimalButton((Prey)ani);
	    	if(ani.getActionInfo().getName().equals("Lion")) {
	    		ImageIcon lionR= new ImageIcon("./resourceFolder/image/animal/LionRight.png");
	    		aniB.setSize(300, 152); // �罿 �̹��� ������
		    	aniB.setIcon(lionR);
	    	} else {
		    	ImageIcon tigerR = new ImageIcon("./resourceFolder/image/animal/TigerRight.png");
	    		aniB.setSize(300, 182); // �䳢 �̹��� ������
		    	aniB.setIcon(tigerR);
	    	}
	    	// ���� ���� ��ġ
	    	int randX = (int)(Math.random() * 900) + 1;
	    	int randY = (int)(Math.random() * 350) + 1;
	    	aniB.setLocation(randX, randY);
	    	
	    	// ���� ��ư ������ ������ �ޱ�
	    	MovingTh movingAni = new MovingTh(aniB);
	    	Thread aniTh = new Thread(movingAni);
	    	aniTh.start();
	    	
	    	animals.add(aniB); 
	    	this.add(aniB);
	    }
	    
	 // ���� ����
	 	mushroom = new AnimalButton(new Mushroom()); 
	 	mushroom.setSize(100, 98);
	 	mushroom.setLocation(700, 500);
	 	mushroom.setIcon(new ImageIcon("./resourceFolder/image/animal/mushroomRight.png"));
	 	this.add(mushroom);
	 	MovingTh movingMsh = new MovingTh((AnimalButton)mushroom);
	 	Thread mshTh = new Thread(movingMsh);
	 	mshTh.start();
	 	
	 	// ��ɰ� ����
	 	hd = new AnimalButton(HunterDog.getInstance());
	 	hd.setIcon(new ImageIcon("./resourceFolder/image/animal/HoundRight.png"));
	 	hd.setSize(230, 190);
	 	hd.setLocation(400, 400);
	 	this.add(hd);
	 	MovingTh movingHd = new MovingTh((AnimalButton)hd);
	 	Thread hdTh = new Thread(movingHd);
	 	hdTh.start();
	 	
	 // �浹 ������ ���� �ʱ�ȭ
	 	cTh = new CrashTh(hb, animals); // hunter�� ���� �浹
		crashTh = new Thread(cTh);
		crashTh.start();
		cTh2 = new CrashTh(trap, animals); // trap�� ���� �浹
		crashTh2 = new Thread(cTh2);
		crashTh2.start();
		cTh3 = new CrashTh(hd, animals); // hunterdog�� ���� �浹
	 	crashTh3 = new Thread(cTh3);
	 	crashTh3.start();
	 	
	 	trap = new JButton(); // Ʈ�� ��ư ����
	 			
	 	// ������ ���� ���� �ʱ�ȭ
	 	itemPanel = new JPanel();
	 	itemPanel.setBounds(0, 600, 1280, 120);
	 	itemPanel.setBackground(Color.YELLOW);
	 	this.add(itemPanel);
	 	itemLabel = new JLabel();
	 	itemLabel.setForeground(Color.BLACK);
	 	itemLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.BOLD, 20));
	 	itemLabel.setBounds(10, 630, 200, 30);
	 	itemLabel.setText("Select item!");
	 	itemPanel.add(itemLabel);
	 	trapBtn = new JButton("Trap"); 
	 	trapBtn.setBounds(400, 630, 50, 50);
	 	trapBtn.addActionListener(this);
	 	itemPanel.add(trapBtn);
	 	netBtn = new JButton("Net"); 
	 	netBtn.setBounds(600, 630, 50, 50); 
	 	netBtn.addActionListener(this);
	 	itemPanel.add(netBtn);
	 	gunBtn = new JButton("Gun"); 
	 	gunBtn.setBounds(800, 630, 50, 50); 
	 	gunBtn.addActionListener(this);
	 	itemPanel.add(gunBtn);	
	 	itemPanel.setVisible(true);

 		this.setVisible(true);
	}
	
	// hb�� ������ ���� �޼ҵ� /////////////////////////////////////////////////////////////
	@Override public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP :
	    		KeyUp = true;
	    		break;
	    	case KeyEvent.VK_DOWN :
	    		KeyDown = true;
	    		break;
	    	case KeyEvent.VK_LEFT :
	    		KeyLeft = true;
	    		if(isUsingNet) {
	    			hb.setSize(270, 270);
	    			hb.setIcon(new ImageIcon("./resourceFolder/image/hunter/tubeNetLeft.png"));
	    			System.out.println("net - left"); // test
	    		}
	    		else if(isUsingGun){
	    			hb.setSize(270, 270);
	    			hb.setIcon(new ImageIcon("./resourceFolder/image/hunter/tubeGunLeft.png"));
	    			System.out.println("gun - left"); // test
	    		}
	    		else { // trap || w/o item
	    			hb.setSize(180, 280);
	    			hb.setIcon(new ImageIcon("./resourceFolder/image/hunter/tubeLeft1.png"));
	    			System.out.println("default - left");
	    		}
	    		break;
	    	case KeyEvent.VK_RIGHT :
	    		KeyRight = true;
	    		if(isUsingNet) {
	    			hb.setSize(270, 270);
	    			hb.setIcon(new ImageIcon("./resourceFolder/image/hunter/tubeNetRight.png"));
	    			System.out.println("net - right"); // test
	    		}
	    		else if(isUsingGun){
	    			hb.setSize(270, 270);
	    			hb.setIcon(new ImageIcon("./resourceFolder/image/hunter/tubeGunRight.png"));
	    			System.out.println("gun - right"); // test
	    		}
	    		else { // trap || w/o item
	    			hb.setSize(180, 280);
	    			hb.setIcon(new ImageIcon("./resourceFolder/image/hunter/tubeRight1.png"));
	    			System.out.println("default - right");
	    		}
	    		break;
	    	default: break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e){
		switch(e.getKeyCode()){
	    	case KeyEvent.VK_UP :
	    		KeyUp = false;
	    		break;
	    	case KeyEvent.VK_DOWN :
	    		KeyDown = false;
	    		break;
	    	case KeyEvent.VK_LEFT :
	    		KeyLeft = false;
	    		break;
	    	case KeyEvent.VK_RIGHT :
	    		KeyRight = false;
	    		break;
		}
	}
	// ������ �޾Ƶ��� Ű���� �������� 5��ŭ �̵���Ŵ
	public void KeyProcess(){
		 if(KeyUp == true) hb.setBounds(hb.getX(),hb.getY()-5, hb.getWidth(),hb.getHeight());
		 if(KeyDown == true) hb.setBounds(hb.getX(),hb.getY()+5, hb.getWidth(),hb.getHeight());
		 if(KeyLeft == true) hb.setBounds(hb.getX()-5,hb.getY(), hb.getWidth(),hb.getHeight());
		 if(KeyRight == true) hb.setBounds(hb.getX()+5,hb.getY(), hb.getWidth(),hb.getHeight());
	 }
	// HunterButton �̵� ������
	public void run() {
		try {
			while(true){
				KeyProcess(); // Ű���� �Է�ó���� �Ͽ� x,y ����
				Thread.sleep(Hunter.getInstance().getActionInfo().getDelay());
			}
		} catch (Exception e){}
	}
	
	// ����ϱ� ////////////////////////////////////////////////////////////////////////
	
	// �������� Ȱ���� ����ϴ� ���: �����۹�ư Ŭ��
	@Override
	public void actionPerformed(ActionEvent e) {
		Item[] currentItem = Hunter.getInstance().getItems();
		// ��ư�� ������ ��ɲ��� �ش� �������� ���������� ����
		if(e.getSource() == trapBtn && currentItem[0] != null && isUsingTrap == false) { 
			setTrap();
		}
		else if(e.getSource() == netBtn && currentItem[1] != null && isUsingNet == false) {
			setNet();
		}
		else if(e.getSource() == gunBtn &&  currentItem[2] != null && isUsingGun == false) {
			setGun();
		}
	}
		// Ʈ�� ��ġ�ϱ�
	public void setTrap() {
		// Ʈ�� �̹��� ����
		trap.setSize(150, 150);
		ImageIcon trapB = new ImageIcon("./resourceFolder/image/store/trapButton.png");
		trap.setIcon(trapB);
		trap.setBorderPainted(false);
    	trap.setContentAreaFilled(false);
    	trap.setFocusPainted(false);
    	
    	trap.setVisible(false); // ��ġ�� �����ϱ� ������ �Ⱥ��̵���
    	this.add(trap);
    	
		// ������ �󿡼� ���ϴ� ��ġ�� ���콺�� Ŭ���ϸ� Ʈ���� ��ġ
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				trap.setLocation(x, y);
				trap.setVisible(true);
				removeMouseListener(this); // ��ġ�� �������� ������ ����
				System.out.println("trap is set! - mouse listener is removed");
			}
			@Override public void mouseEntered(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}
			@Override public void mousePressed(MouseEvent e) {}
			@Override public void mouseReleased(MouseEvent e) {}
		});
		this.isUsingTrap = true;
		this.isUsingNet = false;
		this.isUsingGun = false;
		this.setVisible(true); // ��ġ�� ������ �����ֱ�
		// trap�� crash ������ �ޱ�
		cTh2 = new CrashTh(trap, animals);
		crashTh2 = new Thread(cTh2);
		crashTh2.start();
	
		// Ʈ�� ��� ������ ����
		Hunter.getInstance().useItem(Trap.getInstance());
	}	
	
	// �׹��� �Բ� ����ϱ�: hunter�� �̹����� �ٲ� ��ɲ��� range�� �����ֱ�
	public void setNet() {
		// hunter �̹��� �ٲ��ֱ�
		ImageIcon netR = new ImageIcon("./resourceFolder/image/hunter/tubeNetRight.png");
		ImageIcon netL = new ImageIcon("./resourceFolder/image/hunter/tubeNetLeft.png");
		hb.setSize(270, 270);
		hb.setIcon(netR);
		this.isUsingNet = true;
		this.isUsingTrap = false;
		this.isUsingGun = false;
		
		// �׹� ��� ������ ����
		Hunter.getInstance().useItem(Net.getInstance());
		System.out.println("hunt with a net"); // test
	}
	
	// ������ ����ϱ�
	public void setGun() {
		// hunter�� �̹��� �ٲ��ֱ�
		ImageIcon gunR = new ImageIcon("./resourceFolder/image/hunter/tubeGunRight.png");
		ImageIcon gunL = new ImageIcon("./resourceFolder/image/hunter/tubeGunLeft.png");
		hb.setSize(270, 270);
		hb.setIcon(gunR);
		// ��� ���� ��ư�� ���콺 ������ �ޱ�
		for(AnimalButton aniB: animals) { 
			aniB.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("gun - hunt success!");
					HuntSuccess(aniB);
					removeMouseListener(this); // ��ɵ� �������� ������ ����
				}
				@Override public void mouseEntered(MouseEvent arg0) {}
				@Override public void mouseExited(MouseEvent arg0) {}
				@Override public void mousePressed(MouseEvent arg0) {}
				@Override public void mouseReleased(MouseEvent arg0) {}				
			});
		}
		this.isUsingGun = true;
		this.isUsingTrap = false;
		this.isUsingNet = false;
		// �� ��� ������ �������ֱ�
		Hunter.getInstance().useItem(Gun.getInstance());
		System.out.println("hunt with a gun"); // test
	}
	
	// ��� ���� �� ���� ó�� ////////////////////////////////////////////////////////////////////////
	public void HuntSuccess(AnimalButton aniB) {
		aniB.setCaptured(true); // animal�� ��ȹ���¸� ����
		Hunter.getInstance().getPrison().add(aniB.getAni()); // hunter�� prison�� ���� �ֱ� (type casting error �ذ��ϱ�)
		System.out.println("prison�� ����!");
		for(int i = 0; i < Hunter.getInstance().getPrison().size(); i++) { // prison�� �� ������ test
			System.out.print(i + ": " + Hunter.getInstance().getPrison().get(i).getActionInfo().getName() + " ");
		}
		System.out.println();
		aniB.setVisible(false);
		aniB.setEnabled(false); // ���� ������ ��ư�� ������� �ϱ�
		this.remove(aniB);
	}
	
	
	// ����ȭ�� ���� //////////////////////////////////////////////////////////////////////////////
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}

}