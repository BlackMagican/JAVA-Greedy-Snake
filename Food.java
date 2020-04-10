package com.snake;

import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

/*
* Snake's Food
* */
public class Food
{
    private int x;
    private int y;
    private Snake snake;
    private Color foodColor = Color.red;

    public int point = 0;

    public Food()
    {
        SetFoodLocation();
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public void SetSnake(Snake snake)
    {
        this.snake = snake;
    }

    void SetFoodLocation()
    {
        do {
            Random random = new Random();
            x = random.nextInt(GameView.width - 1);
            y = random.nextInt(GameView.height - 1);
        }while (x < 2 || x >= GameView.width ||
                y < 2 || y >= GameView.height);
    }

    public void Eat()
    {
        int xLocation = snake.GetHead().getX();
        int yLocation = snake.GetHead().getY();
        if (this.x == xLocation && this.y == yLocation)
        {
            snake.AddTail(new Node(this.x, this.y));
            SetFoodLocation();
            this.point++;
            snake.DecreaseSpeed();
        }
    }
    void DrawFood(Graphics graphics)
    {
        graphics.setColor(foodColor);
        graphics.fillRect(x * GameView.cell,
                y * GameView.cell, GameView.cell, GameView.cell);
    }
}
