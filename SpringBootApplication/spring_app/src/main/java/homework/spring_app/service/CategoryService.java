package homework.spring_app.service;

import homework.spring_app.dto.CategoryDto;
import homework.spring_app.exception.EntityNotFoundEntityByID;
import homework.spring_app.mapper.CategoryMapper;
import homework.spring_app.model.Category;
import homework.spring_app.repository.CategoryJPA;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ServiceApp<CategoryDto> {

    private final CategoryJPA categoryJPA;
    private final CategoryMapper mapper;

    @Override
    @Transactional
    public CategoryDto getById(Long id) {
        Optional<Category> category = categoryJPA.findById(id);
        return category.map(mapper::toDto).orElseThrow(() -> new EntityNotFoundEntityByID(id, Category.class));
    }

    @Override
    @Transactional
    public List<CategoryDto> getAll() {
        return categoryJPA.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    @Transactional
    public void add(CategoryDto categoryDto) {
        categoryJPA.save(mapper.toEntity(categoryDto));
    }

    @Override
    @Transactional
    public void update(Long id, CategoryDto categoryDto) {
        Optional<Category> category = categoryJPA.findById(id);
        if (category.isPresent()) {
            Category updateCategory = category.get();
            if (categoryDto.getTitle() != null) updateCategory.setTitle(categoryDto.getTitle());
            categoryJPA.save(updateCategory);
        } else throw new EntityNotFoundEntityByID(id, Category.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!categoryJPA.existsById(id)) throw new EntityNotFoundEntityByID(id, Category.class);
        categoryJPA.deleteById(id);
    }
}
