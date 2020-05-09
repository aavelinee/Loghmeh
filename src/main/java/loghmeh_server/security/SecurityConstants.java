package loghmeh_server.security;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class SecurityConstants {
    public static final String SECRET = "loghmehloghmehloghmehloghmehloghmehloghmeh";
    public static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public static final long EXPIRATION_TIME = 86_400_000; // 1 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/sign_up";
    public static final String SIGN_IN_URL = "/sign_in";
    public static final String GOOGLE_SIGN_IN = "/google_sign_in";
}