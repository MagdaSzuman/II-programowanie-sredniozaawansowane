package pl.sda.hangman.domain;

import pl.sda.hangman.domain.exceptions.ForbiddenWordsInPhraseException;
import pl.sda.hangman.domain.exceptions.PhraseAlreadyExistsException;
import pl.sda.hangman.domain.port.PhraseRepository;

public class PhraseService {

    private PhraseRepository phraseRepository;
    private ForbiddenWordsValidator forbiddenWordsValidator;

    public PhraseService(PhraseRepository phraseRepository, ForbiddenWordsValidator forbiddenWordsValidator) {
        this.phraseRepository = phraseRepository;
        this.forbiddenWordsValidator = forbiddenWordsValidator;
    }

    public void addPhrase(String phrase) throws ForbiddenWordsInPhraseException, PhraseAlreadyExistsException {
        if (phraseRepository.contains(phrase)) {
            throw new PhraseAlreadyExistsException("Phrase " + phrase + " already exists");
        }
        if (!forbiddenWordsValidator.validate(phrase)){
            throw new ForbiddenWordsInPhraseException("Phrase contains forbidden words");
        }
        phraseRepository.save(phrase);
    }
}
