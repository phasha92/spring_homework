package homework.spring_app.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Builder
@Setter
@Getter
public class News {
    private Long id;
    private String title;
    private String text;
    private Instant date;
}
