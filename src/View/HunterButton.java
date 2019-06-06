package View;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import javax.swing.*;

import model.active.Hunter;

public class HunterButton extends JButton {
    private Hunter h;
    private ImageIcon tubeR=new ImageIcon("../resourceFolder/image/tubeRight1.png");
    private ImageIcon tubeL=new ImageIcon("../resourceFolder/image/tubeLeft1.png");
   

 


    public HunterButton(Hunter h) {
    	this.h=h;
    	//this.setText(h.getName());
    	
    	this.setIcon(tubeR);
    	this.setBorderPainted(false);
    	this.setContentAreaFilled(false);
    	this.setFocusPainted(false);
    	this.setVisible(true);
    	
    }
    public void PressRight() {
    	this.setIcon(tubeR);
    }
    public void PressLeft() {
    	this.setIcon(tubeL);
    }
   


}
