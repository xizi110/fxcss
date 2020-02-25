package xyz.yuelai.fxcss.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import xyz.yuelai.fxcss.domain.User;

/**
 * @author zhong
 * @date 2020/2/17 22:23
 */
public class TableController {

    public TableView<User> tableView;

    public void initialize() {
        System.out.println("TableController.initialize");

        ObservableList<User> users = FXCollections.observableArrayList();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(Long.valueOf(i));
            user.setName("小明" + i);
            user.setAge((int) (Math.random() * 80) + 10);
            user.setHobbit("coding");
            user.setNation("汉族");
            user.setEducation("本科");
            users.add(user);
        }
        tableView.setItems(users);
    }
}
