package kata.mp3;

import java.util.ArrayList;
import java.util.List;

public class MP3Player {

    private final List<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        songs.add(song);

    }

    public List<Song> findByTitle(String songTitle) {
        List<Song> matchingSongs = new ArrayList<>();
        for (Song song : songs) {
            if (song.getTitle().contains(songTitle)) {
                matchingSongs.add(song);
            }
        }

        return matchingSongs;
    }


}
