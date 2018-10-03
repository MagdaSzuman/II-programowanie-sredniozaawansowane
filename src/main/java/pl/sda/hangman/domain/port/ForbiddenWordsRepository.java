package pl.sda.hangman.domain.port;

import java.util.List;

public interface ForbiddenWordsRepository {
    List<String> findAll();
}
