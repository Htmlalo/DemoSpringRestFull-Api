package demo_learned.demo_learned.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class UserCreateRequest {
    @Size(min = 8, max = 20, message = "username between 8 to 20 keyword")
    String username;
    @Size(min = 8, max = 16, message = "password between 8 to 16 keyword")
    String password;

    String firstName;

    String lastName;

    LocalDate dob;
}
