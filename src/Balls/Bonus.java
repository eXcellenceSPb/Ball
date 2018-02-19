package Balls;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static Balls.GamePanel.bonus;


public class Bonus implements Runnable {
    private double x;
    private double y;
    private int r;

    private int health;
    private Color color;
    public int time;

    public Bonus() {
        color = Color.BLUE;
        x = Math.random() * GamePanel.WIDTH;
        y = Math.random() * GamePanel.HEIGHT;
        r = 5;
        health = 1;
        time = 3;//(int) (Math.random() * 10);
       // run();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public boolean remove() {
        if (health <= 0) {
            return true;
        }
        return false;
    }

    public void hit() {
        health--;
    }

    public int getTime() {
        return time;
    }

//    public void setTime(int t) {
//        time = t;
//    }

//    public void update() {
//    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(4));
        g.setColor(color.darker());
        g.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
    }

    @Override
    public void run() {
        Timer add = new Timer();
        add.schedule(new TimerTask() {
            @Override
            public void run() {
                if (bonus.size()<= 5)
                for (int k = 0; k < 1; k++) {
                    bonus.add(new Bonus());
                }
            }

        }, 1000, 2000);

    }
}
