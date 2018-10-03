package pl.sda.hangman.domain.exceptions;

public class PhraseAlreadyExistsException extends GameException {
    public PhraseAlreadyExistsException(String message) {
        super(message);
    }
}
