package journalClient.dto;

import java.util.List;

public class MarksResponse {
    List<MarkDTO> marks;

    public List<MarkDTO> getMarks() {
        return marks;
    }

    public void setMarks(List<MarkDTO> marks) {
        this.marks = marks;
    }
}
