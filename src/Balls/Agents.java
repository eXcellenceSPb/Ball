package Balls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Agents implements Runnable, ActionListener {
    private static double x;
    private static double y;
    private static int r;
    private static Color color;
    private static double speed;
    private static double dx;
    private static double dy;
    private static Timer timer;
    private static int health;
    private static int time;
    double angle = Math.toRadians(Math.random()*360);

    public Agents(){
        color = Color.RED;
        x = Math.random() * GamePanel.WIDTH;
        y = 0;
        r = 30;
        health = 1;
        timer = new Timer((int)Math.random()*2,this::actionPerformed);
        speed = Math.random() * 3;
        dx = Math.sin(angle) * speed;
        dy = Math.cos(angle) * speed;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getTime(){
        return time;
    }

    public void setHealth(){
         health = 1;
    }

    public void setTime(int i) throws InterruptedException {
        Time time = new Time(10);
    }
    public void start(){
        timer.start();
    }

    public int getR() {
        return r;
    }

    public boolean remove() {
        if (health <= 0 || time == 0){
            return true;
        }
        return false;
    }
    public void hit(int t){
        timer.setDelay(t);
    }

    public void update(){
        x += dx;
        y += dy;

        if(x < 0 && dx < 0)
            dx = -dx;
        if(x > GamePanel.WIDTH && dx >0)
            dx = -dx;
        if(y < 0 && dy <0)
            dy = -dy;
        if(y > GamePanel.HEIGHT && dy > 0)
            dy = -dy;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int)(x - r),(int) (y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(4));
        g.setColor(color.darker());
        g.drawOval((int)(x - r),(int) (y - r), 2 * r, 2 * r);
    }

    @Override
    public void run() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        remove();
    }
}
