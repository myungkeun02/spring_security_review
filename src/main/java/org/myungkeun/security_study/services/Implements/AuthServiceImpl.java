package org.myungkeun.security_study.services.Implements;

import lombok.RequiredArgsConstructor;
import org.myungkeun.security_study.entities.Role;
import org.myungkeun.security_study.entities.Member;
import org.myungkeun.security_study.payload.LoginRequest;
import org.myungkeun.security_study.payload.RegisterRequest;
import org.myungkeun.security_study.repositories.MemberRepository;
import org.myungkeun.security_study.services.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public Member registerUser(RegisterRequest registerRequest) {
        Member newMember =  Member.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        memberRepository.save(newMember);
        return newMember;
    }

    @Override
    public Member loginUser(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        Member member = memberRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow();
        return member;
    }
}
