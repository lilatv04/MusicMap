import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;

public class BackgroundImageManager {//called to UIComponents.java so wont see it in MusicMap.java

    private static final String BACKGROUND_IMAGE_PATH = "spaceMap.jpg";

    public static void setBackground(StackPane stackPane) {//sets background
        BackgroundSize backgroundSize = new BackgroundSize(0, 0, true, true, true, true);//sets arguments
        Image backgroundImage = new Image(BACKGROUND_IMAGE_PATH);
        Background background = new Background(new BackgroundImage(
                backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,//sets how background works
                BackgroundPosition.DEFAULT, backgroundSize));
        stackPane.setBackground(background);//sets background to stackpane
    }
}
