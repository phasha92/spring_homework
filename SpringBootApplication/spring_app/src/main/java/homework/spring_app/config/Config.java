package homework.spring_app.config;

import homework.spring_app.model.News;
import homework.spring_app.util.NewsGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class Config {

    @Bean
    public Map<Long, News> newsMap() {
        Map<Long, News> map = new ConcurrentHashMap<>();
        for (long i = 0; i < 10; i++) {
            News news = NewsGenerator.generateNews();
            map.put(news.getId(), news);
        }
        return map;
    }

}
