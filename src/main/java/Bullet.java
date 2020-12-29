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
    public boolean collision() {
        boolean isCollision = collisionBound();
        if (!isCollision) {
            isCollision = collisionObject();
        }
        if (isCollision) {
            alive = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean collisionObject() {
        boolean isCollision = false;
        for (GameObject gameObject : TankGame.getGameClient().getGameObjects()) {
            if (gameObject instanceof Tank) {
                if (((Tank) gameObject).isEnemy() == isEnemy()) {
                    continue;
                }
            }
            if (gameObject != this && getRectangle().intersects(gameObject.getRectangle())) {
                if (gameObject instanceof Tank) {
                    gameObject.setAlive(false);
                }
                isCollision = true;
            }
        }
        return isCollision;
    }
}
