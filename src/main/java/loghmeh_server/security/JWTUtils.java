package loghmeh_server.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import loghmeh_server.repository.customer.Customer;
import loghmeh_server.repository.customer.CustomerMapper;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;


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

//        try {


//        String base64Secret = Base64.getEncoder().encodeToString(SECRET.getBytes());
//        byte[] secretBytes = DatatypeConverter.parseBase64Binary(base64Secret);
//        Key key = new SecretKeySpec(secretBytes, SignatureAlgorithm.HS256.getJcaName());


        System.out.println("key: "+SECRET_KEY);

        String token = Jwts.builder()
                    .setHeaderParam("typ", "JWT")
//                .setSubject(customer.getFirstName() + " " + customer.getLastName())
                    .setIssuedAt(new Date(System.currentTimeMillis()))//iat
                    .setIssuer(SecurityConstants.TOKEN_ISSUER)//iss
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))//exp
                    .claim("userId", customer.getCustomerId())
                    .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                    .compact();
            System.out.println("jwt token: " + token);
            return token;
//
//        }
//        catch(UnsupportedEncodingException uee){
//            System.out.println(" UnsupportedEncodingException in generating key");
//            return null;
//        }
    }

    public int verifyJWTToken(String token) {
        System.out.println("token in vertify:" + token);
        String jwtToken = token.split(" ")[1];

        try {
            System.out.println("key: "+SECRET_KEY);
            Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(jwtToken);
            System.out.println((int)jws.getBody().get("userId"));
            return (int)jws.getBody().get("userId");

        }catch (JwtException ex) {
            System.out.println("JWT Exception in Structure");
            System.out.println(ex);
            return -1;
        }
//        catch(UnsupportedEncodingException uee){
//            System.out.println(" UnsupportedEncodingException in generating key");
//            return -1;
//        }
    }

}
