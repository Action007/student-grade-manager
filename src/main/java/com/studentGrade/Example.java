package com.studentGrade;

import java.util.List;
import com.studentGrade.model.Student;
import com.studentGrade.service.GradeManager;

public class Example {
  public static void main(String[] args) {
    Student newStudent = new Student("1", "Action");
    Student newStudent2 = new Student("2", "Poly");
    Student newStudent3 = new Student("3", "Tarana");
    Student newStudent4 = new Student("4", "Shafi");
    Student newStudent5 = new Student("5", "Ilya");
    GradeManager gradeManager = new GradeManager();
    gradeManager.addStudent(newStudent);
    gradeManager.addGrade(newStudent.getStudentId(), "SQL", 10);

    gradeManager.addStudent(newStudent2);
    gradeManager.addGrade(newStudent2.getStudentId(), "SQL", 20);

    gradeManager.addStudent(newStudent3);
    gradeManager.addGrade(newStudent3.getStudentId(), "SQL", 30);

    gradeManager.addStudent(newStudent4);
    gradeManager.addGrade(newStudent4.getStudentId(), "SQL", 40);

    gradeManager.addStudent(newStudent5);
    gradeManager.addGrade(newStudent5.getStudentId(), "SQL", 50);

    gradeManager.getStudentAverage(newStudent.getStudentId());
    List<Student> test = gradeManager.getTopStudents(5);
    Double test2 = gradeManager.getClassAverage();

    System.err.println(test2);
  }
}
