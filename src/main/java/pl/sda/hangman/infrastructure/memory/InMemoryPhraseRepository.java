package pl.sda.hangman.infrastructure.memory;

import pl.sda.hangman.domain.port.PhraseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InMemoryPhraseRepository implements PhraseRepository {

    private List<String> phrases;
    private Random random;

    public InMemoryPhraseRepository(List<String> phrase) {
        this.phrases = new ArrayList<>(phrase);
        this.random = new Random();
    }

    @Override
    public String getRandomPhrase() {
        int randomIndex = random.nextInt(phrases.size());
        return phrases.get(randomIndex);
    }

    @Override
    public boolean contains(String phrase) {
        return phrases.contains(phrase);
    }

    @Override
    public void save(String phrase) {
        phrases.add(phrase);
    }
}
