package homework.spring_app.exception;

import homework.spring_app.dto.FailResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotFoundExceptionHandler {

    @ExceptionHandler(EntityNotFoundEntityByID.class)
    public ResponseEntity<FailResponse> notFoundHandler(EntityNotFoundEntityByID ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new FailResponse(ex.getMessage()));
    }
}
