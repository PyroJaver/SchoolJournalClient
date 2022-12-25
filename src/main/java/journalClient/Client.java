package journalClient;

import journalClient.dto.MarkDTO;
import journalClient.dto.MarksResponse;
import journalClient.dto.StudentDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Client {
    public static void main(String[] args) {
        final String[] studentNameArray = {"Mikhail", "Victor", "Alexander"};
        final String[] studentSurnameArray = {"Plesovskikh", "Ivanov", "Kuznetsov"};
        final String[] subjects = {"chemistry", "math", "English"};
        StudentDTO studentDTO1 = new StudentDTO(studentNameArray[0], studentSurnameArray[0]);
        StudentDTO studentDTO2 = new StudentDTO(studentNameArray[1], studentSurnameArray[1]);
        StudentDTO studentDTO3 = new StudentDTO(studentNameArray[2], studentSurnameArray[2]);
        StudentDTO[] studentDTOArray = {studentDTO1, studentDTO2, studentDTO3};

     //   registerStudents(studentNameArray, studentSurnameArray);
     //   addMarks(subjects, studentDTOArray);
     //   getAllMarksOfStudent(studentDTO1);
        getSubjectMarksOfStudent("chemistry", studentDTO3);
    }

    public static void registerStudents(String[] name, String[] surname){
        final String url = "http://localhost:8080/students/registerStudent";
        Map<String, Object> jsonData = new HashMap<>();
        for(int i = 0; i < 3; i++) {
            jsonData.put("name", name[i]);
            jsonData.put("surname", surname[i]);
            makePostRequestWithJSON(url, jsonData);
        }
    }

    public static void addMarks(String[] subjects, StudentDTO[] studentDTOArray) {
        final String url = "http://localhost:8080/marks/add";
        Map<String, Object> jsonData = new HashMap<>();
        for (int i = 0; i < 100; i++) {
        jsonData.put("subject", subjects[(int) (Math.random() * 3)]);
        jsonData.put("mark", (int) (Math.random() * 4) + 2);
        jsonData.put("student", studentDTOArray[(int) (Math.random() * 3)]);
        makePostRequestWithJSON(url, jsonData);
        }
    }
    public static void getAllMarksOfStudent(StudentDTO studentDTO){
    final RestTemplate restTemplate = new RestTemplate();
    final String url = "http://localhost:8080/marks/{student}";
    final Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("student", studentDTO.getSurname());
        MarksResponse jsonResponse = restTemplate.getForObject(url, MarksResponse.class, uriVariables);
        if (jsonResponse == null || jsonResponse.getMarks() == null){
            System.out.println("Error");
        }
        for(MarkDTO mark:jsonResponse.getMarks()){
            System.out.println(mark.toString());
        }
    }

    public static void getSubjectMarksOfStudent(String subject, StudentDTO studentDTO){
        final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8080/marks/{student}/{subject}";
        final Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("student", studentDTO.getSurname());
        uriVariables.put("subject", subject);
        MarksResponse jsonResponse = restTemplate.getForObject(url, MarksResponse.class, uriVariables);
        if (jsonResponse == null || jsonResponse.getMarks() == null){
            System.out.println("Error");
        }
        for(MarkDTO mark:jsonResponse.getMarks()){
            System.out.println(mark.toString());
        }
    }

    public static void makePostRequestWithJSON(String url, Map<String, Object> jsonData){
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(jsonData, headers);

        try {
            restTemplate.postForObject(url, request, String.class);

        } catch (HttpClientErrorException e) {
            System.out.println("ОШИБКА!");
            System.out.println(e.getMessage());
        }
    }
}
