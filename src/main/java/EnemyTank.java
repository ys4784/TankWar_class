import object.Direction;


import java.awt.*;
import java.util.Random;

public class EnemyTank extends Tank {

    public EnemyTank(int x, int y, Direction direction, Image[] image) {
        super(x, y, direction, true, image);
    }

    public void ai() {
        //設定亂數
        Random random = new Random();
        //  1/20機率進行移動
        if (random.nextInt(20) == 1) {
            dirs = new boolean[4];
            //  1/5機率停止移動
            if (random.nextInt(5) == 1) {
                return;
            }
            //取得新方向
            getNewDirection();
        }
        //  1/25機率開火
        if (random.nextInt(25) == 1) {
            fire();
        }
    }

    public void getNewDirection() {
        Random random = new Random();
        int dir = random.nextInt(Direction.values().length);

        if (dir <= Direction.RIGHT.ordinal()) {
            dirs[dir] = true;
        } else {
            if (dir == Direction.UP_LEFT.ordinal()) {
                dirs[0] = true;
                dirs[2] = true;
            } else if (dir == Direction.UP_RIGHT.ordinal()) {
                dirs[0] = true;
                dirs[3] = true;
            } else if (dir == Direction.DOWN_LEFT.ordinal()) {
                dirs[1] = true;
                dirs[2] = true;
            } else if (dir == Direction.DOWN_RIGHT.ordinal()) {
                dirs[1] = true;
                dirs[3] = true;
            }
        }
    }

    @Override
    public boolean collision() {
        if(super.collision()){
            getNewDirection();
            return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
        ai();
        super.draw(g);
    }
}
