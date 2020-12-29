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
        for (GameObject object : TankGame.getGameClient().getGameObjects()) {
            //判別物件是否為自己，如果true，則continue
            if (object == this) {
                continue;
            }

            //判別物件是否為我方坦克，如果true，則continue
            if (object instanceof Tank && ((Tank) object).isEnemy() == isEnemy()) {
                continue;
            }

            //與其他物件進行碰撞偵測
            if(getRectangle().intersects(object.getRectangle())) {
                alive=false;
                if(object instanceof Tank){
                    object.setAlive(false);
                }
            }

            if(collisionBound()){
                alive=false;
            }
        }
    }
}
