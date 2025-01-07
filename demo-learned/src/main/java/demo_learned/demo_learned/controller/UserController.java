package demo_learned.demo_learned.controller;

import demo_learned.demo_learned.dto.request.ApiResponse;
import demo_learned.demo_learned.dto.request.UserCreateRequest;
import demo_learned.demo_learned.dto.request.UserLoginRequest;
import demo_learned.demo_learned.entity.User;
import demo_learned.demo_learned.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String welcome() {
        return "<html><body>"
                + "<h1>WELCOME</h1>"
                + "</body></html>";
    }

    @GetMapping("/getUser")
    public List<User> findAll() {
        return userService.getUser();
    }

    @PostMapping("/createUser")
    public ResponseEntity<ApiResponse<User>> createUser(@Valid @RequestBody UserCreateRequest request) {
        return userService.createUser(request);
    }






}
