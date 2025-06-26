package homework.spring_app.controller;

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
        return ResponseEntity.ok(news);
    }

    @GetMapping
    public ResponseEntity<List<NewsDto>> getAllNews() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<NewsDto> addNews(@RequestBody NewsDto news) {
        service.add(news);
        return ResponseEntity.status(HttpStatus.CREATED).body(news);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNews(@PathVariable Long id, @RequestBody NewsDto newsDto) {
        NewsDto news = service.getById(id);
        service.update(id, newsDto);
        return ResponseEntity.ok(news);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
