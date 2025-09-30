package com.studentGrade.model;

public class Grade {
  private final String courseName;
  private final double score;

  public Grade(String course, double score) {
    if (course == null || course.trim().isEmpty()) {
      throw new IllegalArgumentException("Course name cannot be null or empty");
    }
    if (score < 0.0 || score > 100.0) {
      throw new IllegalArgumentException("Score must be between 0.0 and 100.0");
    }

    this.courseName = course;
    this.score = score;
  }

  public String getCourseName() {
    return courseName;
  }

  public double getScore() {
    return score;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    Grade grade = (Grade) obj;
    return Double.compare(grade.score, score) == 0 && courseName.equals(grade.courseName);
  }

  @Override
  public int hashCode() {
    int result = courseName.hashCode();
    long temp = Double.doubleToLongBits(score);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return courseName + ": " + score;
  }
}
