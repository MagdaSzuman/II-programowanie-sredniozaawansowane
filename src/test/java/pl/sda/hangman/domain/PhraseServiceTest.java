package pl.sda.hangman.domain;

import netscape.security.ForbiddenTargetException;
import org.junit.Test;
import org.mockito.Mockito;
import pl.sda.hangman.domain.exceptions.ForbiddenWordsInPhraseException;
import pl.sda.hangman.domain.exceptions.PhraseAlreadyExistsException;
import pl.sda.hangman.domain.port.PhraseRepository;

import static org.junit.jupiter.api.Assertions.*;

public class PhraseServiceTest {
    @Test
    public void addPhraseShouldAddNewPhrase() throws Exception{
        //given
        PhraseRepository phraseRepository = Mockito.mock(PhraseRepository.class);
        ForbiddenWordsValidator forbiddenWordsValidator = Mockito.mock(ForbiddenWordsValidator.class);
        Mockito.when(phraseRepository.contains(Mockito.anyString())).thenReturn(false);
        Mockito.when(forbiddenWordsValidator.validate(Mockito.anyString())).thenReturn(true);
        PhraseService phraseService = new PhraseService(phraseRepository, forbiddenWordsValidator);
        //when
        phraseService.addPhrase("Phrase with forbidden word");
    }

    @Test(expected = PhraseAlreadyExistsException.class)
    public void addPhraseShouldThrowAnExceptionWhenPhraseAlreadyExists() throws Exception {
        //given
        PhraseRepository phraseRepository = Mockito.mock(PhraseRepository.class);
        ForbiddenWordsValidator forbiddenWordsValidator = Mockito.mock(ForbiddenWordsValidator.class);
        Mockito.when(phraseRepository.contains(Mockito.anyString())).thenReturn(true);
        Mockito.when(forbiddenWordsValidator.validate(Mockito.anyString())).thenReturn(true);
        PhraseService phraseService = new PhraseService(phraseRepository, forbiddenWordsValidator);
        //when
        phraseService.addPhrase("Phrase with forbidden word");
    }

    @Test(expected = ForbiddenWordsInPhraseException.class)
    public void addPhraseShouldThrowAnExceptionWhenPhraseContainsForbiddenWords() throws Exception {
        //given
        PhraseRepository phraseRepository = Mockito.mock(PhraseRepository.class);
        ForbiddenWordsValidator forbiddenWordsValidator = Mockito.mock(ForbiddenWordsValidator.class);
        Mockito.when(forbiddenWordsValidator.validate(Mockito.anyString())).thenReturn(false);
        Mockito.when(phraseRepository.contains(Mockito.anyString())).thenReturn(false);
        PhraseService phraseService = new PhraseService(phraseRepository, forbiddenWordsValidator);
        //when
        phraseService.addPhrase("Phrase with forbidden word");
    }

}