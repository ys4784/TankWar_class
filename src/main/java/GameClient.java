import javax.swing.*;
import java.awt.*;

//JComponent圖形顯示
public class GameClient extends JComponent {

    private int screenWidth;
    private int screenHeight;

    GameClient() {
        this(800, 600);
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

    //繪製第一台坦克
    @Override
    //繪製元件
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("assets\\images\\itankD.png").getImage(),
                0,0,null);
                        //observer 觀察者
    }
}
