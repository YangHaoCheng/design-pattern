package yhc.java.random.data;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.DSFactory;
import yhc.java.random.data.bean.Student;
import yhc.java.random.data.enums.CourseEnums;
import yhc.java.random.data.enums.PeopleEnums;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class StudentsExamRecord {
    private static PeopleEnums peopleEnums;
    private static CourseEnums courseEnums;

    public static void main(String[] args) {
        List<Student> students = randomInstance(20);
        DataSource dataSource = DSFactory.get();
        Connection connection;
        String InsertSql = "INSERT INTO flink.studentexamrecord (`studentId`,`studentname`,`birthday`,`grade`,`classid`,`course1`,`course2`,`course3`,`course4`,`score`)  VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(InsertSql);
            for (Student student : students) {
                preparedStatement.setInt(1,student.getId());
                preparedStatement.setString(2,student.getName());
                preparedStatement.setDate(3,student.getBirthDay());
                preparedStatement.setInt(4,student.getGrade());
                preparedStatement.setInt(5,student.getClassId());
                Collection<Integer> scores = student.getScore().values();
                Iterator<Integer> iterator = scores.iterator();
                while (iterator.hasNext()){
                    int i = 0;
                    Integer next = iterator.next();
                    preparedStatement.setInt(6 + i, next);
                    i++;
                }
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> randomInstance(int perClassStudent){
        peopleEnums = new PeopleEnums();
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 1; i < perClassStudent; i++) {
            int classId = 1 + i % 3;
            Student student = new Student(i, peopleEnums.getName(), (java.sql.Date) peopleEnums.getBirthDay(), peopleEnums.getGrade(), classId);
            Map<String, Integer> courseScore = randomCourseScore(classId);
            student.setScore(courseScore);
            students.add(student);
        }
        return students;
    }

    public static Map<String,Integer> randomCourseScore(int randomId){
        courseEnums = new CourseEnums();
        for (int i = 0; i < 5; i++) {
            courseEnums.setPersonalScore(i,courseEnums.getScore((i+1) * randomId));
        }

        return  courseEnums.getCourseScore();
    }

}
