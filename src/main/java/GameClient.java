import object.Direction;
import object.Tank;
import object.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

//JComponent圖形顯示
public class GameClient extends JComponent {

    //設定視窗大小
    private int screenWidth;
    private int screenHeight;

    //設定玩家坦克
    private Tank playerTank;

    //設定敵方坦克
    private ArrayList<Tank> enemyTank = new ArrayList<>();

    //設定牆面
    private ArrayList<Wall> walls = new ArrayList<>();

    //執行緒自動繪製判斷條件
    private boolean stop;


    GameClient() {
        this(1024, 768);
    }


    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        init();

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


    public void init() {
        //玩家坦克
        playerTank = new Tank(492, 100, Direction.DOWN);

        //敵方坦克
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                //用 j 調整 x 的位置，用 i 調整 y 的位置
                enemyTank.add(new Tank(350 + j * 80, 500 + 80 * i, Direction.UP, true));
            }
        }

        //圍牆
        walls.add(new Wall(150,300,false,13));
        walls.add(new Wall(850,300,false,13));
        walls.add(new Wall(180,200,true,21));
    }


    //繪製元件
    @Override
    protected void paintComponent(Graphics g) {
        //設定黑背景
        g.setColor(Color.black);
        //填充範圍為整個視窗 [(0,0)到(screenWidth,screenHeight)]
        g.fillRect(0, 0, screenWidth, screenHeight);
        //玩家坦克
        playerTank.draw(g);
        //敵方坦克
        for (Tank tank : enemyTank) {
            tank.draw(g);
        }
        //牆面
        for (Wall wall : walls) {
            wall.draw(g);
        }

    }


    public void keyPressed(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = true;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = true;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = true;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = true;
                break;
        }
    }


    public void keyReleased(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = false;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = false;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = false;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = false;
                break;
        }
    }
}
