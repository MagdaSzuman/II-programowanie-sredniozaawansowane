package pl.sda.hangman.application;

import pl.sda.hangman.domain.ForbiddenWordsValidator;
import pl.sda.hangman.domain.GameFactory;
import pl.sda.hangman.domain.PhraseService;
import pl.sda.hangman.domain.exceptions.ForbiddenWordsInPhraseException;
import pl.sda.hangman.domain.exceptions.PhraseAlreadyExistsException;
import pl.sda.hangman.domain.model.Game;
import pl.sda.hangman.domain.model.GameStatus;
import pl.sda.hangman.infrastructure.file.FileBasedForbiddenWordsRepostiory;
import pl.sda.hangman.infrastructure.memory.InMemoryForbiddenWordsRepository;
import pl.sda.hangman.infrastructure.memory.InMemoryPhraseRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleApplication {

    private ConsoleViews consoleViews;
    private GameFactory gameFactory;
    private PhraseService phraseService;

    public ConsoleApplication() throws FileNotFoundException {
        InMemoryPhraseRepository phraseRepository = new InMemoryPhraseRepository(Arrays.asList("Ala ma kota", "Wielkopolska", "Pan Tadeusz"));
        ForbiddenWordsValidator forbiddenWordsValidator = new ForbiddenWordsValidator(
                new FileBasedForbiddenWordsRepostiory(new File("C:\\Users\\M\\Desktop\\IntelliJ - projekty\\programowanie-sredniozaawansowane\\src\\main\\resources\\hangman\\forbiddenWords.txt")));
        this.gameFactory = new GameFactory(phraseRepository);
        this.consoleViews = new ConsoleViews(new Scanner(System.in));
        this.phraseService = new PhraseService(phraseRepository,forbiddenWordsValidator);
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
                    addPhrase();
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

    private void addPhrase() {
        String phrase = consoleViews.addPhraseMessage();
        try {
            phraseService.addPhrase(phrase);
            consoleViews.displayPhraseAddedSucessfully(phrase);
        } catch (ForbiddenWordsInPhraseException e) {
            consoleViews.displayPhraseContainsForbiddenWords();
        } catch (PhraseAlreadyExistsException e) {
            consoleViews.displayPhraseAlreadyExists();
        }

    }

}
