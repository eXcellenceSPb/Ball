package Balls;

import java.awt.*;


public class Menu {
    private Color color;
    private int buttonWidth;
    private int buttonHeight;
    private String play;
    private String exit;
    private int transparent = 0;
    private int transparent2 = 0;
    public static int mouseX;
    public static int mouseY;
    public static boolean leftMouse;

    public Menu() {
        buttonWidth = 120;
        buttonHeight = 60;
        color = Color.ORANGE;
        play = "Play";
        exit = "Exit";

    }

    public void update() {
        if(GamePanel.mouseX > GamePanel.WIDTH/2 - buttonWidth/2
                && GamePanel.mouseX < GamePanel.WIDTH/2 + buttonWidth/2
                && GamePanel.mouseY > GamePanel.HEIGHT/3 - buttonHeight/2
                && GamePanel.mouseY < GamePanel.HEIGHT/3 + buttonHeight/2) {
            transparent = 60;
            if(GamePanel.leftMouse) {
                GamePanel.states = GamePanel.STATES.PLAY;
            }
        }
        else if(GamePanel.mouseX > GamePanel.WIDTH/2 - buttonWidth/2
                && GamePanel.mouseX < GamePanel.WIDTH/2 + buttonWidth/2
                && GamePanel.mouseY > GamePanel.HEIGHT/2 - buttonHeight/2
                && GamePanel.mouseY < GamePanel.HEIGHT/2 + buttonHeight/2) {
            transparent2 = 60;
            if(GamePanel.leftMouse) {
                System.exit(1);
            }
        }
        else {
            transparent = 0;
            transparent2 = 0;
        }
    }

    public void draw(Graphics2D g) {
        long length = (int) g.getFontMetrics().getStringBounds(play, g).getWidth();
        long length2 = (int) g.getFontMetrics().getStringBounds(exit, g).getWidth();

        g.setColor(color);
        g.setStroke(new BasicStroke(3));
        g.drawRect(GamePanel.WIDTH/2 - buttonWidth/2,
                GamePanel.HEIGHT/3 - buttonHeight/2,
                buttonWidth, buttonHeight);
        g.setColor(new Color(0,255,255, transparent));
        g.fillRect(GamePanel.WIDTH/2 - buttonWidth/2,
                GamePanel.HEIGHT/3 - buttonHeight/2,
                buttonWidth, buttonHeight);
        g.setColor(color);
        g.setFont(new Font("Consolas",Font.ROMAN_BASELINE,40));
        g.drawString(play, (int)(GamePanel.WIDTH/2 - length/2),
                (int)(GamePanel.HEIGHT/3 + length/6));


        g.drawRect(GamePanel.WIDTH/2 - buttonWidth/2,
                GamePanel.HEIGHT/2 - buttonHeight/2,
                buttonWidth, buttonHeight);
        g.setColor(new Color(255,0,10, transparent2));
        g.fillRect(GamePanel.WIDTH/2 - buttonWidth/2,
                GamePanel.HEIGHT/2 - buttonHeight/2,
                buttonWidth, buttonHeight);
        g.setColor(color);
        g.setFont(new Font("Consolas",Font.ROMAN_BASELINE,40));
        g.drawString(exit, (int)(GamePanel.WIDTH/2 - length2/2),
                (int)(GamePanel.HEIGHT/2 + length2/6));
    }
}
