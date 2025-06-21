package homework.spring_app.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class NewsDto {
    private Long id;
    private String title;
    private String text;
    private Instant date;
    private String category;
}
