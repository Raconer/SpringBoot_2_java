package com.spring.java.config.security;

import com.spring.java.service.AuthService;
import com.spring.java.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
  private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class.getName());
  private static final List<String> EXCLUDE_URL =
      List.of("/sign", "/sign/up", "/sign/refreshToken");
  private final AuthService authService;
  private final JwtUtil jwtUtil;
  private final int subLen = "Bearer ".length();

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    logger.info("Check doFilterInternal");
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null && !bearerToken.isEmpty() && bearerToken.startsWith("Bearer ")) {

      String token = bearerToken.substring(subLen);

      Claims claims = jwtUtil.getData(token);
      String email = (String) claims.get("email");

      UserDetails userDetails = this.authService.loadUserByUsername(email);

      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    boolean result =
        EXCLUDE_URL.stream()
            .findFirst()
            .filter(it -> it.equals(request.getRequestURI()))
            .isPresent();
    logger.info("Show Not Filter : {}", result);
    return result;
  }
}
