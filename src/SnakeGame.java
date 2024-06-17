import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    private class Tile {
        int x;
        int y;
        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int boardWidth;
    int boardHeight;
    int tileSize = 25;

    // snake
    Tile snakeHead;
    ArrayList<Tile> snakeBody;

    // food
    Tile food;
    Random random;

    // game logic
    Timer gameLoop;
    int velocityX;
    int velocityY;
    boolean gameOver;

    // score
    int score;

    public SnakeGame(int boardHeight, int boardWidth) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;

        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);
        snakeHead = new Tile(5, 5);
        food = new Tile(10, 10);
        random = new Random();
        snakeBody = new ArrayList<>();
        placeFood();

        velocityX = 0;
        velocityY = 0;
        gameOver = false;
        score = 0;

        // Increase the delay time to slow down the snake
        gameLoop = new Timer(200, this); // Changed from 100 to 200 milliseconds
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (gameOver) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game Over", boardWidth / 4, boardHeight / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Score: " + score, boardWidth / 2 - 50, boardHeight / 2 + 40);
            return;
        }

        // Grid
        for (int i = 0; i < boardWidth / tileSize; i++) {
            g.drawLine(i * tileSize, 0, i * tileSize, boardHeight); // vertical lines 
            g.drawLine(0, i * tileSize, boardWidth, i * tileSize); // horizontal lines
        }

        // Score
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Score: " + score, 10, 20);

        // Food
        g.setColor(Color.red);
        g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);

        // Snake Head
        g.setColor(Color.green);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);

        // Snake Body
        for (Tile snakePart : snakeBody) {
            g.fillRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize);
        }
    }

    public void placeFood() {
        food.x = random.nextInt(boardWidth / tileSize);
        food.y = random.nextInt(boardHeight / tileSize);
    }

    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void checkCollision() {
        // Check if the snake has collided with itself
        for (Tile part : snakeBody) {
            if (collision(snakeHead, part)) {
                gameOver = true;
            }
        }

        // Check if the snake has collided with the wall
        if (snakeHead.x < 0 || snakeHead.x >= boardWidth / tileSize || snakeHead.y < 0 || snakeHead.y >= boardHeight / tileSize) {
            gameOver = true;
        }
    }

    public void move() {
        if (gameOver) {
            gameLoop.stop();
            return;
        }

        // Move snake body
        if (!snakeBody.isEmpty()) {
            for (int i = snakeBody.size() - 1; i > 0; i--) {
                snakeBody.get(i).x = snakeBody.get(i - 1).x;
                snakeBody.get(i).y = snakeBody.get(i - 1).y;
            }
            snakeBody.get(0).x = snakeHead.x;
            snakeBody.get(0).y = snakeHead.y;
        }

        // Move snake head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        // Eat food
        if (collision(snakeHead, food)) {
            // Add a new segment at the current position of the last segment
            if (!snakeBody.isEmpty()) {
                Tile lastSegment = snakeBody.get(snakeBody.size() - 1);
                snakeBody.add(new Tile(lastSegment.x, lastSegment.y));
            } else {
                snakeBody.add(new Tile(snakeHead.x - velocityX, snakeHead.y - velocityY));
            }
            placeFood();
            score++;
        }

        checkCollision();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            velocityX = 0;
            velocityY = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }
    }

    // Unused methods
    @Override
    public void keyTyped(KeyEvent e) { }
    @Override
    public void keyReleased(KeyEvent e) { }
}
