package view;

import javax.swing.*;

import model.active.Hunter;

public class HunterButton extends JButton {
    private Hunter h;
    private ImageIcon tubeR = new ImageIcon("./resourceFolder/image/tubeRight1.png");
    private ImageIcon tubeL = new ImageIcon("./resourceFolder/image/tubeLeft1.png");

    //HunterButton(Hunter h)로 바꾸기, this.h=h; 주석해제 처리하기
    public HunterButton() {
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
}
