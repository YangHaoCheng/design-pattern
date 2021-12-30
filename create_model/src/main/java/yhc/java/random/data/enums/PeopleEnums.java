package yhc.java.random.data.enums;

import cn.hutool.Hutool;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author ：_my
 * @date ：Created in 2021/12/9 16:05
 * @description：Random Name Enums
 * @version: 1.0
 */
public class PeopleEnums {
    private String name;
    private int age;
    private Date birthDay = DateUtil.date(System.currentTimeMillis());
    private int gender; // 0 female 1 male
    private int grade; // 1 2 3 4 5 6

    private static String[] MaleNameEnum = {
            "Aiden"
            ,"Jacob"
            ,"Ethan"
            ,"Matthew"
            ,"Nicholas"
            ,"Jack"
            ,"Joshua"
            ,"Michael"
            ,"Ryan"
            ,"Andrew"
            ,"Caden"
            ,"Tyler"
            ,"Dylan"
            ,"Jaden"
            ,"Zachary"
            ,"Connor"
            ,"Logan"
            ,"Caleb"
            ,"Noah"
            ,"Alexande"
            ,"Jackson"
            ,"Brayden"
            ,"Lucas"
            ,"William"
            ,"Nathan"
            ,"Joseph"
            ,"Justin"
            ,"Daniel"
            ,"Benjamin"
            ,"Owen"
            ,"Messi"
            ,"Would"
    };

    private static String[] FemaleNameEnum = {"Emily"
            ,"Hannah"
            ,"Madison"
            ,"Ashley"
            ,"Sarah"
            ,"Alexis"
            ,"Samantha"
            ,"Jessica"
            ,"Elizabeth"
            ,"Taylor"
            ,"Lauren"
            ,"Alyssa"
            ,"Kayla"
            ,"Abigail"
            ,"Brianna"
            ,"Olivia"
            ,"Emma"
            ,"Megan"
            ,"Grace"
            ,"Victoria"
            ,"Rachel"
            ,"Nicole"
            ,"Sydney"
            ,"Destiny"
            ,"Morgan"
            ,"Jennifer"
            ,"Jasmine"
            ,"Haley"
            ,"Julia"
            ,"Kaitlyn"
            ,"Jena"
            ,"Angle"
    };

    public String getName() {
        int random = new Random().nextInt(30);
        String randomName = this.gender == 1 ? MaleNameEnum[random] : FemaleNameEnum[random];
        setName(randomName);
        return randomName;
    }

    public int getAge(){
        int randomAge = 12 + new Random().nextInt(7);
        setAge(randomAge);
        return randomAge;
    }

    public int getGrade() {
        int randomGrade = this.age - 11;
        setGrade(randomGrade);
        return randomGrade;
    }

    public Date getBirthDay() {
        Date dateTime = DateUtil.offset(this.birthDay, DateField.YEAR, -getAge());
        java.sql.Date sqlDate = new java.sql.Date(dateTime.getTime());
        return sqlDate;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}
