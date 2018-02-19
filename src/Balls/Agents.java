package Balls;

import java.awt.*;

public class Agents extends Thread{
    private static double x;
    private static double y;
    private static int r;
    private static Color color;
    private static double speed;
    private static double dx;
    private static double dy;
    private static int health;
    public int i;
    double angle = Math.toRadians(Math.random()*360);
    public int time;

    public Agents(){
        color = Color.RED;
        x =(int) (Math.random() * GamePanel.WIDTH);
        y =(int) (Math.random() * GamePanel.HEIGHT);
        r = 7;
        health = 1;
        time = (int)(Math.random()*9900);
        speed = 1+(int)(Math.random() * 3);
        dx = Math.sin(angle) * speed/2;
        dy = Math.cos(angle) * speed/2;
        start();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setHealth(){
         health = 1;
    }

    public int getR() {
        return r;
    }

    public boolean remove() {
        if (health <= 0){
            return true;
        }
        return false;
    }

    public void setTime(int t){
        time = t;
    }

    public int getTime(){
        return time;
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
        g.setStroke(new BasicStroke(3));
        g.setColor(color.darker());
        g.drawOval((int)(x - r),(int) (y - r), 2 * r, 2 * r);
    }

//    @Override
//    public void run() {
//        Timer t = new Timer();
//        t.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                if (!agents.isEmpty())
//                    for (int i = 0; i < agents.size(); i++) {
//                    agents.get(i).remove();
//                        agents.remove(i);
////                        agents.get(i).update();
//                    }
//            }
//        },1000, 5000);
//    }
}
