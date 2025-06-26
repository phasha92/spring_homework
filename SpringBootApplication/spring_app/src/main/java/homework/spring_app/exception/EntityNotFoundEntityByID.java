package homework.spring_app.exception;

public class EntityNotFoundEntityByID extends RuntimeException {

    public EntityNotFoundEntityByID(Long id, Class<?> entityClass) {
        super(String.format("Данные %s с id %d не найдены", entityClass.getSimpleName(), id));
    }
}
