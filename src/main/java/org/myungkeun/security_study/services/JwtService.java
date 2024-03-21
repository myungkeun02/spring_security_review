package org.myungkeun.security_study.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    // token 에서 Email 을 추출한다.
    String extractEmail(String token);

    // token 에서 사용자 정의 claim 을 추출한다.
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    // userDetails 의 사용자 정보를 이용해 token 을 생성한다. (빈 맵을 사용하여 추가 클레임을 전달한다.)
    String generateToken(UserDetails userDetails);

    // 사용자 정보와 추가 클레임을 기반으로 token 을 생헝한다. (setClaims 매서드를 통해 추가 클레임을 설정한다.)
    String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    );

    // 주어진 token 이 유효한지 확인한다. (email이 toekn에서 추출한 email과 일치하며 토큰이 만료되지 않았는지 확인한다.)
    boolean isTokenValid(String token, UserDetails userDetails);

    // 주어진 token 이 만료되었는지 확인합니다.
    boolean isTokenExpired(String token);

    // 주어진 token 의 만료 일자를 추출합니다.
    Date extractExpiration(String token);

    // 주어진 token 에서 모든 클레임을 추출합니다.
    Claims extractAllClaims(String token);

    // secretKey를 디코딩하여 key 객체를 반환합니다. 이 키는 jwt의 서명 및 검증에서 사용합니다.
    Key getSignInKey();
}


// 여기서 클레임이란 JWT에 포함될 정보의 Key - value 입니다. 클레임을 사용하여 토큰에 관련된 유용한 정보를 포함시킵니다. 클레임은 일반적으로 토큰의 발급자 만료일자 제목 등을 포함한다.