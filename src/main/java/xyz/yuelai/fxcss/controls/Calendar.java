package xyz.yuelai.fxcss.controls;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhong
 * @date 2020/3/2 21:04
 */
public class Calendar extends VBox {

    @FXML
    private FlowPane dayPane;
    @FXML
    private Label currentDate;
    @FXML
    private HBox weekdays;
    @FXML
    private StackPane dayContainer;


    public Calendar() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/calendar.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        List<DayCell> nowCalendar = getNowCalendar();
        dayPane.getChildren().addAll(nowCalendar);
    }

    private java.util.Calendar calendar1 = java.util.Calendar.getInstance();

    private SimpleDateFormat format =
            new SimpleDateFormat("yyyy年MM月");

    @FXML
    public void prevMonth(ActionEvent event) {
        calendar1.add(java.util.Calendar.MONTH, -1);
        List<DayCell> nowCalendar = getNowCalendar();
        currentDate.setText(format.format(calendar1.getTime()));
        dayPane.getChildren().clear();
        dayPane.getChildren().addAll(nowCalendar);
    }

    @FXML
    public void nextMonth(ActionEvent event) {
        calendar1.add(java.util.Calendar.MONTH, 1);
        List<DayCell> nowCalendar = getNowCalendar();
        dayPane.getChildren().clear();
        dayPane.getChildren().addAll(nowCalendar);
        currentDate.setText(format.format(calendar1.getTime()));
    }


    public List<DayCell> getNowCalendar() {

        DayCell[] dayCells = new DayCell[42];
        java.util.Calendar calendar = (java.util.Calendar) this.calendar1.clone();
        calendar.set(java.util.Calendar.DAY_OF_MONTH, 1);
        int startDayOfMonth = calendar.get(java.util.Calendar.DAY_OF_WEEK);
        int endDayOfMonth = calendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

        java.util.Calendar now = java.util.Calendar.getInstance();
        int today = now.get(java.util.Calendar.DAY_OF_MONTH);
        int year = now.get(java.util.Calendar.YEAR);
        int month = now.get(java.util.Calendar.MONTH);

        boolean thisMonth = (year == calendar.get(java.util.Calendar.YEAR)) &&
                month == calendar.get(java.util.Calendar.MONTH);

        if (thisMonth) {
            int week = calendar1.get(java.util.Calendar.DAY_OF_WEEK);
            if (week == 0) {
                week = 6;
            } else {
                week = week - 2;
            }
            weekdays.getChildren().get(week).getStyleClass().remove("active");
            weekdays.getChildren().get(week).getStyleClass().add("active");
        }

        int p = 0;

        // 如果本月一号不是周一，则需要计算上个月的后几天
        if (startDayOfMonth != 2) {
            // 上个月
            calendar.add(java.util.Calendar.MONTH, -1);

            int lastEndMonthNum = 0;
            // 如果是周日，则上个月还有六个位置
            if (startDayOfMonth == 1) {
                lastEndMonthNum = 6;
            } else {
                lastEndMonthNum = startDayOfMonth - 2;
            }


            int lastEndDayOfMonth = calendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

            // 上月后几天
            for (int j = lastEndMonthNum - 1, i = 0; j >= 0; j--, i++, p++) {
                dayCells[i] = new DayCell();
                dayCells[i].setDay(String.valueOf(lastEndDayOfMonth - j));
            }

            for (int i = 1; i <= endDayOfMonth; i++, p++) {
                dayCells[lastEndMonthNum - 1 + i] = new DayCell();

                dayCells[lastEndMonthNum - 1 + i].setDay(String.valueOf(i));
                dayCells[lastEndMonthNum - 1 + i].setNowMonth(true);
                if (i == today && thisMonth) {
                    dayCells[lastEndMonthNum - 1 + i].setToday(true);
                    dayCells[lastEndMonthNum - 1 + i].setEffect(new DropShadow(10, Color.valueOf("#666")));
                }
            }

        } else {
            // 本月一号是周一，从第一位开始加到月末
            for (int i = 1; i <= endDayOfMonth; i++, p++) {
                dayCells[i - 1] = new DayCell();
                dayCells[i - 1].setDay(String.valueOf(i));
                dayCells[i - 1].setNowMonth(true);

            }
        }

        for (int i = 1; p < 42; p++, i++) {
            dayCells[p] = new DayCell();

            dayCells[p].setDay(String.valueOf(i));
        }
        return Arrays.asList(dayCells);
    }


    private static class DayCell extends Label {
        private StringProperty day = new SimpleStringProperty();
        private BooleanProperty today = new SimpleBooleanProperty(false);
        private BooleanProperty nowMonth = new SimpleBooleanProperty(false);


        public boolean isNowMonth() {
            return nowMonth.get();
        }

        public BooleanProperty nowMonthProperty() {
            return nowMonth;
        }

        public void setNowMonth(boolean nowMonth) {
            this.nowMonth.set(nowMonth);
        }

        public String getDay() {
            return day.get();
        }

        public StringProperty dayProperty() {
            return day;
        }

        public boolean isToday() {
            return today.get();
        }

        public BooleanProperty todayProperty() {
            return today;
        }

        public void setDay(String day) {
            this.day.set(day);
        }

        public void setToday(boolean today) {
            this.today.set(today);
        }

        public DayCell(String day, boolean isToday, boolean isNowMonth) {
            setDay(day);
            setToday(isToday);

            textProperty().bind(this.day);

            this.today.addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    getStyleClass().add("today");
                } else {
                    getStyleClass().remove("today");
                }
            });

            this.nowMonth.addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    getStyleClass().add("nowMonth");
                } else {
                    getStyleClass().remove("nowMonth");
                }
            });
        }

        public DayCell() {
            this(null, false, false);
        }
    }

}
