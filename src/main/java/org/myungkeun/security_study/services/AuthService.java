package org.myungkeun.security_study.services;

import org.myungkeun.security_study.entities.Member;
import org.myungkeun.security_study.payload.RegisterRequest;

public interface AuthService {
    Member registerUser(RegisterRequest registerRequest);
}
