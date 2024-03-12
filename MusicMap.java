import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MusicMap extends Application {
	private UIComponents uiComponents;

    @Override
    public void start(Stage primaryStage) {
    	
        uiComponents = new UIComponents();//creates instance of UIComponents

        StackPane stackPane = uiComponents.createStackPane();//adds all of UIComponents including background
        Scene scene = new Scene(stackPane);

        //sets up stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("MUSIC_MAP");
        primaryStage.setWidth(800);
        primaryStage.setHeight(620);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
