package loghmeh_server.security;


public class SecurityConstants {
    public static final String SECRET = "loghme";
    public static final long EXPIRATION_TIME = 86_400_000; // 1 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/sign_up";
    public static final String SIGN_IN_URL = "/sign_in";
}