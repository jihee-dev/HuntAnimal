package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.active.HunterDog;
import model.active.Mushroom;
import model.active.Prey;

public class AnimalButton extends JButton {

    private Prey ani;
    private Mushroom mush;
    private HunterDog hd;
    private boolean isCaptured;
    private ImageIcon img;

    public AnimalButton(Prey ani) {
        this.ani = ani;
        this.isCaptured = false;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setVisible(true);
    }

    public AnimalButton(Mushroom mush) {
        this.mush = mush;
        this.isCaptured = false;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setVisible(true);
    }

    public AnimalButton(HunterDog hd) {
        this.hd = hd;
        this.isCaptured = false;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setVisible(true);
    }

    public Prey getAni() {
        return this.ani;
    }

    public Mushroom getMush() {
        return this.mush;
    }

    public HunterDog getHd() {
        return this.hd;
    }

    public boolean isCaptured() {
        return isCaptured;
    }

    public void setCaptured(boolean isCaptured) {
        this.isCaptured = isCaptured;
    }
}