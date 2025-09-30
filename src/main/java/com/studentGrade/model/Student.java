package com.studentGrade.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student {
  private final String studentId;
  private final String name;
  private List<Grade> grades;

  public Student(String id, String name) {
    this.studentId = id;
    this.name = name;
    this.grades = new ArrayList<>();
  }

  public void addGrade(Grade grade) {
    grades.add(grade);
  }

  public String getStudentId() {
    return studentId;
  }

  public String getName() {
    return name;
  }

  public List<Grade> getGrades() {
    return Collections.unmodifiableList(grades);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    Student other = (Student) obj;
    return studentId != null && studentId.equals(other.studentId);
  }

  @Override
  public int hashCode() {
    return studentId != null ? studentId.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Student{id='" + studentId + "', name='" + name + "', grades=" + grades + "}";
  }
}
