import object.GameObject;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;

public class Wall extends GameObject {

    //設定牆面為水平或垂直
    private boolean horizontal;
    //設定磚塊數
    private int bricks;

    public Wall(int x, int y, boolean horizontal, int bricks, Image[] image) {
        super(x, y, image);
        this.horizontal = horizontal;
        this.bricks = bricks;
    }

    public void draw(Graphics g) {
        if (horizontal) {
            for (int i = 0; i < bricks; i++) {
                g.drawImage(image[0], x + i * width, y, null);
            }
        } else {
            for (int i = 0; i < bricks; i++) {
                g.drawImage(image[0], x, y + i * height, null);
            }
        }
    }

//    @Override
//    public Rectangle getRectangle() {
//        return horizontal ? new Rectangle(x, y, bricks * width, height) :
//                new Rectangle(x, y, width, bricks * height);
//    }
}
