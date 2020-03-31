package loghmeh_server.service;

public class CustomerInfo {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private int credit;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
