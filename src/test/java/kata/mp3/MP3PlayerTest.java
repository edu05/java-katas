package kata.mp3;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class MP3PlayerTest {

    @Test
    public void shouldFindByTitle() throws Exception {
        MP3Player mp3Player = new MP3Player();

        Song song = new Song("Should I Stay or Should I Go", "The Clash");
        mp3Player.addSong(song);
        List<Song> matchingSongs = mp3Player.findByTitle(song.getTitle());

        assertThat(matchingSongs.size(), is(1));
        assertThat(matchingSongs.get(0), is(song));
    }

    @Test
    public void shouldFindByPartialTitle() throws Exception {
        MP3Player mp3Player = new MP3Player();

        Song shouldIStayOrShouldIGo = new Song("Should I Stay or Should I Go", "The Clash");
        Song sheMovesInHerOwnWay = new Song("She Moves in Her Own Way", "The Kooks");
        Song dontStop = new Song("Don't Stop", "Fleetwood Mac");
        mp3Player.addSong(shouldIStayOrShouldIGo);
        mp3Player.addSong(sheMovesInHerOwnWay);
        mp3Player.addSong(dontStop);
        List<Song> matchingSongs = mp3Player.findByTitle("Sh");

        assertThat(matchingSongs.size(), is(2));
        assertTrue(matchingSongs.contains(shouldIStayOrShouldIGo));
        assertTrue(matchingSongs.contains(sheMovesInHerOwnWay));
    }

    @Test
    public void shouldFindByPartialArtist() throws Exception {
        MP3Player mp3Player = new MP3Player();

        Song doIWannaKnow = new Song("Do I Wanna Know?", "Artic Monkeys");
        Song sheMovesInHerOwnWay = new Song("She Moves in Her Own Way", "The Kooks");
        Song iGotMine = new Song("I Got Mine", "The Black Keys");
        mp3Player.addSong(doIWannaKnow);
        mp3Player.addSong(sheMovesInHerOwnWay);
        mp3Player.addSong(iGotMine);
        List<Song> matchingSongs = mp3Player.findByTitle("key");

        assertThat(matchingSongs.size(), is(2));
        assertTrue(matchingSongs.contains(doIWannaKnow));
        assertTrue(matchingSongs.contains(iGotMine));
    }
}