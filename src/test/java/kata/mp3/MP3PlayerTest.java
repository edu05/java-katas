package kata.mp3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

public class MP3PlayerTest {

    private InternetProvider internetProvider = mock(InternetProvider.class);

    @Before
    public void setUp() throws Exception {
        reset(internetProvider);
    }

    @Test
    public void shouldFindByTitle() throws Exception {
        MP3Player mp3Player = new MP3Player(internetProvider);

        Song song = new Song("Should I Stay or Should I Go", "The Clash");
        mp3Player.addSong(song);
        List<Song> matchingSongs = mp3Player.findByTitle(song.getTitle());

        assertThat(matchingSongs.size(), is(1));
        assertThat(matchingSongs.get(0), is(song));
    }

    @Test
    public void shouldFindByPartialTitle() throws Exception {
        MP3Player mp3Player = new MP3Player(internetProvider);

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
        MP3Player mp3Player = new MP3Player(internetProvider);

        Song doIWannaKnow = new Song("Do I Wanna Know?", "Artic Monkeys");
        Song sheMovesInHerOwnWay = new Song("She Moves in Her Own Way", "The Kooks");
        Song iGotMine = new Song("I Got Mine", "The Black Keys");
        mp3Player.addSong(doIWannaKnow);
        mp3Player.addSong(sheMovesInHerOwnWay);
        mp3Player.addSong(iGotMine);
        List<Song> matchingSongs = mp3Player.findByArtist("key");

        assertThat(matchingSongs.size(), is(2));
        assertTrue(matchingSongs.contains(doIWannaKnow));
        assertTrue(matchingSongs.contains(iGotMine));
    }

    @Test
    public void shouldCountArtistSongs() throws Exception {
        MP3Player mp3Player = new MP3Player(internetProvider);

        Song doIWannaKnow = new Song("Do I Wanna Know?", "Artic Monkeys");
        Song why = new Song("why'd you only call me when you're high", "Artic Monkeys");
        Song dancefloor = new Song("I Bet You Look Good On The Dancefloor", "Artic Monkeys");
        Song sheMovesInHerOwnWay = new Song("She Moves in Her Own Way", "The Kooks");
        Song junkOfTheHeart = new Song("Junk of The Heart", "The Kooks");
        Song iGotMine = new Song("I Got Mine", "The Black Keys");

        mp3Player.addSong(doIWannaKnow);
        mp3Player.addSong(why);
        mp3Player.addSong(dancefloor);
        mp3Player.addSong(sheMovesInHerOwnWay);
        mp3Player.addSong(junkOfTheHeart);
        mp3Player.addSong(iGotMine);
        Map<String, Integer> songCountByArtist = mp3Player.countByArtist();

        assertThat(songCountByArtist.size(), is(3));
        assertThat(songCountByArtist.get("Artic Monkeys"), is(3));
        assertThat(songCountByArtist.get("The Kooks"), is(2));
        assertThat(songCountByArtist.get("The Black Keys"), is(1));
    }

    @Test
    public void shouldGetFullSongInfoFromTheInternet() throws Exception {
        MP3Player mp3Player = new MP3Player(internetProvider);

        Song song = new Song("Should I Stay or Should I Go", "The Clash");
        mp3Player.addSong(song);

        SongInfo expectedSongIfo = new SongInfo(song.getTitle(), song.getArtist(), "Punk", 1982);
        when(internetProvider.getMoreInfo(song)).thenReturn(expectedSongIfo);

        SongInfo actualSongInfo = mp3Player.getSongInfo(song);

        Assert.assertThat(actualSongInfo, is(expectedSongIfo));
    }

    @Test
    public void shouldReturnMinimumInfoWhenInternetNotAvailable() throws Exception {
        MP3Player mp3Player = new MP3Player(internetProvider);

        Song song = new Song("Should I Stay or Should I Go", "The Clash");
        mp3Player.addSong(song);

        when(internetProvider.getMoreInfo(song)).thenThrow(new NoSignalException());

        SongInfo actualSongInfo = mp3Player.getSongInfo(song);

        Assert.assertThat(actualSongInfo.getTitle(), is(song.getTitle()));
        Assert.assertThat(actualSongInfo.getArtist(), is(song.getArtist()));
        Assert.assertNull(actualSongInfo.getCategory());
        Assert.assertThat(actualSongInfo.getReleaseYear(), is(0));
    }
}