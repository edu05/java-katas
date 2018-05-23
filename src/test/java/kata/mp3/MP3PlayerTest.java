package kata.mp3;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

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
}