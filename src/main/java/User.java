import java.util.Objects;

public class User {

    private String user;
    private String role;

    public User(String user, String role) {
        this.user = user;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getRole().equals(user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRole());
    }
}
