package com.snake;

import javax.swing.*;
import java.awt.*;

/*
*  Show the game window
* */
public class GameView extends JFrame
{
    public final static int cell = 30;
    public final static int width = 30;
    public final static int height = 20;
    public enum Direction
    {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    KeyController kc = new KeyController();
    Food food = new Food();
    Obstacles obstacles = new Obstacles();
    Snake snake = new Snake();
    public GameView()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(cell * width + 50, cell * height + 50);
        food.SetSnake(snake);
        kc.SetSnake(snake);
        this.addKeyListener(kc);
        this.setVisible(true);
    }

    // 图像的缓冲
    private Image iBuffer;
    // Graphics 的缓冲
    private Graphics gBuffer;
    @Override
    public void paint(Graphics g)
    {
        // 抄的双缓冲，把闪烁问题解决了
        /*
        *  其实问题原因我也想到了，但是不知道应该怎么处理
        *  百度说 Swing 自带双缓冲，但是好像要重写 paintComponent 方法
        *  怀念 Unity 的 Update
        * */
        if(iBuffer == null)
        {
            // 获取和当前窗口一样大的图像
            iBuffer = createImage(this.getSize().width,this.getSize().height);
            // 获取这张图像上的 Graphics 组件并将其赋给 gBuffer
            gBuffer = iBuffer.getGraphics();
        }
        gBuffer.setColor(getBackground());
        // 清屏
        gBuffer.fillRect(0,0,this.getSize().width,this.getSize().height);
        // 这时候调用的 Graphics 组件就是 gBuffer 了
        super.paint(gBuffer);
        // 搜了要先调用父类方法不然原来的颜色不会消失
        // 我人晕了这是什么鬼。。。
        food.DrawFood(gBuffer);
        obstacles.drawBoundary(gBuffer);
        snake.DrawSnake(gBuffer);
        // 最后把保存的图像绘制到屏幕上
        g.drawImage(iBuffer, 0, 0, this);
    }

    public static void main(String[] args)
    {
        GameView gameView = new GameView();
        // 游戏主函数
        while (gameView.snake.isAlive)
        {
            gameView.snake.Move();
            gameView.food.Eat();
            gameView.repaint();
            try {
                Thread.sleep(gameView.snake.speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 游戏结束时弹出窗口
        JOptionPane.showMessageDialog(gameView,
                "游戏结束，您的得分为：" + gameView.food.point);
    }
}
