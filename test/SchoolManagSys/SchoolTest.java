package SchoolManagSys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SchoolTest {

    private School school;
    private Student Gbenga;
    private Student Shola;
    private Student Funmi;
    private Course Biology;
    private Course Yoruba;
    private Course French;
    private Course Latin;

    @BeforeEach
    void setUp(){
     school = new School("Union Baptist", 1);
     Gbenga = new Student("Gbenga");
     Shola = new Student("Shola");
     Funmi = new Student("Funmi");
     Biology = new Course("Biology");
     Yoruba = new Course("Yoruba");
     French = new Course("French");
     Latin = new Course("Latin");
    }
    @Test
    void testThatSchoolExist (){
        assertNotNull(school);
    }

    @Test
    void testThatSchoolDetailsCanBeAdded(){
        assertEquals("Union Baptist",school.getName());
        assertEquals(1,school.getId());
    }

    @Test
    void testThatSchoolCanAdmitStudent(){
        school.admitStudent(Gbenga);
        assertEquals(1,school.studentSize());
    }

    @Test
    void testThatSchoolCanAdmitMultipleStudents(){
        school.admitStudent(Gbenga);
        assertEquals("Uni1",Gbenga.getId());

        school.admitStudent(Shola);
        assertEquals("Uni2",Shola.getId());

        school.admitStudent(Funmi);
        assertEquals("Uni3",Funmi.getId());

        assertEquals(3,school.studentSize());


    }
    @Test
    void testThatSchoolCanDeleteStudentRecord(){
        school.admitStudent(Gbenga);
        school.admitStudent(Shola);
        school.admitStudent(Funmi);
        school.deleteStudent(Shola);

        Student Bisi = new Student("Bisi");
        assertThrows(StudentNotFoundException.class,()->school.deleteStudent(Bisi));
        assertEquals(2,school.studentSize());
    }
    @Test
    void testThatSchoolCanAllStudentCanBeGotten(){
        school.admitStudent(Gbenga);
        school.admitStudent(Shola);
        school.admitStudent(Funmi);

        List<Student> students = school.getAllStudents();
        assertEquals("""
                [Name : Gbenga
                Id : Uni1 , Name : Shola
                Id : Uni2 , Name : Funmi
                Id : Uni3 ]""",students.toString());
    }
    @Test
    void testThatCourseCanBeAdded(){
        school.addCourse(Biology);
        assertEquals(1,school.getNumberOfCourses());
    }

    @Test
    void testThatCourseCanBeFound(){
        school.addCourse(Biology);
        school.addCourse(Yoruba);
        school.addCourse(French);

        assertEquals("French",school.findCourseById("Fre3"));

    }

    @Test
    void testThatCourseCanBeDeleted(){
        school.addCourse(Biology);
        school.addCourse(Yoruba);
        school.addCourse(French);
        school.deleteCourse(Biology);
        assertEquals(2,school.getNumberOfCourses());
    }

    @Test
    void testThatAllCoursesCanBeGotten(){
        school.addCourse(Biology);
        school.addCourse(Yoruba);
        school.addCourse(French);
        List<Course>courses = school.getAllCourses();
        assertEquals("""
                [Name: Biology
                Id: Bio1
                , Name: Yoruba
                Id: Yor2
                , Name: French
                Id: Fre3
                ]""", courses.toString());
    }

    @Test
    void testThatCourseCanBeActivated(){
        Yoruba.activateCourse();
        assertTrue(Yoruba.isActivateCourse());
    }

    @Test
    void testThatCourseCanBeDeactivated(){
        French.activateCourse();
        assertTrue(French.isActivateCourse());

        French.deactivate();
        assertFalse(French.isActivateCourse());
    }

    @Test
    void testThatWeCanSetStudentOfferingACourse(){
        school.admitStudent(Gbenga);
        school.admitStudent(Shola);
        school.admitStudent(Funmi);
        Yoruba.setStudentOffering(Funmi,school);
        Yoruba.setStudentOffering(Gbenga, school);
        List<Student>students = Yoruba.getAllStudentsOffering();
        assertEquals("""
                [Name : Funmi
                Id : Uni3 , Name : Gbenga
                Id : Uni1 ]""", students.toString());
    }

    @Test
    void testThatStudentCanSelectCourse(){
        school.addCourse(Yoruba);
        school.addCourse(Biology);
        school.addCourse(French);
        Funmi.selectCourse(Yoruba,school);
        Funmi.selectCourse(Biology,school);
        Funmi.selectCourse(French,school);
        List<Course>courses = Funmi.viewCourses();
        assertEquals("""
                [Name: Yoruba
                Id: Yor1
                , Name: Biology
                Id: Bio2
                , Name: French
                Id: Fre3
                ]""", courses.toString());
    }

    @Test
    void testThatStudentCanDropCourse(){
        school.addCourse(Yoruba);
        school.addCourse(Biology);
        school.addCourse(French);

        Funmi.selectCourse(Yoruba,school);
        Funmi.selectCourse(Biology,school);
        Funmi.selectCourse(French,school);
        Funmi.dropCourse(French);
        List<Course>courses = Funmi.viewCourses();
        assertEquals("""
                [Name: Yoruba
                Id: Yor1
                , Name: Biology
                Id: Bio2
                ]""", courses.toString());
    }
}
