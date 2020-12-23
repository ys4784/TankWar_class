import javax.management.relation.Relation;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankGame {

    public static GameClient gameClient;

    public static void main(String[] args) {
        JFrame frame = new JFrame();    //實體化JFrame物件(視窗顯示)
        GameClient gameClient = new GameClient();
        frame.add(gameClient);  //裝載gameClient物件
        frame.setTitle("坦克大戰"); //設定視窗標題名稱
        frame.setVisible(true); //設定遊戲視窗為可視
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  //關掉視窗即釋放記憶體
        frame.pack();   //通知frame物件將其尺寸設定為:可將其內部所有的元件包起來的大小

        gameClient.repaint();   //繪出gameClient物件內的圖形

        frame.addKeyListener(new KeyAdapter() {
            @Override
            //鍵盤按下
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                gameClient.keyPressed(e);
                //System.out.println((char)e.getKeyCode());
            }

            @Override
            //鍵盤鬆開
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                gameClient.keyReleased(e);
            }
        });

    }
}
