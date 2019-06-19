package view;

import javax.swing.*;

import controller.ViewController;

import java.util.TimerTask;
import java.awt.event.*;
import java.awt.*;

import model.active.Hunter;
import model.map.FileIO;
import model.map.Map;
import java.util.Timer;
public class MapSelect extends JPanel {
    Map map;
  
    JButton f1, f2, shop, zoo, exit;
    JButton hb = new HunterButton(Hunter.getInstance());
    private FileIO fileio=FileIO.getInstance();
    // 멤버 필드에 ImageIcon 클래스 생성자    
    private ImageIcon icon = new ImageIcon("./resourceFolder/image/map4.PNG");
    private Image bg = icon.getImage();
    private Ost ost = new Ost();
    private ViewController F;
  
    private Timer m_timer;
    private TimerTask m_task;
    public MapSelect(ViewController f) {
    
        this.setSize(1280, 720);

        ost.playmusic("./resourceFolder/sound/mapbgm.wav");

        f1 = new JButton(new ImageIcon("./resourceFolder/image/Forest11.png"));
        f2 = new JButton(new ImageIcon("./resourceFolder/image/Forest22.png"));
        shop = new JButton(new ImageIcon("./resourceFolder/image/Shop1.png"));
        zoo = new JButton(new ImageIcon("./resourceFolder/image/Zoo1.png"));
        exit = new JButton(new ImageIcon("./resourceFolder/image/finish1.png"));
        // 백그라운드 이미지 삽입할 메소드에 이름없는 클래스로 구현

        this.setLayout(null);

        F = f;

        f1.setBorderPainted(false);
        f2.setBorderPainted(false);
        shop.setBorderPainted(false);
        zoo.setBorderPainted(false);


        f1.setContentAreaFilled(false);
        f2.setContentAreaFilled(false);
        shop.setContentAreaFilled(false);
        zoo.setContentAreaFilled(false);
        exit.setContentAreaFilled(false);

        f1.setFocusPainted(false);
        f2.setFocusPainted(false);
        shop.setFocusPainted(false);
        zoo.setFocusPainted(false);


        f1.setBounds(190, 140, 100, 100);
        f2.setBounds(460, 305, 100, 100);
        shop.setBounds(700, 500, 100, 100);
        zoo.setBounds(830, 290, 100, 100);
        exit.setBounds(1110, 540, 130, 130);

        f1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                System.out.println("f1 click!");
                ost.playmusic("./resourceFolder/sound/clickbgm.wav");
                F.getContentPane().add("f1", new Forest1(f));
                F.getCardLayout().show(F.getContentPane(), "f1");
               
;            }
        });
        f2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                System.out.println("f2 click!");
                ost.playmusic("./resourceFolder/sound/clickbgm.wav");
                F.getCardLayout().show(F.getContentPane(), "f2");
            }
        });
        shop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                System.out.println("shop click!");
                ost.playmusic("./resourceFolder/sound/clickbgm.wav");
                F.getCardLayout().show(F.getContentPane(), "shop");
            }
        });
        zoo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                System.out.println("zoo click!");
                ost.playmusic("./resourceFolder/sound/clickbgm.wav");
               
                F.getCardLayout().show(F.getContentPane(), "zoo");
                m_timer=new Timer();
                m_task =new TimerTask() {
                	public void run() {
                		F.getCardLayout().show(F.getContentPane(), "Map");
                	}
                };
                m_timer.schedule(m_task, 3000);
           
            }     
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                System.out.println("exit click!");
                fileio.save(Hunter.getInstance());
                fileio.sortRank();
                ost.playmusic("./resourceFolder/sound/clickbgm.wav");
                ost.stopbg();
                ost.playmusic("./resourceFolder/sound/ending.wav");
                F.dispose();
                new Ranking();
      
            }
        });

        add(f1);
        add(f2);
        add(shop);
        add(zoo);
        add(exit);
        add(hb);
        this.setVisible(true);

    }

    public void paintComponent(Graphics g) {
        // Display image at at full size
        g.drawImage(bg, 0, 0, null);
        setOpaque(false);
        super.paintComponent(g);
    }
    
    
}


