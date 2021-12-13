package yhc.java.random.data.enums;

import java.util.Map;
import java.util.Random;

/**
 * @author ：_my
 * @date ：Created in 2021/12/10 9:41
 * @description：课程list
 * @version: 1.0
 */
public class CourseEnums {
    private String[] courses = {"Chinese", "English", "Math", "Physics", "Chemistry"};
    private int score;
    private Map<String,Integer> courseScore;

    public String getCourse(int index) {
        return courses[index];
    }

    public int getPersonalScore(String course) {
        return courseScore.get(course);
    }

    public void setPersonalScore(String course,int score) {
        courseScore.put(course,score);
    }

    public void setPersonalScore(int index,int score) {
        courseScore.put(courses[index],score);
    }

    public int getScore(int index) {
        return 70 + new Random().nextInt(index) + new Random().nextInt(index);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Map<String, Integer> getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(Map<String, Integer> courseScore) {
        this.courseScore = courseScore;
    }
}
