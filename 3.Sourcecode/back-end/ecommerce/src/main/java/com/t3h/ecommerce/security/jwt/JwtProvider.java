package com.t3h.ecommerce.security.jwt;

import com.t3h.ecommerce.security.userprincal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret ="laingocson007.vn";
    private int jwtExpriration = 86400; // xet thoi gian song 1 ngay = 86400 giay

    // tao 1 chuoi token
    public String createToken(Authentication authentication){
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpriration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            logger.error("Invalid Jwt signture -> message: {}", e);
        }
        catch (ExpiredJwtException e){
            logger.error("expiredJwt -> message: {}", e);
        }
        catch (UnsupportedJwtException e){
            logger.error("unsupport Jwt  -> message: {}", e);
        }
        catch (MalformedJwtException e){
            logger.error("Malformed Jwt  -> message: {}", e);

        } catch (IllegalArgumentException e){
            logger.error("IllegalArgument Jwt  -> message: {}", e);
        }
        return false;
    }

    public String getUserNameFromToken(String token){
        String userName = Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody()
                .getSubject();
        return userName;
    }


}
