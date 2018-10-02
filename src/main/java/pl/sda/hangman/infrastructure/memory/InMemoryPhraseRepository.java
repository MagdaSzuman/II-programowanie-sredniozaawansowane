package pl.sda.hangman.infrastructure.memory;

import pl.sda.hangman.domain.port.PhraseRepository;

import java.util.List;
import java.util.Random;

public class InMemoryPhraseRepository implements PhraseRepository {

    private List<String> phrase;
    private Random random;

    public InMemoryPhraseRepository(List<String> phrase) {
        this.phrase = phrase;
        this.random = new Random();
    }

    @Override
    public String getRandomPhrase() {
        int randomIndex = random.nextInt(phrase.size());
        return phrase.get(randomIndex);
    }
}
