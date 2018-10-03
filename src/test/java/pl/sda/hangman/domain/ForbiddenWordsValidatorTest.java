package pl.sda.hangman.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pl.sda.hangman.domain.port.ForbiddenWordsRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ForbiddenWordsValidatorTest {

    private ForbiddenWordsRepository forbiddenWordsRepository;
    private ForbiddenWordsValidator forbiddenWordsValidator;

    @Before
    public void Before() {
        forbiddenWordsRepository = Mockito.mock(ForbiddenWordsRepository.class);
        forbiddenWordsValidator = new ForbiddenWordsValidator(forbiddenWordsRepository);
    }

    private void mockRepository(List<String> forbiddenWords) {
        Mockito.when(forbiddenWordsRepository.findAll()).thenReturn(forbiddenWords);
    }

    @Test
    public void validateShouldReturnTrueWhenPhraseDoesNotContainForbidenWords() {
        //given
        mockRepository(Arrays.asList("forbidden"));
        //when
        boolean isValid = forbiddenWordsValidator.validate("test phrase");
        //then
        Assert.assertTrue(isValid);
    }

    @Test
    public void validateShouldReturnFalseWhenPhraseContainsForbidenWords() {
        //given
        mockRepository(Arrays.asList("forbidden"));
        //when
        boolean isValid = forbiddenWordsValidator.validate("test phrase with forbidden words");
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void validateShouldReturnFalseWhenPhraseContainsForbiddenWordsWithWhitespaces() {
        //given
        mockRepository(Arrays.asList("forbidden"));
        //when
        boolean isValid = forbiddenWordsValidator.validate("test phrase with f o r b i d d e n words");
        //then
        Assert.assertFalse(isValid);
    }

    @Test
    public void validateShouldReturnFalseWhenPhraseContainsUppercaseForbiddenWordsWithWhitespaces() {
        //given
        mockRepository(Arrays.asList("forbidden"));
        //when
        boolean isValid = forbiddenWordsValidator.validate("test phrase with F O R B I D D E N words");
        //then
        Assert.assertFalse(isValid);
    }
// "f o r b".replaceAll(" ","").toLowerCase();

    @Test
    public void validateShouldReturnFalseWhenPhraseContainsForbiddenWordsCombinedWithMultipleWords() {
        //given
        mockRepository(Arrays.asList("forbidden words"));
        //when
        boolean isValid = forbiddenWordsValidator.validate("test phrase with F O R B I D D E N words");
        //then
        Assert.assertFalse(isValid);

    }
}