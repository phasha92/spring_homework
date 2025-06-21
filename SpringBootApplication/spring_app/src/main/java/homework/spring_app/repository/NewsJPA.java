package homework.spring_app.repository;

import homework.spring_app.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsJPA extends JpaRepository<News, Long> {
}
