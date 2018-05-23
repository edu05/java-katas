package kata.mp3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MP3Player {

    private final List<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        songs.add(song);

    }

    public List<Song> findByTitle(String songTitle) {
        return songs.stream()
                .filter(song -> song.getTitle().toLowerCase().contains(songTitle.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Song> findByArtist(String artist) {
        return songs.stream()
                .filter(song -> song.getArtist().toLowerCase().contains(artist.toLowerCase()))
                .collect(Collectors.toList());
    }


}
