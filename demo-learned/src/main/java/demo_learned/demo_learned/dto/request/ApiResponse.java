package demo_learned.demo_learned.dto.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    ApiError status;
    T data;


    public static <T> ApiResponse<T> ok(T data) {
        final ApiError status = new ApiError(HttpStatus.OK.value());
        return ApiResponse.<T>builder()
                .status(status)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> fail() {
        final ApiError status = new ApiError(HttpStatus.BAD_REQUEST.value());
        return ApiResponse.<T>builder()
                .status(status)
                .build();
    }

    public static <T> ApiResponse<T> fail(int code, String message) {
        final ApiError status = new ApiError(code, message);
        return ApiResponse.<T>builder()
                .status(status)
                .build();
    }

}
