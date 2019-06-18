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
	
	// 키보드 입력 처리를 위한 변수들
	private boolean KeyUp = false; 
	private boolean KeyDown = false;
	private boolean KeyLeft = false;
	private boolean KeyRight = false;

	// 타이머 관련 필드
	private JLabel timerLabel;
	Thread timerTh; 	
//	Timer m_timer;
//	TimerTask m_task;
	
	Thread th; // 사냥꾼 움직임 스레드
	
	// 사냥 스레드
	CrashTh cTh; // hunter
	Thread crashTh;
	CrashTh cTh2; // trap
	Thread crashTh2;
	CrashTh cTh3; // hunterdog
	Thread crashTh3; 
	
	private JButton hb; // 사냥꾼
	private ArrayList<AnimalButton> animals; // 동물 리스트 (각각 4마리씩 생성하기)
	private ArrayList<Animal> prey; // forest model에서 받아온 동물 리스트
	private JButton mushroom; // 버섯
	private JButton hd; // 사냥개
	
	// 아이템 선택 패널 관련
	private JPanel itemPanel;
	private JLabel itemLabel;
	private JButton trapBtn, netBtn, gunBtn; // 아이템 선택 패널에서 클릭할 버튼들
	private JButton trap; // 실제 숲에서의 트랩 이미지 (버튼)
	
	// hunter의 사냥상태를 알려줄 변수들: 사용여부 저장
	private boolean isUsingTrap = false;
	private boolean isUsingNet = false;
	private boolean isUsingGun = false;
	
	// CONSTRUCTOR ///////////////////////////////////////////////////////////////////
	public Forest2(ViewController f) {
		
		this.setLayout(null);
		this.setSize(1280,720);
		this.F = f;
		
		// 타이머 스레드 관련 초기화
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
							System.out.println("타이머 스레드 종료");
							F.getCardLayout().show(F.getContentPane(), "Map");
							this.interrupt();
						}
					} catch(InterruptedException e) {}
				}
			}
		};
		timerTh.start();
		// 사냥꾼 생성
		hb = new HunterButton(Hunter.getInstance());
		hb.setBounds(100, 100, 180, 280);
		hb.addKeyListener(this); // 키보드 이벤트 실행
		this.add(hb);
		th = new Thread(this); // 스레드 생성
		th.start();
		
		// 동물 생성
	    animals = new ArrayList<AnimalButton>(); // 동물 버튼 생성용 arraylist (forest1에서만)
	    prey = new ArrayList<Animal>(); // forest model에서 받아올 동물리스트
	    fModel = new Forest("Forest2");
	    fModel.setPrey(prey);
	    fModel.enter(2);
	    for(Animal ani: prey) {
	    	AnimalButton aniB = new AnimalButton((Prey)ani);
	    	if(ani.getActionInfo().getName().equals("Lion")) {
	    		ImageIcon lionR= new ImageIcon("./resourceFolder/image/animal/LionRight.png");
	    		aniB.setSize(300, 152); // 사슴 이미지 사이즈
		    	aniB.setIcon(lionR);
	    	} else {
		    	ImageIcon tigerR = new ImageIcon("./resourceFolder/image/animal/TigerRight.png");
	    		aniB.setSize(300, 182); // 토끼 이미지 사이즈
		    	aniB.setIcon(tigerR);
	    	}
	    	// 동물 랜덤 배치
	    	int randX = (int)(Math.random() * 900) + 1;
	    	int randY = (int)(Math.random() * 350) + 1;
	    	aniB.setLocation(randX, randY);
	    	
	    	// 동물 버튼 움직임 스레드 달기
	    	MovingTh movingAni = new MovingTh(aniB);
	    	Thread aniTh = new Thread(movingAni);
	    	aniTh.start();
	    	
	    	animals.add(aniB); 
	    	this.add(aniB);
	    }
	    
	 // 버섯 생성
	 	mushroom = new AnimalButton(new Mushroom()); 
	 	mushroom.setSize(100, 98);
	 	mushroom.setLocation(700, 500);
	 	mushroom.setIcon(new ImageIcon("./resourceFolder/image/animal/mushroomRight.png"));
	 	this.add(mushroom);
	 	MovingTh movingMsh = new MovingTh((AnimalButton)mushroom);
	 	Thread mshTh = new Thread(movingMsh);
	 	mshTh.start();
	 	
	 	// 사냥개 생성
	 	hd = new AnimalButton(HunterDog.getInstance());
	 	hd.setIcon(new ImageIcon("./resourceFolder/image/animal/HoundRight.png"));
	 	hd.setSize(230, 190);
	 	hd.setLocation(400, 400);
	 	this.add(hd);
	 	MovingTh movingHd = new MovingTh((AnimalButton)hd);
	 	Thread hdTh = new Thread(movingHd);
	 	hdTh.start();
	 	
	 // 충돌 스레드 관련 초기화
	 	cTh = new CrashTh(hb, animals); // hunter과 동물 충돌
		crashTh = new Thread(cTh);
		crashTh.start();
		cTh2 = new CrashTh(trap, animals); // trap과 동물 충돌
		crashTh2 = new Thread(cTh2);
		crashTh2.start();
		cTh3 = new CrashTh(hd, animals); // hunterdog과 동물 충돌
	 	crashTh3 = new Thread(cTh3);
	 	crashTh3.start();
	 	
	 	trap = new JButton(); // 트랩 버튼 생성
	 			
	 	// 아이템 선택 관련 초기화
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
	
	// hb의 움직임 관련 메소드 /////////////////////////////////////////////////////////////
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
	// 위에서 받아들인 키값을 바탕으로 5만큼 이동시킴
	public void KeyProcess(){
		 if(KeyUp == true) hb.setBounds(hb.getX(),hb.getY()-5, hb.getWidth(),hb.getHeight());
		 if(KeyDown == true) hb.setBounds(hb.getX(),hb.getY()+5, hb.getWidth(),hb.getHeight());
		 if(KeyLeft == true) hb.setBounds(hb.getX()-5,hb.getY(), hb.getWidth(),hb.getHeight());
		 if(KeyRight == true) hb.setBounds(hb.getX()+5,hb.getY(), hb.getWidth(),hb.getHeight());
	 }
	// HunterButton 이동 스레드
	public void run() {
		try {
			while(true){
				KeyProcess(); // 키보드 입력처리를 하여 x,y 갱신
				Thread.sleep(Hunter.getInstance().getActionInfo().getDelay());
			}
		} catch (Exception e){}
	}
	
	// 사냥하기 ////////////////////////////////////////////////////////////////////////
	
	// 아이템을 활용해 사냥하는 경우: 아이템버튼 클릭
	@Override
	public void actionPerformed(ActionEvent e) {
		Item[] currentItem = Hunter.getInstance().getItems();
		// 버튼을 누르고 사냥꾼이 해당 아이템을 구매했으면 실행
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
		// 트랩 설치하기
	public void setTrap() {
		// 트랩 이미지 설정
		trap.setSize(150, 150);
		ImageIcon trapB = new ImageIcon("./resourceFolder/image/store/trapButton.png");
		trap.setIcon(trapB);
		trap.setBorderPainted(false);
    	trap.setContentAreaFilled(false);
    	trap.setFocusPainted(false);
    	
    	trap.setVisible(false); // 위치를 선택하기 전까지 안보이도록
    	this.add(trap);
    	
		// 프레임 상에서 원하는 위치에 마우스를 클릭하면 트랩을 설치
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				trap.setLocation(x, y);
				trap.setVisible(true);
				removeMouseListener(this); // 설치한 다음에는 리스너 제거
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
		this.setVisible(true); // 설치한 다음에 보여주기
		// trap에 crash 스레드 달기
		cTh2 = new CrashTh(trap, animals);
		crashTh2 = new Thread(cTh2);
		crashTh2.start();
	
		// 트랩 사용 내역을 전달
		Hunter.getInstance().useItem(Trap.getInstance());
	}	
	
	// 그물과 함께 사냥하기: hunter의 이미지를 바꿔 사냥꾼의 range를 넓혀주기
	public void setNet() {
		// hunter 이미지 바꿔주기
		ImageIcon netR = new ImageIcon("./resourceFolder/image/hunter/tubeNetRight.png");
		ImageIcon netL = new ImageIcon("./resourceFolder/image/hunter/tubeNetLeft.png");
		hb.setSize(270, 270);
		hb.setIcon(netR);
		this.isUsingNet = true;
		this.isUsingTrap = false;
		this.isUsingGun = false;
		
		// 그물 사용 내역을 전달
		Hunter.getInstance().useItem(Net.getInstance());
		System.out.println("hunt with a net"); // test
	}
	
	// 총으로 사냥하기
	public void setGun() {
		// hunter의 이미지 바꿔주기
		ImageIcon gunR = new ImageIcon("./resourceFolder/image/hunter/tubeGunRight.png");
		ImageIcon gunL = new ImageIcon("./resourceFolder/image/hunter/tubeGunLeft.png");
		hb.setSize(270, 270);
		hb.setIcon(gunR);
		// 모든 동물 버튼에 마우스 리스너 달기
		for(AnimalButton aniB: animals) { 
			aniB.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("gun - hunt success!");
					HuntSuccess(aniB);
					removeMouseListener(this); // 사냥된 다음에는 리스너 제거
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
		// 총 사용 내역을 전달해주기
		Hunter.getInstance().useItem(Gun.getInstance());
		System.out.println("hunt with a gun"); // test
	}
	
	// 사냥 성공 후 동물 처리 ////////////////////////////////////////////////////////////////////////
	public void HuntSuccess(AnimalButton aniB) {
		aniB.setCaptured(true); // animal의 포획상태를 저장
		Hunter.getInstance().getPrison().add(aniB.getAni()); // hunter의 prison에 동물 넣기 (type casting error 해결하기)
		System.out.println("prison에 저장!");
		for(int i = 0; i < Hunter.getInstance().getPrison().size(); i++) { // prison에 잘 들어갔는지 test
			System.out.print(i + ": " + Hunter.getInstance().getPrison().get(i).getActionInfo().getName() + " ");
		}
		System.out.println();
		aniB.setVisible(false);
		aniB.setEnabled(false); // 잡힌 동물의 버튼이 사라지게 하기
		this.remove(aniB);
	}
	
	
	// 바탕화면 구성 //////////////////////////////////////////////////////////////////////////////
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}

}