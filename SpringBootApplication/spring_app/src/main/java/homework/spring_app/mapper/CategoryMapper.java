package homework.spring_app.mapper;

import homework.spring_app.dto.CategoryDto;
import homework.spring_app.model.Category;
import homework.spring_app.model.News;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {

        private final NewsMapper newsMapper = new NewsMapper();

        public CategoryDto toDto(Category category) {
            return CategoryDto.builder()
                    .id(category.getId())
                    .title(category.getTitle())
                    .news(category.getNews().stream()
                            .map(newsMapper::toDto)
                            .toList())
                    .build();
        }

        public Category toEntity(CategoryDto dto) {
            Category category = Category.builder()
                    .id(dto.getId())
                    .title(dto.getTitle())
                    .build();

            if (dto.getNews() != null) {
                List<News> newsList = dto.getNews().stream()
                        .map(newsMapper::toEntity)
                        .peek(news -> news.setCategory(category))
                        .toList();

                category.setNews(newsList);
            }

            return category;
        }

}
