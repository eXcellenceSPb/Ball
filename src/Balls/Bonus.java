package Balls;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class Bonus implements Runnable {
    private double x;
    private double y;
    private int r;
    private double speed;
    private double dx;
    private double dy;
    private int health;
    private Color color;
    public int i;
    double angle = Math.toRadians(Math.random()*360);

    public Bonus(){
        color = Color.BLUE;
        x = Math.random() * GamePanel.WIDTH;
        y = 0;
        r = 30;
        health = 1;
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

    public int getR() {
        return r;
    }

    protected boolean remove() {
        if (health <= 0){
            return true;
        }
        return false;
    }
    public void hit(){
        health--;
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

}
