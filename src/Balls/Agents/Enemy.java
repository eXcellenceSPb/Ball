package Balls.Agents;

import Balls.Game.GamePanel;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class Enemy extends Thread {
    private double x;
    private double dx;
    private double y;
    private double dy;
    private int r;
    private Color color;
    private double speed;
    private int time;

    public Enemy() throws IOException{
        this.color = Color.GREEN;
        this.x = Math.random() * GamePanel.WIDTH;
        this.y = 0;
        this.r = 10;
        //time = 5;
        Properties props = new Properties();
        props.load(new FileInputStream(new File("src/config.ini")));
        time = Integer.valueOf(props.getProperty("time","1"))*(int)(Math.random()*2);
        this.speed = 2;
        double angle = Math.toRadians(Math.random() * 360);
        dx = Math.sin(angle) * speed;
        dy = Math.cos(angle) * speed;
        run();
    }

    public void update() {
        x += dx;
        y += dy;

        if (x < 0 && dx < 0) {
            dx = -dx;
        }
        if (x > GamePanel.WIDTH && dx > 0) {
            dx = -dx;
        }
        if (y < 0 && dy < 0) {
            dy = -dy;
        }
        if (y > GamePanel.HEIGHT && dy > 0) {
            dy = -dy;
        }
    }

    public void setTime(int t) {
        time = t;
    }

    public int getTime() {
        return time;
    }

    public double getX() {
        return (int) x;
    }

    public double getY() {
        return (int) y;
    }

    public int getR() {
        return r;
    }

    public boolean remove() {
        if (time <= 0) {
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) x - r, (int) y - r, 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
        g.setColor(color.darker());
        g.drawOval((int) x - r, (int) y - r, 2 * r, 2 * r);
    }

    @Override
    public void run() {
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    time--;
                }
            }, 1000, 1000);
        }
    }


