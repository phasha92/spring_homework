package homework.spring_app.controller;

import homework.spring_app.dto.FailResponse;
import homework.spring_app.dto.NewsDto;
import homework.spring_app.service.ServiceApp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final ServiceApp<NewsDto> service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getNews(@PathVariable Long id) {
        NewsDto news = service.getById(id);
        if (news == null) {
            return notFound(id);
        }
        return ResponseEntity.ok(news);
    }

    @GetMapping
    public ResponseEntity<List<NewsDto>> getAllNews() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<NewsDto> addNews(@RequestBody NewsDto news) {
        service.add(news);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(news);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNews(@PathVariable Long id, @RequestBody NewsDto news) {
        NewsDto oldNews = service.getById(id);
        if (oldNews == null) {
            return notFound(id);
        }
        service.update(id, news);
        return ResponseEntity.ok(news);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable Long id) {
        if (service.getById(id) == null) {
            return notFound(id);
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private static ResponseEntity<FailResponse> notFound(Long id) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new FailResponse("Новость с id " + id + " не найдена"));
    }
}
