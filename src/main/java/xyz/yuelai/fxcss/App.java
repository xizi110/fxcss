package xyz.yuelai.fxcss;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import xyz.yuelai.fxcss.controls.Calendar;

import java.net.URL;

/**
 * @author zhong
 * @date 2020/2/22 16:44
 */
public class App extends Application {

    private static final String FXCSS_FXML_PATH = "/fxml/fxcss.fxml";
    private static final String TABLE_FXML_PATH = "/fxml/table.fxml";
    private static final String LOGIN_FXML_PATH = "/fxml/login.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.DECORATED);

        URL resource = getClass().getResource(LOGIN_FXML_PATH);
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Pane root = fxmlLoader.load();

        Calendar calendar = new Calendar();

        Scene scene = new Scene(calendar);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
