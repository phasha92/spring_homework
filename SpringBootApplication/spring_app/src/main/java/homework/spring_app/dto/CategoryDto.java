package homework.spring_app.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CategoryDto {
    private Long id;
    private String title;
    private List<NewsDto> news;
}
