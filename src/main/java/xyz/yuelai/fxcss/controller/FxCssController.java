package xyz.yuelai.fxcss.controller;

import com.jfoenix.controls.JFXButton;
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
    /**
     * 关闭
     */
    public Label close;
    /**
     * 最大化
     */
    public Label maximize;
    /**
     * 最小化
     */
    public Label minimize;
    /**
     * 汉堡折叠按钮
     */
    public JFXHamburger fold;
    public VBox nav;
    public TextField keyword;
    public JFXButton searchIcon;
    public Label title;
    private double offsetX;
    private double offsetY;

    /**
     * 侧边菜单是否展开，默认为展开
     */
    private BooleanProperty expand = new SimpleBooleanProperty(true);

    public void initialize() {

        // 鼠标按下事件
        root.setOnMousePressed(event -> {
            Window window = root.getScene().getWindow();
            //             鼠标在屏幕中的坐标，    窗体在屏幕中的坐标
            this.offsetX = event.getScreenX() - window.getX();
            this.offsetY = event.getScreenY() - window.getY();
        });

        // 拖拽事件
        root.setOnMouseDragged(event -> {
            Window window = root.getScene().getWindow();
            //   新的鼠标位置-旧的鼠标位置+旧的窗体位置
            // = 鼠标的偏移量+旧的窗体位置
            window.setX(event.getScreenX() - this.offsetX);
            window.setY(event.getScreenY() - this.offsetY);
        });

        // 最小化
        minimize.setOnMouseClicked(event -> {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setIconified(true);
        });

        // 最大化
        maximize.setOnMouseClicked(event -> {
            Stage stage = (Stage) root.getScene().getWindow();
            // 最大化，取消最大化
            stage.setMaximized(stage.maximizedProperty().not().get());
        });

        // 退出
        close.setOnMouseClicked(event -> {
            Platform.exit();
        });
        // 当折叠后，搜索框显示为搜索图标
        searchIcon.visibleProperty().bind(expand.not());
        // 当展开后，搜索框显示
        keyword.visibleProperty().bind(expand);

        // 汉堡菜单点击，改变expand的值，expand标志为侧边菜单打开标志
        fold.setOnMouseClicked(event -> {
            expand.set(expand.not().get());
        });

        // 为expand标志提供change监听器
        expand.addListener((observableValue, oldVal, newVal) -> {
            System.out.println("newVal = "+newVal);
            if (!newVal) {
                // 如果为false，则为折叠状态，侧边菜单可以折叠
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
            if (newVal) {
                // 如果为true，则为展开状态，可以展开
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
