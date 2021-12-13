package yhc.java.random.data.bean;

import java.sql.Date;
import java.util.Map;

public class Student {
    private int id;
    private String name;
    private Date BirthDay;
    private int grade;
    private int classId;
    private Map<String,Integer> score;

    private Student() {

    }

    public Student(int id, String name, Date birthDay, int grade, int classId) {
        this.id = id;
        this.name = name;
        BirthDay = birthDay;
        this.grade = grade;
        this.classId = classId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.sql.Date getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(Date birthDay) {
        BirthDay = birthDay;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public Map<String,Integer> getScore() {
        return score;
    }

    public void setScore(Map<String,Integer> score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "{ \"Student\" = {" +
                "\"id\"=\"" + id +
                "\", \"name\"=\"" + name +
                "\", \"BirthDay\"=\"" + BirthDay +
                "\", \"grade\"=\"" + grade +
                "\", \"classId\"=\"" + classId +
                "\", \"score\"=\"" + score +
                "\"}}";
    }
}
