package helper;

public class User {
    private int uid;
    private String username;
    private String password;
    private String emailAddress;
    private String firstName;
    private String lastName;

    public User(int uid, String username, String password, String emailAddress, String firstName, String lastName) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getUid() {
        return this.uid;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }
}