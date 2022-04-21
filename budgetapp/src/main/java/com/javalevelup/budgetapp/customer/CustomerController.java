package com.javalevelup.budgetapp.customer;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController @RequestMapping(path = "api/v1/users") @RequiredArgsConstructor @Slf4j
public class CustomerController {

    private final CustomerService userService;

    @GetMapping(path = "get-user/{username}")
    public Customer getUser(@PathVariable("username") String username, Principal principal) {
        if (username.equals(principal.getName())) {
            return userService.getUser(username);
        }
        throw new IllegalStateException(
                "User not authorized to access information");
    }

    @PostMapping(path = "auth/signup")
    public void addUser(@RequestBody Customer user) {
        userService.addUser(user);
    }

    @DeleteMapping(path = "delete-user/{username}")
    public void deleteUser(@PathVariable("username") String username, Principal principal) {
        if (username.equals(principal.getName())) {
            userService.deleteUser(username);
        } else throw new IllegalStateException(
                "User not authorized to access information");
    }

    @PutMapping(path = "update-user/{username}")
    public void updateUser(@PathVariable("username") String username,
                           @RequestBody(required = false) Customer user,
                           Principal principal) {
        if (username.equals(principal.getName())) {
            userService.updateUser(username, user);
        } else throw new IllegalStateException(
                "User not authorized to access information");
    }

        @GetMapping(path = "update-token")
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
                        .withIssuer(request.getRequestURI())
                        .sign(algorithm);
                Map<String, String> access_token_map = new HashMap<>();
                access_token_map.put("access_token", accessToken);
                response.setStatus(200);
                response.setContentType(APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("utf-8");

                new ObjectMapper().writeValue(response.getOutputStream(), access_token_map);
            } catch (Exception e) {
                response.setStatus(400);
                response.setContentType(APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("utf-8");

                new ObjectMapper().writeValue(response.getOutputStream(), e.getMessage());
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
