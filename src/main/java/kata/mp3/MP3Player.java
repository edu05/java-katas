package kata.mp3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Map<String, Integer> countByArtist() {
        Map<String, Integer> counts = new HashMap<>();
        for (Song song : songs) {
            counts.merge(song.getArtist(), 1, (currentValue, increment) -> currentValue + increment);
        }

        return counts;
    }

    private List<Song> findBy(String searchTerm, Function<Song, String> searchByFunction) {
        return songs.stream()
                .filter(song -> searchByFunction.apply(song).toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
    }
}
