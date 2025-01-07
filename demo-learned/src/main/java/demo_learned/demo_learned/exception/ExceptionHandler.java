package demo_learned.demo_learned.exception;

import demo_learned.demo_learned.dto.request.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse> handleAppException(AppException ex) {
        return ResponseEntity.badRequest().body(ApiResponse.fail(ex.getErrorMessage().getCode(), ex.getErrorMessage().getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleRuntimeException() {
        return ResponseEntity.badRequest().body(ApiResponse.fail(ErrorMessage.SERVER_ERROR.getCode(), ErrorMessage.SERVER_ERROR.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleModelException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), ex.getFieldError().getDefaultMessage()));
    }
}
