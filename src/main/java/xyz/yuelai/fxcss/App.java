package xyz.yuelai.fxcss;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

/**
 * @author zhong
 * @date 2020/2/22 16:44
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UNDECORATED);

        URL resource = getClass().getResource("/fxml/fxcss.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        fxmlLoader.load();

        BorderPane root = fxmlLoader.getRoot();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
