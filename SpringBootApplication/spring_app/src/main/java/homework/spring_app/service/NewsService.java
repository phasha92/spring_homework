package homework.spring_app.service;

import homework.spring_app.dto.NewsDto;
import homework.spring_app.exception.EntityNotFoundEntityByID;
import homework.spring_app.mapper.NewsMapper;
import homework.spring_app.model.Category;
import homework.spring_app.model.News;
import homework.spring_app.repository.CategoryJPA;
import homework.spring_app.repository.NewsJPA;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsService implements ServiceApp<NewsDto> {

    private final NewsJPA newsJPA;
    private final CategoryJPA categoryJPA;
    private final NewsMapper mapper;


    @Override
    @Transactional
    public NewsDto getById(Long id) {
        Optional<News> news = newsJPA.findById(id);
        return news.map(mapper::toDto).orElseThrow(() -> new EntityNotFoundEntityByID(id, News.class));
    }

    @Override
    public List<NewsDto> getAll() {
        return newsJPA.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    @Transactional
    public void add(NewsDto newsDto) {
        News news = mapper.toEntity(newsDto);
        Optional<Category> category = categoryJPA.getByTitle(newsDto.getCategory());
        category.ifPresent(news::setCategory);
        newsJPA.save(news);
    }

    @Override
    @Transactional
    public void update(Long id, NewsDto newsDto) {
        Optional<News> news = newsJPA.findById(id);
        if (news.isPresent()) {
            News updateNews = news.get();
            if (newsDto.getTitle() != null) updateNews.setTitle(newsDto.getTitle());
            if (newsDto.getText() != null) updateNews.setText(newsDto.getText());
            if (newsDto.getDate() != null) updateNews.setDate(newsDto.getDate());
            Optional<Category> category = categoryJPA.getByTitle(newsDto.getCategory());
            category.ifPresent(updateNews::setCategory);
            newsJPA.save(updateNews);
        } else throw new EntityNotFoundEntityByID(id, News.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!newsJPA.existsById(id)) throw new EntityNotFoundEntityByID(id, News.class);
        newsJPA.deleteById(id);
    }
}
