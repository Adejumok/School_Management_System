package SchoolManagSys;

public class StudentAlreadyExistException extends RuntimeException {
    StudentAlreadyExistException(String message){
        super(message);
    }
}
