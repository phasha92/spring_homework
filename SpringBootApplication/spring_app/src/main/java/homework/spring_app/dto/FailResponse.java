package homework.spring_app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FailResponse {
    private String message;

    public FailResponse(String error) {
        this.message = error;
    }
}
