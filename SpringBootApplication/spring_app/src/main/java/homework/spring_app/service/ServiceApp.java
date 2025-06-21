package homework.spring_app.service;

import java.util.List;

public interface ServiceApp<T> {
    T getById(Long id);

    List<T> getAll();

    void add(T t);

    void update(Long id, T t);

    void delete(Long id);
}
