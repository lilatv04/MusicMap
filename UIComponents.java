import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UIComponents {

	private static final String FONT_FAMILY = "Helvetica";// creates a fond that will be used for everything
	private static final Insets PADDING_ZERO = new Insets(0);// sets a default of zero padding
	private FilteredList<Song> filteredSongs;
	private StackPane stackPane;
	private ListView<Song> listView;
	private TextField musicSearch;
	private GridPane textFieldGridPane;
	private Button searchButton;
	private Label enterLabel;
	private HBox searchBox;
	private VBox mainBox;

	public StackPane createStackPane() {

		stackPane = new StackPane();
		BackgroundImageManager.setBackground(stackPane);

		listView = initializeAndConfigureListView();

		musicSearch = createTextField();
		addSearchListener(musicSearch);

		configureListViewVisibility(musicSearch, listView, stackPane);

		textFieldGridPane = new GridPane();
		textFieldGridPane.add(musicSearch, 0, 0);
		textFieldGridPane.setAlignment(Pos.CENTER);

		searchButton = createSearchButton();

		enterLabel = createLabel();

		searchBox = createSearchBox(textFieldGridPane, searchButton);

		mainBox = createMainBox(enterLabel, searchBox, listView);

		stackPane.getChildren().addAll(mainBox);

	    addListViewSelectionListener(listView, musicSearch, stackPane);

		
		return stackPane;
	}

	private ListView<Song> initializeAndConfigureListView() {
	    ListView<Song> listView = new ListView<>();

	    DatabaseSort databaseSort = new DatabaseSort();
	    ObservableList<Song> songList = databaseSort.parseDatabaseFile("database.txt");

	    // create a FilteredList with the original songList
	    filteredSongs = new FilteredList<>(songList, p -> true);

	    // create a SortedList to keep the filtered data sorted
	    SortedList<Song> sortedSongs = new SortedList<>(filteredSongs);
	    listView.setItems(sortedSongs);
	    listView.setMaxSize(270, 130);

	    return listView;
	}
	
	private void addListViewSelectionListener(ListView<Song> listView, TextField musicSearch, StackPane stackPane) {
	    listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	        if (newValue != null) {
	            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
	            if (selectedIndex >= 0 && selectedIndex < listView.getItems().size()) {
	                musicSearch.setText(formatSongDetails(newValue));
	                listView.setVisible(false);
	            }
	        }
	    });
	}

	private String formatSongDetails(Song song) {
	    return String.format("%s by %s - %s", song.getName(), song.getArtist(), song.getGenre());
	}


	//sets when the list should be visible 
	private void configureListViewVisibility(TextField musicSearch, ListView<Song> listView, StackPane stackPane) {
	    //updates visibility based on focus and non-empty text
	    musicSearch.textProperty().addListener((obs, oldVal, newVal) ->
	            listView.setVisible(musicSearch.isFocused() || !newVal.isEmpty())
	    );

	    //close the ListView when clicked outside it
	    stackPane.setOnMouseClicked(event -> {
	        if (event.getButton() == MouseButton.PRIMARY && !listView.getBoundsInParent().contains(event.getX(), event.getY())) {
	            listView.setVisible(false);
	            musicSearch.getParent().requestFocus();
	        }
	    });
	}


	private void addSearchListener(TextField musicSearch) {
		musicSearch.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				updateFilterPredicate(newValue);
			}
		});
	}

	private void updateFilterPredicate(String newValue) {
		filteredSongs.setPredicate(song -> {
			if (newValue == null || newValue.isEmpty()) {
				return true; // shows songs when txt box is empty
			}

			String lowerCaseFilter = newValue.toLowerCase();

			// Filter songs based on their properties (you can modify this based on your
			// Song class)
			return song.getName().toLowerCase().contains(lowerCaseFilter);
		});
	}

	private TextField createTextField() {
		TextField musicSearch = new TextField();
		musicSearch.setFont(Font.font("FONT_FAMILY", FontWeight.NORMAL, 30));
		musicSearch.setStyle("-fx-background-radius: 15px; -fx-background-color: white;");
		musicSearch.setMaxWidth(300);
		return musicSearch;
	}

	// binds an image to a button allowing it to function as a working search button
	// function
	private Button createSearchButton() {
		Button searchButton = new Button();
		Image searchImage = new Image("search.jpg");
		ImageView imageView = new ImageView(searchImage);
		imageView.setFitWidth(30);// setting size of image
		imageView.setFitHeight(30);
		searchButton.setGraphic(imageView);// sets the image to button
		searchButton.setPrefSize(55, 55);// setting size of button
		searchButton.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");//formatting button
		searchButton.setOnAction(e -> System.out.print("Search button pressed"));// testing button
		searchButton.setCursor(Cursor.HAND);// changing type of cursor when hovering over button
		return searchButton;
	}

	// creates a label to prompt user to enter a song
	private Label createLabel() {
		Label enterLabel = new Label("Select a song:");
		enterLabel.setFont(Font.font(FONT_FAMILY, FontWeight.NORMAL, 30));
		enterLabel.setStyle("-fx-font-style: italic;");
		enterLabel.setTextFill(javafx.scene.paint.Color.WHITE);
		return enterLabel;
	}

	// creates a HBox that creates a functional search bar
	private HBox createSearchBox(GridPane textFieldGridPane, Button searchButton) {
		HBox searchBox = new HBox(10);
		searchBox.setAlignment(Pos.CENTER);
		searchBox.getChildren().addAll(textFieldGridPane, searchButton);
		searchBox.setPadding(new Insets(0, 0, 0, 65));
		return searchBox;
	}

	private VBox createMainBox(Label enterLabel, HBox searchBox, ListView<Song> listView) {
		VBox mainBox = new VBox();// creates a VBOX that contains all elements
		mainBox.setAlignment(Pos.CENTER);
		mainBox.getChildren().addAll(enterLabel, searchBox, listView);// adds to mainBox
		mainBox.setPadding(PADDING_ZERO);
		return mainBox;
	}
}
