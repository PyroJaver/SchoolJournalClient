package journalClient.testRequests;

import journalClient.dto.MarkDTO;
import journalClient.dto.MarksResponse;
import journalClient.dto.StudentDTO;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class GetTestRequests {
    public void getSubjectMarksOfStudent(String subject, StudentDTO studentDTO){
        final String url = "http://localhost:8080/marks/{student}/{subject}";
        final Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("student", studentDTO.getDiaryNumber());
        uriVariables.put("subject", subject);
        makeGetRequestForMarks(url, uriVariables);
    }

    public void getAllMarksOfStudent(StudentDTO studentDTO){
        final String url = "http://localhost:8080/marks/{student}";
        final Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("student", studentDTO.getDiaryNumber());
        makeGetRequestForMarks(url, uriVariables);
    }
    private void printMarks(MarksResponse jsonResponse){
        if (jsonResponse == null || jsonResponse.getMarks() == null){
            System.out.println("Error");
        }
        for(MarkDTO mark:jsonResponse.getMarks()){
            System.out.println(mark.toString());
        }
    }
    private void makeGetRequestForMarks(String url, Map<String, String> uriVariables){
        final RestTemplate restTemplate = new RestTemplate();
        MarksResponse jsonResponse;
        if(!uriVariables.isEmpty()) {
            jsonResponse = restTemplate.getForObject(url, MarksResponse.class, uriVariables);
        }else{
            jsonResponse = restTemplate.getForObject(url, MarksResponse.class);
        }
        printMarks(jsonResponse);
    }
}
