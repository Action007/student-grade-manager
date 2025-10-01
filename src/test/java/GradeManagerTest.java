import com.studentGrade.exceptions.StudentNotFoundException;
import com.studentGrade.model.Student;
import com.studentGrade.service.GradeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class GradeManagerTest {

  private GradeManager manager;

  @BeforeEach
  void setUp() {
    manager = new GradeManager();
  }

  @Test
  void addStudent_addsStudentSuccessfully() {
    Student student = new Student("S1", "Alice");
    manager.addStudent(student);

    assertEquals(student, manager.getStudent("S1"));
  }

  @Test
  void addStudent_duplicateId_throwsException() {
    Student s1 = new Student("S1", "Alice");
    Student s2 = new Student("S1", "Bob");

    manager.addStudent(s1);
    assertThrows(IllegalArgumentException.class, () -> {
      manager.addStudent(s2);
    });
  }

  @Test
  void getStudentAverage_returnsCorrectAverage() {
    Student student = new Student("S1", "Alice");
    manager.addStudent(student);
    manager.addGrade("S1", "Math", 90.0);
    manager.addGrade("S1", "Physics", 80.0);

    double average = manager.getStudentAverage("S1");
    assertEquals(85.0, average, 0.001);
  }

  @Test
  void getTopStudents_returnsCorrectOrder() {
    manager.addStudent(new Student("S1", "Alice"));
    manager.addGrade("S1", "Course", 90.0);

    manager.addStudent(new Student("S2", "Bob"));
    manager.addGrade("S2", "Course", 80.0);

    manager.addStudent(new Student("S3", "Charlie"));
    manager.addGrade("S3", "Course", 85.0);

    var topStudents = manager.getTopStudents(2);

    assertEquals(2, topStudents.size());
    assertEquals("S1", topStudents.get(0).getStudentId());
    assertEquals("S3", topStudents.get(1).getStudentId());
  }

  @Test
  void getStudent_nonExistent_throwsStudentNotFoundException() {
    assertThrows(StudentNotFoundException.class, () -> {
      manager.getStudent("S1");
    });
  }

  @Test
  void getClassAverage_correctCalculation() {
    manager.addStudent(new Student("S1", "Alice"));
    manager.addGrade("S1", "Spring boot", 90.0);

    manager.addStudent(new Student("S2", "Bob"));
    manager.addGrade("S2", "Java Core", 80.0);

    manager.addStudent(new Student("S3", "Charlie"));
    manager.addGrade("S3", "SQL", 85.0);

    assertEquals(85.0, manager.getClassAverage());
  }

  @Test
  void getFailingStudents_onlyFailingReturned() {
    manager.addStudent(new Student("S1", "Alice"));
    manager.addGrade("S1", "Course", 90.0);

    manager.addStudent(new Student("S2", "Bob"));
    manager.addGrade("S2", "Course", 55.0);

    manager.addStudent(new Student("S3", "Charlie"));
    manager.addGrade("S3", "Course", 59.0);

    manager.addStudent(new Student("S4", "Diana"));
    manager.addGrade("S4", "Course", 40.0);

    List<Student> failing = manager.getFailingStudents();

    assertEquals(3, failing.size());

    var failingIds = failing.stream().map(Student::getStudentId).toList();

    assertTrue(failingIds.contains("S2"));
    assertTrue(failingIds.contains("S3"));
    assertTrue(failingIds.contains("S4"));
    assertFalse(failingIds.contains("S1"));
  }

  @Test
  void getStudentsInCourse_filtersCorrectly() {
    manager.addStudent(new Student("S1", "Alice"));
    manager.addGrade("S1", "Spring Boot", 90.0);

    manager.addStudent(new Student("S2", "Bob"));
    manager.addGrade("S2", "Java Core", 80.0);

    manager.addStudent(new Student("S3", "Charlie"));
    manager.addGrade("S3", "Spring Boot", 85.0);

    List<Student> springBootStudents = manager.getStudentsInCourse("Spring Boot");

    assertEquals(2, springBootStudents.size());

    var ids = springBootStudents.stream().map(Student::getStudentId).toList();

    assertTrue(ids.contains("S1"));
    assertTrue(ids.contains("S3"));
    assertFalse(ids.contains("S2"));
  }

  @Test
  void countPassingStudents_correctCount() {
    manager.addStudent(new Student("S1", "Alice"));
    manager.addGrade("S1", "Course", 90.0);

    manager.addStudent(new Student("S2", "Bob"));
    manager.addGrade("S2", "Course", 80.0);

    manager.addStudent(new Student("S3", "Charlie"));
    manager.addGrade("S3", "Course", 55.0);

    manager.addStudent(new Student("S4", "Diana"));

    long passingCount = manager.countPassingStudents();

    assertEquals(2, passingCount);
  }
}
