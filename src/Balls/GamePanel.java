package Balls;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel implements Runnable {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private static GameBack background;
    public static ArrayList<Agents> agents;
    public static ArrayList<Bonus> bonus;
    public static Menu menu;
    private Thread thread;
    private BufferedImage image;
    private Graphics2D g;
    public static boolean leftMouse;
    public static int mouseX;
    public static int mouseY;
    private int rand;

    public enum STATES {
        MENU,
        PLAY,
        WIN
    }

    public static STATES states = STATES.MENU;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        addMouseMotionListener(new Listeners());
        addMouseListener(new Listeners());
    }

    public void start() {
        thread = new Thread(this);
        Thread thread2 = new Thread(new Agents(), "Kek");
        Thread thread3 = new Thread(new Bonus(), "Lol");
        thread.start();
        thread2.start();
        thread3.start();
    }

    @Override
    public void run() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        leftMouse = false;
        menu = new Menu();
        background = new GameBack();
        bonus = new ArrayList<>();
        agents = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            //bonus.add(new Bonus());
            agents.add(new Agents());
        }

        Timer del = new Timer();
        del.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!bonus.isEmpty())
                    for (int i = 0; i < bonus.size(); i++) {
                        bonus.get(i).remove();
                        bonus.remove(i);
                    }
            }
        }, 10000, 3000);


        while (true) {
            if (states.equals(STATES.MENU)) {

                background.update();
                background.draw(g);
                menu.update();
                menu.draw(g);
                gameDraw();
            }
            if (states.equals(STATES.PLAY)) {
                gameUpdate();
                gameRender();
                gameDraw();
            }

            if (states.equals(STATES.WIN)) {
                background.update();
                long len = (int) g.getFontMetrics().getStringBounds("Agents win", g).getWidth();
                background.draw(g);
                g.setColor(new Color(255, 250, 25, 250));
                g.drawString("Agents win", (int) (GamePanel.WIDTH / 2 - len / 2),
                        (int) (GamePanel.HEIGHT / 4 - len / 6));
                menu.update();
                menu.draw(g);
                gameDraw();
            }
        }
    }

    public void restart() {
        agents.clear();
        bonus.clear();
        run();
    }

    public void gameUpdate() {
        background.update();

            for (int i = 0; i < agents.size(); i++) {
                if (agents.size() == 1 ) {
                    double a = agents.get(i).getX();
                    double b = agents.get(i).getY();
                    String d = String.valueOf(agents.get(i).getTime());
                    String c = (int) a + "," + (int) b;

                    String query = "INSERT INTO balls (pos) \n" +
                            " VALUES ('" + c + "');";
                    String query2 = "INSERT INTO balls (time) \n" +
                            " VALUES ('" + d + "');";
                    try {
                        GameStart.statement.executeUpdate(query);
                        GameStart.statement.executeUpdate(query2);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    GamePanel.states = GamePanel.STATES.WIN;
                    restart();
                }
            }

            for (int i = 0; i < agents.size(); i++) {
                agents.get(i).update();
            }

            for (int i = 0; i < agents.size(); i++) {
                for (int h = 0; h < bonus.size(); h++) {
                    double ex = bonus.get(h).getX();
                    double ey = bonus.get(h).getY();
                    double px = agents.get(i).getX();
                    double py = agents.get(i).getY();
                    double dx = ex - px;
                    double dy = ey - py;
                    double dist = Math.sqrt(dx * dx + dy * dy);
                    if ((int) dist <= bonus.get(h).getR() + agents.get(i).getR()) {
                        agents.get(i).setTime(agents.get(i).getTime() + bonus.get(h).getTime());
                        bonus.get(h).hit();
                        boolean remove = bonus.get(h).remove();
                        if (remove) {
                            bonus.remove(h);
                        }
                    }
                }
            }
        }

    private void gameRender() {
        background.draw(g);
        for (int i = 0; i < bonus.size(); i++) {
            bonus.get(i).draw(g);
        }
        for (int i = 0; i < agents.size(); i++) {
            agents.get(i).draw(g);
        }
    }

    private void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        g.setFont(new Font("Consolas", Font.ROMAN_BASELINE, 40));
    }
}
