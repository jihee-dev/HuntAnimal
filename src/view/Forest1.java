package view;
import javax.swing.JButton;
import javax.swing.JPanel;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Forest1 extends JPanel implements KeyListener,Runnable {
	private JButton hb;
	private boolean KeyUp = false; //Ű���� �Է� ó���� ���� ����
	private boolean KeyDown = false;
	private boolean KeyLeft = false;
	private boolean KeyRight = false;
    private ViewController F;
	     

	    Thread th; // ������ ����
	public Forest1(ViewController f) {
		this.setLayout(null);
		F=f;
		this.setSize(1280,720);
		hb=new HunterButton();
		hb.setSize(180,280);
		hb.setLocation(100,100);
		hb.addKeyListener(this); //Ű���� �̺�Ʈ ����
    	th = new Thread(this);  // ������ ����
    	th.start();  // ������ ���� 	
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
	  //������ �޾Ƶ��� Ű���� �������� 5��ƴ �̵���Ų��.
	  public void KeyProcess(){
	    if(KeyUp == true) hb.setBounds(hb.getX(),hb.getY()-5, hb.getWidth(),hb.getHeight());
	    if(KeyDown == true) hb.setBounds(hb.getX(),hb.getY()+5, hb.getWidth(),hb.getHeight());
	    if(KeyLeft == true) hb.setBounds(hb.getX()-5,hb.getY(), hb.getWidth(),hb.getHeight());
	    if(KeyRight == true) hb.setBounds(hb.getX()+5,hb.getY(), hb.getWidth(),hb.getHeight());
	  }
		public void run() {
			try{ 
				while(true){ 
					KeyProcess(); // Ű���� �Է�ó���� �Ͽ� x,y ����
					Thread.sleep(20); // 20 milli sec �� ������ ������ //hunter�� Animal�� speed���� 20 ��� ��
				}
			}catch (Exception e){}
		}

	
	
}
