package kata.mp3;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class MP3Player {

    private final List<Song> songs = new ArrayList<>();

    public MP3Player(InternetProvider internetProvider) {

    }

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

        return songs.stream()
                .collect(groupingBy(Song::getArtist)).entrySet().stream() //group songs by artists into a Map<String, List<Song>> map
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().size())) //get the number of songs per artist for each entry in the previous map
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)); //reassemble into a new map
    }

    private List<Song> findBy(String searchTerm, Function<Song, String> searchByFunction) {
        return songs.stream()
                .filter(song -> searchByFunction.apply(song).toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
    }

    public SongInfo getSongInfo(Song song) {
        return null;
    }
}
