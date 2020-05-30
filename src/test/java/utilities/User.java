package utilities;

import java.util.Objects;

public class User {
    public String id;
    public String username;
    public String realname;
    public String password;
    public String email;

    public User(String id, String username, String realname, String password, String email) {
        this.id = id;
        this.username = username;
        this.realname = realname;
        this.password = password;
        this.email = email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, realname, password, email);
    }
}