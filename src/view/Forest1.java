package view;
import javax.swing.JButton;
import javax.swing.JPanel;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Forest1 extends JPanel implements KeyListener,Runnable {
	private JButton hb;
	private boolean KeyUp = false; //키보드 입력 처리를 위한 변수
	private boolean KeyDown = false;
	private boolean KeyLeft = false;
	private boolean KeyRight = false;
    private ViewController F;
	     

	    Thread th; // 스레드 생성
	public Forest1(ViewController f) {
		this.setLayout(null);
		F=f;
		this.setSize(1280,720);
		hb=new HunterButton();
		hb.setSize(180,280);
		hb.setLocation(100,100);
		hb.addKeyListener(this); //키보드 이벤트 실행
    	th = new Thread(this);  // 스레드 생성
    	th.start();  // 스레드 실행 	
		this.add(hb);
		this.setVisible(true);
			
	}
	
	 public void keyTyped(KeyEvent e){
	    }

	 public void keyPressed(KeyEvent e){
		 switch(e.getKeyCode()){
	    	case KeyEvent.VK_UP :
	    		KeyUp = true;
	    		break;
	    	case KeyEvent.VK_DOWN :
	    		KeyDown = true;
	    		break;
	    	case KeyEvent.VK_LEFT :
	    		KeyLeft = true;
	    		((HunterButton) hb).PressLeft();
	    		break;
	    	case KeyEvent.VK_RIGHT :
	    		KeyRight = true;
	    		((HunterButton) hb).PressRight();
	    		break;
	    	default:
	    		break;
	    	}

	    }

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
	  //위에서 받아들인 키값을 바탕으로 5만틈 이동시킨다.
	  public void KeyProcess(){
	    if(KeyUp == true) hb.setBounds(hb.getX(),hb.getY()-5, hb.getWidth(),hb.getHeight());
	    if(KeyDown == true) hb.setBounds(hb.getX(),hb.getY()+5, hb.getWidth(),hb.getHeight());
	    if(KeyLeft == true) hb.setBounds(hb.getX()-5,hb.getY(), hb.getWidth(),hb.getHeight());
	    if(KeyRight == true) hb.setBounds(hb.getX()+5,hb.getY(), hb.getWidth(),hb.getHeight());
	  }
		public void run() {
			try{ 
				while(true){ 
					KeyProcess(); // 키보드 입력처리를 하여 x,y 갱신
					Thread.sleep(20); // 20 milli sec 로 스레드 돌리기 //hunter와 Animal의 speed값이 20 대신 들어감
				}
			}catch (Exception e){}
		}

	
	
}
