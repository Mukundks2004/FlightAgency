
import au.edu.uts.ap.javafx.*;
import javafx.stage.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import model.*;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = new Stage();
        stage.setX(ViewLoader.X + 601);
        stage.setY(ViewLoader.Y);
        stage.getIcons().add(new Image("/image/login_icon.png"));
        ViewLoader.showStage(new Agency(), "/view/LoginView.fxml", "Login", stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
