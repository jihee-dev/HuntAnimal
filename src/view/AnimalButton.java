package View;

import javax.swing.JButton;

import model.active.Animal;

public class AnimalButton extends JButton {
	private Animal ani;
	private String tag;
	private boolean isCaptured;

	public AnimalButton(Animal ani) {
		this.ani = ani;
		// this.setText(ani.getName());
		
		this.tag = "";
		this.isCaptured = false;
		
//		this.setIcon(각 동물별 이미지);
//		this.setBorderPainted(false);
//    	this.setContentAreaFilled(false);
//    	this.setFocusPainted(false);
    	this.setVisible(true);
	}
	
	// getter and setters
	public String getTag() { return tag; }
	public void setTag(String tag) { this.tag = tag; }
	public boolean isCaptured() { return isCaptured; }
	public void setCaptured(boolean isCaptured) { this.isCaptured = isCaptured; }
	
}
