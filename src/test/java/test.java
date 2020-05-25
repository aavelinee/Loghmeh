import loghmeh_server.domain.Loghmeh;
import loghmeh_server.repository.customer.Customer;
import loghmeh_server.security.JWTUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

public class test {

    String token;
    Customer customer = new Customer(1, "احسان", "خامس‌پناه", "۰۹۱۲۳۴۵۶۷۸۹", "ekhamespanah@yahoo.com", 0f, 0f, "pass");

    @Before
    public void setUp() throws SQLException {
        token = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODg5NDE5MjYsImlzcyI6InNlY3VyZS1hcGkiLCJleHAiOjE1ODkwMjgzMjYsInVzZXJJZCI6MzV9.dbGZviQcZPgrZ1rLabrn6bDIouCLuTsjNAhPW5_9kxU";

    }

    @Test
    public void whenLoaded_find() throws SQLException {
        Object body = JWTUtils.getInstance().verifyJWTToken(token);
        System.out.println(body);
    }

}
