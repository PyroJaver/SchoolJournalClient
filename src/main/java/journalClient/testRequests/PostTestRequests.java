package journalClient.testRequests;

import journalClient.dto.StudentDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class PostTestRequests {
    public void registerStudents(String[] name, String[] surname){
        final String url = "http://localhost:8080/students/registerStudent";
        Map<String, Object> jsonData = new HashMap<>();
        for(int i = 0; i < 3; i++) {
            jsonData.put("name", name[i]);
            jsonData.put("surname", surname[i]);
            jsonData.put("diaryNumber", String.valueOf(i));
            makePostRequestWithJSON(url, jsonData);
        }
    }
    private void makePostRequestWithJSON(String url, Map<String, Object> jsonData){
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
    public void addMarks(String[] subjects, StudentDTO[] studentDTOArray) {
        final String url = "http://localhost:8080/marks/add";
        Map<String, Object> jsonData = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            jsonData.put("subject", subjects[(int) (Math.random() * 3)]);
            jsonData.put("mark", (int) (Math.random() * 4) + 2);
            jsonData.put("student", studentDTOArray[(int) (Math.random() * 3)]);
            makePostRequestWithJSON(url, jsonData);
        }
    }
}
