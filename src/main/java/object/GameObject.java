package object;

import java.awt.*;

public abstract class GameObject {
    //物件的座標和圖像
    protected int x;
    protected int y;
    protected Image[] image;

    //物件的寬高
    protected int width;
    protected int height;

    //物件的上一個座標(用於碰撞偵測)
    protected int oldX;
    protected int oldY;

    protected boolean alive;

    public GameObject(int x, int y, Image[] image) {
        this.x = x;
        this.y = y;
        this.image = image;
        width = image[0].getWidth(null);
        height = image[0].getHeight(null);
        alive = true;
    }

    //矩形類別(建構一個矩形，座標為(x,y)，寬 X 高 為 width X height )
    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public abstract void draw(Graphics g);
}
