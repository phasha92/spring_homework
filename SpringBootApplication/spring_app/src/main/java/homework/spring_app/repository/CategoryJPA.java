package homework.spring_app.repository;

import homework.spring_app.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryJPA extends JpaRepository<Category, Long> {
    Optional<Category> getByTitle(String title);
}
