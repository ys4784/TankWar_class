package object;

import javax.swing.*;
import java.awt.*;

public class Wall {
    private int x;
    private int y;

    //設定牆面為水平或垂直
    private boolean horizontal;

    //設定磚塊數
    private int bricks;

    private Image image;


    public Wall(int x, int y, boolean horizontal, int bricks) {
        this.x = x;
        this.y = y;
        this.horizontal = horizontal;
        this.bricks = bricks;
        image = new ImageIcon("assets/images/brick.png").getImage();
    }

    public void draw(Graphics g) {
        if (horizontal) {
            for (int i = 0; i < bricks; i++) {
                g.drawImage(image, x + i * image.getWidth(null), y, null);
            }
        } else {
            for (int i = 0; i < bricks; i++) {
                g.drawImage(image, x, y + i * image.getHeight(null), null);
            }
        }
    }


}
