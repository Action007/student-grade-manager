package com.studentGrade.service;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.studentGrade.exceptions.StudentNotFoundException;
import com.studentGrade.model.Grade;
import com.studentGrade.model.Student;

public class GradeManager {
  private final Map<String, Student> students = new HashMap<>();
  private static final double PASSING_THRESHOLD = 60.0;

  public void addStudent(Student student) {
    if (students.containsKey(student.getStudentId())) {
      throw new IllegalArgumentException("Student with this id already exist");
    }

    students.put(student.getStudentId(), student);
  }

  public void addGrade(String studentId, String course, double score) {
    Student student = students.get(studentId);

    if (student == null) {
      throw new StudentNotFoundException("Student not found");
    }

    Grade newGrade = new Grade(course, score);
    student.addGrade(newGrade);
  }

  public Student getStudent(String id) {
    Student student = students.get(id);
    if (student == null) {
      throw new StudentNotFoundException("Student not found");
    }
    return student;
  }

  public Collection<Student> getAllStudents() {
    return students.values();
  }

  public double getStudentAverage(String studentId) {
    Student student = getStudent(studentId);

    return student.getGrades().stream().mapToDouble(Grade::getScore).average().orElse(0.0);
  }

  private double calculateAverage(Student student) {
    return student.getGrades().stream().mapToDouble(Grade::getScore).average().orElse(0.0);
  }

  public List<Student> getTopStudents(int n) {
    return students.values().stream().filter((student) -> !student.getGrades().isEmpty())
        .sorted(Comparator.comparingDouble(this::calculateAverage).reversed()).limit(n).toList();
  }

  public List<Student> getFailingStudents() {
    return students.values().stream().filter((student) -> !student.getGrades().isEmpty())
        .filter((student) -> calculateAverage(student) < 60.0).toList();
  }

  public double getClassAverage() {
    return students.values().stream().filter((student) -> !student.getGrades().isEmpty())
        .mapToDouble(this::calculateAverage).average().orElse(0.0);
  }

  public List<Student> getStudentsInCourse(String course) {
    return students.values().stream().filter((student) -> student.getGrades().stream()
        .anyMatch((grade) -> grade.getCourseName().equals(course))).toList();
  }

  public long countPassingStudents() {
    return students.values().stream()
        .filter((student) -> calculateAverage(student) >= PASSING_THRESHOLD).count();
  }
}
