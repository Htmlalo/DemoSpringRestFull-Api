package demo_learned.demo_learned.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    int code;
    String message;
    final LocalDateTime timestamp = LocalDateTime.now();

    public ApiError(int code) {
        this.code = code;
    }

    public ApiError(String message) {
        this.message = message;
    }

    public ApiError(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
