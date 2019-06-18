package view;

import javax.swing.*;

import model.active.Hunter;

public class HunterButton extends JButton {
    private Hunter h;
    private ImageIcon tubeR = new ImageIcon("./resourceFolder/image/hunter/tubeRight1.png");
    private ImageIcon tubeL = new ImageIcon("./resourceFolder/image/hunter/tubeLeft1.png");

    //HunterButton(Hunter h)로 바꾸기, this.h=h; 주석해제 처리하기
    public HunterButton(Hunter h) {
        this.h = h;
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
    public void PressRight(ImageIcon img) {
    	this.setIcon(img);
    }
    public void PressLeft(ImageIcon img) {
    	this.setIcon(img);
    }
    public void PressRight(int index) {
    	this.setIcon(new ImageIcon(h.getBtmImg(index)));
    }
    public void PressLeft(int index) {
    	this.setIcon(new ImageIcon(h.getBtmImg(index)));
    }
    
}