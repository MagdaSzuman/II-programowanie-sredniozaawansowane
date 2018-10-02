package pl.sda.hangman.domain;

import pl.sda.hangman.domain.model.Game;
import pl.sda.hangman.domain.model.GameStatus;
import pl.sda.hangman.domain.port.PhraseRepository;

import java.time.Instant;

public class GameFactory {

    public static final int LEFT_ATTEMPTS = 5;
    private PhraseRepository phraseRepository;

    public GameFactory(PhraseRepository phraseRepository) {
        this.phraseRepository = phraseRepository;
    }

    public Game createGame() {
        String phrase = phraseRepository.getRandomPhrase();
        return Game.builder()
                .phrase(phrase)
                .phraseStatus(convertToStartingPhraseStarus(phrase))
                .status(GameStatus.NEW)
                .startDate(Instant.now())
                .leftAttempts(LEFT_ATTEMPTS)
                .build();
    }

    private char[] convertToStartingPhraseStarus(String phrese) {
        char[] array = phrese.toCharArray();

        for (int i = 0; i < array.length; i++) {
            if (Character.isLetter(array[i])) {
                array[i] = '_';
            }
        }

        return array;
    }
}
