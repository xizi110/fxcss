package xyz.yuelai.fxcss.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import xyz.yuelai.fxcss.controls.Message;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * @author l1103
 */
public class LoginController implements Initializable {

    public AnchorPane root;
    public VBox lgPane;
    public JFXComboBox<String> type;
    public JFXTextField accountText;
    public JFXPasswordField passwordText;
    public JFXButton loginBtn;

    private ProgressIndicator progressIndicator;

    private SimpleStringProperty loginTypeProperty = new SimpleStringProperty();
    private SimpleStringProperty accountProperty = new SimpleStringProperty();
    private SimpleStringProperty passwordProperty = new SimpleStringProperty();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 登录面板居中
        AnchorPane.setLeftAnchor(lgPane, 250D);
        AnchorPane.setTopAnchor(lgPane, 150D);

        // 身份下拉框
        ObservableList<String> typeList = FXCollections.observableArrayList("学生", "教师", "管理员");
        type.setItems(typeList);

        type.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            loginTypeProperty.setValue(newValue);
        });

        type.setValidators(new RequiredFieldValidator("请选择登录身份"));

        // 账号密码
        accountText.textProperty().bindBidirectional(accountProperty);
        accountText.setValidators(new RequiredFieldValidator("请输入账号"));
        passwordText.textProperty().bindBidirectional(passwordProperty);
        passwordText.setValidators(new RequiredFieldValidator("请输入密码"));

        progressIndicator = new ProgressIndicator(-1);
        progressIndicator.setPrefHeight(loginBtn.getPrefHeight() / 2);
        progressIndicator.setStyle("-fx-progress-color: #d4d2d1");

    }

    public void login(ActionEvent actionEvent) {

        loginBtn.setText(null);
        loginBtn.setGraphic(progressIndicator);

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                Thread.sleep(new Random().nextInt(1000));
                return null;
            }

            @Override
            protected void updateValue(Void value) {
                if (type.validate() && accountText.validate() && passwordText.validate()) {
                    Message message = new Message("登录成功", root, Message.MessageType.SUCCESS);
                    message.show();
                }

                loginBtn.setText("登录");
                loginBtn.setGraphic(null);
            }
        };

        new Thread(task).start();


    }
}
