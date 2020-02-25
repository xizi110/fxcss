package xyz.yuelai.fxcss;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

/**
 * @author zhong
 * @date 2020/2/22 16:44
 */
public class App extends Application {

    private static final String FXCSS_FXML_PATH = "/fxml/fxcss.fxml";
    private static final String TABLE_FXML_PATH = "/fxml/table.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UNDECORATED);

        URL resource = getClass().getResource(TABLE_FXML_PATH);
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
