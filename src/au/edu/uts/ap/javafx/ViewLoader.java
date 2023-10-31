package au.edu.uts.ap.javafx;

import javafx.fxml.*;
import javafx.stage.*;
import model.Exceptions.ErrorModel;
import javafx.scene.*;
import javafx.scene.image.Image;
import java.io.*;
import java.lang.Runnable;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.AgencyController;

public class ViewLoader {

    public final static int X = 5;
    public final static int Y = 5;

    public static void showErrorWindow(ErrorModel error) {
        try {
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/image/error_icon.png"));
            showStage(error, "/view/Error/ErrorView.fxml", "Error", stage);
        }
        catch (IOException ex) {
            Logger.getLogger(AgencyController.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }

    public static <T> void showStage(T model, String fxml, String title, Stage stage, Runnable onStageClosed) throws IOException {
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource(fxml), null, null,
                type -> {
                    try {
                        @SuppressWarnings("unchecked")
                        Controller<T> controller = (Controller<T>) type.newInstance();
                        controller.model = model;
                        controller.stage = stage;
                        return controller;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        Parent root = loader.load();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.sizeToScene();
        stage.setOnCloseRequest(e -> onStageClosed.run());
        stage.show();
    }

    public static <T> void showStage(T model, String fxml, String title, Stage stage) throws IOException {
        showStage(model, fxml, title, stage, () -> {
        });
    }

    public static void showStage(String viewAgencyViewfxml, String Agency_View, Stage primaryStage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
