package javokhirbek.student.developer;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.placeEnemy();
        game.placePlaces();
        game.placePlayer();
        game.setName();
        game.startGame();
    }
}
