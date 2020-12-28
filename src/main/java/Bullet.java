import object.Direction;
import object.GameObject;

import java.awt.*;

public class Bullet extends Tank {
    public Bullet(int x, int y, Direction direction, boolean enemy, Image[] images) {
        super(x, y, direction, enemy, images);
        setSpeed(25);
    }

    @Override
    public void draw(Graphics g) {
        if (!alive) {
            return;
        }
        move();
        collision();
        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    @Override
    //碰撞偵測
    public void collision() {

        if (collisionBound()) {
            alive = false;
            return;
        }

        for (GameObject object : TankGame.getGameClient().getGameObjects()) {
            //判別物件是否為自己，如果true，則continue
            if (object == this) {
                alive = true;
                continue;
            }

            //判別物件是否為我方坦克，如果true，則continue
            if (object instanceof Tank && ((Tank) object).isEnemy() == isEnemy()) {
                alive = true;
                continue;
            }

            if(object instanceof Tank){
                setAlive(false);
            }

            //與其他物件進行碰撞偵測
            if (getRectangle().intersects(object.getRectangle())) {
                alive = false;
            }
        }
    }
}
