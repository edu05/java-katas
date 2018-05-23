package kata.mp3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MP3Player {

    private final List<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        songs.add(song);

    }

    public List<Song> findByTitle(String songTitle) {
        return findBy(songTitle, Song::getTitle);
    }

    public List<Song> findByArtist(String artist) {
        return findBy(artist, Song::getArtist);
    }

    private List<Song> findBy(String searchTerm, Function<Song, String> searchByFunction) {
        return songs.stream()
                .filter(song -> searchByFunction.apply(song).toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
    }
}
