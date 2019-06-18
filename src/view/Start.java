package view;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import controller.ViewController;

public class Start extends JPanel{
	    private ImageIcon icon = new ImageIcon("./resourceFolder/image/startbg.png");
	    private Image bg=icon.getImage();
	    private Ost ost=new Ost();

	    private JButton login;
	    private JButton join;
	    
		private LoginView lv;
		private JoinView jv;
		
	    private ViewController F;
	    	public Start(ViewController f) {
	    		
	    		this.setSize(1280, 720);
	        	//ost.playmusic("./sound/mapbgm.wav");
	        	this.setLayout(null);
	
	        	this.F=f;
	        	login=new JButton("login");
	        	join=new JButton("join");

	        	
	        	login.setBounds(500,450,150,50);
	        	join.setBounds(700,450,150,50);
	        	
	        	login.setFont(new Font("µ¸¿òÃ¼", Font.BOLD, 16));
	        	join.setFont(new Font("µ¸¿òÃ¼", Font.BOLD, 16));
	        	
	        	login.setBackground(Color.PINK);
	        	join.setBackground(Color.GRAY);
	        	
	        	login.addActionListener(new ActionListener() {
	        		public void actionPerformed(ActionEvent e) {
	        			lv=new LoginView();
	        			if(lv.getFlag())
	        			F.getCardLayout().show(F.getContentPane(), "Map");
	        		}
	        	});
	        	join.addActionListener(new ActionListener() {
	        		public void actionPerformed(ActionEvent e) {
	        			jv=new JoinView();
	        		}
	        	});
	        	
	        	
	  
	        	add(login);
	        	add(join);
	        	
	        	this.setVisible(true);
	    	}
	    
	   	   public void paintComponent(Graphics g) {
	            // Display image at at full size
	   	        g.drawImage(bg, 0, 0, null);
	   	        setOpaque(false);
	   	        super.paintComponent(g);
	       }
	    
}
