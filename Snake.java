package com.snake;

import java.awt.Graphics;
import java.awt.Color;
import java.util.LinkedList;

public class Snake
{
    private LinkedList<Node> body = new LinkedList<>();
    // 蛇当前的方向
    public static GameView.Direction direction = GameView.Direction.RIGHT;
    // 判断蛇是否存活
    public boolean isAlive = true;
    public int speed = 500;

    public Snake()
    {
        // 在游戏中间初始化蛇的头部
        Node head = new Node(GameView.width / 2,
                GameView.height / 2);
        body.add(head);
    }

    public void Move()
    {
        Node section;
        int xNow = body.getFirst().getX();
        int yNow = body.getFirst().getY();
        switch (direction)
        {
            case UP:
                section = new Node(xNow, yNow - 1);
                body.addFirst(section);
                break;
            case DOWN:
                section = new Node(xNow, yNow + 1);
                body.addFirst(section);
                break;
            case LEFT:
                section = new Node(xNow - 1, yNow);
                body.addFirst(section);
                break;
            case RIGHT:
                section = new Node(xNow + 1, yNow);
                body.addFirst(section);
                break;
            default:
                break;
        }
        body.removeLast();
        this.Collide();
    }

    // 检测蛇与障碍物的碰撞
    private void Collide()
    {
        Node head = GetHead();
        if (head.getX() == GameView.width - 1 || head.getY() == GameView.height - 1
        || head.getY() == 2 || head.getX() == 1)
        {
            isAlive = false;
        }
        boolean isHead = true;
        for (Node node : body)
        {
            if (isHead)
            {
                isHead = false;
                continue;
            }
            if (node.getX() == head.getX() && node.getY() == head.getY())
            {
                isAlive = false;
                break;
            }
        }
    }

    // 每次吃食物蛇的速度就减少
    public void DecreaseSpeed()
    {
        speed -= 10;
        if (speed == 0)
        {
            speed = 10;
        }
    }

    // 在尾部添加节点
    public void AddTail(Node area)
    {
        this.body.addLast(area);
    }

    // 获取蛇的头部
    public Node GetHead()
    {
        return body.getFirst();
    }

    public void DestroySnake()
    {
        body.clear();
    }

    public void DrawSnake(Graphics graphics)
    {
        // 蛇的颜色
        graphics.setColor(Color.BLUE);
        // 只有当蛇存活的时候才画蛇
        if (isAlive)
        {
            for (Node n : body)
            {
                graphics.fillRect(GameView.cell * n.getX(),
                        GameView.cell * n.getY(),
                        GameView.cell, GameView.cell);
            }
        }else
        {
            DestroySnake();
        }
    }
}
