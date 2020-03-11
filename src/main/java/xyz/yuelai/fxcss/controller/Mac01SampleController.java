package xyz.yuelai.fxcss.controller;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * @author zhong
 * @date 2020/3/11 17:03
 */
public class Mac01SampleController {

    public VBox vb;
    public AnchorPane container;
    public ImageView imageView;

    public void  initialize(){
        container.prefHeightProperty().bind(vb.heightProperty().add(70));
        Rectangle rectangle = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        rectangle.setArcWidth(10);
        rectangle.setArcHeight(10);
        imageView.setClip(rectangle);
    }
}
