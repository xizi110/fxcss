package xyz.yuelai.fxcss;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.kordamp.ikonli.dashicons.Dashicons;
import org.kordamp.ikonli.javafx.FontIcon;
import xyz.yuelai.fxcss.controls.Message;

import java.net.URL;

/**
 * @author zhong
 * @date 2020/2/22 16:44
 */
public class App extends Application {

    private static final String FXCSS_FXML_PATH = "/fxml/fxcss.fxml";
    private static final String TABLE_FXML_PATH = "/fxml/table.fxml";
    private static final String LOGIN_FXML_PATH = "/fxml/login.fxml";
    private static final String MESSAGE_FXML_PATH = "/fxml/message.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UNDECORATED);

        URL resource = getClass().getResource(LOGIN_FXML_PATH);
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Pane root = fxmlLoader.load();

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
