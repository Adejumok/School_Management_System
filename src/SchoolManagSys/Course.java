package SchoolManagSys;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private final String name;
    private String id;
    private boolean isCourseActivated;
    private ArrayList <Student> students= new ArrayList<>();

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return String.format("""
                Name: %s
                Id: %s
                """, name, id);
    }

    public void activateCourse() {
        isCourseActivated = true;
    }

    public boolean isActivateCourse() {
        return isCourseActivated;
    }

    public void deactivate() {
        isCourseActivated = false;
    }

    public void setStudentOffering(Student student, School school) {
        if(school.isStudentRegistered(student)){
        students.add(student);}
    }

    public List<Student> getAllStudentsOffering() {
        return students;
    }
}
