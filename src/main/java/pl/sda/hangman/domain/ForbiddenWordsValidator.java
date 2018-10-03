package pl.sda.hangman.domain;

import pl.sda.hangman.domain.port.ForbiddenWordsRepository;

public class ForbiddenWordsValidator {

    private ForbiddenWordsRepository forbiddenWordsRepository;

    public ForbiddenWordsValidator(ForbiddenWordsRepository forbiddenWordsRepository) {
        this.forbiddenWordsRepository = forbiddenWordsRepository;
    }

    public boolean validate(String phrase) {
        String preparedPhrase = preprarePhrase(phrase);
        return forbiddenWordsRepository.findAll()
                .stream()
                .map(e-> preprarePhrase(e))
                .noneMatch(e -> preparedPhrase.contains(e));
    }

    private String preprarePhrase(String phrase) {
        return phrase.replaceAll(" ", "")
                .toLowerCase();
    }
}
