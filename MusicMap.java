
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MusicMap extends Application {
	private ImageView ivStar1, ivStar2, ivStar3, ivStar4, ivStar5, ivStar6, ivStar7;
	private static final String FONT_FAMILY = "Helvetica";// creates a fond that will be used for everything
	private static final Insets PADDING_ZERO = new Insets(0);// sets a default of zero padding
	private StackPane stackPane, starPane;
	private ListView<Song> listView;
	private TextField musicSearch;
	private GridPane textFieldGridPane;
	private Button searchButton, resetButton;
	private Label enterLabel;
	private HBox searchBox;
	protected VBox mainBox;
	private BackgroundImg background;
	private Text txtStar1, txtStar2, txtStar3, txtStar4, txtStar5, txtStar6, txtStar7;
	private DatabaseSort database;
	private ObservableList<Song> songList;

	@Override
	public void start(Stage primaryStage) {

		ListViewConfigurations lvc = new ListViewConfigurations();

		stackPane = new StackPane();
		starPane = new StackPane();
		// CONFIGURING ITEMS FOR MAINBOX
		listView = lvc.initializeListView();
		listView.setVisible(false);
		musicSearch = createTextField();
		textFieldGridPane = new GridPane();
		textFieldGridPane.add(musicSearch, 0, 0);
		textFieldGridPane.setAlignment(Pos.CENTER);
		searchButton = createSearchButton();
		enterLabel = createLabel();
		searchBox = createSearchBox(textFieldGridPane, searchButton);
		resetButton = createResetButton();

		mainBox = createMainBox(enterLabel, searchBox, listView);
		stackPane.getChildren().addAll(mainBox, resetButton);
		background = new BackgroundImg();
		background.setBackground(starPane);

		initializeStars();// adding stars
		// METHODS SETTING PARAMETERS FOR LISTVIEW
		lvc.addListViewSelectionListener(listView, musicSearch, stackPane);
		lvc.configureListViewVisibility(musicSearch, listView, stackPane);
		lvc.addSearchListener(musicSearch);

		Scene scene = new Scene(new StackPane(starPane, stackPane));

		// sets up stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("MUSIC_MAP");
		primaryStage.setFullScreen(true);
		primaryStage.setWidth(800);
		primaryStage.setHeight(620);
		primaryStage.show();

		resetStage();

	}

	public static void main(String[] args) {
		launch(args);
	}

	private void initializeStars() {

		ivStar1 = new ImageView(new Image("images/stars.png"));
		txtStar1 = new Text();
		txtStar1.setFont(Font.font("Helvetica", FontWeight.NORMAL, .10));
		txtStar1.setFill(javafx.scene.paint.Color.WHITE);
		ivStar2 = new ImageView(new Image("images/stars.png"));
		txtStar2 = new Text();
		txtStar2.setFont(Font.font("Helvetica", FontWeight.NORMAL, .10));
		txtStar2.setFill(javafx.scene.paint.Color.WHITE);
		ivStar3 = new ImageView(new Image("images/stars.png"));
		txtStar3 = new Text();
		txtStar3.setFont(Font.font("Helvetica", FontWeight.NORMAL, .10));
		txtStar3.setFill(javafx.scene.paint.Color.WHITE);
		ivStar4 = new ImageView(new Image("images/stars.png"));
		txtStar4 = new Text();
		txtStar4.setFont(Font.font("Helvetica", FontWeight.NORMAL, .10));
		txtStar4.setFill(javafx.scene.paint.Color.WHITE);
		ivStar5 = new ImageView(new Image("images/stars.png"));
		txtStar5 = new Text();
		txtStar5.setFont(Font.font("Helvetica", FontWeight.NORMAL, .10));
		txtStar5.setFill(javafx.scene.paint.Color.WHITE);
		ivStar6 = new ImageView(new Image("images/stars.png"));
		txtStar6 = new Text();
		txtStar6.setFont(Font.font("Helvetica", FontWeight.NORMAL, .10));
		txtStar6.setFill(javafx.scene.paint.Color.WHITE);
		ivStar7 = new ImageView(new Image("images/stars.png"));
		txtStar7 = new Text();
		txtStar7.setFont(Font.font("Helvetica", FontWeight.NORMAL, .10));
		txtStar7.setFill(javafx.scene.paint.Color.WHITE);

		ivStar1.setTranslateX(0);
		ivStar1.setTranslateY(-200);
		ivStar1.setFitWidth(0.1);
		ivStar1.setFitHeight(0.1);
		txtStar1.setTranslateX(0);
		txtStar1.setTranslateY(-170);

		ivStar2.setTranslateX(400);
		ivStar2.setTranslateY(300);
		ivStar2.setFitWidth(0.1);
		ivStar2.setFitHeight(0.1);
		txtStar2.setTranslateX(400);
		txtStar2.setTranslateY(330);

		ivStar3.setTranslateX(300);
		ivStar3.setTranslateY(00);
		ivStar3.setFitWidth(0.1);
		ivStar3.setFitHeight(0.1);
		txtStar3.setTranslateX(300);
		txtStar3.setTranslateY(30);

		ivStar4.setTranslateX(500);
		ivStar4.setTranslateY(-300);
		ivStar4.setFitWidth(0.1);
		ivStar4.setFitHeight(0.1);
		txtStar4.setTranslateX(500);
		txtStar4.setTranslateY(-270);

		ivStar5.setTranslateX(-400);
		ivStar5.setTranslateY(300);
		ivStar5.setFitWidth(0.1);
		ivStar5.setFitHeight(0.1);
		txtStar5.setTranslateX(-400);
		txtStar5.setTranslateY(330);

		ivStar6.setTranslateX(-300);
		ivStar6.setTranslateY(0);
		ivStar6.setFitWidth(0.1);
		ivStar6.setFitHeight(0.1);
		txtStar6.setTranslateX(-300);
		txtStar6.setTranslateY(30);

		ivStar7.setTranslateX(-500);
		ivStar7.setTranslateY(-300);
		ivStar7.setFitWidth(0.1);
		ivStar7.setFitHeight(0.1);
		txtStar7.setTranslateX(-500);
		txtStar7.setTranslateY(-270);

		// Add the images to the StackPane
		starPane.getChildren().addAll(txtStar1, ivStar1, txtStar2, ivStar2, txtStar3, ivStar3, txtStar4, ivStar4,
				txtStar5, ivStar5, txtStar6, ivStar6, txtStar7, ivStar7);

		
		
		// SETTING UP ANIMATIONS
		ParallelTransition pt = new ParallelTransition();

		searchButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				searchButton.setDisable(true);// disallows the search button to be pressed again before reset

				final String song = musicSearch.getText();
				System.out.println(song);

				database = new DatabaseSort();
				songList = database.parseDatabaseFile("songs.txt");
				txtStar1.setText(song);
				txtStar2.setText(database.getRandomSongWithSameGenre(songList, song));
				txtStar3.setText(database.getRandomSongWithSameGenre(songList, song));
				txtStar4.setText(database.getRandomSongWithSameGenre(songList, song));
				txtStar5.setText(database.getRandomSongWithSameGenre(songList, song));
				txtStar6.setText(database.getRandomSongWithSameGenre(songList, song));
				txtStar7.setText(database.getRandomSongWithSameGenre(songList, song));

				Animations animate = new Animations();

				animate.scaleDown(mainBox);
				animate.moveUp(mainBox);
				animate.fadeIn(resetButton);// fades a reset button in
				resetButton.setVisible(true);

				pt.getChildren().addAll(animate.scaleUp(ivStar1), animate.floats(ivStar1), animate.scaleUp(txtStar1),
						animate.floats4(txtStar1), animate.scaleUp(ivStar2), animate.floats2(ivStar2),
						animate.scaleUp(txtStar2), animate.floats3(txtStar2), animate.scaleUp(ivStar3),
						animate.floats3(ivStar3), animate.scaleUp(txtStar3), animate.floats4(txtStar3),
						animate.scaleUp(ivStar4), animate.floats4(ivStar4), animate.scaleUp(txtStar4),
						animate.floats2(txtStar4), animate.scaleUp(ivStar5), animate.floats2(ivStar5),
						animate.scaleUp(txtStar5), animate.floats3(txtStar5), animate.scaleUp(ivStar6),
						animate.floats3(ivStar6), animate.scaleUp(txtStar6), animate.floats2(txtStar6),
						animate.scaleUp(ivStar7), animate.floats2(ivStar7), animate.scaleUp(txtStar7),
						animate.floats3(txtStar7));

				pt.play();
				

				resetButton.setDisable(false);
			}
			
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
	private Button createResetButton() {
		Button resetButton = new Button("Reset");
		resetButton.setPrefSize(150, 50);
		resetButton.setFont(Font.font(FONT_FAMILY, FontWeight.NORMAL, 30));
		resetButton.setStyle("-fx-background-color: white; -fx-background-radius: 15px;-fx-font-style: italic;");
		resetButton.setCursor(Cursor.HAND);
		resetButton.setTranslateX(0);
		resetButton.setTranslateY(150);
		resetButton.setVisible(false);
		resetButton.setDisable(true);
		return resetButton;
	}

	private Button createSearchButton() {
		Button searchButton = new Button();
		Image searchImage = new Image("images/search.jpg");
		ImageView imageView = new ImageView(searchImage);
		imageView.setFitWidth(30);// setting size of image
		imageView.setFitHeight(30);
		searchButton.setGraphic(imageView);// sets the image to button
		searchButton.setPrefSize(55, 55);// setting size of button
		searchButton.setStyle("-fx-background-color: white; -fx-background-radius: 15px;");// formatting button
		searchButton.setCursor(Cursor.HAND);// changing type of cursor when hovering over button
		return searchButton;
	}

	// creates a label to prompt user to enter a song
	private Label createLabel() {
		Label enterLabel = new Label("Find similar songs:");
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

	private void resetStage() {
		Animations animate = new Animations();
		ParallelTransition pt = new ParallelTransition();

		resetButton.setOnAction(e -> {

			resetButton.setDisable(true);

			pt.getChildren().addAll(animate.scaleDown(ivStar1), animate.scaleDown(txtStar1), animate.scaleDown(ivStar2),
					animate.scaleDown(txtStar2), animate.scaleDown(ivStar3), animate.scaleDown(txtStar3),
					animate.scaleDown(ivStar4), animate.scaleDown(txtStar4), animate.scaleDown(ivStar5),
					animate.scaleDown(txtStar5), animate.scaleDown(ivStar6), animate.scaleDown(txtStar6),
					animate.scaleDown(ivStar7), animate.scaleDown(txtStar7), animate.fadeOut(resetButton),
					animate.scaleUp(mainBox), animate.moveDown(mainBox), animate.fadeIn(mainBox));
			pt.play();
		});
		pt.setOnFinished(event -> {
		    searchButton.setDisable(false);
		});

		

	}

}
