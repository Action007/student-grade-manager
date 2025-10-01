import org.junit.jupiter.api.Test;
import com.studentGrade.model.Grade;
import com.studentGrade.model.Student;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class StudentTest {

  private Student student;

  @BeforeEach
  void setUp() {
    student = new Student("S1", "Alice");
  }

  @Test
  void addGrade_addsGradeToList() {
    student.addGrade(new Grade("Math", 85.0));
    assertEquals(1, student.getGrades().size());
    assertEquals("Math", student.getGrades().get(0).getCourseName());
  }

  @Test
  void getGrades_returnsUnmodifiableList() {
    student.addGrade(new Grade("Math", 90.0));
    List<Grade> grades = student.getGrades();

    assertThrows(UnsupportedOperationException.class, () -> {
      grades.add(new Grade("Physics", 80.0));
    });
  }

  @Test
  void equals_returnsTrue_forSameStudentId() {
    Student student1 = new Student("S1", "Alice");
    Student student2 = new Student("S1", "Bob");

    assertTrue(student1.equals(student2));
    assertTrue(student2.equals(student1));
  }

  @Test
  void equals_returnsFalse_forDifferentStudentId() {
    Student student1 = new Student("S1", "Alice");
    Student student2 = new Student("S2", "Alice");

    assertFalse(student1.equals(student2));
    assertFalse(student2.equals(student1));
  }

  @Test
  void hashCode_sameId_returnsSameHashCode() {
    Student s1 = new Student("S1", "Alice");
    Student s2 = new Student("S1", "Bob");

    assertEquals(s1.hashCode(), s2.hashCode());
  }

  @Test
  void testToString_containsNameAndId() {
    Student newStudent = new Student("S1", "Alice");
    String str = newStudent.toString();
    assertTrue(str.contains("S1"));
    assertTrue(str.contains("Alice"));
    assertTrue(str.contains("grades="));
  }
}
