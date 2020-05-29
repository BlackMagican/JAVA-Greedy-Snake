package com.snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter
{
    int keyCode;
    Snake snake;
    public void SetSnake(Snake snake)
    {
        this.snake = snake;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        keyCode = e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        ProcessKey(keyCode);
    }

    private void ProcessKey(int keyCode) {
        switch (keyCode)
        {
            case 38:
                // 在蛇向某一方向行进时，不能将方向直接变更为反方向
                if (Snake.direction != GameView.Direction.DOWN)
                {
                    Snake.direction = GameView.Direction.UP;
                    snake.Move();
                }
                break;
            case 37:
                if (Snake.direction != GameView.Direction.RIGHT)
                {
                    Snake.direction = GameView.Direction.LEFT;
                    snake.Move();
                }
                break;
            case 39:
                if (Snake.direction != GameView.Direction.LEFT)
                {
                    Snake.direction = GameView.Direction.RIGHT;
                    snake.Move();
                }
                break;
            case 40:
                if (Snake.direction != GameView.Direction.UP)
                {
                    Snake.direction = GameView.Direction.DOWN;
                    snake.Move();
                }
                break;
            default:
                break;
        }
    }
}
