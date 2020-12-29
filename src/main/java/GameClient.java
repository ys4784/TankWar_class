import object.Direction;
import object.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

//JComponent圖形顯示
public class GameClient extends JComponent {

    //設定視窗大小
    private int screenWidth;
    private int screenHeight;

    private CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<>();

    //設定父類別集合
    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    //設定玩家坦克
    private Tank playerTank;

    //新增砲彈圖片(全域變數)
    public static Image[] bulletImage = new Image[8];

    //執行緒自動繪製判斷條件
    private boolean stop;


    GameClient() {
        this(960, 640);
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
        Image[] brickImage = {Tools.getImage("brick.png")};
        Image[] iTankImage = new Image[8];
        Image[] eTankImage = new Image[8];

        String[] sub = {"U.png", "D.png", "L.png", "R.png", "LU.png", "RU.png", "LD.png", "RD.png"};

        for (int i = 0; i < iTankImage.length; i++) {
            iTankImage[i] = Tools.getImage("iTank" + sub[i]);
            eTankImage[i] = Tools.getImage("eTank" + sub[i]);
            bulletImage[i] = Tools.getImage("missile" + sub[i]);
        }

        //玩家坦克
        playerTank = new Tank(0, 0, Direction.DOWN_RIGHT, iTankImage);
        gameObjects.add(playerTank);


        //敵方坦克
//        gameObjects.add(new Tank(screenWidth - eTankImage[4].getWidth(null),
//                screenHeight - eTankImage[4].getHeight(null),
//                Direction.UP_LEFT, true, eTankImage));

        gameObjects.add(new EnemyTank(screenWidth - (eTankImage[4].getWidth(null) * 3),
                screenHeight - eTankImage[4].getHeight(null),
                Direction.UP_LEFT, eTankImage));

//        gameObjects.add(new Tank(screenWidth - eTankImage[4].getWidth(null),
//                screenHeight - (eTankImage[4].getHeight(null) * 3),
//                Direction.UP_LEFT, true, eTankImage));

//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 3; j++) {
//                //用 j 調整 x 的位置，用 i 調整 y 的位置
//                gameObjects.add(new Tank(320 + j * 120, 400 + 120 * i,
//                        Direction.UP, true, eTankImage));
//            }
//        }

        //圍牆
        gameObjects.add(new Wall(screenWidth / 30 * 15, screenHeight / 20 * 5, false, 10, brickImage));
    }


    //繪製元件
    @Override
    protected void paintComponent(Graphics g) {
        //設定黑背景
        g.setColor(Color.black);
        //填充範圍為整個視窗 [(0,0)到(screenWidth,screenHeight)]
        g.fillRect(0, 0, screenWidth, screenHeight);
        //繪製元件
        for (GameObject gameObject : gameObjects) {
            gameObject.draw(g);
        }
        //清除物件
        Iterator<GameObject> iterator = gameObjects.iterator();
        while (iterator.hasNext()) {
            if (!(iterator.next()).isAlive()) {
                iterator.remove();
            }
        }
        for (GameObject object : objects) {
            if (!object.isAlive()) {
                objects.remove(object);
            }
        }
        System.out.println("當前物件數量：" + gameObjects.size());
    }

    //新增物件
    public void addGameObject(GameObject object) {
        gameObjects.add(object);
    }


    //按鍵壓下
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
            case KeyEvent.VK_SPACE:
                playerTank.fire();
                break;
        }
    }


    //按鍵釋放
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

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }
}
