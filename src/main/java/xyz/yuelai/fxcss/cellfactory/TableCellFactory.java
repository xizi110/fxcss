package xyz.yuelai.fxcss.cellfactory;

import com.jfoenix.controls.JFXButton;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import xyz.yuelai.fxcss.domain.User;

/**
 * @author zhong
 * @date 2020/2/23 13:39
 */
public class TableCellFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<User, JFXButton>> {
    @Override
    public TableCell<User, JFXButton> call(TableColumn<S, T> param) {
        return new TableCell<>() {
            @Override
            protected void updateItem(JFXButton item, boolean empty) {
                super.updateItem(item, empty);
                setText(null);
                if (item != null) {
                    setGraphic(item);
                    item.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
                        int index = getTableRow().getIndex();
                        getTableView().getSelectionModel().select(index);
                    });

                    ReadOnlyBooleanProperty b = getTableRow().selectedProperty();
                    item.textFillProperty().bind(Bindings.when(b).then(Color.WHITE).otherwise(Color.BLACK));

                    item.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        int index = getTableRow().getIndex();
                        if (index != -1) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setContentText(String.valueOf(getTableRow().getItem()));
                            alert.showAndWait();
                        }
                    });
                } else {
                    setGraphic(null);
                }
            }
        };
    }
}
