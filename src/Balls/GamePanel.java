package Balls;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public enum STATES {
        MENU,
        PLAY,
        LOOSE,
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
        thread.start();
    }

    @Override
    public void run() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        leftMouse = false;
        menu = new Menu();
        background = new GameBack();
        bonus = new ArrayList<Bonus>();
        agents = new ArrayList<Agents>();

        synchronized (agents) {
            new Agents().run();
            new Bonus().run();
        }
        agents.add(new Agents());
        bonus.add(new Bonus());

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
            if (states.equals(STATES.LOOSE)) {
                background.update();
                long len = (int) g.getFontMetrics().getStringBounds("Agents die", g).getWidth();
                background.draw(g);
                g.setColor(new Color(255, 50, 250, 150));
                g.drawString("Agents die", (int) (GamePanel.WIDTH / 2 - len / 2),
                        (int) (GamePanel.HEIGHT / 4 - len / 6));
                menu.update();
                menu.draw(g);
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
        agents.add(new Agents());
        bonus.add(new Bonus());
    }

    public void gameUpdate() {
        background.update();
        if (agents.isEmpty()) {
            GamePanel.states = STATES.LOOSE;
            restart();
        }
        if (!agents.isEmpty() || bonus.isEmpty()) {
            for (int i = 0; i < agents.size(); i++) {
                if (bonus.isEmpty()) {
                    double a = agents.get(i).getX();
                    double b = agents.get(i).getY();
                    String c = (int) a + "," + (int) b;
                    String query = "INSERT INTO balls (pos) \n" +
                            " VALUES ('" + c + "');";
                    try {
                        GameStart.statement.executeUpdate(query);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //agents.get(i).remove();

                    GamePanel.states = GamePanel.STATES.WIN;
                    restart();
                }
            }
        }
        if (!bonus.isEmpty()) {
            for (int i = 0; i < bonus.size(); i++) {
                bonus.get(i).update();
            }
        }
        if (!agents.isEmpty()) {
            for (int i = 0; i < agents.size(); i++) {
                agents.get(i).update();
            }
        }
        if (!agents.isEmpty()) {
            for (int i = 0; i < bonus.size(); i++) {
                Bonus b = bonus.get(i);
                double ex = b.getX();
                double ey = b.getY();
                double px = agents.get(i).getX();
                double py = agents.get(i).getY();
                double dx = ex - px;
                double dy = ey - py;
                double dist = Math.sqrt(dx * dx + dy * dy);
                if ((int) dist <= b.getR() + agents.get(i).getR()) {
                    agents.get(i).setTime(agents.get(i).getTime() + bonus.get(i).getTime());
                    b.hit();
                    boolean remove = b.remove();
                    if (remove) {
                        bonus.remove(i);
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
