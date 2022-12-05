package finalproject.entity;

public class Account {
    public static int ACCOUNT_ID = 0;

    private final int id;
    private String email;
    private String username;
    private String password;


    public Account() {
        this.id = ACCOUNT_ID;
        ACCOUNT_ID++;
    }

    public Account(String email, String username, String password) {
        this.id = ACCOUNT_ID++;
        this.email = email;
        this.username = username;
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
