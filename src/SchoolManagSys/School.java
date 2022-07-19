package SchoolManagSys;

import java.util.ArrayList;
import java.util.List;

public class School {
    private String name;
    private ArrayList<Student>students = new ArrayList<>();
    private ArrayList<Course>courses = new ArrayList<>();
    private int id;
    private int studentCount = 0;
    private int courseCount = 0;

    public School(String name, int id){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Student admitStudent(Student student) {
        if(isStudentRegistered(student)){
            throw new StudentAlreadyExistException("student already exist!");}
        else{
            studentCount++;
            String id = generatedId(name) + studentCount;
        student.setId(id);
        students.add(student);
        return student;}
    }


    public boolean isStudentRegistered(Student student){
        for(Student registeredStudent : students){
            if(student.equals(registeredStudent)){
                return true;
            }
        }
        return false;
    }

    public boolean isCourseRegistered(Course course){
        for(Course registeredCourse : courses){
            if(course.equals(registeredCourse)){
                return true;
            }
        }
        return false;
    }

    public int studentSize() {
        return students.size();
    }

    public Student deleteStudent(Student student) {
        if(isStudentRegistered(student)){
            Student tempStudent;
                tempStudent = student;
                students.remove(student);
                return tempStudent;
            }
        throw new StudentNotFoundException("ops! student not found!");
        }

    public List<Student> getAllStudents() {
        return students;
    }


    private String generatedId(String name){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            stringBuilder.append(name.charAt(i));
        }
        return stringBuilder.toString();
    }

    public void addCourse(Course course) {
        if(isCourseRegistered(course)){
            throw new CourseAlreadyExistException("Course already exists!");
        }
        else{
            courseCount++;
            course.setId(generatedId(course.getName()) + (courseCount));
            courses.add(course);
        }
    }

    public int getNumberOfCourses() {
        return courses.size();
    }

    public String findCourseById(String id) {
        for(Course course: courses){
            if(id.equalsIgnoreCase(course.getId())){
                return course.getName();
            }
        }
        throw new CourseNotFoundException("course not found!");
    }

    public Course deleteCourse(Course course) {
        Course tempCourse;
        for (Course foundCourse : courses){
            if (course.equals(foundCourse)){
                tempCourse = course;
                courses.remove(course);
                return tempCourse;
            }
        }throw new CourseNotFoundException("Course not found!");
    }

    public List<Course> getAllCourses() {
        return courses;
    }
}
