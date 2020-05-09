package loghmeh_server.service;

        import java.io.IOException;
        import java.security.GeneralSecurityException;
        import java.util.Collections;

        import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
        import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
        import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
        import com.google.api.client.http.HttpTransport;
        import com.google.api.client.http.javanet.NetHttpTransport;
        import com.google.api.client.json.JsonFactory;
        import com.google.api.client.json.jackson2.JacksonFactory;

public class GoogleValidator {
    private String CLIENT_ID;
    private String idTokenString;

    private static GoogleValidator googleValidator = null;

    private GoogleValidator() {
        this.CLIENT_ID = "964150934775-gp7lee33askr8laivsf0prbhgpb5lddu.apps.googleusercontent.com";
    }

    public static GoogleValidator getInstance() {
        if (googleValidator == null) {
            googleValidator = new GoogleValidator();
        }
        return googleValidator;
    }


    public String verify() {
        JsonFactory jsonFactory = new JacksonFactory();
        HttpTransport httpTransport = new NetHttpTransport();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();


// (Receive idTokenString by HTTPS POST)
        GoogleIdToken idToken;
        try {
            idToken = verifier.verify(this.idTokenString);
        } catch (GeneralSecurityException ex) {
            System.out.println("General exception");
            System.out.println(ex);
            return "error";

        } catch (IOException ex) {
            System.out.println("IO exception");
            System.out.println(ex);
            return "error";

        }
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            if (emailVerified) {
                return email;
            }
            return "email not verified";

        } else {
            System.out.println("Invalid ID token.");
            return "invalid ID";
        }
    }

    public void setIdTokenString(String idTokenString) {
        this.idTokenString = idTokenString;
    }

}

