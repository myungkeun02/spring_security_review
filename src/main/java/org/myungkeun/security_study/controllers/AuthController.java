package org.myungkeun.security_study.controllers;

import lombok.RequiredArgsConstructor;
import org.myungkeun.security_study.entities.Member;
import org.myungkeun.security_study.payload.RegisterRequest;
import org.myungkeun.security_study.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/singup")
    public ResponseEntity<Member> registerUser(
            @RequestBody RegisterRequest registerRequest
    ) {
        return new ResponseEntity<>(authService.registerUser(registerRequest), HttpStatus.CREATED);
    }
}
