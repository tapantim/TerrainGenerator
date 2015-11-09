package fi.timotapanainen.tergen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA.
 * User: tapantim
 * Date: 19.5.2013
 * Time: 20:37
 * To change this template use File | Settings | File Templates.
 */
public class TerrainGenerator extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/noiseviewer.fxml"));
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(TerrainGenerator.class, args);
    }

}
