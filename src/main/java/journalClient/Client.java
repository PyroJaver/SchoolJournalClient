package journalClient;

import journalClient.dto.MarkDTO;
import journalClient.dto.MarksResponse;
import journalClient.dto.StudentDTO;
import journalClient.testRequests.GetTestRequests;
import journalClient.testRequests.PostTestRequests;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Client {
    public static void main(String[] args) {
        final String[] studentNameArray = {"Mikhail", "Victor", "Alexander"};
        final String[] studentSurnameArray = {"Plesovskikh", "Ivanov", "Kuznetsov"};
        final String[] subjects = {"chemistry", "math", "English"};
        StudentDTO studentDTO1 = new StudentDTO(studentNameArray[0], studentSurnameArray[0], String.valueOf(0));
        StudentDTO studentDTO2 = new StudentDTO(studentNameArray[1], studentSurnameArray[1], String.valueOf(1));
        StudentDTO studentDTO3 = new StudentDTO(studentNameArray[2], studentSurnameArray[2], String.valueOf(2));
        StudentDTO[] studentDTOArray = {studentDTO1, studentDTO2, studentDTO3};

        GetTestRequests getTestRequests = new GetTestRequests();
        PostTestRequests postTestRequests = new PostTestRequests();
     //   postTestRequests.registerStudents(studentNameArray, studentSurnameArray);
     //   postTestRequests.addMarks(subjects, studentDTOArray);
        getTestRequests.getAllMarksOfStudent(studentDTO1);
        getTestRequests.getSubjectMarksOfStudent("chemistry", studentDTO1);

    }

}
