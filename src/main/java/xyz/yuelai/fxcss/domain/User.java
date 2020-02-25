package xyz.yuelai.fxcss.domain;

import com.jfoenix.controls.JFXButton;

/**
 * @author zhong
 * @date 2020/2/17 22:25
 */
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String sex;
    private String hobbit;
    private String nation;
    private String education;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", hobbit='" + hobbit + '\'' +
                ", nation='" + nation + '\'' +
                ", education='" + education + '\'' +
                '}';
    }

    private JFXButton deleteBtn = new JFXButton("删除");

    public JFXButton getDeleteBtn() {
        return deleteBtn;
    }

    public void setDeleteBtn(JFXButton deleteBtn) {
        this.deleteBtn = deleteBtn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHobbit() {
        return hobbit;
    }

    public void setHobbit(String hobbit) {
        this.hobbit = hobbit;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}
