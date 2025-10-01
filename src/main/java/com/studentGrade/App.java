package com.studentGrade;

import java.util.List;
import com.studentGrade.model.Student;
import com.studentGrade.service.GradeManager;

public class App {
  public static void main(String[] args) {
    GradeManager gradeManager = new GradeManager();

    Student student1 = new Student("1", "Action");
    Student student2 = new Student("2", "Poly");
    Student student3 = new Student("3", "Tarana");
    Student student4 = new Student("4", "Shafi");
    Student student5 = new Student("5", "Ilya");

    gradeManager.addStudent(student1);
    gradeManager.addStudent(student2);
    gradeManager.addStudent(student3);
    gradeManager.addStudent(student4);
    gradeManager.addStudent(student5);

    gradeManager.addGrade("1", "SQL", 10);
    gradeManager.addGrade("2", "SQL", 20);
    gradeManager.addGrade("3", "Math", 85);
    gradeManager.addGrade("3", "Physics", 90);
    gradeManager.addGrade("4", "Java Core", 75);
    gradeManager.addGrade("5", "Spring Boot", 95);

    System.out.println("=== All Students ===");
    for (Student student : gradeManager.getAllStudents()) {
      double average = gradeManager.getStudentAverage(student.getStudentId());
      System.out.printf("ID: %s | Name: %s | Grades: %s | Average: %.2f%n", student.getStudentId(),
          student.getName(), student.getGrades(), average);
    }

    System.out.println("\n=== Top 3 Students ===");
    List<Student> topStudents = gradeManager.getTopStudents(3);
    for (int i = 0; i < topStudents.size(); i++) {
      Student student = topStudents.get(i);
      double average = gradeManager.getStudentAverage(student.getStudentId());
      System.out.printf("%d. %s (%s) - Average: %.2f%n", i + 1, student.getName(),
          student.getStudentId(), average);
    }

    System.out.println("\n=== Failing Students ===");
    List<Student> failingStudents = gradeManager.getFailingStudents();
    if (failingStudents.isEmpty()) {
      System.out.println("No failing students");
    } else {
      for (Student student : failingStudents) {
        double average = gradeManager.getStudentAverage(student.getStudentId());
        System.out.printf("%s (%s) - Average: %.2f%n", student.getName(), student.getStudentId(),
            average);
      }
    }

    System.out.println("\n=== Class Average ===");
    System.out.printf("%.2f%n", gradeManager.getClassAverage());

    System.out.println("\n=== Students in SQL Course ===");
    List<Student> sqlStudents = gradeManager.getStudentsInCourse("SQL");
    for (Student student : sqlStudents) {
      System.out.println(student.getName());
    }

    System.out.println("\n=== Passing Students Count ===");
    System.out.println(gradeManager.countPassingStudents());
  }
}
