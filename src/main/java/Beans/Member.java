package Beans;

import java.util.Date;

public class Member {
    private int id;
    private String sno;
    private String sex;
    private int age;
    private String name;
    private String major;
    private String sdept;
    private String className;
    private int joinDate;
    private String assistant;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Member() {
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", sno='" + sno + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", major='" + major + '\'' +
                ", sdept='" + sdept + '\'' +
                ", className='" + className + '\'' +
                ", joinDate=" + joinDate +
                ", assistant='" + assistant + '\'' +
                '}';
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSdept() {
        return sdept;
    }

    public void setSdept(String sdept) {
        this.sdept = sdept;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(int joinDate) {
        this.joinDate = joinDate;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }
}
