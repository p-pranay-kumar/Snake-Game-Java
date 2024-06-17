# Snake Game

This project is a classic implementation of the Snake Game using Java and Swing for the GUI. The objective of the game is to control a snake to eat food that appears randomly on the screen. Each time the snake eats the food, it grows in size and the score increases. The game ends if the snake collides with itself or the walls.

## Features

- **Classic Gameplay**: Move the snake using arrow keys to eat food and grow in size.
- **Dynamic Food Placement**: Food appears at random positions on the game board.
- **Collision Detection**: The game detects and handles collisions with the snake's body and the walls.
- **Score Display**: The current score is displayed at the top left corner of the game window.
- **Game Over Screen**: Displays a "Game Over" message along with the final score when the game ends.
- **Smooth Movement**: Adjusted game loop speed for better playability.

## Controls

- **Arrow Keys**: Use the Up, Down, Left, and Right arrow keys to control the snake's movement.

## How to Run

1. **Clone the Repository**:
    ```sh
    git clone https://github.com/yourusername/snake-game.git
    cd snake-game
    ```

2. **Compile and Run**:
    ```sh
    javac App.java SnakeGame.java
    java App
    ```

## Code Structure

- **App.java**: Sets up the game window and initializes the SnakeGame panel.
- **SnakeGame.java**: Contains the game logic, including snake movement, food placement, collision detection, and rendering.

## Screenshots

![snakepic1](https://github.com/p-pranay-kumar/Snake-Game-Java/assets/100279756/fad59083-d923-4ddf-bb07-6f33971c3e49)
![snake2](https://github.com/p-pranay-kumar/Snake-Game-Java/assets/100279756/adf3a915-081d-41ec-8b52-a507e147e74d)
![snake3](https://github.com/p-pranay-kumar/Snake-Game-Java/assets/100279756/914f17bb-e586-46a7-a888-2feedd420f62)


## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests for improvements and new features.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
