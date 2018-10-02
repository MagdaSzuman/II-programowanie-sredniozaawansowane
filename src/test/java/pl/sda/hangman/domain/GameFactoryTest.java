package pl.sda.hangman.domain;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import pl.sda.hangman.domain.model.Game;
import pl.sda.hangman.domain.model.GameStatus;
import pl.sda.hangman.domain.port.PhraseRepository;

public class GameFactoryTest {
    @Test
    public void shouldCreateGameInstanceWithRandomPhrase() {
        //given
        PhraseRepository phraseRepository = Mockito.mock(PhraseRepository.class);
        Mockito.when(phraseRepository.getRandomPhrase()).thenReturn("test frazy");
        GameFactory gameFactory = new GameFactory(phraseRepository);
        //when
        Game game = gameFactory.createGame();
        //then
        Assert.assertEquals("test frazy", game.getPhrase());
        Assert.assertArrayEquals(new char[]{'_','_','_','_',' ','_','_','_','_','_'}, game.getPhraseStatus());
        Assert.assertEquals(GameStatus.NEW,game.getStatus());
    }
}
