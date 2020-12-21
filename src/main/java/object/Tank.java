package object;

import javax.swing.*;
import java.awt.*;

public class Tank {

    //設定坦克座標
    private int x;
    private int y;
    //設定坦克移動速度
    private int speed;
    //設定方向
    private Direction direction;
    //設定方向(上下左右)變數
    private boolean[] dirs = new boolean[4];
    //設定敵我識別
    private boolean enemy;

    public Tank(int x, int y, Direction direction) {
        this(x, y, direction, false);
    }

    public Tank(int x, int y, Direction direction, boolean enemy) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        speed = 15;
        this.enemy = enemy;
    }

    public Image getImage() {

        String name = enemy ? "etank" : "itank";
        
        if (direction == Direction.UP)
            return new ImageIcon("assets/images/"+name+"U.png").getImage();
        if (direction == Direction.DOWN)
            return new ImageIcon("assets/images/"+name+"D.png").getImage();
        if (direction == Direction.LEFT)
            return new ImageIcon("assets/images/"+name+"L.png").getImage();
        if (direction == Direction.RIGHT)
            return new ImageIcon("assets/images/"+name+"R.png").getImage();

        if (direction == Direction.UP_LEFT)
            return new ImageIcon("assets/images/"+name+"LU.png").getImage();
        if (direction == Direction.DOWN_LEFT)
            return new ImageIcon("assets/images/"+name+"LD.png").getImage();
        if (direction == Direction.UP_RIGHT)
            return new ImageIcon("assets/images/"+name+"RU.png").getImage();
        if (direction == Direction.DOWN_RIGHT)
            return new ImageIcon("assets/images/"+name+"RD.png").getImage();

        return null;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean[] getDirs() {
        return dirs;
    }

    //移動
    public void move() {
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;

            case UP_LEFT:
                y -= speed;
                x -= speed;
                break;

            case UP_RIGHT:
                y -= speed;
                x += speed;
                break;

            case DOWN_LEFT:
                y += speed;
                x -= speed;
                break;

            case DOWN_RIGHT:
                y += speed;
                x += speed;
                break;
        }

    }

    //偵測方向
    private void determineDirection() {
        if (dirs[0] && !dirs[1] && !dirs[2] && !dirs[3]) direction = Direction.UP;
        else if (!dirs[0] && dirs[1] && !dirs[2] && !dirs[3]) direction = Direction.DOWN;
        else if (!dirs[0] && !dirs[1] && dirs[2] && !dirs[3]) direction = Direction.LEFT;
        else if (!dirs[0] && !dirs[1] && !dirs[2] && dirs[3]) direction = Direction.RIGHT;
        else if (dirs[0] && !dirs[1] && dirs[2] && !dirs[3]) direction = Direction.UP_LEFT;
        else if (dirs[0] && !dirs[1] && !dirs[2] && dirs[3]) direction = Direction.UP_RIGHT;
        else if (!dirs[0] && dirs[1] && dirs[2] && !dirs[3]) direction = Direction.DOWN_LEFT;
        else if (!dirs[0] && dirs[1] && !dirs[2] && dirs[3]) direction = Direction.DOWN_RIGHT;
    }

    public void draw(Graphics g) {
        if (!isStop()) {
            determineDirection();
            move();
        }
        g.drawImage(getImage(), x, y, null);
    }

    public boolean isStop() {
        for (int i = 0; i < dirs.length; i++) {
            if (dirs[i]) {
                //只要有任一方向在移動，回傳false
                return false;
            }
        }
        //八方向皆停止，回傳true( isStop()為true )
        return true;
    }


}
