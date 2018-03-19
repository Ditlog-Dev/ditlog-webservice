package id.ac.itb.logistik.ditlog.service;

import id.ac.itb.logistik.ditlog.model.RoleConstant;
import id.ac.itb.logistik.ditlog.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

public class TokenAuthenticationService {
    public static final long EXPIRATIONTIME = 864_000_000; // 10 days
    public static final String SECRET = "ThisIsASecret";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static Map<Long,String> ROLE_CONSTANT = RoleConstant.ROLE;

    public static String addAuthenticateUser(HttpServletResponse res, User user) {
        String JWT = getJWT(user);
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
        return JWT;
    }

    public static String getJWT(User user){
        String JWT = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roleId",user.getIdEmployee())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return JWT;
    }

    public static User getAuthenticateUser(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody();
                String username = claims.getSubject();
                Integer roleId = (Integer) claims.get("roleId");
                User user = new User();
                user.setUsername(username);
                user.setIdEmployee(Long.valueOf(roleId));
                return user;
            } catch (Exception e){
                throw new MalformedJwtException("Invalid jwt token");
            }
        }
        return null;
    }
}