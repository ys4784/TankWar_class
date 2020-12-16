import object.Direction;
import object.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//JComponent圖形顯示
public class GameClient extends JComponent {

    private int screenWidth;
    private int screenHeight;

    private Tank playerTank;
    private boolean stop;

    GameClient() {
        this(800, 600);
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

        playerTank = new Tank(100, 100, Direction.DOWN);

        //執行緒(自行繪製)
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stop) {
                    repaint();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    //繪製第一台坦克
    @Override
    //繪製元件
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(playerTank.getImage(), playerTank.getX(), playerTank.getY(), null);
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                playerTank.setDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                playerTank.setDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                playerTank.setDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                playerTank.setDirection(Direction.RIGHT);
                break;
        }

        playerTank.move();
    }
}
