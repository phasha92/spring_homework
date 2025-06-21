package homework.spring_app.mapper;

import homework.spring_app.dto.NewsDto;
import homework.spring_app.model.News;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    public NewsDto toDto(News news) {
        return NewsDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .text(news.getText())
                .date(news.getDate())
                .category(news.getCategory() != null ? news.getCategory().getTitle() : null)
                .build();
    }

    public News toEntity(NewsDto dto) {
        return News.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .text(dto.getText())
                .date(dto.getDate())
                .build();
    }
}