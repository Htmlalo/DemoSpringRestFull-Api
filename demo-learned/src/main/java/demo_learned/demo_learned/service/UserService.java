package demo_learned.demo_learned.service;

import demo_learned.demo_learned.dto.request.ApiAuthentication;
import demo_learned.demo_learned.dto.request.ApiResponse;
import demo_learned.demo_learned.dto.request.UserCreateRequest;
import demo_learned.demo_learned.dto.request.UserLoginRequest;
import demo_learned.demo_learned.entity.Role;
import demo_learned.demo_learned.entity.User;
import demo_learned.demo_learned.exception.AppException;
import demo_learned.demo_learned.exception.ErrorMessage;
import demo_learned.demo_learned.repository.UserRepository;
import demo_learned.demo_learned.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {


    UserRepository userRepository;
    ModelMapper mapper;
    PasswordEncoder passwordEncoder;
    JwtUtil jwtUtil;

    public List<User> getUser() {
        return userRepository.findAll();
    }


    public ResponseEntity<ApiResponse<User>> createUser(UserCreateRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) throw new AppException(ErrorMessage.USER_EXIST);
        User user = mapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);
        return ResponseEntity.ok(ApiResponse.ok(userRepository.save(user)));
    }

    public ResponseEntity<ApiResponse<ApiAuthentication>> login(UserLoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new AppException(ErrorMessage.USER_NOTFOUND));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new AppException(ErrorMessage.INVALID_PASSWORD);
        String token = jwtUtil.generateToken(user.getUsername(), user.getRoles().stream().findFirst().orElse(Role.USER.name()));
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername(), user.getRoles().stream().findFirst().orElse(Role.USER.name()));

        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(TimeUnit.DAYS.toMillis(7))
                .build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(ApiResponse.ok(ApiAuthentication.builder().token(token).build()));
    }

    public ResponseEntity<ApiResponse<String>> logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();

        ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(ApiResponse.ok("Logout success"));
    }

    public ResponseEntity<ApiResponse<ApiAuthentication>> refreshToken(HttpServletRequest request) {
        String refreshToken = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("refreshToken")) {
                    refreshToken = cookie.getValue();
                }
            }
        }
        if (refreshToken == null || !jwtUtil.validateToken(refreshToken))
            throw new AppException(ErrorMessage.INVALID_REFRESH_TOKEN);

        String username = jwtUtil.extractUsername(refreshToken);
        String newToken = jwtUtil.generateToken(username, jwtUtil.extractRole(refreshToken));
        return ResponseEntity.ok(ApiResponse.ok(ApiAuthentication.builder().token(newToken).build()));
    }

    public String test() {
        String token = jwtUtil.generateToken("vanthuat2005", Role.USER.name());
        return token;
    }
}
