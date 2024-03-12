import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DatabaseSort{

	
    ObservableList<Song> parseDatabaseFile(String filePath) {
    	filePath = "database.txt";
        ObservableList<Song> songList = FXCollections.observableArrayList();//stores instances of Song class

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // skip the header line
            String line;

            while ((line = br.readLine()) != null) {//goes through the file separating it into an an array then writing it to a new song from Song class
                String[] data = line.split(",");
                if (data.length >= 4) {
                    String songName = data[0];
                    String artistName = data[1];
                    String album = data[2];
                    String genre = data[3];

                    Song song = new Song(songName, artistName, album, genre);
                    songList.add(song);
                }
            }
        } catch (IOException e) {//catch exception
            e.printStackTrace();
        }

        return songList;
    }

    
}

class Song {//creates a song class to store songs with specific information
    private String name;
    private String artist;
    private String album;
    private String genre;

    public Song(String name, String artist, String album, String genre) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return name + " by " + artist + " - " + genre;
    }
}
