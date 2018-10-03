package pl.sda.hangman.infrastructure.memory;

import pl.sda.hangman.domain.port.ForbiddenWordsRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryForbiddenWordsRepository implements ForbiddenWordsRepository {

    private List<String>forbiddenWords;

    public InMemoryForbiddenWordsRepository(List<String> forbiddenWords) {
        this.forbiddenWords = forbiddenWords;
    }

    @Override
    public List<String> findAll() {
        return new ArrayList<>(forbiddenWords); // konstruktor kopiujący
    }
}
