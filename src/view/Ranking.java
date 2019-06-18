package view;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;

import controller.ViewController;
import model.map.FileIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;



public class Ranking extends JFrame{

    private ImageIcon icon = new ImageIcon("./resourceFolder/image/rankingbg.png");
    private Image bg = icon.getImage();
    private JPanel back;
    private JScrollPane scrollPane;
    private Timer m_timer;
    private TimerTask m_task;
    private ViewController F;
    private FileIO fileio=FileIO.getInstance();
    private JLabel[] ranks;

    public Ranking() {
    	this.setLayout(null);
    	this.setSize(1280, 720);
    	
    	
    	ranks=new JLabel[3];
    	JLabel lblRanking = new JLabel("Ranking!");
    	lblRanking.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
    	lblRanking.setBounds(575, 26, 162, 38);
    	
    	back=new JPanel(){
    		public void paintComponent(Graphics g) {
    	        // Display image at at full size
    	        g.drawImage(bg, 0, 0, null);
    	        setOpaque(false);
    	        super.paintComponent(g);
    		}
    	    };
    	back.setLayout(null);
    	back.add(lblRanking);
    	

    	
    	
    	this.fileio.sortRank();
    	  for (int i = 0; i < 3; i++) {
    		  String str=(i + 1) + ". " + fileio.getInfo().get(i).getId() + " " + fileio.getInfo().get(i).getAsset();
              ranks[i]=new JLabel(str);
              ranks[i].setBounds(520,50+(i+1)*100,500,150);
              ranks[i].setFont(new Font("Times New Roman",Font.BOLD,20));
              back.add(ranks[i]);
          }
    	  m_timer=new Timer();
          m_task =new TimerTask() {
          	public void run() {
          		System.exit(0);
          	}
          };
          m_timer.schedule(m_task, 11000);  
    	  
     
        scrollPane = new JScrollPane(back);
        setContentPane(scrollPane);
    	this.setVisible(true);
    	
    }

    
    
}
