package homework.spring_app.service;

import homework.spring_app.model.News;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NewsService {
    private final Map<Long, News> newsMap;
    private long idCounter;

    @PostConstruct
    public void init(){
        idCounter = newsMap.size();
    }

    public NewsService(Map<Long, News> newsMap){
        this.newsMap = new ConcurrentHashMap<>(newsMap);
    }

    public News getNews(Long id){
        return newsMap.get(id);
    }

    public Map<Long, News> getAllNews(){
        return Map.copyOf(newsMap);
    }

    public void addNews(News news){
        news.setId(++idCounter);
        newsMap.put(idCounter, news);
    }

    public void removeNews(Long id){
        newsMap.remove(id);
    }

    public void updateNews(Long id, News news){
        News oldNews = newsMap.get(id);
        oldNews.setText(news.getText());
        oldNews.setTitle(news.getTitle());
        oldNews.setDate(news.getDate());
    }

}
