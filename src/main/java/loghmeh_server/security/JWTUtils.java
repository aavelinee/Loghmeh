package loghmeh_server.security;


import io.jsonwebtoken.*;
import loghmeh_server.repository.customer.Customer;
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
        System.out.println("key: "+SECRET_KEY);

        String token = Jwts.builder()
                    .setHeaderParam("typ", "JWT")
                    .setSubject(customer.getFirstName() + " " + customer.getLastName())
                    .setIssuedAt(new Date(System.currentTimeMillis()))//iat
                    .setIssuer(SecurityConstants.TOKEN_ISSUER)//iss
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))//exp
                    .claim("userId", customer.getCustomerId())
                    .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                    .compact();
            System.out.println("jwt token: " + token);
            return token;
    }

    public int verifyJWTToken(String token) {
        System.out.println("token in vertify:" + token);
        String jwtToken = token.split(" ")[1];

        try {
            System.out.println("key: "+SECRET_KEY);
            Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(jwtToken);
            Double id = (Double)(jws.getBody().get("userId"));
            System.out.println("id: " + id.intValue());
            return id.intValue();

        }catch (JwtException ex) {
            System.out.println("JWT Exception in Structure");
            System.out.println(ex);
            return -1;
        }
    }

}
