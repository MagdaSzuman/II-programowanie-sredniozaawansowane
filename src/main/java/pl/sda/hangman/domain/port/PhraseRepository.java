package pl.sda.hangman.domain.port;

public interface PhraseRepository {
    String getRandomPhrase();

    boolean contains(String phrase);

    void save(String phrase);
}
