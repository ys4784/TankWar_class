import object.Direction;
import object.GameObject;

import javax.swing.*;
import java.awt.*;

public class Tank extends GameObject {

    //設定坦克移動速度
    private int speed;
    //設定方向
    protected Direction direction;
    //設定方向(上下左右)變數
    protected boolean[] dirs = new boolean[4];
    //設定敵我識別
    private boolean enemy;

    public Tank(int x, int y, Direction direction, Image[] image) {
        this(x, y, direction, false, image);
    }

    public Tank(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, image);
        this.direction = direction;
        speed = 20;
        this.enemy = enemy;
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

    //敵我識別
    public boolean isEnemy() {
        return enemy;
    }

    //設定速度
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean[] getDirs() {
        return dirs;
    }

    //移動
    public void move() {
        //紀錄碰撞前座標
        oldX = x;
        oldY = y;

        //移動
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

        //偵測碰撞
        collision();
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

    //繪製元件
    public void draw(Graphics g) {
        if (!isStop()) {
            determineDirection();
            move();
        }
        //方向的編號 direction.ordinal()
        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    //坦克目前狀態(是否停止)
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

    //開火
    public void fire() {
        TankGame.getGameClient().addGameObject(new Bullet(
                x + width / 2 - GameClient.bulletImage[0].getWidth(null) / 2,
                y + height / 2 - GameClient.bulletImage[0].getHeight(null) / 2,
                direction, enemy, GameClient.bulletImage
        ));
    }


    //邊界碰撞
    public boolean collisionBound() {
        boolean isCollision = false;
        //邊界碰撞
        if (x < 0) {
            x = 0;
            isCollision = true;
        } else if (x > TankGame.getGameClient().getScreenWidth() - width) {
            x = TankGame.getGameClient().getScreenWidth() - width;
            isCollision = true;
        }
        if (y < 0) {
            y = 0;
            isCollision = true;
        } else if (y > TankGame.getGameClient().getScreenHeight() - height) {
            y = TankGame.getGameClient().getScreenHeight() - height;
            isCollision = true;
        }
        return isCollision;
    }

    //物件碰撞
    public boolean collisionObject() {
        boolean isCollision = false;
        for (GameObject gameObject : TankGame.getGameClient().getGameObjects()) {
            if (gameObject != this && getRectangle().intersects(gameObject.getRectangle())) {
                x = oldX;
                y = oldY;
                isCollision = true;
            }
        }
        return isCollision;
    }

    //偵測碰撞
    public boolean collision() {
        boolean isCollision = collisionBound();
        if(!isCollision){
            isCollision = collisionObject();
        }
        return isCollision;
    }


}

