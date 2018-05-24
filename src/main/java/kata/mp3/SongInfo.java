package kata.mp3;

public class SongInfo {

    private final String title;
    private final String artist;
    private final String category;
    private final int releaseYear;

    public SongInfo(String title, String artist, String category, int releaseYear) {
        this.title = title;
        this.artist = artist;
        this.category = category;
        this.releaseYear = releaseYear;
    }

    public SongInfo(String title, String artist) {
        this(title, artist, null, 0);
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getCategory() {
        return category;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
}
