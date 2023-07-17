package edu.uady.authservice.security;

import edu.uady.authservice.dto.RequestDto;
import edu.uady.authservice.entity.UserAuth;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@Log4j2
public class JwtProvider {
    @Value("${security.keysecret}")
    private String secret ;

    @Autowired
    private RouteValidator routeValidator;

    @PostConstruct
    protected void init() {
        log.info(secret);
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String creteToken(UserAuth userAuth) {
        Map<String, Object> claims = new HashMap<>();
        claims = Jwts.claims().setSubject(userAuth.getUserName());
        claims.put("id", userAuth.getId());
        claims.put("role", userAuth.getRoles());
        Date now = new Date();
        Date exp = new Date(now.getTime() + 3600000);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validete(String token, RequestDto requestDto) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        } catch (Exception ex) {
            return false;
        }
//        if( !isAdmin(token) || !routeValidator.isAdminPath(requestDto)) {
//            return false;
//        }
        return true;
    }

    public String getUserNameFromToken(String token){
        try{
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                    .getBody().getSubject();
        }catch (Exception ex){
            return "token invalido";
        }
    }

    private boolean isAdmin(String token){
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody().get("role").equals("admin");
    }


}
