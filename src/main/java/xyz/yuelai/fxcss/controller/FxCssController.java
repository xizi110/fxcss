package xyz.yuelai.fxcss.controller;

import com.jfoenix.controls.JFXHamburger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 * @author zhong
 * @date 2020/2/22 16:47
 */
public class FxCssController {

    public BorderPane root;
    public Label close;
    public Label maximize;
    public Label minimize;
    public JFXHamburger fold;
    public VBox nav;
    public TextField keyword;
    public FontIcon searchIcon;
    public Label title;
    private double offsetX;
    private double offsetY;

    private BooleanProperty expand = new SimpleBooleanProperty(false);

    public void initialize() {

        root.setOnMousePressed(event -> {
            Window window = root.getScene().getWindow();
            this.offsetX = event.getScreenX() - window.getX();
            this.offsetY = event.getScreenY() - window.getY();
        });

        root.setOnMouseDragged(event -> {
            Window window = root.getScene().getWindow();
            window.setX(event.getScreenX() - this.offsetX);
            window.setY(event.getScreenY() - this.offsetY);
        });

        minimize.setOnMouseClicked(event -> {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setIconified(true);
        });

        maximize.setOnMouseClicked(event -> {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setMaximized(stage.maximizedProperty().not().get());
        });

        close.setOnMouseClicked(event -> {
            Platform.exit();
        });

        searchIcon.visibleProperty().bind(expand);
        keyword.visibleProperty().bind(expand.not());

        fold.setOnMouseClicked(event -> {
            expand.set(expand.not().get());
        });

        expand.addListener((observableValue, oldVal, newVal) -> {

            if (newVal) {
                Timeline timeline = new Timeline();
                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.millis(30), new KeyValue(nav.prefWidthProperty(), 220)),
                        new KeyFrame(Duration.millis(60), new KeyValue(nav.prefWidthProperty(), 100)),
                        new KeyFrame(Duration.millis(90), new KeyValue(nav.prefWidthProperty(), 0)),
                        new KeyFrame(Duration.millis(150), new KeyValue(nav.prefWidthProperty(), 40))
                );
                timeline.play();
                title.setStyle("-fx-background-color: transparent; -fx-padding: 0 0 0 50px");
            }
            if (!newVal) {
                Timeline timeline = new Timeline();
                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.millis(30), new KeyValue(nav.prefWidthProperty(), 40)),
                        new KeyFrame(Duration.millis(60), new KeyValue(nav.prefWidthProperty(), 100)),
                        new KeyFrame(Duration.millis(90), new KeyValue(nav.prefWidthProperty(), 360)),
                        new KeyFrame(Duration.millis(150), new KeyValue(nav.prefWidthProperty(), 320))
                );
                timeline.play();
                title.setStyle("");
            }
        });


    }
}
