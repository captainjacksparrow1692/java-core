package lesson05;

import java.util.ArrayList;
import java.util.List;

abstract class Person {
    private String name;
    private int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public Person(){
        //super calling in class student
    }
    public String getName() {

        return name;
    }

    public int getId() {

        return id;
    }

}
//Student
class Student extends Person{
    private double score;
    private List<Boolean> attendance = new ArrayList<>();

    public Student(String name, int id) {
        super(name, id);
    }

    public void setScore(double score){
        this.score = score;
    }

    //Attendance
    public void markAttendance(boolean present){
        attendance.add(present);
    }
    public double attendancepercentage(){
        if(attendance.isEmpty()){
            return 0;
        }
        else{
            long present = attendance.stream().filter(b -> b).count();
            return (present * 100.0) / attendance.size();
        }
    }

    public String getInfo(){
        return "Student Info: "+ "Name: " + getName() + "ID: " + getId() + " Score: " + score;
    }
}
//Teacher
class Teacher extends Person{
    private String subject;

    public Teacher(String name, int id) {
        super(name, id);
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getInfo(){
        return "Teacher Info: "+ "Name: " + getName() + "ID: " + getId() + "Subject: " + subject;
    }
}
//Cource
class course extends Person{
    private String subject;
    private Teacher teacher;
    private List<Student> students = new ArrayList<>();

    public course(String subject, Teacher teacher) {
        this.subject = subject;
        this.teacher = teacher;
    }

    public void addStudent(Student student){
        students.add(student);
        System.out.println("Student added: " + student.getName() + "Cource: " + subject);
    }

    public void showStudents() {
        System.out.println("Course: " + subject + " (Teacher: " + teacher.getName() + ")");
        for (Student s : students) {
            System.out.println(
                    s.getInfo() + " | Attendance: " + s.attendancepercentage() + "%");
        }
    }
    public Teacher getTeacher(){
        return teacher;
    }
}

public class Main{
    public static void main(String[] args) {
        Teacher teacher = new Teacher("Captain Jack Sparrow", 131);
        course math = new course("Math", teacher);

        Student student = new Student("John Smith", 132);
        Student student2 = new Student("Will Turner", 133);

        math.addStudent(student);
        math.addStudent(student2);


        student.markAttendance(true);
        student.markAttendance(false);
        student.setScore(96);

        student2.markAttendance(true);
        student2.markAttendance(false);
        student2.setScore(90);

        System.out.println("Student Info: " + student.getInfo());
        System.out.println("Student Info: " + student2.getInfo());
        math.showStudents();
    }
}
