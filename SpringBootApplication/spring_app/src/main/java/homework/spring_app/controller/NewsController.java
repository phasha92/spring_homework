package homework.spring_app.controller;

import homework.spring_app.dto.FailResponse;
import homework.spring_app.model.News;
import homework.spring_app.service.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService service;

    public NewsController(NewsService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNews(@PathVariable Long id) {
        News news = service.getNews(id);
        if (news == null) {
            return notFound(id);
        }
        return ResponseEntity.ok(news);
    }

    @GetMapping
    public ResponseEntity<Map<Long, News>> getAllNews() {
        return ResponseEntity.ok(service.getAllNews());
    }

    @PostMapping
    public ResponseEntity<News> addNews(@RequestBody News news) {
        service.addNews(news);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(news);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNews(@PathVariable Long id, @RequestBody News news) {
        News oldNews = service.getNews(id);
        if (oldNews == null) {
            return notFound(id);
        }
        service.updateNews(id, news);
        return ResponseEntity.ok(news);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable Long id) {
        if (service.getNews(id) == null) {
            return notFound(id);
        }
        service.removeNews(id);
        return ResponseEntity.noContent().build();
    }

    private static ResponseEntity<FailResponse> notFound(Long id){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new FailResponse("Новость с id " + id + " не найдена"));
    }
}
