package demo_learned.demo_learned.controller;

import demo_learned.demo_learned.dto.request.ApiAuthentication;
import demo_learned.demo_learned.dto.request.ApiResponse;
import demo_learned.demo_learned.dto.request.UserLoginRequest;
import demo_learned.demo_learned.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationUser {
    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<ApiResponse<ApiAuthentication>> login(@RequestBody UserLoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("logout")
    public ResponseEntity<ApiResponse<String>> logout(HttpServletRequest request) {
        return userService.logout(request);
    }

    @PostMapping("refreshToken")
    public ResponseEntity<ApiResponse<ApiAuthentication>> refreshToken(HttpServletRequest request) {
        return userService.refreshToken(request);
    }


    @GetMapping("testAutication")
    public String testAutication() {
        return userService.test();
    }

}
