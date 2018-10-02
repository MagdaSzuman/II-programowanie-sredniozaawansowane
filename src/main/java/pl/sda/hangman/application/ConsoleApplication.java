package pl.sda.hangman.application;

import pl.sda.hangman.domain.GameFactory;
import pl.sda.hangman.domain.model.Game;
import pl.sda.hangman.domain.model.GameStatus;
import pl.sda.hangman.infrastructure.memory.InMemoryPhraseRepository;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleApplication {

    private ConsoleViews consoleViews;
    private GameFactory gameFactory;

    public ConsoleApplication() {
        this.gameFactory = new GameFactory(new InMemoryPhraseRepository(Arrays.asList("Ala ma kota", "Wielkopolska", "Pan Tadeusz")));
        this.consoleViews = new ConsoleViews(new Scanner(System.in));
    }

    public void start() {
        boolean flag = true;
        while (flag) {
            Integer menuOption = consoleViews.mainMenu();
            switch (menuOption) {
                case 1:
                    startGame();
                    break;
                case 2:
                    System.out.println("Wyniki");
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Wybrano złą opcję");
            }
        }
    }

    private void startGame() {
        Game game = gameFactory.createGame();
        game.setStatus(GameStatus.ACTIVE);
        do {
            char nextLetter = consoleViews.displayGame(game);
            boolean result = game.addNextLetter(nextLetter);
            if (!result) {
                consoleViews.displayWrongLetterAdded();
            }
        } while (game.getStatus() == GameStatus.ACTIVE);

        if (game.getStatus() == GameStatus.WON) {
            consoleViews.displayGameWon();
        } else {
            consoleViews.displayGameLost();
        }
    }
}
