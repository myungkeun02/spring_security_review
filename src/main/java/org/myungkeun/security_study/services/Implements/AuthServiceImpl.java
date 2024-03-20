package org.myungkeun.security_study.services.Implements;

import lombok.RequiredArgsConstructor;
import org.myungkeun.security_study.entities.Role;
import org.myungkeun.security_study.entities.Member;
import org.myungkeun.security_study.payload.RegisterRequest;
import org.myungkeun.security_study.repositories.MemberRepository;
import org.myungkeun.security_study.services.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
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
}
