package object;

import javax.swing.*;
import java.awt.*;

public class Tank {

    private int x;
    private int y;
    private int speed;
    private Direction direction;

    public Tank(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        speed = 10;
    }

    public Image getImage(){
        if(direction == Direction.UP)
            return new ImageIcon("assets/images/itankU.png").getImage();
        if(direction == Direction.DOWN)
            return new ImageIcon("assets/images/itankD.png").getImage();
        if(direction == Direction.LEFT)
            return new ImageIcon("assets/images/itankL.png").getImage();
        if(direction == Direction.RIGHT)
            return new ImageIcon("assets/images/itankR.png").getImage();

        if(direction == Direction.UP_LEFT)
            return new ImageIcon("assets/images/itankLU.png").getImage();
        if(direction == Direction.DOWN_LEFT)
            return new ImageIcon("assets/images/itankLD.png").getImage();
        if(direction == Direction.UP_RIGHT)
            return new ImageIcon("assets/images/itankRU.png").getImage();
        if(direction == Direction.DOWN_RIGHT)
            return new ImageIcon("assets/images/itankRD.png").getImage();

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

    public void move(){
        switch (direction){
            case UP:
                y-=speed;
                break;
            case DOWN:
                y+=speed;
                break;
            case LEFT:
                x-=speed;
                break;
            case RIGHT:
                x+=speed;
                break;
        }
    }
}
