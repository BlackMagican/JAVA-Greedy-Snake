package com.snake;

import java.awt.Graphics;
import java.awt.Color;


/*
*  Game's boundary
* */
public class Obstacles
{
    private Color color = Color.green;
    public void drawBoundary(Graphics graphics)
    {
        graphics.setColor(color);
        // 上边界
        graphics.fillRect(0, GameView.cell, GameView.cell * GameView.width, GameView.cell);
        // 左边界
        graphics.fillRect(0, GameView.cell,
                GameView.cell, GameView.cell * GameView.height);
        // 下边界
        graphics.fillRect(0, GameView.cell * (GameView.height),
                GameView.cell * GameView.width, GameView.cell);
        // 右边界
        graphics.fillRect(GameView.cell * GameView.width, GameView.cell,
                GameView.cell, GameView.cell * GameView.height);

    }
}
