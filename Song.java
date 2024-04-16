import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Song {
	static List<Song> songList = new ArrayList<Song>();

	private String trackName;
	private String artistName;
	private String albumName;
	private String genre;
	private String isrc;

	public Song(String trackName, String artistName, String albumName, String genre, String isrc) {
		this.trackName = trackName;
		this.artistName = artistName;
		this.albumName = albumName;
		this.genre = genre;
		this.isrc = isrc;
	}

	// Return true if method was successful otherwise return false
	static boolean populateSongList() {
		// If the list has already been populated don't add songs
		if (!songList.isEmpty()) {
			return false;
		}

		File file;
		Scanner scan;

		try {
			file = new File("songs.txt");
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			return false;
		}

		scan.nextLine();
		int iter = 0;

		while (scan.hasNextLine()) {
			String[] data = scan.nextLine().split(",");
			iter += 1;

			try {
				songList.add(new Song(data[0], data[1], data[2], data[3], data[5]));
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.print("Error: " + iter + ": ");

				for (int i = 0; i < data.length; i++) {
					System.out.print(data[i]);
				}

				System.out.println();
			}

		}

		scan.close();

		return true;
	}

	// Sort songs into arrays according to their genre
	static List<Song>[] sortSongsByGenre() {
		// Assuming a finite number of genres, we'll use an array of lists
		List<Song>[] sortedByGenre = new ArrayList[8]; // Change 10 to the actual number of genres

		// Initialize each list
		for (int i = 0; i < sortedByGenre.length; i++) {
			sortedByGenre[i] = new ArrayList<>();
		}

		// Sort songs into appropriate list based on genre
		for (Song song : songList) {
			// Assuming genre is represented as a string, convert it to an index
			int genreIndex = getGenreIndex(song.getGenre());
			sortedByGenre[genreIndex].add(song);
		}

		return sortedByGenre;
	}

	// Helper method to convert genre string to an index
	private static int getGenreIndex(String genre) {
		// Example logic to map genre strings to indices
		// You can implement your own logic based on your specific genres
		switch (genre) {
		case "folk":
			return 0;
		case "Hip Hop":
			return 1;
		case "Indi":
			return 2;
		case "Jazz":
			return 3;
		case "Pop":
			return 4;
		case "Rock":
			return 5;
		case "EDM/Techno":
			return 6;
		default:
			return 7;
		}
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getIsrc() {
		return isrc;
	}

	public void setIsrc(String isrc) {
		this.isrc = isrc;
	}

	public String toString() {
		return "Track: " + trackName + ", Artist: " + artistName + ", Album: " + albumName + ", Genre: " + genre;
	}
}