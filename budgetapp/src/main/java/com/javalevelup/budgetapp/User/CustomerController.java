package com.javalevelup.budgetapp.User;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Date;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController @RequestMapping(path = "api/v1/users") @RequiredArgsConstructor @Slf4j
public class CustomerController {

    private final CustomerService userService;

    @GetMapping(path = "get_user/{username}")
    public Customer getUser(@PathVariable("username") String username, Principal principal) {
        log.info(principal.getName());
        return userService.getUser(username);
    }

    @GetMapping(path = "auth/login-error")
    public void login(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
            }
        }
    }

    @PostMapping(path = "auth/signup")
    public void addUser(@RequestBody Customer user) {
        userService.addUser(user);
    }

    @DeleteMapping(path = "delete_user/{username}")
    public void deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
    }

    @PutMapping(path = "update_user/{username}")
    public void updateUser(@PathVariable("username") String username,
                           @RequestBody(required = false) Customer user) {
        userService.updateUser(username, user);
    }

        @GetMapping(path = "update_token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("my-secret".getBytes(StandardCharsets.UTF_8));
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                Customer user = userService.getUser(username);
                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURI().toString())
                        .sign(algorithm);
                response.setHeader("access_token", accessToken);
            } catch (Exception e) {
                log.error("Error logging in: {}", e.getMessage());
                response.setHeader("error", e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
