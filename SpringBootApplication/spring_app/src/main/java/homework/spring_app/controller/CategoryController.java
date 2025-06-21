package homework.spring_app.controller;

import homework.spring_app.dto.CategoryDto;
import homework.spring_app.dto.FailResponse;

import homework.spring_app.service.ServiceApp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ServiceApp<CategoryDto> service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {
        CategoryDto category = service.getById(id);
        return (category != null) ? ResponseEntity.ok(category) : notFound(id);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<CategoryDto> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        service.add(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        CategoryDto category = service.getById(id);
        if (category == null) {
            return notFound(id);
        }
        service.update(id, categoryDto);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        if (service.getById(id) == null) {
            return notFound(id);
        }
        return ResponseEntity.noContent().build();
    }

    private static ResponseEntity<FailResponse> notFound(Long id) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new FailResponse("Категория с id " + id + " не найдена"));
    }
}
