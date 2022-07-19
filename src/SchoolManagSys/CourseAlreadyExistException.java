package SchoolManagSys;

public class CourseAlreadyExistException extends RuntimeException{
    public CourseAlreadyExistException(String message){
        super(message);
    }
}
