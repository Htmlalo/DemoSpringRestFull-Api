package demo_learned.demo_learned.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorMessage {
    USER_EXIST(400, "UserName Existed"),
    SERVER_ERROR(999, "Sever_Errol, Sorry"),
    USER_NOTFOUND(404, "User Not Found"),
    INVALID_PASSWORD(405, "Invalid Password"),
    INVALID_REFRESH_TOKEN(406, "Invalid Refresh Token"),
    ;
    int code;
    String message;

    ErrorMessage(int code, String message) {
        this.message = message;
        this.code = code;
    }

}
