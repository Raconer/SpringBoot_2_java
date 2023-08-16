package com.spring.java.utils;

import com.spring.java.common.exception.CustomAuthenticationException;
import io.jsonwebtoken.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
  @Value("${common.jwt.secret_key}")
  private String secretKey;

  @Value("${common.jwt.expire_time}")
  private long expired;

  public String create(String email) {
    Map<String, Object> header = new HashMap();
    Map<String, Object> payloads = new HashMap();
    header.put("type", "jwt");
    payloads.put("email", email);

    Date curDate = new Date();
    Date expiredDate = new Date(curDate.getTime() + expired);

    return Jwts.builder()
        .setHeader(header)
        .setClaims(payloads)
        .setIssuedAt(curDate)
        .setExpiration(expiredDate)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  public Claims getData(String token) {
    this.isValidate(token);
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
  }

  public Boolean isValidate(String token) {
    if (token == null) throw new CustomAuthenticationException("토큰이 Null 입니다.");

    try {
      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return true;
    } catch (AuthenticationException ex) {
      throw new CustomAuthenticationException("인증에 실패하였습니다", ex);
    } catch (SignatureException ex) {
      throw new CustomAuthenticationException("잘못된 JWT 서명입니다", ex);
    } catch (MalformedJwtException ex) {
      throw new CustomAuthenticationException("잘못된 JWT 토큰입니다", ex);
    } catch (ExpiredJwtException ex) {
      throw new CustomAuthenticationException("토큰 유효기간이 만료되었습니다", ex);
    } catch (UnsupportedJwtException ex) {
      throw new CustomAuthenticationException("지원되지 않는 JWT 토큰입니다", ex);
    } catch (IllegalArgumentException ex) {
      throw new CustomAuthenticationException("JWT claims 문자열이 비어있습니다", ex);
    }
  }
}
