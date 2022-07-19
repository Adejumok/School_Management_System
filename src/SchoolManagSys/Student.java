package SchoolManagSys;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String id;
    private ArrayList <Course> offeredCourses= new ArrayList<>();

    public Student(String studentName) {
        name = studentName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString(){
       return String.format("""
               Name : %s
               Id : %s\s""",name,id);
    }

    public Course selectCourse(Course course, School school) {
        List<Course> availableCourses = school.getAllCourses();
        for(Course checkedCourse : availableCourses){
            if(course.equals(checkedCourse)){
                offeredCourses.add(course);
                return course;
            }
        }
       throw new CourseNotFoundException("Course not found!");
    }

    public List <Course> viewCourses() {
        return offeredCourses;
    }

    public Course dropCourse(Course course) {
        Course tempCourse;
        for (Course foundCourse:offeredCourses){
            if (course.equals(foundCourse)){
                tempCourse = course;
                offeredCourses.remove(course);
                return tempCourse;
            }
        }throw new CourseNotFoundException("Course not found!");
    }

    public void withdraw(School school){
        List<Student> students = school.getAllStudents();
        for (Student student : students) {
            if (name.equalsIgnoreCase(student.getName()) && id.equalsIgnoreCase(student.getId())) {
                school.deleteStudent(student);
            }
        }
    }
}
