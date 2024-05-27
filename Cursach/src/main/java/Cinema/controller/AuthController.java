package Cinema.controller;

import Cinema.dto.JwtAuthenticationResponse;
import Cinema.dto.SignInRequest;
import Cinema.dto.SignUpRequest;
import Cinema.service.UserService;
import Cinema.service.impl.UserServiceImpl;
import Cinema.service.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@ControllerAdvice
public class AuthController {

    @Autowired
    private UserService userService;

    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }

    @GetMapping("/admin")
    public void getAdmin() {
        userService.getAdmin();
    }
}
