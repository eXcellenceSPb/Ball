package Balls;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private static GameBack background;
    private static ArrayList<Agents> agents;
    private static ArrayList<Bonus> bonus;
    public static Menu menu;
    private Thread thread;
    private BufferedImage image;
    private Graphics2D g;
    public static boolean leftMouse;
    public static int mouseX;
    public static int mouseY;

    public enum STATES {
        MENU,
        PLAY
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

        try {
            bonus.add(new Bonus());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        agents.add(new Agents());



        Toolkit kit = Toolkit.getDefaultToolkit();
        BufferedImage buffered = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = (Graphics2D) buffered.getGraphics();
        g3.setColor(new Color(255, 255, 255));
        g3.drawOval(0, 0, 4, 4);
        g3.drawLine(2, 0, 2, 4);
        g3.drawLine(0, 2, 4, 2);
        g3.dispose();

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
        }
    }

    public void gameUpdate() {
        background.update();
//        for (int i = 0; i < agents.size(); i++) {
//            if (agents.get(i).getTime() <= 0) {
//                double a = agents.get(i).getX();
//                double b = agents.get(i).getY();
//                String c = (int)a+","+(int)b;
//                String query = "INSERT INTO balls (pos) \n" +
//                        " VALUES ('"+c+"');";
//
//                try {
//                    GameStart.statement.executeUpdate(query);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                agents.get(i).remove();
//               GamePanel.states = GamePanel.STATES.MENU;
//                agents.get(i).setHealth();
//                if (bonus.isEmpty() != true) {
//                    bonus.clear();
//                }
//            }
//        }
        for (int i = 0; i < bonus.size(); i++) {
            bonus.get(i).update();
        }
        for (int i = 0; i < agents.size(); i++) {
            agents.get(i).update();
        }
//        for (int i = 0; i < bonus.size(); i++) {
//            Bonus e = bonus.get(i);
//            double ex = e.getX();
//            double ey = e.getY();
//            double px = agents.get(i).getX();
//            double py = agents.get(i).getY();
//            double dx = ex - px;
//            double dy = ey - py;
//            double dist = Math.sqrt(dx * dx + dy * dy);
//            if ((int) dist <= e.getR() + agents.get(i).getR()) {
//                e.hit();
//                agents.get(i).hit(bonus.get(i).time());
//                boolean remove = e.remove();
//                if (remove) {
//                    i--;
//                    bonus.remove(i);
//                }
//            }
//        }
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
