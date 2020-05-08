package loghmeh_server.security;


import io.jsonwebtoken.*;
import loghmeh_server.repository.customer.Customer;
import loghmeh_server.repository.customer.CustomerMapper;

import java.util.Date;

import static loghmeh_server.security.SecurityConstants.*;

public class JWTUtils {
    private static JWTUtils jwtUtils = null;

    public static JWTUtils getInstance() {
        if(jwtUtils == null){
            jwtUtils = new JWTUtils();
        }
        return jwtUtils;
    }

    public String generateJWTToken(Customer customer) {

        String token = Jwts.builder()
                .setSubject(customer.getFirstName() + " " + customer.getLastName())
                .setIssuedAt(new Date(System.currentTimeMillis()))//iat
                .setIssuer(SecurityConstants.TOKEN_ISSUER)//iss
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))//exp
                .claim("userId", customer.getCustomerId())
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        System.out.println("jwt token: " + token);
        return token;
    }

    public int verifyJWTToken(String token) {
        try {
            Jws<Claims> jws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            System.out.println((int)jws.getBody().get("userId"));
            return (int)jws.getBody().get("userId");

        }catch (JwtException ex) {
            System.out.println("JWT Exception in Structure");
            return -1;
        }
    }

}
