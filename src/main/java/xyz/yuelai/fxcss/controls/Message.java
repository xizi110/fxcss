package xyz.yuelai.fxcss.controls;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.kordamp.ikonli.fontawesome.FontAwesome;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 * @author zhong
 * @date 2020/2/26 16:57
 */
public class Message extends Label {

    private static final MessageType DEFAULT_TYPE = MessageType.INFO;

    private static final String DEFAULT_STYLE_CLASS = "message";

    private Pane container;

    public void setContainer(Pane container) {
        this.container = container;
    }

    public Pane getContainer() {
        return container;
    }

    public Message(Pane container) {
        this(null, container, DEFAULT_TYPE);
    }

    public Message(String text, Pane container) {
        this(text, container, DEFAULT_TYPE);
    }

    public Message(String text, Pane container, MessageType type) {
        super(text, getIcon(type));
        this.container = container;

        this.getStylesheets().add(getClass().getResource("/css/message.css").toExternalForm());

        if (type == null) {
            type = DEFAULT_TYPE;
        }

        this.getStyleClass().addAll(DEFAULT_STYLE_CLASS, type.name().toLowerCase());

    }

    public enum MessageType {

        INFO,

        SUCCESS,

        WARNING,

        ERROR
    }

    private static FontIcon getIcon(MessageType type) {
        FontIcon fontIcon = new FontIcon();
        fontIcon.setIconSize(20);
        switch (type) {
            case INFO: {
                fontIcon.setIconCode(FontAwesome.INFO_CIRCLE);
                fontIcon.setIconColor(Color.valueOf("#909399"));
                break;
            }
            case SUCCESS: {
                fontIcon.setIconCode(FontAwesome.CHECK_CIRCLE);
                fontIcon.setIconColor(Color.valueOf("#67c23a"));
                break;
            }
            case WARNING: {
                fontIcon.setIconCode(FontAwesome.EXCLAMATION_CIRCLE);
                fontIcon.setIconColor(Color.valueOf("#e6a23c"));
                break;
            }
            default: {
                fontIcon.setIconCode(FontAwesome.TIMES_CIRCLE);
                fontIcon.setIconColor(Color.valueOf("#f56c6c"));
            }
        }
        return fontIcon;
    }

    /**
     * 显示信息框
     */
    public void show() {
        if (this.container != null && this.container.lookup(".message") == null) {
            this.container.getChildren().add(this);
        }
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();

        double prefWidth = this.container.getPrefWidth();
        double width = getWidth();

        double centerX = (prefWidth - width) / 2;
        resizeRelocate(centerX, 0 - getHeight(), getWidth(), getHeight());

        translateY(getLayoutY(), 10);
    }



    private void translateY(double start, double end) {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(0), new KeyValue(layoutYProperty(), start, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(200), new KeyValue(layoutYProperty(), end, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(2200), new KeyValue(layoutYProperty(), end, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(2400), new KeyValue(layoutYProperty(), start, Interpolator.EASE_BOTH))
        );
        timeline.play();

        timeline.setOnFinished(event -> {
            this.container.getChildren().remove(this);
        });
    }
}
